package com.jiao.client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jiao.client.interceptor.ClientLoginAspect;
import com.jiao.order.parse.common.Const;
import com.jiao.order.parse.common.EncryptUtil;
import com.jiao.order.parse.common.ResultEnum;
import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.coupon.CouponAccountEntity;
import com.jiao.client.interceptor.ClientInterceptor;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import com.jiao.order.parse.repository.CouponAccountRepository;
import com.jiao.order.parse.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static com.jiao.order.parse.common.Const.REQ_PHONE_NUM;

@RestController
@RequestMapping("/coupon")
public class ClientCouponController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCouponController.class);


    @Resource
    private CouponAccountRepository couponAccountRepository;
    @Resource
    private CouponRepository couponRepository;

    @RequestMapping("/draw")
    public WebResult drawCoupon(HttpServletRequest request, @RequestParam("couponId") int couponId){

        Object phoneNum = request.getSession().getAttribute(REQ_PHONE_NUM);

        if( phoneNum == null){
            return WebResult.failResult("您好，请先登录");
        }
        if(couponId <= 0){
            return WebResult.failResult("请求参数错误");
        }

        List<CouponAccountEntity> couponAccountEntityList = couponAccountRepository.findByUserNameAndCouponId(phoneNum.toString(), couponId);

        if(couponAccountEntityList.size() > 0){
            return WebResult.failResult(ResultEnum.REPEAT_IN);
        }
        CouponEntity couponEntity = couponRepository.getById(couponId);
        List<Integer> money = JSON.parseObject( couponEntity.getDesc(), new TypeReference<List<Integer>>(){});

        Random random = new Random();
        int randomInt = random.nextInt(money.size());
        LOGGER.info("随机数为：{}", randomInt);
        return WebResult.sucessResult(money.get(randomInt));
    }


    @RequestMapping("/save")
    public WebResult saveCoupon(HttpServletRequest request, HttpSession session, CouponAccountEntity couponAccountEntity){
        Pattern pattern = Pattern.compile(Const.PHONE_NUM_PATTERN_STR);
        Object phoneObj = session.getAttribute(REQ_PHONE_NUM);
        if( phoneObj == null || !pattern.matcher(phoneObj.toString()).matches()){
            return WebResult.failResult("您好，请先登录");
        }

        String phone = phoneObj.toString();

        if(couponAccountEntity.getFaceValue() <= 0){
            return WebResult.failResult("系统参数错误，请刷新后再试");
        }

        CouponEntity couponEntity = couponRepository.getById((int)couponAccountEntity.getCouponId());
        if(couponEntity == null){
            return WebResult.failResult("优惠券信息为空，请刷新后再试");
        }
        couponEntity.setDeliveryNum( couponEntity.getDeliveryNum() + 1);
        couponAccountEntity.setUserName(phone);
        couponAccountEntity.setPhone( phone);
        couponAccountEntity.setCouponName( couponEntity.getName() );
        couponAccountEntity.setEffectTime( couponEntity.getLimitStartTime());
        couponAccountEntity.setExpirationTime( couponEntity.getLimitEndTime());
        couponAccountEntity.setGetTime( System.currentTimeMillis());

        couponRepository.save(couponEntity);
        couponAccountRepository.save(couponAccountEntity);

        return WebResult.sucessResult();
    }

    @RequestMapping("/getMyCoupons")
    public WebResult getCoupon(HttpSession session, CouponAccountEntity couponAccountEntity ){
        Pattern pattern = Pattern.compile(Const.PHONE_NUM_PATTERN_STR);
        Object phoneNum = session.getAttribute(REQ_PHONE_NUM);
        if( phoneNum == null || !pattern.matcher(phoneNum.toString()).matches()){
            return WebResult.failResult("您好，请先登录");
        }

        List<CouponAccountEntity> couponAccountEntityList = couponAccountRepository.findByUserName(phoneNum.toString());

        return WebResult.sucessResult(couponAccountEntityList);
    }

    @RequestMapping("/getCouponById")
    public WebResult getCoupon( String encodeId ){
        try {
            if( !StringUtils.isEmpty(encodeId)){
                String idStr = EncryptUtil.getInstance().XORdecode(encodeId);
                if( StringUtils.isEmpty(idStr)){
                    return WebResult.failResult("参数错误，请稍后再试");
                }
                CouponEntity couponEntity = couponRepository.getById( Integer.parseInt(idStr));
                if(couponEntity == null){
                    return WebResult.failResult("参数错误，获取不到活动劵信息");
                }
                return WebResult.sucessResult(couponEntity);
            }else {
                List<CouponEntity> couponEntityList = couponRepository.findByIsDefault(1);
                if(couponEntityList.size() > 0){
                    return WebResult.sucessResult(couponEntityList.get(0));
                }
                return WebResult.failResult("找不到优惠券信息");
            }
        }catch (Exception ex){
            LOGGER.error("获取优惠券信息异常, encodeId:{}", encodeId, ex);
            return WebResult.failResult("参数错误，请稍后再试");
        }
    }

    @RequestMapping("/useCoupon")
    public WebResult useCoupon( @RequestParam("couponAccountId") int couponAccountId ){
        try {
            CouponAccountEntity entity = couponAccountRepository.getById(couponAccountId);

            if(entity == null){
                return WebResult.failResult("参数错误，获取不到活动劵信息");
            }
            entity.setRemark("用户已点击使用");
            couponAccountRepository.save(entity);
            return WebResult.sucessResult();
        }catch (Exception ex){
            LOGGER.error("获取优惠券信息异常, couponAccountId:{}", couponAccountId, ex);
            return WebResult.failResult("参数错误，请稍后再试");
        }
    }


}
