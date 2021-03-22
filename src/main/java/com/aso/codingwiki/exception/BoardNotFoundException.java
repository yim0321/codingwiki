package com.aso.codingwiki.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(String msg){
        super(msg);
    }
    public BoardNotFoundException(Exception ex){
        super(ex);
    }
}
