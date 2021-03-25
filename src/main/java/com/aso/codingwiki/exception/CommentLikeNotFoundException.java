package com.aso.codingwiki.exception;

public class CommentLikeNotFoundException extends RuntimeException{
    public CommentLikeNotFoundException(String msg){
        super(msg);
    }
    public CommentLikeNotFoundException(Exception ex){
        super(ex);
    }
}
