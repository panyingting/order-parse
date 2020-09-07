package com.jiao.client.controller.weichat;

import jdk.nashorn.internal.runtime.GlobalConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Controller
@RequestMapping("/wx")
public class WeixinAction {

    private static Logger LOGGER = LoggerFactory.getLogger(WeixinAction.class);

    private static final String TOKEN = "Jiaojiao";


    @RequestMapping(value="/signature",method = RequestMethod.GET)
    public @ResponseBody
    String authGet(@RequestParam(value="signature",required=false) String signature,
                   @RequestParam("timestamp") String timestamp,
                   @RequestParam("nonce") String nonce,
                   @RequestParam("echostr") String echostr) throws Exception {

        LOGGER.info("微信回调验证接口, signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        if (!checkSignature( signature, timestamp, nonce)) {
            LOGGER.error("微信接入验证失败!");
            return null;
        }
        return echostr;
    }

    private static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { TOKEN, timestamp, nonce };
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md ;
        String tmpStr;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        return tmpStr.equals(signature.toUpperCase());
    }


    /**
     * 将字节数组转换为十六进制字符
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符
     *
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        return new String(tempArr);
    }

    //
//    @RequestMapping(value ="/signature2", method = RequestMethod.GET)
//    public void signature(
//            @RequestParam(value = "signature", required = true) String signature,
//            @RequestParam(value = "timestamp", required = true) String timestamp,
//            @RequestParam(value = "nonce", required = true) String nonce,
//            @RequestParam(value = "echostr", required = true) String echostr,
//            HttpServletResponse response) throws IOException {
//
//        LOGGER.info("微信回调验证接口, signature2:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
//
//        String[] values = { TOKEN, timestamp, nonce };
//        Arrays.sort(values); // 字典序排序
//        String value = values[0] + values[1] + values[2];
//        String sign = DigestUtils.shaHex(value);
//        PrintWriter writer = response.getWriter();
//        if (signature.equals(sign)) {// 验证成功返回ehcostr
//            writer.print(echostr);
//        } else {
//            writer.print("error");
//        }
//        writer.flush();
//        writer.close();
//    }

}


