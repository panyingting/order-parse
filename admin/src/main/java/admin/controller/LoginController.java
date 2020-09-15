package admin.controller;

import com.jiao.order.parse.common.Const;
import com.jiao.order.parse.common.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import static com.jiao.order.parse.common.Const.REQ_PHONE_NUM;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @RequestMapping("/user/login")
    @ResponseBody
    public WebResult adminLogin( @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "admin123".equals(password)) {
            session.setAttribute("loginUser", username);
            return WebResult.sucessResult();
        } else {
            return WebResult.failResult("用户名或密码错误");
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/login.html";
    }
}
