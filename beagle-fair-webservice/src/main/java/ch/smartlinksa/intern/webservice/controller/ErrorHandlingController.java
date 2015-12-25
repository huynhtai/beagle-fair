package ch.smartlinksa.intern.webservice.controller;


import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponseHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.IllegalFormatException;

@ControllerAdvice
public class ErrorHandlingController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestApiResponse<?> handleMethodArgumentNotValidExceptionS(HttpServletRequest request, HttpServletResponse response,
                                                     Object handler, MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        if (isObjectValidateError(result)) {
            ObjectError error = result.getGlobalError();
            return createPaymentResponseError(request, error.getDefaultMessage(), null);
        } else {
            FieldError fieldError = result.getFieldErrors().get(0);
            return createPaymentResponseError(request, fieldError.getDefaultMessage(), fieldError.getArguments());
        }

    }

/*    @ExceptionHandler(IllegalFormatException.class)
    @ResponseBody
    public RestApiResponse<?> handleIllegalFormatException(HttpServletRequest request, HttpServletResponse response,
                                                                     Object handler, IllegalFormatException ex) {
       return createPaymentResponseError(request, "500", ["Internal Error"]);

    }*/

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestApiResponse<?> handleIllegalFormatException(HttpServletRequest request, HttpServletResponse response,
                                                           Object handler, Exception ex) {
        String resultCode = "500";
        return createPaymentResponseError(request,resultCode, null);
    }


    private RestApiResponse<?> createPaymentResponseError(HttpServletRequest request, String resultCode, Object[] messageArguments) {
        RestApiResponse<String> RestApiResponse = new RestApiResponse<String>();
        RestApiResponseHeaders responseHeaders = RestApiResponse.getHeaders();
        responseHeaders.setClientTransactionId("");
        responseHeaders.setTransactionId("");
        responseHeaders.setResultCode(resultCode);
        responseHeaders.setResultDescription(getMessage(resultCode, messageArguments));
        return RestApiResponse;
    }


    private boolean isObjectValidateError(BindingResult result) {
        return result.getGlobalErrorCount() > 0;
    }

    private String getMessage(String resultCode, Object[] messageAgruments) {
        try {
            return messageSource.getMessage(resultCode, messageAgruments, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCode;
    }
}
