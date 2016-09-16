package ekb.work.Controllers;

import ekb.work.Domain.ResponseDto;
import ekb.work.Domain.ResumeDto;
import ekb.work.Enums.*;
import ekb.work.Enums.ResponseStatus;
import ekb.work.Services.HtmlContentParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
@RestController
public class WorkController {

    private HtmlContentParseService htmlContentParseService;

    @Autowired
    public WorkController(HtmlContentParseService htmlContentParseService){
        this.htmlContentParseService = htmlContentParseService;
    }

    @RequestMapping(value = {"/run"}, method = RequestMethod.GET)
    public ResponseDto run() throws Exception {
        List<ResumeDto> resumeDtoList = htmlContentParseService.parse();

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(resumeDtoList);
        responseDto.setStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
}
