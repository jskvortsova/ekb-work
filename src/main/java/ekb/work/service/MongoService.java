package ekb.work.service;

import ekb.work.entity.CategoryEntity;
import ekb.work.entity.ResumeEntity;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
public interface MongoService {
    List<CategoryEntity> getCategories();
    void saveCategories(List<CategoryEntity> categoryEntities) throws Exception;
    void saveResumes(List<ResumeEntity> resumeEntities) throws Exception;
    void clearAll();
}
