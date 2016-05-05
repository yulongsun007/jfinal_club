package win.yulongsun.jfinal_club.util;

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
    private String  result;

    public void setFailureResponse(String errorMsg, int errorCode) {
        this.error = true;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.result = "";
    }

    public void setSuccessResponse(String result) {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
