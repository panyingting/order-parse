package com.jiao.client.interceptor;

import com.jiao.client.controller.weichat.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OAuth2Interceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Interceptor.class);

    // 微信授权地址
    private static String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    // 微信授权方式 :应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
    // snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，
    // 即使在未关注的情况下，只要用户授权，也能获取其信息
    private static String SCOPE = "snsapi_userinfo";
    // private static String SCOPE = "snsapi_base";
    private static String REDIRECT_URI = "http://336u40w310.qicp.vip/wx/callback";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();

        LOGGER.info("进入拦截器请求 cookies:{}", cookies);
        String openid = "";
        if (cookies != null) {
            //这里是根据cookie的key获取value
            //调用ReadCookies 方法取值

            Map<String,Cookie> cookieMap = ReadCookies(request);

            if(cookieMap.containsKey("openid")){  //cookieName 为cookie名称

                Cookie cookie = (Cookie)cookieMap.get("openid");

                System.out.println("cookie="+cookie.getValue());

                openid = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(openid)) {
            LOGGER.info("###### wechat auth login !");
            String queryString = request.getQueryString();
            String redirectUrl = "";
            if(StringUtils.isEmpty(queryString)){
                redirectUrl = request.getRequestURL() + "?" + request.getQueryString();
            }else {
                redirectUrl = URLEncoder.encode( "http://336u40w310.qicp.vip/wx/callback", "utf-8");
            }
            String url = authUrl.replace("APPID", Constants.APPID).replace("SCOPE", SCOPE).replace("REDIRECT_URI", redirectUrl);
            LOGGER.info("url:{}", url);
            response.sendRedirect(url);
            return false;
        }

        return super.preHandle(request, response, handler);
    }
    //微信授权获取登录凭证code接口

    private static Map<String,Cookie> ReadCookies(HttpServletRequest request){  //私有静态方法

        Map<String,Cookie> cookieMap =new HashMap<String,Cookie>();

        Cookie[] cookies = request.getCookies();

        if(null!=cookies){

            for(Cookie cookie : cookies){

                cookieMap.put(cookie.getName(),cookie);

            }

        }

        return cookieMap;

    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
