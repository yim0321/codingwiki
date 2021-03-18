package com.aso.codingwiki.service;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.board.ImgEntity;
import com.aso.codingwiki.model.board.ImgResopnse;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.repository.BoardRepository;
import com.aso.codingwiki.repository.CategoryRepository;
import com.aso.codingwiki.repository.ImgRepository;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;
    private final CategoryRepository categoryRepository;
    private final ImgRepository imgRepository;


    public long insBoard(BoardEntity boardEntity,long categoryId,String uuid) {



        Optional<CategoryEntity> categoryEntity_ = categoryRepository.findById(categoryId);
        if(categoryEntity_.isPresent()){
            boardEntity.setCategoryEntity(categoryEntity_.get());
            repository.save(boardEntity);

            Optional<ImgEntity>imgEntity_ = imgRepository.findByUuid(uuid);
            if(imgEntity_.isPresent()){
                imgEntity_.get().setImgBool(true);
            }

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

    public long delBoard(long boardId) {

        Optional<BoardEntity> boardEntity_ = repository.findById(boardId);
        if(boardEntity_.isPresent()){
            repository.delete(boardEntity_.get());
            return boardEntity_.get().getId();
        }
        return 0;
    }


    public long updBoard(long boardId) {
        Optional<BoardEntity> boardEntity_ = repository.findById(boardId);
        if(boardEntity_.isPresent()){
            repository.save(boardEntity_.get());
            return boardEntity_.get().getId();
        }
        return 0;
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
}
