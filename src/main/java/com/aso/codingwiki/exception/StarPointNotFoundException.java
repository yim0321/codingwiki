package com.aso.codingwiki.exception;

public class StarPointNotFoundException extends RuntimeException{
    public StarPointNotFoundException(String msg){
        super(msg);
    }
    public StarPointNotFoundException(Exception ex){
        super(ex);
    }
}
