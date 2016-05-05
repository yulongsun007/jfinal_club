package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import win.yulongsun.jfinal_club.model.Club;
import win.yulongsun.jfinal_club.model.User;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

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
            response.setFailureResponse("参数不能为空", 1001);
            renderJson(response);
            return;
        }

        Club club = new Club();
        club.setScale(Integer.parseInt(club_scale));
        club.setName(club_name);
        club.setAddr(club_addr);
        boolean isClubSave = club.save();

        Integer id   = club.getId();
        User    user = new User();
        user.setMobile(user_mobile);
        user.setName(user_name);
        user.setPassword(user_pwd);
        user.setAddr(club_addr);
        user.setJobId(1);
        user.setCId(id);
        user.setRId(1);
        boolean isUserSave = user.save();
        if (isUserSave && isClubSave) {
            response.setSuccessResponse(null);
        } else {
            response.setFailureResponse("用户注册失败",1002);
        }
        renderJson(response);

    }

    /*登陆*/
    public void login() {

    }


//    public void

}
