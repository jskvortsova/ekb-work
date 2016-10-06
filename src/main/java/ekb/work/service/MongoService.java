package ekb.work.service;

import ekb.work.entity.CategoryEntity;
import ekb.work.entity.ResumeEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
public interface MongoService {
    List<CategoryEntity> getCategories();
    List<ResumeEntity> getResumes();
    void saveCategories(List<CategoryEntity> categoryEntities) throws Exception;
    void saveResumes(List<ResumeEntity> resumeEntities) throws Exception;
    void saveResume(ResumeEntity resumeEntity) throws Exception;
    void clearAll();
    List<CategoryEntity> getCategories(Map<String, String> params);
    List<ResumeEntity> getResumes(Map<String, String> params);
}
