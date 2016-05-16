package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import win.yulongsun.jfinal_club.model.Bill;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

/**
 * Created by yulongsun on 2016/5/15.
 */
public class BillController extends Controller {
    Response response;

    /*添加账单*/
    public void addBill() {
        String  user_id        = getPara("user_id");
        String  user_order_num = getPara("user_order_num");
        String  user_profit    = getPara("user_profit_in");
        String  user_sell_in   = getPara("user_sell_in");
        String  user_c_id      = getPara("user_c_id");
        boolean isNull         = ValidateUtils.validatePara(user_sell_in, user_id, user_order_num, user_profit, user_c_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Bill bill = new Bill();
        bill.setOrderNum(Integer.valueOf(user_order_num));
        bill.setProfitIn(Double.valueOf(user_profit));
        bill.setCId(Integer.valueOf(user_c_id));
        bill.setSellIn(Double.valueOf(user_sell_in));
        bill.setCreateBy(Integer.valueOf(user_id));
        boolean isSave = bill.save();
        if (isSave) {
            response.setSuccessResponse();
        } else {
            response.setFailureResponse(Response.ErrorCode.ADD_FAILURE);
        }
        renderJson(response);
    }

}
