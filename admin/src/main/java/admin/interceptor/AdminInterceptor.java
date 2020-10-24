package admin.interceptor;

import admin.config.Security;
import com.jiao.order.parse.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 */
public class AdminInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");
//        try {
//            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
//            String username = (String)request.getSession().getAttribute("loginUser");
//            if(username!=null){
//                return true;
//            }
//            response.sendRedirect(request.getContextPath()+"/admin/login.html");
//            return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
//        //如果设置为true时，请求将会继续执行后面的操作

        /**
         * handler保存了本次请求的controller也就是接口方法的一些信息，如类，方法，参数等
         * 如果是一次静态资源的请求则该handler不应该是HandlerMethod的实现类
         * 判断是否是一个正常的接口，如果是则进行鉴权操作，不是则直接放行
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;// 把handler强转为HandlerMethod
            // 从handlerMethod中获取本次请求的接口方法对象然后判断该方法上是否标有我们自定义的注解@Security
            Security security = handlerMethod.getMethod().getAnnotation(Security.class);
            if (null != security) {
                // 对用户进行鉴权
                String username = (String)request.getSession().getAttribute("loginUser");
                if(username!=null){
                    return true;
                }
                try {
                    response.sendRedirect(request.getContextPath()+"/admin/login.html");
                    return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}

