package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import win.yulongsun.jfinal_club.model.Club;
import win.yulongsun.jfinal_club.model.User;
import win.yulongsun.jfinal_club.util.Response;

/**
 * Created by yulongsun on 2016/4/14.
 * 店面经理
 */
public class ManagerController extends Controller {
    private Response response;

    /*注册*/
    public void register() {
        String  club_name   = getPara("club_name");
        Integer club_scale  = getParaToInt("club_scale");
        String  club_addr   = getPara("club_addr");
        String  user_mobile = getPara("user_mobile");
        String  user_name   = getPara("user_name");
        String  user_gender = getPara("user_gender");

        Club club = new Club();
        club.setScale(club_scale);
        club.setName(club_name);
        club.setAddr(club_addr);
        club.save();
        Integer id = club.getId();

        User user = new User();
        user.setAddr(club_addr);
        user.setJobId(1);
        user.setCId(id);
        user.setRId(1);
    }

    /*登陆*/
    public void login(){

    }


//    public void

}
