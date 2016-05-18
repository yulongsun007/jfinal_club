package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import win.yulongsun.jfinal_club.model.Member;
import win.yulongsun.jfinal_club.model.Order;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

/**
 * Created by yulongsun on 2016/5/15.
 * 账单
 */
public class OrderController extends Controller {
    Response response;

    /*添加账单*/
    public void addOrder() {
        response = new Response();
        String  user_c_id     = getPara("user_c_id");
        String  user_id       = getPara("user_id");
        String  card_id       = getPara("card_id");
        String  num           = getPara("num");
        boolean isNull        = ValidateUtils.validatePara(user_c_id, user_id, card_id, num);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findByCardId(card_id,user_c_id);
        if (member != null) {
            Double money  = member.getMoney();
            Double result = money - Double.parseDouble(num);
            if (result < 0) {//余额不足
                response.setFailureResponse(Response.ErrorCode.MONEY_INADEQUATE);
            } else {
                member.setMoney(result);
                member.update();
                //计入
                Order order = new Order();
                order.setCId(Integer.parseInt(user_c_id));
                order.setCardId(Integer.parseInt(card_id));
                order.setNum(Double.parseDouble(num));
                order.setCreateBy(Integer.parseInt(user_id));
                order.save();
                response.setSuccessResponse();
            }
        } else {//用户不存在
            response.setFailureResponse(Response.ErrorCode.CARD_NULL);
        }
        renderJson(response);
    }

    /*查询历史*/
    public void listOrder() {
        response= new Response();
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
