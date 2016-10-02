//package ekb.work.repository;
//
//import com.mongodb.WriteResult;
//import ekb.work.domain.EducationDto;
//import ekb.work.domain.ExperienceDto;
//import ekb.work.domain.LanguageLevelDto;
//import ekb.work.entity.CategoryEntity;
//import ekb.work.entity.ResumeEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//import java.util.List;
//
///**
// * Created by juliaskvortsova on 9/28/16.
// */
//public class ResumeRepositoryImpl implements Repository<ResumeEntity> {
//    private Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
//    MongoTemplate mongoTemplate;
//
//    @Autowired
//    public ResumeRepositoryImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public List<ResumeEntity> findAll() {
//
//        List<ResumeEntity> resumeEntities = mongoTemplate.findAll(ResumeEntity.class);
//        logger.info("Resumes - Documents fetched {}", resumeEntities.size());
//        return resumeEntities;
//    }
//
//    @Override
//    public ResumeEntity save(ResumeEntity entity) throws Exception {
////        Query query = Query.query(Criteria.where("id").is(entity.getId()));
////
////        Update update = new Update()
////                .set("id", entity.getId())
////                .set("name", entity.getName())
////                .set("dateOfBirth", entity.getDateOfBirth())
////                .set("personalInfo", entity.getPersonalInfo())
////                .set("location", entity.getLocation())
////                .set("jobTitle", entity.getJobTitle())
////                .set("salary", entity.getSalary())
////                .set("workTypes", entity.getWorkTypes())
////                .set("totalExperience", entity.getTotalExperience())
////                .set("jobAreaExperience", entity.getJobAreaExperience())
////                .set("experience", entity.getExperience())
////                .set("description", entity.getDescription())
////                .set("educationLevel", entity.getEducationLevel())
////                .set("education", entity.getEducation())
////                .set("additionalInformation", entity.getAdditionalInformation())
////                .set("languages", entity.getLanguages())
////                .set("locationPreference", entity.getLocationPreference())
////                .set("categoryId", entity.getCategoryId());
////        WriteResult result = mongoTemplate.upsert(query, update, ResumeEntity.class);
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
//    public void saveAll(List<ResumeEntity> entities) throws Exception {
//        mongoTemplate.save(entities);
//    }
//
//    @Override
//    public void clearAll() {
//        mongoTemplate.findAllAndRemove(new Query(), ResumeEntity.class);
//
//    }
//}
