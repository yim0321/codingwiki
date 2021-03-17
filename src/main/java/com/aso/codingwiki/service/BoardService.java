package com.aso.codingwiki.service;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.repository.BoardRepository;
import com.aso.codingwiki.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;
    private final CategoryRepository categoryRepository;

    public long insBoard(BoardEntity boardEntity,long categoryId) {

        Optional<CategoryEntity> categoryEntity_ = categoryRepository.findById(categoryId);
        if(categoryEntity_.isPresent()){
            boardEntity.setCategoryEntity(categoryEntity_.get());
            repository.save(boardEntity);
            return boardEntity.getId();
        }
        return 0;

    }

    public List<BoardEntity> selBoard(long categoryId) {

        List<BoardEntity> boardEntities = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity_ = categoryRepository.findById(categoryId);
        if(categoryEntity_.isPresent()){
            boardEntities.addAll(repository.findByCategoryEntity(categoryEntity_.get()));
        }
        return boardEntities;
    }
}
