package ekb.work.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public T save(T entity) throws Exception {
        mongoTemplate.save(entity);
        return entity;
    }

    @Override
    public void saveAll(List<T> entities) throws Exception {
        mongoTemplate.insertAll(entities);
//        mongoTemplate.save(entities);

    }

    @Override
    public void clearAll(Class<T> typeParameterClass) {
        mongoTemplate.findAllAndRemove(new Query(), typeParameterClass);

    }
}
