package com.jiao.client.controller.weichat;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
@ResponseBody
public class WxController {

    private static  Logger LOGGER = LoggerFactory.getLogger(WxController.class);


    @RequestMapping("/callback")
    public String auth(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String redirectUrl = request.getParameter("redirect");
        try {

            String res = getOpendId(code);
            LOGGER.info("openId from wx res:" + res);
            JSONObject json = JSONObject.parseObject(res);
            String openid = json.getString("openid");
            if (!StringUtils.isEmpty(openid)) {
                //用户信息存储到cookie
                Cookie userCookie = new Cookie("openid", openid);
                userCookie.setMaxAge(-1);   //设置为“0”或负值时，关闭浏览器才会清除cookie
                userCookie.setPath("/");
                response.addCookie(userCookie);
            }
        } catch (Exception e) {
            LOGGER.error("程序错误", e);
            return "web/common/error";
        }
        if (StringUtils.isEmpty(redirectUrl)) {
            //redirect 地址为空的话默认跳转到主页
            redirectUrl = "/wow/main";
        }
        return "redirect:" + redirectUrl;
    }

    private static String getOpendId(String code) throws IOException {


        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppId&secret=AppSecret&code=CODE&grant_type=authorization_code";
        url = url.replace("AppId", Constants.APPID)
                .replace("AppSecret", Constants.APPSECRET)
                .replace("CODE", code);


        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        StringBuilder result = new StringBuilder();
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line ;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        LOGGER.info("微信result：{}", result.toString());
        return result.toString();

    }

    @RequestMapping("/authToken")
    public void login(HttpServletResponse response) throws IOException {

        //这里是回调的url
        String redirect_uri = URLEncoder.encode("http://回调页面的路径", "UTF-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=APPID" +
                "&redirect_uri=REDIRECT_URI"+
        "&response_type=code" +
                "&scope=SCOPE" +
                "&state=123#wechat_redirect";
        String newStr = url.replace("APPID",Constants.APPID).replace("REDIRECT_URL",redirect_uri).replace("SCOPE","snsapi_userinfo");
        response.sendRedirect(newStr);

        LOGGER.info("重定向:{}", newStr);
    }

}