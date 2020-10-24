package com.jiao.client.controller;

import com.jiao.order.parse.common.Const;
import com.jiao.order.parse.common.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import static com.jiao.order.parse.common.Const.REQ_PHONE_NUM;

@Controller
public class ClientLoginController {

    @RequestMapping("/user/login")
    @ResponseBody
    public WebResult login( @RequestParam(REQ_PHONE_NUM) String phoneNum, HttpSession session) {
        Pattern pattern = Pattern.compile(Const.PHONE_NUM_PATTERN_STR);
        if (!StringUtils.isEmpty(phoneNum) && pattern.matcher(phoneNum).matches()) {
            session.setAttribute("phoneNum", phoneNum);
            return WebResult.sucessResult();
        } else {
            return WebResult.failResult("用户名或密码错误");
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/admin_login.html";
    }
}
