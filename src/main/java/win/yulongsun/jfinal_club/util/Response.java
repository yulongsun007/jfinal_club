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
    private Object  result;

    public void setFailureResponse(int errorCode) {
        this.error = true;
        this.errorCode = errorCode;
        switch (errorCode) {
            case ErrorCode.REQUEST_NULL:
                this.errorMsg = "请求参数不能为空";
                break;
            case ErrorCode.USER_REGISTERED:
                this.errorMsg = "此手机号已注册";
                break;
            case ErrorCode.USER_NULL:
                this.errorMsg = "用户不存在";
                break;
            case ErrorCode.ERROR_PWD:
                this.errorMsg = "密码错误";
                break;
            case ErrorCode.ERROR_UN_ENABLE:
                this.errorMsg = "当前账户未启用";
                break;
            case ErrorCode.ERROR_SEND_FAILURE:
                this.errorMsg = "验证码发送失败";
                break;
            case ErrorCode.ADD_SUCCESS:
                this.errorMsg = "添加成功";
                break;
            case ErrorCode.ADD_FAILURE:
                this.errorMsg = "添加失败";
                break;
            case ErrorCode.UPDATE_SUCCESS:
                this.errorMsg = "修改成功";
                break;
            case ErrorCode.UPDATE_FAILURE:
                this.errorMsg = "修改失败";
                break;
            case ErrorCode.DELETE_SUCCESS:
                this.errorMsg = "删除成功";
                break;
            case ErrorCode.DELETE_FAILURE:
                this.errorMsg = "删除失败";
                break;
            case ErrorCode.QUERY_SUCCESS:
                this.errorMsg = "查询成功";
                break;
            case ErrorCode.QUERY_FAILURE:
                this.errorMsg = "查询失败";
                break;

        }
        this.result = null;
    }

//    public void setFailureResponse(String errorMsg, int errorCode) {
//        this.error = true;
//        this.errorCode = errorCode;
//        this.errorMsg = errorMsg;
//        this.result = null;
//    }

    public void setSuccessResponse(Object result) {
        this.error = false;
        this.errorCode = 0;
        this.errorMsg = "";
        this.result = result;
    }

    public void setSuccessResponse() {
        this.error = false;
        this.errorCode = 0;
        this.errorMsg = "";
        this.result = "";
    }

//    public void setSuccessResponse(String msg) {
//        this.error = false;
//        this.errorCode = 0;
//        this.errorMsg = msg;
//        this.result = "";
//    }

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
        int REQUEST_NULL       = 1001;
        int USER_REGISTERED    = 1002;
        int USER_NULL          = 1003;
        int ERROR_PWD          = 1004;
        int ERROR_UN_ENABLE    = 1006;
        int ERROR_SEND_FAILURE = 1007;
        ///////
        int ADD_SUCCESS        = 1008;
        int ADD_FAILURE        = 1009;
        ///////
        int UPDATE_SUCCESS     = 1010;
        int UPDATE_FAILURE     = 1011;
        ///////
        int DELETE_SUCCESS     = 1012;
        int DELETE_FAILURE     = 1013;
        ///////
        int QUERY_SUCCESS      = 1014;
        int QUERY_FAILURE      = 1015;
        ///////
    }
}
