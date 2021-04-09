package cn.linstudy.travel.qo.response;

import cn.linstudy.travel.constant.SystemConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * jsonResult
 *
 * {
 *     code:
 *     msg:
 *     data:{}/[]
 * }
 */
// JSON返回对象
@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> {


    private int code;  //区分不同结果, 而不再是true或者false
    private String msg;
    private T data;  //除了操作结果之后, 还行携带数据返回

    public JsonResult(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> JsonResult success(T data){
        return new JsonResult(SystemConstant.CODE_SUCCESS, SystemConstant.MSG_SUCCESS, data);
    }

    public static JsonResult success(){
        return new JsonResult(SystemConstant.CODE_SUCCESS, SystemConstant.MSG_SUCCESS, null);
    }

    public static <T>  JsonResult error(int code, String msg, T data){
        return new JsonResult(code, msg, data);
    }

    public static JsonResult defaultError(){
        return new JsonResult(SystemConstant.CODE_ERROR, SystemConstant.MSG_ERROR, null);
    }


    public static JsonResult noLogin() {
        return new JsonResult(SystemConstant.CODE_NOLOGIN, SystemConstant.MSG_NOLOGIN, null);
    }

    public static JsonResult error(int code, String msg) {
        return new JsonResult(code,msg);
    }
}
