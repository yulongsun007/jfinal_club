package win.yulongsun.jfinal_club.util;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.util.Random;

//阿里大鱼验证码工具类
public class DayuSMSUtils {

    // private static final String HTTP = "http://gw.api.tbsandbox.com/router/rest";
    private static final String HTTP       = "https://eco.taobao.com/router/rest";
    // private static final String API_KEY = "1023311491";
    // private static final String APP_SECRET = "sandbox064a98abe3ebfea03825aba60";
    private static final String API_KEY    = "23311491";
    private static final String APP_SECRET = "c5ce919064a98abe3ebfea03825aba60";

    public interface SmsType {
        int REGISTER_CODE = 0;
        int LOGIN_CODE    = 1;
    }


    /*修改密码验证码-SMS_1535002*/
    public static BizResult sendSms(String phone, int code) throws ApiException {
        TaobaoClient                     client = new DefaultTaobaoClient(HTTP, API_KEY, APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req    = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        //  验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。

        String content = "{\"code\":\"" + code + "\",\"product\":\"健康会所\"}";
        req.setSmsParamString(content);
        req.setRecNum(phone);
        req.setSmsFreeSignName("变更验证");
        req.setSmsTemplateCode("SMS_1535002");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        rsp = client.execute(req);
        BizResult result = rsp.getResult();
        return result;
    }

//    //暂时没用到
//    public static boolean sendSMS(Coupn coupn) {
//        try {
//            Integer merId = coupn.getMerId();
//            Mercht mercht = Mercht.dao.findById(merId);
//            String phone = mercht.getPhone();
//            // 该券没有对应的商户
//            if (StringUtils.isNullOrEmpty(phone)) {
//                return false;
//            }
//            TaobaoClient client = new DefaultTaobaoClient(HTTP, API_KEY, APP_SECRET);
//            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//            req.setSmsType("normal");
//            req.setSmsFreeSignName("身份验证");
//            // 尊敬的用户，您提交的编号为${couId}的{name} {result},审核时间{chktime}
//            if (coupn.getIsChk() == 1) {// 审核成功
//                String smsContent = "{\"couId\":\"" + coupn.getCouId() + "\",\"name\":\"" + coupn.getName()
//                        + "\",\"result\":\"审核成功\",\"chktime\":\"" + coupn.getChktime() + "\"}";
//                req.setSmsParamString(smsContent);
//            } else if (coupn.getIsChk() == 2) {// 审核失败
//                String smsContent = "{\"couId\":\"" + coupn.getCouId() + "\",\"name\":\"" + coupn.getName()
//                        + "\",\"result\":\"审核失败\",\"chktime\":\"" + coupn.getChktime() + "\"}";
//                req.setSmsParamString(smsContent);
//            }
//            // req.setRecNum(phone);
//            req.setRecNum("13750843926");
//            req.setSmsTemplateCode("SMS_1535007");
//            AlibabaAliqinFcSmsNumSendResponse rsp;
//            rsp = client.execute(req);
//            System.out.println(rsp.getBody());
//            String errorCode = rsp.getErrorCode();
//            if (errorCode.equals(0)) {
//                return true; // 短信发送成功
//            } else {
//                return false; // 短信发送失败
//            }
//        } catch (ApiException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    /* 发送短信验证码 */
    public static AlibabaAliqinFcSmsNumSendResponse sendSmsVerifyCode(String agentphone, int code, int sendType) {
        TaobaoClient                     client = new DefaultTaobaoClient(HTTP, API_KEY, APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req    = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");

        // 验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。
        String content = "{\"code\":\"" + code + "\",\"product\":\"淘券网\"}";
        req.setSmsParamString(content);
        req.setRecNum(agentphone);

        //判断短信发送类型
        if (sendType == SmsType.LOGIN_CODE) {
            req.setSmsFreeSignName("登录验证");
            req.setSmsTemplateCode("SMS_1535006");
        } else if (sendType == SmsType.REGISTER_CODE) {
            req.setSmsFreeSignName("注册验证");
            req.setSmsTemplateCode("SMS_1535004");
        }
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            String body = rsp.getBody();
            System.out.println("body==" + body.toString());
            return rsp;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
