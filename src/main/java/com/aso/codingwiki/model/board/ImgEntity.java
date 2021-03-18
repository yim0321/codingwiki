package com.aso.codingwiki.model.board;

import com.aso.codingwiki.model.common.DateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImgEntity extends DateTime {

    @Id @GeneratedValue
    @Column(name = "img_id")
    private long id;

    private String url;
    private boolean imgBool;
    private String uuid;

    public ImgEntity(String url,String uuid){
        this.url = url;
        this.imgBool = false;
        this.uuid = uuid;
    }

    public void setImgBool(boolean imgBool) {
        this.imgBool = imgBool;
    }
}
