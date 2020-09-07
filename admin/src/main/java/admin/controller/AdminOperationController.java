package admin.controller;


import com.alibaba.fastjson.JSON;
import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import com.jiao.order.parse.repository.CouponAccountRepository;
import com.jiao.order.parse.repository.CouponRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/admin")
public class AdminOperationController {

    @Resource
    private CouponAccountRepository couponAccountRepository;

    @Resource
    private CouponRepository couponRepository;



}
