package ekb.work.Services;

import ekb.work.Domain.ResumeDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
public interface HtmlContentParseService {
    List<ResumeDto> parse() throws Exception;
}
