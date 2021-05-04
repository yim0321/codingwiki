package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.BoardNotFoundException;
import com.aso.codingwiki.exception.CategoryNotFoundException;
import com.aso.codingwiki.exception.LanguageNotFoundException;
import com.aso.codingwiki.model.PopularBoardEntity;
import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.board.BoardResponse;
import com.aso.codingwiki.model.board.ImgEntity;
import com.aso.codingwiki.model.board.ImgResopnse;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.*;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;
    private final CategoryRepository categoryRepository;
    private final ImgRepository imgRepository;
    private final StarPointRepository starPointRepository;
    private final CommentRepository commentRepository;
    private final LanguageRepository languageRepository;
    private final PopularBoardRepository popularBoardRepository;
    private final UserRepository userRepository;


    public long insBoard(BoardEntity boardEntity, long categoryId, String uuid, Principal principal) {

        Optional<CategoryEntity> categoryEntity_ = categoryRepository.findById(categoryId);
        Optional<UserEntity> userEntity_ = userRepository.findOpByUserEmail(principal.getName());
        if(!categoryEntity_.isPresent()){
            throw new CategoryNotFoundException("없는 카테고리 입니다.");
        }

        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("없는 유저 입니다.");
        }

        boardEntity.setCategoryEntity(categoryEntity_.get());
        boardEntity.setUserEntity(userEntity_.get());

        repository.save(boardEntity);

        Optional<ImgEntity>imgEntity_ = imgRepository.findByUuid(uuid);

        //오류 처리 하면안됨 이미지가 없을수도 있음
        if(imgEntity_.isPresent()){
            imgEntity_.get().setImgBool(true);
        }

        return boardEntity.getId();

    }

    public List<BoardEntity> sellBoardCategory(long categoryId) {

        Optional<CategoryEntity> categoryEntity_ = categoryRepository.findById(categoryId);
        if(!categoryEntity_.isPresent()){
            throw new CategoryNotFoundException("없는 카테고리 입니다.");
        }
        return repository.findByCategoryEntity(categoryEntity_.get());
    }

    public long delBoard(long boardId) {

        Optional<BoardEntity> boardEntity_ = repository.findById(boardId);
        if(!boardEntity_.isPresent()){
            throw new BoardNotFoundException("없는 글 입니다.");
        }
        repository.delete(boardEntity_.get());
        return boardEntity_.get().getId();
    }


    public long updBoard(long boardId, BoardEntity newBoardEntity, String uuid) {

        Optional<BoardEntity> boardEntity_ = repository.findById(boardId);
        if(!boardEntity_.isPresent()){
            throw new BoardNotFoundException("없는 글 입니다.");
        }
        BoardEntity BoardEntity = boardEntity_.get();
        //기존 이미지
        Optional<ImgEntity> oldImgEntity_ = imgRepository.findByUuid(BoardEntity.getUuid());
        if(oldImgEntity_.isPresent()){
            oldImgEntity_.get().setImgBool(false);
        }
        //새로운 이미지
        Optional<ImgEntity> imgEntity_ = imgRepository.findByUuid(uuid);
        if(imgEntity_.isPresent()){
            imgEntity_.get().setImgBool(true);
        }
        BoardEntity.changeEntity(newBoardEntity);
        repository.save(BoardEntity);
        return BoardEntity.getId();

    }

    public ImgResopnse imgIns(HttpServletRequest request,
                       HttpServletResponse response,
                       MultipartHttpServletRequest multiFile,
                       String uuid) {

        JsonObject json = new JsonObject();
        PrintWriter printWriter = null;
        OutputStream outputStream = null;
        MultipartFile file = multiFile.getFile("upload");

        if(file != null){//널체크
            if(file.getSize()>0 && StringUtils.isNotBlank(file.getName())){//사이즈 빈값체크
                if(file.getContentType().toLowerCase().startsWith("image/")){//확장자 체크
                    try {
                        String fileName = file.getName();
                        byte[] bytes = file.getBytes();
                        String uploadPathDir = request.getServletContext().getRealPath("/img");//저장할 경로 만들기
                        File uploadDir = new File(uploadPathDir);
                        if(!uploadDir.exists()){//폴더 체크및 생성
                            uploadDir.mkdirs();//복수형태로 하위 디렉토리까지 같이 만듬
                        }
                        fileName = uuid;
                        /**
                         * StringBuffer 로 고치기
                         */

                        String uploadPathFile = uploadPathDir + "/" + fileName;
                        outputStream = new FileOutputStream(new File(uploadPathFile));
                        outputStream.write(bytes);

                        printWriter = response.getWriter();
                        String fileUrl = "http://localhost:8090" + "/img/" + fileName;

                        ImgEntity imgEntity = new ImgEntity(fileUrl,uuid);
                        imgRepository.save(imgEntity);

                        json.addProperty("uploaded",1);
                        json.addProperty("fileName",fileName);
                        json.addProperty("url",fileUrl);

                        printWriter.println(json);
                    }
                    catch (Exception e){

                    }
                }
            }
        }

        return null;
    }

    public BoardResponse sellBoardOne(long boardId) {
        Optional<BoardEntity> boardEntity_ = repository.findById(boardId);
        if(!boardEntity_.isPresent()){
            throw new BoardNotFoundException("없는 글 입니다.");
        }
        BoardEntity boardEntity = boardEntity_.get();
        boardEntity.addViews();

        repository.save(boardEntity);

        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntity(boardEntity);
        return new BoardResponse(commentEntityList,boardEntity);

    }

    public List<BoardEntity> sellPopularBoard(long languageId) {

        Optional<LanguageEntity> languageEntity_ = languageRepository.findById(languageId);
        if(!languageEntity_.isPresent()){
            throw new LanguageNotFoundException("없는 언어 입니다.");
        }
        List<PopularBoardEntity> popularBoardEntity_ =
                popularBoardRepository.findByLanguageEntity(languageEntity_.get());
        return popularBoardEntity_.stream().map(PopularBoardEntity-> PopularBoardEntity.getBoardEntity())
                .collect(Collectors.toList());
    }
}




















