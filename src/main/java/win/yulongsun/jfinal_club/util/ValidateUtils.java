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

    /*验证手机格式*/
    public static boolean isMobilePattern(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (StringUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

}
