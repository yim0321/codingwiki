package com.aso.codingwiki.controller;


import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.service.BoardService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public long insBoard(@RequestBody InsBoardRequest insBoardRequest){

        BoardEntity boardEntity =
                BoardEntity.builder()
                        .boardTitle(insBoardRequest.boardTitle)
                        .boardContents(insBoardRequest.boardContents)
                        .uuid(insBoardRequest.uuid)
                        .build();

        return service.insBoard(boardEntity,insBoardRequest.categoryId,insBoardRequest.uuid);
    }

    /**
     * 시큐리티에서 유저값 가져오도록 변경
     */
    //이미지 업로드 과정
    @PostMapping("/img")
    public void insImg(
            HttpServletRequest request,
            HttpServletResponse response,
            MultipartHttpServletRequest multiFile) throws Exception{

        String uuid = request.getParameter("uuid");
        service.imgIns(request,response,multiFile,uuid);

    }
    /**
     * read
     */
    //카테고리 셀렉트
    @GetMapping("/board/{categoryId}")
    public List<SelBoardResponse> selBoard(@PathVariable(name = "categoryId") long categoryId){

        return service.selBoard(categoryId)
                .stream()
                .map((boardEntity)->new SelBoardResponse(boardEntity))
                .collect(Collectors.toList());
    }

    //점수를 통한 보드 전체 검색

    //검색어를 통한 검색

    /**
     * update
     *
     * PUT 일부 업데이트
     * PATCH 모두 업데이트
     */

    @PatchMapping("/board/{boardId}")
    public long updBoard(@PathVariable(name = "boardId") long boardId,
                         @RequestBody InsBoardRequest insBoardRequest){

        BoardEntity boardEntity =
                BoardEntity.builder()
                        .boardTitle(insBoardRequest.boardTitle)
                        .boardContents(insBoardRequest.boardContents)
                        .uuid(insBoardRequest.uuid)
                        .build();
        return service.updBoard(boardId,boardEntity,insBoardRequest.uuid);
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
        private String uuid;

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
