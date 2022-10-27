package com.bssd.boffice.application.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }
}
