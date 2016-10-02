//package ekb.work.repository;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.WriteResult;
//import ekb.work.entity.CategoryEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by juliaskvortsova on 9/21/16.
// */
//@Service
//public class CategoryRepositoryImpl implements Repository<CategoryEntity> {
//    private Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
//    MongoTemplate mongoTemplate;
//
//    @Autowired
//    public CategoryRepositoryImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public List<CategoryEntity> findAll() {
//
//        List<CategoryEntity> categoryEntities = mongoTemplate.findAll(CategoryEntity.class);
//        logger.info("Categories - Documents fetched {}", categoryEntities.size());
//        return categoryEntities;
//    }
//
//    @Override
//    public CategoryEntity save(CategoryEntity entity) throws Exception {
////        Query query = Query.query(Criteria.where("id").is(entity.getId()));
////
////        Update update = new Update()
////                .set("id", entity.getId())
////                .set("name", entity.getName());
////        WriteResult result = mongoTemplate.upsert(query, update, CategoryEntity.class);
////
////        if (result.getN() <= 0) {
////            throw new Exception("Documents affected: " + result.getN() + " Failed to save document");
////        }
////        return entity;
//        mongoTemplate.save(entity);
//        return entity;
//    }
//
//    @Override
//    public void saveAll(List<CategoryEntity> entities) {
//        mongoTemplate.save(entities);
//    }
//
//    @Override
//    public void clearAll() {
//        mongoTemplate.findAllAndRemove(new Query(), CategoryEntity.class);
//    }
//}
