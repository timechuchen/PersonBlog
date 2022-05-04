package ltd.chuchen.annotation;

import ltd.chuchen.enums.VisitBehavior;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问日志注解
 */
@Target(ElementType.METHOD) //该注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) //声明这个注解的生命周期
public @interface VisitLogger {
    /**
     * 访问行为枚举
     */
    VisitBehavior value() default VisitBehavior.UNKNOWN;
}
