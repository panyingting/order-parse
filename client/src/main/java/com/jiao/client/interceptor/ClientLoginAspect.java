package com.jiao.client.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Aspect
//@Component
//@Order(1)
public class ClientLoginAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientLoginAspect.class);

//
//    @Around("within(@org.springframework.stereotype.Controller * || @org.springframework.web.bind.annotation.RestController *) && @annotation(token)")
//    public Object verify(ProceedingJoinPoint pjp, TokenAnnotation token) throws Throwable {
//        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = requestAttr.getResponse();
//        HttpServletRequest request = requestAttr.getRequest();
//
//
//        //父级域名Token的头
//        String tokenHeader = request.getHeader(HttpHeaders.X_ROOT_AUTH_TOKEN);
//        if (Strings.isNullOrEmpty(tokenHeader)) {
//            tokenHeader = CookieUtil.getCookie(request, HttpHeaders.X_ROOT_AUTH_TOKEN);
//        }
//
//        // 兼容
//        if (Strings.isNullOrEmpty(tokenHeader)) {
//            tokenHeader = request.getHeader(HttpHeaders.X_AUTH_TOKEN);
//            if (Strings.isNullOrEmpty(tokenHeader)) {
//                tokenHeader = CookieUtil.getCookie(request, HttpHeaders.X_AUTH_TOKEN);
//            }
//            LOGGER.info("api get tokenHeader from X-Auth-Token ,tokenHeader = {}", tokenHeader);
//        }
//
//        String paramsHeader = request.getHeader(HttpHeaders.X_STATIC_PARAMS);
//
//        if (token.requireAuth() && Strings.isNullOrEmpty(tokenHeader)) {
//            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "无效的请求,请先登录");
//            return null;
//        }
//
//        ServiceResponse<Token> tokenRes = apiTokenCommService.validateUserByTokenMode(tokenHeader, paramsHeader, TokenAnnotationMode.buildByAnnotation(token));
//        if (tokenRes.isFailure()) {
//            sendError(response, tokenRes.getCode(), tokenRes.getMsg());
//            return null;
//        } else {
//            request.setAttribute(Const.REQUEST_ATTR_TOKEN_USER, tokenRes.getData());
//        }
//
//        return pjp.proceed();
//    }
//
//
//    private void sendError(HttpServletResponse response, int code, String msg) {
//        if (response != null) {
//            try {
//                response.sendError(code, msg);
//            } catch (IOException e) {
//                //ignore it
//            }
//        }
}
