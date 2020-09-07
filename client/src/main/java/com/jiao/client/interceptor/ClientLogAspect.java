package com.jiao.client.interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName: LogAspect
 * @Description: 日志记录AOP实现
 *
 */
@Aspect
@Component
@Order(1)
public class ClientLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String requestPath = null ; // 请求地址
    private String userName = null ; // 用户名
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String ip = null;//customer ip


    /**
     *
     * 方法调用前触发 记录开始时间
     */
    @Before("execution(* com.jiao.order.parse.controller..*.*(..))")
    public void doBeforeInServiceLayer() {
        startTimeMillis = System.currentTimeMillis();
    }

    /**
     * 方法调用后触发
     *  记录结束时间
     */
    @After("execution(* com.jiao.order.parse.controller..*.*(..))")
    public void doAfterInServiceLayer() {
        endTimeMillis = System.currentTimeMillis();
        this.printOptLog();
    }

    /**
     * 环绕触发
     */
    @Around("execution(* com.jiao.order.parse.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        /*
         * 1.获取request信息
         * 2.根据request获取session
         * 3.从session中取出登录用户信息
         */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();

        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<>();
        try{
            Object result = pjp.proceed();// result的值就是被拦截方法的返回值
            outputParamMap.put("result", result);

            logger.info("请求信息为：{}", inputParamMap);
            return result;
        }catch (Exception ex){
            logger.error("请求出现异常, inputParamMap:{}", inputParamMap, ex);
            throw ex;
        }
    }

    /**
     *
     * 2014年11月2日 下午4:47:09
     */
    private void printOptLog() {
        try {
            String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
            logger.info("{ user：" + userName + " ip:" + ip
                    + ",url：" + requestPath + ",op_time：" + optTime + ",pro_time：" + (endTimeMillis - startTimeMillis) + "ms,"
                    + " param：" + JSON.toJSONString(inputParamMap) + "," + " result：" + JSON.toJSONString(outputParamMap) + "}");
        }
        catch (Exception ex)
        {
            logger.error("request log error",ex);
        }

    }
}