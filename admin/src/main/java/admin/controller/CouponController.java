package admin.controller;

import admin.config.Security;
import com.alibaba.fastjson.JSON;
import com.jiao.order.parse.common.EncryptUtil;
import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.coupon.CouponAccountEntity;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import com.jiao.order.parse.repository.CouponAccountRepository;
import com.jiao.order.parse.repository.CouponRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {


    @Resource
    private CouponAccountRepository couponAccountRepository;

    @Resource
    private CouponRepository couponRepository;


    @RequestMapping("/getAllCouponAccountList")
    @Security
    public WebResult getAllCouponAccountList(@RequestParam("page") int page, @RequestParam("size") int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);

        return WebResult.sucessResult(couponAccountRepository.findAll(pageRequest));
    }


    @RequestMapping("/getAllCouponList")
    @Security
    public WebResult getAllCouponList(){
        List<CouponEntity> couponEntityList = couponRepository.findAll();
        return WebResult.sucessResult(couponEntityList);
    }


    @RequestMapping("/getCoupon")
    @Security
    public WebResult getAllCouponList( @RequestParam("id") int id){
        return WebResult.sucessResult(couponRepository.getById(id));
    }

    @RequestMapping("/deleteCoupon")
    @Security
    public WebResult deleteCoupon(CouponEntity couponEntity){

        if(couponEntity.getId() <= 0){
            return WebResult.failResult("参数错误");
        }
        couponRepository.deleteById(couponEntity.getId());
        return WebResult.sucessResult();
    }

    @RequestMapping("/deleteAccountCoupon")
    @Security
    public WebResult deleteAccountCoupon(CouponAccountEntity accountEntity){

        if(accountEntity.getId() <= 0){
            return WebResult.failResult("参数错误");
        }
        couponAccountRepository.deleteById(accountEntity.getId());
        return WebResult.sucessResult();
    }

    @RequestMapping("/addCoupon")
    @Security
    public WebResult addCoupon( CouponEntity couponEntity){

        if(couponEntity.getInitNum() <= 0){
            return WebResult.failResult("参数错误");
        }
        if(StringUtils.isEmpty(couponEntity.getDesc())){
            return WebResult.failResult("参数错误");
        }
        String [] arr = couponEntity.getDesc().split("[^0-9]+");

        couponEntity.setDesc(JSON.toJSONString(arr));
        couponRepository.save(couponEntity);
        couponEntity.setIdEncode(EncryptUtil.getInstance().XORencode(String.valueOf(couponEntity.getId())));
        couponRepository.save(couponEntity);
        return WebResult.sucessResult(couponEntity);
    }

}
