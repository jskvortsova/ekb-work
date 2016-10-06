package ekb.work.service;

import ekb.work.entity.CategoryEntity;
import ekb.work.entity.ResumeEntity;
//import ekb.work.repository.CategoryRepositoryImpl;
import ekb.work.repository.RepositoryImpl;
//import ekb.work.repository.ResumeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
@Service
public class MongoServiceImpl implements MongoService {

    RepositoryImpl repository;

    @Autowired
    public MongoServiceImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return repository.findAll(CategoryEntity.class);
    }

    @Override
    public List<ResumeEntity> getResumes() {
        return repository.findAll(ResumeEntity.class);
    }

    @Override
    public void saveCategories(List<CategoryEntity> categoryEntities) throws Exception {
        repository.saveAll(categoryEntities);
    }

    @Override
    public void saveResumes(List<ResumeEntity> resumeEntities) throws Exception {
        repository.saveAll(resumeEntities);

    }

    @Override
    public void saveResume(ResumeEntity resumeEntity) {
        repository.save(resumeEntity);
    }

    @Override
    public void clearAll() {
        repository.clearAll(CategoryEntity.class);
        repository.clearAll(ResumeEntity.class);
    }

    @Override
    public List<CategoryEntity> getCategories(Map<String, String> params) {
        return repository.findAll(params, CategoryEntity.class);
    }

    @Override
    public List<ResumeEntity> getResumes(Map<String, String> params) {
        return repository.findAll(params, ResumeEntity.class);
    }
}
