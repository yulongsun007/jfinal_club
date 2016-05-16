package win.yulongsun.jfinal_club.controller;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import win.yulongsun.jfinal_club.model.Member;
import win.yulongsun.jfinal_club.util.Response;
import win.yulongsun.jfinal_club.util.ValidateUtils;

import java.util.List;

/**
 * Created by yulongsun on 2016/5/5.
 */
public class MemberController extends Controller {
    Response response;

    /*分页显示会员*/
    public void listMember() {
        response = new Response();
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

    /*添加会员*/
    @Before(POST.class)
    public void addMember() {
        response = new Response();
        UploadFile member_avatar      = getFile("member_avatar");
        String     member_name        = getPara("member_name");
        String     member_mobile      = getPara("member_mobile");
        String     member_rank        = getPara("member_rank");
        String     member_gender      = getPara("member_gender");
        String     member_card_id     = getPara("member_card_id");
        String     member_c_id        = getPara("member_c_id");
        String     member_addr        = getPara("member_addr");
        String     member_score       = getPara("member_score");
        String     member_operator_id = getPara("member_operator_id");
        boolean    isNull             = ValidateUtils.validatePara(member_avatar.getFileName(), member_name, member_mobile, member_rank, member_gender, member_card_id, member_c_id, member_addr, member_score, member_operator_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = new Member();
        member.setAvatar(member_avatar.getFileName());
        member.setName(member_name);
        member.setMobile(member_mobile);
        member.setRank(Integer.parseInt(member_rank));
        member.setGender(Integer.parseInt(member_gender));
        member.setCardId(member_card_id);
        member.setCId(Integer.parseInt(member_c_id));
        member.setAddr(member_addr);
        member.setScore(Integer.parseInt(member_score));
        member.setCreateBy(Integer.parseInt(member_operator_id));
        boolean isSave = member.save();
        if (isSave) {
            response.setSuccessResponse(Response.ErrorCode.ADD_SUCCESS);
        } else {
            response.setFailureResponse(Response.ErrorCode.ADD_FAILURE);
        }
        renderJson(response);
    }

    /*更新会员*/
    public void updateMember() {
        response = new Response();
        UploadFile member_id          = getFile("member_id");
        UploadFile member_avatar      = getFile("member_avatar");
        String     member_name        = getPara("member_name");
        String     member_mobile      = getPara("member_mobile");
        String     member_rank        = getPara("member_rank");
        String     member_gender      = getPara("member_gender");
        String     member_card_id     = getPara("member_card_id");
        String     member_club_id     = getPara("member_club_id");
        String     member_addr        = getPara("member_addr");
        String     member_score       = getPara("member_score");
        String     member_operator_id = getPara("member_operator_id");
        boolean    isNull             = ValidateUtils.validatePara(member_avatar.getUploadPath(), member_name, member_mobile, member_rank, member_gender, member_card_id, member_club_id, member_addr, member_score, member_operator_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findById(member_id);

    }

    /*删除会员*/
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
        response = new Response();
        String  member_mobile = getPara("member_mobile");
        String  member_money  = getPara("member_money");
        boolean isNull        = ValidateUtils.validatePara(member_mobile, member_money);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findByMobile(member_mobile);
        if (member != null) {
            member.setMoney(Double.valueOf(member_money));
            member.update();
        } else {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        }
        renderJson(response);
    }

    /*消费*/
    public void consume() {
        response = new Response();
        String  member_mobile = getPara("member_mobile");
        String  member_money  = getPara("member_money");
        boolean isNull        = ValidateUtils.validatePara(member_mobile, member_money);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        Member member = Member.dao.findByMobile(member_mobile);
        if (member != null) {
            Double money  = member.getMoney();
            Double result = money - Double.valueOf(member_money);
            if (result < 0) {
                response.setFailureResponse(Response.ErrorCode.MONEY_INADEQUATE);
            } else {
                member.setMoney(result);
                member.update();
                response.setSuccessResponse();
            }
        } else {
            response.setFailureResponse(Response.ErrorCode.USER_NULL);
        }
        renderJson(response);
    }

    /*查找会员*/
    public void queryUser() {
        String  member_name    = getPara("member_name");
        String  member_club_id = getPara("member_club_id");
        boolean isNull         = ValidateUtils.validatePara(member_name, member_club_id);
        if (isNull) {
            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
            renderJson(response);
            return;
        }
        List<Member> memberList = Member.dao.findByName(member_name, member_club_id);
        response.setSuccessResponse(memberList);
        renderJson(response);
    }
}
