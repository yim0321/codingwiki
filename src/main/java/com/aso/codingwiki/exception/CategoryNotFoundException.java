package com.aso.codingwiki.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String msg){
        super(msg);
    }
    public CategoryNotFoundException(Exception ex){
        super(ex);
    }
}
