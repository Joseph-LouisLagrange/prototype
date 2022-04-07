package com.darwin.prototype.dto;

import com.darwin.prototype.util.GlobalStaticBean;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class LoginDto {
    private boolean success;
    private String message;


    public static LoginDto loginSuccess(){
        return new LoginDto(true,null);
    }
    public static LoginDto loginFail(String message){
        return new LoginDto(false,message);
    }

    public String toJson(){
        return GlobalStaticBean.GSON_BEAN.toJson(this);
    }
}
