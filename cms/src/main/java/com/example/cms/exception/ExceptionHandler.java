package com.example.cms.exception;

<<<<<<< HEAD
import com.example.cms.dto.response.ResponseBody;
import com.example.cms.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseBody> handleUnhandledException(Exception exception){
        return ResponseUtil.errorResponse(exception.getMessage(),500);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ResponseBody> handleNotFoundException(NotFoundException exception) {
        return ResponseUtil.errorResponse(exception.getMessage());
    }
=======
public class ExceptionHandler {
>>>>>>> d9c9d73b6c54175526b062a1c1657ce6416d9130
}
