package admin.config;

import java.lang.annotation.*;

/**
 * @Created with IDEA
 * @author: bobinghua
 * @see:
 * @description:
 * @Date: 2019/5/10
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})// 让该注解可以注解在方法和类上
public @interface Security {
}
