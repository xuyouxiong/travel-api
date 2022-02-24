package cn.linstudy.travel.qo.response;

import lombok.Data;

@Data
public class LoginResponse {
    public LoginResponse(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }
    private int id;
    private String msg;
}
