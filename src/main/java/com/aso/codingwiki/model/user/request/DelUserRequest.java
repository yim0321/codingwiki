package com.aso.codingwiki.model.user.request;

import lombok.Getter;

@Getter
public class DelUserRequest {
    private long id;
    private String userPw;
}
