package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.service.StarPointService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class StarPointController {

    private final StarPointService service;

    /**
     * create 업데이트랑 같은 로직임
     */
    @PostMapping("/starPoint")
    public float insStarPoint(@RequestBody InsStarPointRequest insStarPointRequest, Principal principal){

        return service.updStarPoint(
                insStarPointRequest.getStarPoint(),
                insStarPointRequest.getBoardId(),
                principal.getName());
    }

    /**
     * read
     */

    /**
     * update 인설트랑 같은 로직임 하나 삭제해도 될지도
     */
    @PutMapping("/starPoint")
    public float updStarPoint(@RequestBody InsStarPointRequest insStarPointRequest, Principal principal ){
        
        return service.updStarPoint(
                insStarPointRequest.getStarPoint(),
                insStarPointRequest.getBoardId(),
                principal.getName());

    }

    /**
     * delete
     */

    @DeleteMapping("/starPoint/{starPointId}")
    public long delStarPoint(@PathVariable(name = "starPointId") long starPointId){
        return service.delStarPoint(starPointId);
    }

    /**
     * inner class
     */
    @Getter
    static class InsStarPointRequest{

        private long boardId;
        private int starPoint;
    }

    @Getter
    static class UpdStarPointRequest{
        private int starPoint;
    }
}
