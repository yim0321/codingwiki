package com.aso.codingwiki.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private long id;
    private String userName;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public CommentResponse(CommentEntity commentEntity){
        this.id = commentEntity.getId();
        this.userName = commentEntity.getUserEntity().getName();
        this.comment = commentEntity.getComment();
        this.createDate = commentEntity.getCreatedDate();
        this.lastModifiedDate = commentEntity.getLastModifiedDate();
    }
}
