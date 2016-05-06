package win.yulongsun.jfinal_club.util;

import com.alibaba.druid.util.StringUtils;

import java.util.List;


/**
 * Created by yulongsun on 2016/5/5.
 */
public class ValidateUtils {

    public static boolean validatePara(String... para) {
        boolean isNull = false;
        for (int i = 0; i < para.length; i++) {
            if (StringUtils.isEmpty(para[0])) {
                isNull = true;
            }
        }
        return isNull;
    }

    public static boolean isListEmpty(List list) {
        if (list == null && list.size() == 0) {
            return true;
        }
        return false;
    }
}
