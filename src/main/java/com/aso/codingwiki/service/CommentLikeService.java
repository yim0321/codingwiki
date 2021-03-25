package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.CommentLikeNotFoundException;
import com.aso.codingwiki.exception.CommentNotFoundException;
import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.commentlike.CommentLike;
import com.aso.codingwiki.model.commentlike.CommentLikeEntity;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.CommentLikeRepository;
import com.aso.codingwiki.repository.CommentRepository;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository repository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public long insCommentLike(long commentId,String userEmail,CommentLike like) {

        Optional<CommentEntity> commentEntity_ = commentRepository.findById(commentId);
        if(!commentEntity_.isPresent()){
            throw new CommentNotFoundException("없는 뎃글 입니다.");
        }

        Optional<UserEntity> userEntity_ = userRepository.findOpByUserEmail(userEmail);
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("없는 이메일 입니다.");
        }

        CommentLikeEntity commentLikeEntity = CommentLikeEntity
                .builder()
                .commentEntity(commentEntity_.get())
                .userEntity(userEntity_.get())
                .commentLike(like)
                .build();
        repository.save(commentLikeEntity);
        return commentLikeEntity.getId();
    }


    public long delCommentLike(long commentLikeId) {

        Optional<CommentLikeEntity> commentLikeEntity_ = repository.findById(commentLikeId);
        if(!commentLikeEntity_.isPresent()){
            throw new CommentLikeNotFoundException("뎃글의 좋아요를 찾을수 없습니다.");
        }
        CommentLikeEntity commentLikeEntity= commentLikeEntity_.get();
        repository.delete(commentLikeEntity);
        return commentLikeEntity.getId();
    }

    public long updCommentLike(long commentLikeId, CommentLike like) {

        Optional<CommentLikeEntity> commentLikeEntity_ = repository.findById(commentLikeId);
        if(!commentLikeEntity_.isPresent()){
            throw new CommentLikeNotFoundException("뎃글의 좋아요를 찾을수 없습니다.");
        }
        CommentLikeEntity commentLikeEntity= commentLikeEntity_.get();
        commentLikeEntity.setCommentLike(like);
        repository.save(commentLikeEntity);//영속성 반영되는지 확인후 삭제

        return commentLikeEntity.getId();

    }
}
