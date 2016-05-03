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
    private int     errorType;
    private String  errorMessage;
    private String  result;

    public void setFailureResponse(String errorMessage, int errorType) {
        this.error = true;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.result = "";
    }

    public void setSuccessResponse(String result) {
        this.error = false;
        this.errorType = 0;
        this.errorMessage = "";
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
