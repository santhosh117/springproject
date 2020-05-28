package com.project.sample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException{

    public CustomException(String errorMessage){
        super(errorMessage);
    }


}
