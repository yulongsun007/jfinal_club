package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
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
        String  c_id      = getPara("c_id");
        int     page_num  = getParaToInt("page_num");
        int     page_size = getParaToInt("page_size");
        boolean isNull    = ValidateUtils.validatePara(c_id, String.valueOf(page_num), String.valueOf(page_size));
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Page<User> userPage = User.dao.paginateByCId(c_id, page_num, page_size);
        response.setSuccessResponse(userPage.getList());
        renderJson(response);
    }


    /*添加店员*/
    public void addUser() {
        response = new Response();
        String user_mobile = getPara("user_mobile");
        String user_pwd    = getPara("user_pwd");
    }


    /*修改店员*/
    public void updateUser() {
        response = new Response();
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        User user = User.dao.findById(user_id);
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
        user.setIsEnable(0);
        boolean isUpdate = user.update();
        if (isUpdate) {
            response.setSuccessResponse(null);
        } else {
            response.setFailureResponse(Response.ErrorCode.DELETE_FAILURE);
        }
        renderJson(response);
    }

}
