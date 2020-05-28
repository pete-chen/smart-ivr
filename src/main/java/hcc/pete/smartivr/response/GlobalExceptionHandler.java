package hcc.pete.smartivr.response;


import hcc.pete.smartivr.pojo.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Pete Chen
 * @date 2020/5/27
 */

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public CommonResponse handleMyException(MyException ex) {
        CommonResponse result;
        result = new CommonResponse(ex.getResponse().getState(), ex.getResponse().getMsg());
        return result;
    }

}
