package com.aso.codingwiki.exception;

public class OverlapUserException extends RuntimeException{
    public OverlapUserException(String msg){
        super(msg);
    }
    public OverlapUserException(Exception ex){
        super(ex);
    }
}



