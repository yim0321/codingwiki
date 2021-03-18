package com.aso.codingwiki.controller;


import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.service.BoardService;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
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
     * 시큐리티에서 유저값 가져오도록 변경
     */
    //이미지 업로드 과정
    @PostMapping("/img/{uuid}")
    public void insImg(HttpServletRequest request,
                       HttpServletResponse response,
                       MultipartHttpServletRequest multiFile,
                       @PathVariable(name = "uuid") long uuid) throws Exception{


        service.imgIns(request,response,multiFile,uuid);



        
        

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
     *
     * PUT 일부 업데이트
     * PATCH 모두 업데이트
     */

    @PatchMapping("/board/{boardId}")
    public long updBoard(@PathVariable(name = "boardId") long boardId){
        return service.updBoard(boardId);
    }



    /**
     * delete
     */

    @DeleteMapping("/board/{boardId}")
    public long delBoard(@PathVariable(name = "boardId") long boardId){

        return service.delBoard(boardId);
    }



    /**
     * inner class
     */
    @Getter
    @NoArgsConstructor
    static class InsBoardRequest{

        private long categoryId;//카테고리 id

        private String boardTitle;
        private String boardContents;

    }
    @Getter
    @NoArgsConstructor
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
