package com.jiao.client.controller.weichat;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXUtils {

    // 获取access_token的接口地址（GET） 限2000（次/天）
    private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    // 获取jsapi_ticket的接口地址（GET） 限2000（次/天）
    private final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
    // 获取openId的接口地址
    private final static String oauth_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 获取网页授权微信用户信息地址
    private final static String sns_userinfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESSTOKEN&openid=OPENID&lang=zh_CN";

    // 获取微信卡券api_ticket 接口地址
    private final static String api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESSTOKEN&type=wx_card";

    private static final Logger logger = LoggerFactory.getLogger(WXUtils.class);




//    /**
//     * 获取网页授权微信用户信息
//     */
//    public static JSONObject getUserInfo(String token, String openId) {
//        try {
//            // 通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。
//            String url = sns_userinfo_url.replace("ACCESSTOKEN", token).replace(
//                    "OPENID", openId);
//            String res = HttpUtils.doGet(url);
//            return JSONObject.fromObject(res);
//        } catch (Exception e) {
//            logger.error("用户授权获取用户信息失败！", e);
//        }
//        return null;
//    }


}
