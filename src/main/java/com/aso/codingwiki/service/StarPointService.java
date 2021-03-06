package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.BoardNotFoundException;
import com.aso.codingwiki.exception.CategoryNotFoundException;
import com.aso.codingwiki.exception.StarPointNotFoundException;
import com.aso.codingwiki.model.PopularBoardEntity;
import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.statpoint.StarPointEntity;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StarPointService {

    private final StarPointRepository repository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PopularBoardRepository popularBoardRepository;
    private final BoardDslRepository boardDslRepository;

    public float updStarPoint(float starPoint, long boardId, String userEmail) {

        Optional<BoardEntity> boardEntity_ = boardRepository.findById(boardId);
        Optional<UserEntity> userEntity_ = userRepository.findOpByUserEmail(userEmail);

        if(!boardEntity_.isPresent()){
            throw new StarPointNotFoundException("없는 글 입니다.");
        }
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("없는 유저 입니다.");
        }
        BoardEntity boardEntity = boardEntity_.get();
        UserEntity userEntity = userEntity_.get();
        Optional<StarPointEntity> starPointEntity_ =
                repository.findByBoardEntityAndUserEntity(boardEntity,userEntity);
        //기존의 별점이 있으면 삭제후 다시 작성
        if(starPointEntity_.isPresent()){
            repository.delete(starPointEntity_.get());
        }

        StarPointEntity starPointEntity = StarPointEntity
                .builder()
                .boardEntity(boardEntity)
                .userEntity(userEntity)
                .starPoint(starPoint)
                .build();
        repository.save(starPointEntity);
        float starPointAvg = repository.starPointAvg(boardEntity);
        ///////////////////////////////////////////////////////글에대한 평균 별점 고치기
        boardEntity.setAvgStarPoint(starPointAvg);
        boardRepository.save(boardEntity);// 영속성 확인
        ///////////////////////////////////////////////////////가장 인기있는글 뽑아내기
        BoardEntity popularBoard = boardDslRepository.test(boardEntity.getCategoryEntity());//가장인기있는글
        CategoryEntity categoryEntity = boardEntity.getCategoryEntity();

        Optional<PopularBoardEntity> popularBoardEntity_ = popularBoardRepository.findByCategoryEntity(categoryEntity);

        if(popularBoardEntity_.isPresent()){
           popularBoardRepository.delete(popularBoardEntity_.get());
        }

        PopularBoardEntity popularBoardEntity =
                new PopularBoardEntity(popularBoard, categoryEntity, categoryEntity.getLanguageEntity());

        popularBoardRepository.save(popularBoardEntity);
        //////////////////////////////////////////////////////

        return starPointAvg;
    }

    public long delStarPoint(long starPointId) {

        Optional<StarPointEntity> starPointEntity_ = repository.findById(starPointId);
        if(!starPointEntity_.isPresent()){
            throw new StarPointNotFoundException("예전에 준 별점 정보를 찾을수 없습니다.");
        }
        StarPointEntity starPointEntity = starPointEntity_.get();
        repository.delete(starPointEntity);

        return starPointEntity.getId();
    }
}
