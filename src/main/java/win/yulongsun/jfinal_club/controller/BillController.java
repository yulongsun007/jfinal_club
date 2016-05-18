package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.tools.corba.se.idl.constExpr.Or;
import win.yulongsun.jfinal_club.model.Bill;
import win.yulongsun.jfinal_club.model.Order;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

import java.util.*;

/**
 * Created by yulongsun on 2016/5/15.
 * 日结
 */
public class BillController extends Controller {
    Response response;

    /*添加日结*/
    public void addBill() {
        response = new Response();
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
        Bill billLast = Bill.dao.findFirst("SELECT * FROM bill where bill.c_id=? ORDER BY create_time DESC", user_c_id);
        if (billLast != null) {//有记录
            Date lastDate = billLast.getCreateTime();
            int  result   = new Date().getDate() - lastDate.getDate();//17 -16=1 17-17=0
            if (result == 0) {
                response.setFailureResponse(Response.ErrorCode.REQUESTED);
                renderJson(response);
                return;
            }
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


    /*当天的订单数*/
    public void listDay() {
        response = new Response();
        String  user_c_id = getPara("user_c_id");
        boolean isNull    = ValidateUtils.validatePara(user_c_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        //// TODO: 2016/5/18
        List<Order>                        orders = Order.dao.find("SELECT SUM(num), HOUR (create_time) FROM `order` WHERE TO_DAYS(create_time) = TO_DAYS(NOW()) and c_id =? GROUP BY HOUR (create_time);", user_c_id);
        ArrayList<HashMap<String, Object>> items  = new ArrayList<HashMap<String, Object>>();
        int                                j      = 0;
        int                                time   = -1;
        for (int i = 0; i < 24; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (j < orders.size()) {
                time = orders.get(j).get("HOUR (create_time)");
            }
            if (time != -1 && time == i) {
                map.put("num", orders.get(j).get("SUM(num)"));
                map.put("time", orders.get(j).get("HOUR (create_time)"));
                j++;
            } else {
                map.put("num", 0);
                map.put("time", i);
            }
            items.add(map);
        }
        response.setSuccessResponse(items);
        renderJson(response);
    }


    public void listMonth() {
        response = new Response();
        String  user_c_id = getPara("user_c_id");
        boolean isNull    = ValidateUtils.validatePara(user_c_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        List<Order>                        orders = Order.dao.find("select SUM(num),DAY(create_time) from `order` where date_format(create_time,'%Y-%m')=date_format(now(),'%Y-%m') and c_id =?  GROUP BY DAY(create_time);", user_c_id);
        ArrayList<HashMap<String, Object>> items  = new ArrayList<HashMap<String, Object>>();
        int                                j      = 0;
        int                                time   = -1;

        for (int i = 0; i < Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (j < orders.size()) {
                time = orders.get(j).get("DAY(create_time)");
            }
            if (time != -1 && time == i) {
                map.put("num", orders.get(j).get("SUM(num)"));
                map.put("time", orders.get(j).get("DAY(create_time)"));
                j++;
            } else {
                map.put("num", 0);
                map.put("time", i);
            }
            items.add(map);
        }
        response.setSuccessResponse(items);
        renderJson(response);
    }

    public void listQuarter() {
        response = new Response();
        String  user_c_id = getPara("user_c_id");
        boolean isNull    = ValidateUtils.validatePara(user_c_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        List<Order>                        orders = Order.dao.find("select SUM(num),MONTH(create_time) from `order` where  c_id =? and create_time between date_sub(now(),interval 3 month) and now() GROUP BY MONTH(create_time); ", user_c_id);
        ArrayList<HashMap<String, Object>> items  = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < orders.size(); i++) {
            HashMap<String, Object> map   = new HashMap<String, Object>();
            int                     month = orders.get(i).get("MONTH(create_time)");
            map.put("num", orders.get(i).get("SUM(num)"));
            map.put("time", month);
            items.add(map);
        }
        response.setSuccessResponse(items);
        renderJson(response);
    }
}
