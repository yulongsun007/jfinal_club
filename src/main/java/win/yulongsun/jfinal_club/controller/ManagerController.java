package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.taobao.api.ApiException;
import com.taobao.api.domain.BizResult;
import win.yulongsun.jfinal_club.model.Club;
import win.yulongsun.jfinal_club.model.User;
import win.yulongsun.jfinal_club.util.DayuSMSUtils;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by yulongsun on 2016/4/14.
 * 店面经理
 */
public class ManagerController extends Controller {
    private Response response;

    /*注册*/
    public void register() {
        response = new Response();
        String  club_name   = getPara("club_name");
        String  club_scale  = getPara("club_scale");
        String  club_addr   = getPara("club_addr");
        String  user_mobile = getPara("user_mobile");
        String  user_name   = getPara("user_name");
        String  user_pwd    = getPara("user_pwd");
        boolean isNull      = ValidateUtils.validatePara(club_name, club_scale, club_addr, user_mobile, user_name, user_pwd);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }

        List<User> userList = User.dao.findByMobile(user_mobile);
        if (userList.isEmpty() || userList.size() < 0) {
            Club club = new Club();
            club.setScale(Integer.parseInt(club_scale));
            club.setName(club_name);
            club.setAddr(club_addr);
            club.save();
            Integer id   = club.getId();
            User    user = new User();
            user.setMobile(user_mobile);
            user.setName(user_name);
            user.setAvatar("http://img3.imgtn.bdimg.com/it/u=2673973173,2449000084&fm=21&gp=0.jpg");
            user.setPassword(user_pwd);
            user.setAddr(club_addr);
            user.setJobId(String.valueOf(1));
            user.setCId(id);
            user.setRId(1);
            user.save();
            response.setSuccessResponse();
        } else {
            response.setFailureResponse(Response.ErrorCode.USER_REGISTER);
        }
        renderJson(response);

    }

    /*登陆*/
    public void login() {
        response = new Response();
        String  user_mobile = getPara("user_mobile");
        String  user_pwd    = getPara("user_pwd");
        boolean isNull      = ValidateUtils.validatePara(user_mobile, user_pwd);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        List<User> userList = User.dao.findByMobile(user_mobile);
        if (userList.isEmpty()) {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        } else if (!user_pwd.equals(userList.get(0).getPassword())) {
            response.setFailureResponse(Response.ErrorCode.ERROR_PWD);
        } else if (userList.get(0).getIsEnable() == 0) {
            response.setFailureResponse(Response.ErrorCode.ERROR_UN_ENABLE);
        } else {
            response.setSuccessResponse(userList.get(0));

        }
        renderJson(response);
    }


    /*发送验证码*/
    public void sendVerifyCode() {
        response = new Response();
        String  user_mobile = getPara("user_mobile");
        boolean isPhone     = ValidateUtils.isMobilePattern(user_mobile);
        if (!isPhone) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        int       code      = new Random().nextInt(999999);
        BizResult bizResult = null;
        try {
            bizResult = DayuSMSUtils.sendSms(user_mobile, code);
        } catch (ApiException e) {
//            e.printStackTrace();
        } finally {
            if (bizResult == null || bizResult.getSuccess() == false) {
                response.setFailureResponse(Response.ErrorCode.ERROR_SEND_FAILURE);
            } else {
                response.setSuccessResponse(code);
            }
        }
        renderJson(response);

    }

    /*重置密码*/
    public void resetPwd() {
        response = new Response();
        String  user_mobile  = getPara("user_mobile");
        String  user_new_pwd = getPara("user_new_pwd");
        boolean isNull       = ValidateUtils.validatePara(user_mobile, user_new_pwd);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        List<User> userList = User.dao.findByMobile(user_mobile);
        if (userList == null || userList.size() == 0) {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        } else {
            User user = userList.get(0);
            user.setPassword(user_new_pwd);
            user.update();
            response.setSuccessResponse();
        }
        renderJson(response);
    }
}
