package com.aso.codingwiki.exception;

public class OverlapLanguageException extends RuntimeException{
    public OverlapLanguageException(String msg){
        super(msg);
    }
    public OverlapLanguageException(Exception ex){
        super(ex);
    }
}

