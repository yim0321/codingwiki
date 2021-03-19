package com.aso.codingwiki.model.user.request;

import com.aso.codingwiki.model.user.UserEntity;
import lombok.*;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdUserRequest {

    @NotEmpty
    private long id;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  oldPw;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  newPw;

}
