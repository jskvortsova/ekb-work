package ekb.work.service;

import ekb.work.domain.CategoryDto;
import ekb.work.domain.ResumeDto;
import ekb.work.entity.CategoryEntity;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
public interface HtmlContentParseService {
    List<CategoryDto> getCategories() throws Exception;
    List<ResumeDto> getResumes(List<CategoryDto> categories);
}
