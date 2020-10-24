package com.jiao.client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jiao.order.parse.common.ResultEnum;
import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.coupon.CouponAccountEntity;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import com.jiao.order.parse.repository.CouponAccountRepository;
import com.jiao.order.parse.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

import static com.jiao.order.parse.common.Const.REQ_PHONE_NUM;

@Component
public class CouponHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponHandler.class);


    @Resource
    private CouponAccountRepository couponAccountRepository;
    @Resource
    private CouponRepository couponRepository;

    public WebResult doDrawCoupon(HttpServletRequest request, int couponId){

        return WebResult.sucessResult( );
    }
}
