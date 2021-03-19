package com.aso.codingwiki.exception;

public class LanguageNotFoundException extends RuntimeException{
    public LanguageNotFoundException(String msg){
        super(msg);
    }
    public LanguageNotFoundException(Exception ex){
        super(ex);
    }
}
