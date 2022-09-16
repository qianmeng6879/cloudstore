package top.mxzero.common.exception;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
