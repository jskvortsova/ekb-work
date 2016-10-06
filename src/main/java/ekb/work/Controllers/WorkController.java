package ekb.work.controllers;

import ekb.work.domain.CategoryDto;
import ekb.work.domain.ResponseDto;
import ekb.work.domain.ResumeDto;
import ekb.work.entity.CategoryEntity;
import ekb.work.entity.ResumeEntity;
import ekb.work.enums.ResponseStatus;
import ekb.work.service.HtmlContentParseService;
import ekb.work.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
@RestController
public class WorkController {

    private HtmlContentParseService htmlContentParseService;
    private MongoService mongoService;

    @Autowired
    public WorkController(HtmlContentParseService htmlContentParseService, MongoService mongoService){
        this.htmlContentParseService = htmlContentParseService;
        this.mongoService = mongoService;
    }

    @RequestMapping(value = {"/load"}, method = RequestMethod.GET)
    public ResponseDto load() throws Exception {
        mongoService.clearAll();

        List<CategoryDto> categories = htmlContentParseService.getCategories();
        List<CategoryEntity> categoryEntities = categories.stream().map(c -> c.toCategoryEntity()).collect(toList());
        mongoService.saveCategories(categoryEntities);

        List<ResumeDto> resumes = htmlContentParseService.getResumes(categories);
        List<ResumeEntity> resumeEntities = resumes.stream().map(r -> r.toResumeEntity()).collect(toList());
        mongoService.saveResumes(resumeEntities);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(String.format("%d documents loaded", categoryEntities.size() + resumeEntities.size()));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }

    @RequestMapping(value = {"/categories"}, method = RequestMethod.GET)
    public ResponseDto getCategories(@RequestParam Map<String, String> params) throws Exception {

        List<CategoryEntity> categories = mongoService.getCategories(params);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(categories);
        responseDto.setStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }

    @RequestMapping(value = {"/resume"}, method = RequestMethod.GET)
    public ResponseDto getResumes(@RequestParam Map<String, String> params) throws Exception {

        List<ResumeEntity> resumes = mongoService.getResumes(params);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(resumes);
        responseDto.setStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
}
