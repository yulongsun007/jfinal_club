package win.yulongsun.jfinal_club.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import win.yulongsun.jfinal_club.model.Member;
import win.yulongsun.jfinal_club.model.User;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

/**
 * Created by yulongsun on 2016/5/5.
 */
public class MemberController extends Controller {
    Response response;

    public void listMember() {
        response = new Response();
        //会所id
        String  c_id      = getPara("c_id");
        int     page_num  = getParaToInt("page_num");
        int     page_size = getParaToInt("page_size");
        boolean isNull    = ValidateUtils.validatePara(c_id, String.valueOf(page_num), String.valueOf(page_size));
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Page<Member> memberPage = Member.dao.paginateByCId(c_id, page_num, page_size);
        response.setSuccessResponse(memberPage.getList());
        renderJson(response);
    }

    public void addMember() {

    }

    public void updateMember() {
        response = new Response();
        //会所id
        String  m_id   = getPara("m_id");
        boolean isNull = ValidateUtils.validatePara(m_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findById(m_id);

    }

    public void deleteMember() {
        response = new Response();
        //会所id
        String  m_id   = getPara("m_id");
        boolean isNull = ValidateUtils.validatePara(m_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findById(m_id);
        member.setIsEnable(0);
        boolean isUpdate = member.update();
        if (isUpdate) {
            response.setSuccessResponse(null);
        } else {
            response.setFailureResponse(Response.ErrorCode.DELETE_FAILURE);
        }
        renderJson(response);
    }

    /*充值*/
    public void recharge() {

    }

    /*消费*/
    public void consume() {

    }
}
