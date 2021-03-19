package com.aso.codingwiki.exception;

public class OverlapCategoryException extends RuntimeException{
    public OverlapCategoryException(String msg){
        super(msg);
    }
    public OverlapCategoryException(Exception ex){
        super(ex);
    }
}
