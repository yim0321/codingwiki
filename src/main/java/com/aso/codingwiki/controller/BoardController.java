package com.aso.codingwiki.controller;


import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.board.BoardResponse;
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
    //카테고리 셀렉트 수정요망 뎃글 정보가 없음
//    @GetMapping("/board/category/{categoryId}")
//    public List<BoardResponse> sellBoardCategory(@PathVariable(name = "categoryId") long categoryId){
//
//        return service.sellBoardCategory(categoryId)
//                .stream()
//                .map((boardEntity)->new BoardResponse(boardEntity))
//                .collect(Collectors.toList());
//    }
    //하나 검색 조회수도 증가시킴
    @GetMapping("/board/{boardId}")
    public BoardResponse sellBoardOne(@PathVariable(name = "boardId") long boardId){
        return service.sellBoardOne(boardId);
    }

    //별점을 통한 최상위 글만 가져오기
    @GetMapping("/board/popular/{languageId}")
    public BoardResponse sellPopularBoard(@PathVariable(name = "languageId") long languageId){
        service.sellPopularBoard(languageId);
        return null;
    }


    //검색어를 통한 검색

    /**
     * update
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



}
