package ekb.work.Controllers;

import ekb.work.Domain.ResponseDto;
import ekb.work.Enums.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by juliaskvortsova on 9/10/16.
 */
@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    static Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(ResponseStatus.ERROR);
        responseDto.setMessage(ex.getMessage());
        log.error("Handled by controller", ex);
        return responseDto;
    }

}