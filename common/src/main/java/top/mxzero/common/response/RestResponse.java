package top.mxzero.common.response;

import lombok.Data;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Data
public class RestResponse<T> {
    private int code;
    private String msg;
    private T data;

    public RestResponse<?> code(int code){
        this.code = code;
        return this;
    }

    public RestResponse<?> message(String message){
        this.msg = message;
        return this;
    }

    public  RestResponse<?> data(T data){
        this.data = data;
        return this;
    }


    public static <T> RestResponse<?> success(T data) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static <T> RestResponse<?> fail(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(400);
        response.setMsg(message);
        return response;
    }


    public static <T> RestResponse<?> error(String error) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(500);
        response.setMsg(error);
        return response;
    }
}
