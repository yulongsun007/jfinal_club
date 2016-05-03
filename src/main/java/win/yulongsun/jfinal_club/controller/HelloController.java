package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;

/**
 * Created by yulongsun on 2016/1/28.
 */
public class HelloController extends Controller {

    public void index() {
        renderCaptcha();
//        renderText("Hello JFinal World.");
    }
}
