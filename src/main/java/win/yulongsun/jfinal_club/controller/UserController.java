package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import sun.security.provider.MD5;
import win.yulongsun.jfinal_club.model.Club;
import win.yulongsun.jfinal_club.model.User;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

/**
 * Created by yulongsun on 2016/5/5.
 */
public class UserController extends Controller {

    private Response response;


    /*分页显示店员*/
    public void listUser() {
        response = new Response();
        //会所id
        String  user_c_id = getPara("user_c_id");
        String  page_num  = getPara("page_num");
        String  page_size = getPara("page_size");
        boolean isNull    = ValidateUtils.validatePara(user_c_id, page_num, page_size);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Page<User> userPage = User.dao.paginateByCId(user_c_id, Integer.valueOf(page_num), Integer.valueOf(page_size));
        response.setSuccessResponse(userPage.getList());
        renderJson(response);
    }


    /*添加店员*/
    public void addUser() {
        response = new Response();
        String  user_mobile = getPara("user_mobile");
        String  user_pwd    = getPara("user_pwd");
        String  user_avatar = getPara("user_avatar");
        String  user_gender = getPara("user_gender");
        String  user_addr   = getPara("user_addr");
        String  user_job_id = getPara("user_job_id");
        String  user_c_id   = getPara("user_c_id");
        boolean isNull      = ValidateUtils.validatePara(user_mobile, user_pwd, user_avatar, user_gender, user_addr, user_job_id, user_c_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        User user = new User();
        user.setMobile(user_mobile);
        user.setAvatar(user_avatar);
        user.setPassword(user_pwd);
        user.setGender(Integer.valueOf(user_gender));
        user.setAddr(user_addr);
        user.setJobId(user_job_id);
        user.setCId(Integer.valueOf(user_c_id));
        user.setRId(0);
        boolean isSave = user.save();
        if (isSave) {
            response.setSuccessResponse(Response.ErrorCode.ADD_SUCCESS);
        } else {
            response.setFailureResponse(Response.ErrorCode.ADD_FAILURE);
        }
        renderJson(response);
    }


    /*修改店员*/
    public void updateUser() {
        response = new Response();
        String  user_id     = getPara("user_id");
        String  user_mobile = getPara("user_mobile");
        String  user_pwd    = getPara("user_pwd");
        String  user_avatar = getPara("user_avatar");
        String  user_gender = getPara("user_gender");
        String  user_addr   = getPara("user_addr");
        String  user_job_id = getPara("user_job_id");
        boolean isNull      = ValidateUtils.validatePara(user_id, user_mobile, user_pwd, user_avatar, user_gender, user_addr, user_job_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        User user = User.dao.findById(user_id);
        if (user != null) {
            user.setMobile(user_mobile);
            user.setAvatar(user_avatar);
            user.setPassword(user_pwd);
            user.setGender(Integer.valueOf(user_gender));
            user.setAddr(user_addr);
            user.setJobId(user_job_id);
            user.setRId(0);
            user.update();
            response.setSuccessResponse(Response.ErrorCode.UPDATE_SUCCESS);
        } else {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        }
        renderJson(response);
    }


    /*删除店员*/
    public void deleteUser() {
        response = new Response();
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        User user = User.dao.findById(user_id);
        if (user != null) {
            user.setIsEnable(0);
            user.update();
            response.setSuccessResponse(Response.ErrorCode.DELETE_SUCCESS);
        } else {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        }
        renderJson(response);
    }

}
