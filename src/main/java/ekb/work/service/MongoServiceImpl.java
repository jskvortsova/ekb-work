package ekb.work.service;

import ekb.work.entity.CategoryEntity;
import ekb.work.entity.ResumeEntity;
//import ekb.work.repository.CategoryRepositoryImpl;
import ekb.work.repository.RepositoryImpl;
//import ekb.work.repository.ResumeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
@Service
public class MongoServiceImpl implements MongoService {

//    CategoryRepositoryImpl categoryRepository;
//    ResumeRepositoryImpl resumeRepository;
    RepositoryImpl repository;

//    @Autowired
//    public MongoServiceImpl(CategoryRepositoryImpl categoryRepository, ResumeRepositoryImpl resumeRepository) {
//        this.categoryRepository = categoryRepository;
//        this.resumeRepository = resumeRepository;
//    }


    @Autowired
    public MongoServiceImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return repository.findAll(CategoryEntity.class);
    }

    @Override
    public void saveCategories(List<CategoryEntity> categoryEntities) throws Exception {
//        for (CategoryEntity categoryEntity : categoryEntities) {
//            categoryRepository.save(categoryEntity);
//        }
//        categoryRepository.saveAll(categoryEntities);
        repository.saveAll(categoryEntities);
    }

    @Override
    public void saveResumes(List<ResumeEntity> resumeEntities) throws Exception {
//        for (ResumeEntity resumeEntity : resumeEntities) {
//            resumeRepository.save(resumeEntity);
//        }
//        resumeRepository.saveAll(resumeEntities);
        repository.saveAll(resumeEntities);

    }

    @Override
    public void clearAll() {
//        categoryRepository.clearAll();
//        resumeRepository.clearAll();
        repository.clearAll(CategoryEntity.class);
        repository.clearAll(ResumeEntity.class);
    }
}
