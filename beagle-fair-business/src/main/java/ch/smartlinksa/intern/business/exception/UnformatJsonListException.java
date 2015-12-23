package ch.smartlinksa.intern.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UnformatJsonListException extends RuntimeException{
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")//400
    public String returnError400(){

        return  "Error400ttttttttttt";
    }


}
