package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import win.yulongsun.jfinal_club.model.Order;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

/**
 * Created by yulongsun on 2016/5/15.
 */
public class OrderController extends Controller {
    Response response;

    public void addOrder() {
        response = new Response();
        String  user_c_id = getPara("user_c_id");
        String  user_id   = getPara("user_id");
        String  card_id   = getPara("card_id");
        String  num       = getPara("num");
        boolean isNull    = ValidateUtils.validatePara(user_c_id, user_id, card_id, num);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Order order = new Order();
        order.setCId(Integer.valueOf(user_c_id));
        order.setCardId(Integer.valueOf(card_id));
        order.setNum(Double.valueOf(num));
        order.setCreateBy(Integer.valueOf(user_id));
        boolean isSave = order.save();
        if (isSave) {
            response.setSuccessResponse(Response.ErrorCode.ADD_SUCCESS);
        } else {
            response.setFailureResponse(Response.ErrorCode.ADD_FAILURE);
        }
        renderJson(response);
    }

    public void listOrder() {
        String  user_c_id = getPara("user_c_id");
        String  page_num  = getPara("page_num");
        String  page_size = getPara("page_size");
        boolean isNull    = ValidateUtils.validatePara(user_c_id, page_num, page_size);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Page<Order> orderPage = Order.dao.paginateByCId(user_c_id, Integer.valueOf(page_num), Integer.valueOf(page_size));
        response.setSuccessResponse(orderPage.getList());
        renderJson(response);

    }
}
