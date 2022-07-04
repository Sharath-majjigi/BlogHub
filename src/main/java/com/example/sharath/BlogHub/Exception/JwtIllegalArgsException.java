package com.example.sharath.BlogHub.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtIllegalArgsException extends RuntimeException{

    String msg;

    public JwtIllegalArgsException(String msg){
        super(String.format("This is not a valid Argument %s",msg));
        this.msg=msg;
    }
}
