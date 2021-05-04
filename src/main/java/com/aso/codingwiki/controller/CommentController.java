package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.comment.CommentResponse;
import com.aso.codingwiki.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    /**
     * create
     */
    @PostMapping("/comment")
    public CommentResponse insComment(@RequestBody InsCommentRequest insCommentRequest, Principal principal){

        CommentEntity commentEntity = new CommentEntity(insCommentRequest.getComment());
        commentEntity = service.insComment(commentEntity,insCommentRequest.getBoardId(),principal.getName());
        return new CommentResponse(commentEntity);
    }

    /**
     * read
     */

    @GetMapping("/comment/{boardId}")
    public List<SellCommentResponse> sellCommentBoard(@PathVariable(name = "boardId") long boardId){

        return service.sellCommentBoard(boardId)
                .stream()
                .map((commentEntity)-> new SellCommentResponse(commentEntity))
                .collect(Collectors.toList());
    }

    /**
     * update
     */

    @PatchMapping("/comment/{commentId}")
    public long updComment(
            @PathVariable(name = "commentId") long commentId,
            @RequestBody UpdComment updComment){

        return service.updComment(commentId,updComment.getComment());
    }

    /**
     * delete
     */
    @DeleteMapping("/comment/{commentId}")
    public long delComment(
            @PathVariable(name = "commentId") long commentId){

        return service.delComment(commentId);
    }


    /**
     * innerclass
     */
    @Getter
    static class InsCommentRequest{

        private long boardId;
        private String comment;
    }
    @Getter
    static class SellCommentResponse{

        private String userEmail;
        private String comment;
        private LocalDateTime lastModifiedDate;

        public SellCommentResponse(CommentEntity commentEntity){
            this.userEmail = commentEntity.getUserEntity().getUserEmail();
            this.comment = commentEntity.getComment();
            this.lastModifiedDate = commentEntity.getLastModifiedDate();
        }
    }

    @Getter
    static class UpdComment{

        private String comment;
    }




}
