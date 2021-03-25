package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.commentlike.CommentLike;
import com.aso.codingwiki.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService service;

    /**
     * create
     */

    @PostMapping("/commentLike/{commentId}")
    public long insCommentLike(
            @PathVariable(name = "commentId") long commentId,
            @RequestBody CommentLike like,
            Principal principal){

        return  service.insCommentLike(commentId,principal.getName(),like);
    }


    /**
     * read
     */


    /**
     * update
     */

    @PatchMapping("/commentLike/{commentLikeId}")
    public long updCommentLike(
            @PathVariable(name = "commentLikeId") long commentLikeId,
            @RequestBody CommentLike like){

        return service.updCommentLike(commentLikeId,like);
    }
    /**
     * delete
     */

    @DeleteMapping("/commentLike/{commentLikeId}")
    public long delCommentLike(@PathVariable(name = "commentLikeId") long commentLikeId){

        return service.delCommentLike(commentLikeId);
    }

    /**
     * inner class
     */


}
