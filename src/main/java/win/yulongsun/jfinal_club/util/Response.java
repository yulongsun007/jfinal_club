package win.yulongsun.jfinal_club.util;

import static javafx.scene.input.KeyCode.T;

/**
 * @Project YulongsunSimple
 * @Packate win.yulongsun.yulongsunlib.bean
 * @Author yulongsun
 * @Email yulongsun@gmail.com
 * @Date 2016/4/13
 * @Version 1.0.0
 * @Description
 */
public class Response {
    private boolean error;
    private int     errorCode;
    private String  errorMsg;
    private Object  result;

    public void setFailureResponse(int errorCode) {
        this.error = true;
        this.errorCode = errorCode;
        switch (errorCode) {
            case ErrorCode.REQUEST_NULL:
                this.errorMsg = "请求参数不能为空";
                break;
            case ErrorCode.REGISTER_FAILURE:
                this.errorMsg = "用户注册失败";
                break;
            case ErrorCode.USER_NULL:
                this.errorMsg = "用户不存在";
                break;
            case ErrorCode.ERROR_PWD:
                this.errorMsg = "密码错误";
                break;
            case ErrorCode.DELETE_FAILURE:
                this.errorMsg = "删除失败";
                break;
            case ErrorCode.ERROR_UN_ENABLE:
                this.errorMsg = "当前账户未启用";
                break;
        }
        this.result = "";
    }

    public void setFailureResponse(String errorMsg, int errorCode) {
        this.error = true;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.result = "";
    }

    public void setSuccessResponse(Object result) {
        this.error = false;
        this.errorCode = 0;
        this.errorMsg = "";
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public interface ErrorCode {
        int REQUEST_NULL     = 1001;
        int REGISTER_FAILURE = 1002;
        int USER_NULL        = 1003;
        int ERROR_PWD        = 1004;
        int DELETE_FAILURE   = 1005;
        int ERROR_UN_ENABLE  = 1006;
    }
}
