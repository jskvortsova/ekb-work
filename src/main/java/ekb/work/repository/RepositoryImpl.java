package ekb.work.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/28/16.
 */
@org.springframework.stereotype.Repository
public class RepositoryImpl<T> implements Repository<T> {
    MongoTemplate mongoTemplate;

    @Autowired
    public RepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<T> findAll(Class<T> typeParameterClass) {
        List<T> entities = mongoTemplate.findAll(typeParameterClass);
        return entities;
    }

    @Override
    public T save(T entity) {
        mongoTemplate.save(entity);
        return entity;
    }

    @Override
    public void saveAll(List<T> entities) throws Exception {
        mongoTemplate.insertAll(entities);

    }

    @Override
    public void clearAll(Class<T> typeParameterClass) {
        mongoTemplate.findAllAndRemove(new Query(), typeParameterClass);

    }

    @Override
    public List<T> findAll(Map<String, String> params, Class<T> entityClass) {

        Query query = new Query();
        for (Map.Entry<String, String> paramEntry : params.entrySet()) {
            String key = paramEntry.getKey();
            String value = paramEntry.getValue();
            query.addCriteria(Criteria.where(key).regex(value));
        }
        return mongoTemplate.find(query, entityClass);
    }
}
