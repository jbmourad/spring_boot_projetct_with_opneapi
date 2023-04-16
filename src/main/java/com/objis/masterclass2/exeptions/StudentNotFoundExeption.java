package com.objis.masterclass2.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundExeption extends RuntimeException{
    public StudentNotFoundExeption(String message){
        super(message);
    }
}
