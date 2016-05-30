package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

/**
 * Created by yulongsun on 2016/5/30.
 */
public class TestController extends Controller {

    public void uploadImg() {
//        UploadFile img = getFile("img");
//        System.out.println("getFile=" + img.getFile());//C:/Users/yulongsun/Documents/IDEA/JAVA/jfinal_club/src/main/webapp/upload/10202.jpg
//        System.out.println("getContentType=" + img.getContentType());//image/jpeg
//        System.out.println("getFileName=" + img.getFileName());//10202.jpg
//        System.out.println("getUploadPath=" + img.getUploadPath());//C:/Users/yulongsun/Documents/IDEA/JAVA/jfinal_club/src/main/webapp/upload
//        System.out.println("getOriginalFileName=" + img.getOriginalFileName());//10202.jpg
        System.out.println("getRootClassPath=" + PathKit.getRootClassPath());
        System.out.println("getWebRootPath=" + PathKit.getWebRootPath());
    }
}
