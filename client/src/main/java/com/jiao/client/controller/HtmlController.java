package com.jiao.client.controller;

import com.jiao.order.parse.common.EncryptUtil;
import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import com.jiao.order.parse.repository.CouponAccountRepository;
import com.jiao.order.parse.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/commonPage")
public class HtmlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCouponController.class);

    @Resource
    private CouponRepository couponRepository;

    @RequestMapping("/redPack")
    public String RedPacketPage(){

        return null;
    }

    @RequestMapping("/couponRemindNum")
    public WebResult RedPacketPage(@RequestParam("encodeId") String encodeId ){
        try {
            String idStr = EncryptUtil.getInstance().XORdecode(encodeId);
            if( StringUtils.isEmpty(idStr)){
                return WebResult.failResult("参数错误，请稍后再试");
            }
            CouponEntity couponEntity = couponRepository.getById( Integer.parseInt(idStr));

            return WebResult.sucessResult(couponEntity.getInitNum() - couponEntity.getDeliveryNum());

        }catch (Exception ex){
            LOGGER.error("获取优惠券信息异常, encodeId:{}", encodeId, ex);
            return WebResult.failResult("参数错误，请稍后再试");
        }
    }



}
