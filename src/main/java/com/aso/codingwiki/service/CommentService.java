package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.BoardNotFoundException;
import com.aso.codingwiki.exception.CommentNotFoundException;
import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.BoardRepository;
import com.aso.codingwiki.repository.CommentRepository;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public long insComment(CommentEntity commentEntity, long boardId, String userEmail) {

        Optional<BoardEntity> boardEntity_ = boardRepository.findById(boardId);
        Optional<UserEntity> userEntity_ = userRepository.findOpByUserEmail(userEmail);

        if(!boardEntity_.isPresent()){
            throw new BoardNotFoundException("없는 글 입니다.");
        }
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("없는 유저 이름입니다.");
        }

        commentEntity.setCommentMap(boardEntity_.get(),userEntity_.get());

        repository.save(commentEntity);
        return  commentEntity.getId();
    }

    public List<CommentEntity> sellCommentBoard(long boardId) {

        Optional<BoardEntity> boardEntity_ = boardRepository.findById(boardId);
        if(!boardEntity_.isPresent()){
            throw new BoardNotFoundException("없는 글 입니다.");
        }
        return repository.findByBoardEntity(boardEntity_.get());
    }

    public long updComment(long commentId, String comment) {

        Optional<CommentEntity> commentEntity_ = repository.findById(commentId);
        if(!commentEntity_.isPresent()){
            throw new CommentNotFoundException("없는 뎃글입니다.");
        }
        CommentEntity commentEntity =  commentEntity_.get();
        commentEntity.setComment(comment);
        repository.save(commentEntity);//jpa 영속성 적용확인
        return commentEntity.getId();
    }

    public long delComment(long commentId) {
        Optional<CommentEntity> commentEntity_ = repository.findById(commentId);
        if(commentEntity_.isPresent()){
            throw new CommentNotFoundException("없는 뎃글입니다.");
        }
        return commentEntity_.get().getId();
    }
}
