package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.service.BoardService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    /**
     * create
     */
    @PostMapping("/board")
    public long insBoard(InsBoardRequest insBoardRequest){

        BoardEntity boardEntity =
                BoardEntity.builder()
                        .boardTitle(insBoardRequest.boardTitle)
                        .boardContents(insBoardRequest.boardContents)
                        .build();

        return service.insBoard(boardEntity,insBoardRequest.categoryId);
    }

    /**
     * read
     */

    @GetMapping("/board/{categoryId}")
    public List<SelBoardResponse> selBoard(@PathVariable(name = "categoryId") long categoryId){

        return service.selBoard(categoryId)
                .stream()
                .map((boardEntity)->new SelBoardResponse(boardEntity))
                .collect(Collectors.toList());
    }

    /**
     * update
     */




    /**
     * delete
     */




    /**
     * inner class
     */
    static class InsBoardRequest{

        private long categoryId;//카테고리 id

        private String boardTitle;
        private String boardContents;

    }
    static class SelBoardResponse{

        private String boardTitle;//글제몪
        private String boardContents;//글내용
        private float sumStarPoint;//별점
        private long views;//조회수


        public SelBoardResponse(BoardEntity boardEntity){
            this.boardTitle = boardEntity.getBoardTitle();
            this.boardContents = boardEntity.getBoardContents();
            this.sumStarPoint = boardEntity.getSumStarPoint();
            this.views = boardEntity.getViews();
        }
    }
}
