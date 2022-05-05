package ltd.chuchen.aspect;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.entity.ExceptionLog;
import ltd.chuchen.service.ExceptionLogService;
import ltd.chuchen.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 异常 AOP
 */
@Component
@Aspect
public class ExceptionLogAspect {

    @Autowired
    ExceptionLogService exceptionLogService;

    /**
     * 配置切入点
     */
    @Pointcut("execution(* ltd.chuchen.controller..*.*(..))")
    public void logPointcut() {
    }

    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        ExceptionLog log = handleLog(joinPoint, e);
        exceptionLogService.saveExceptionLog(log);
    }

    private ExceptionLog handleLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String userAgent = request.getHeader("User-Agent");
        String description = getDescriptionFromAnnotations(joinPoint);
        String error = StringUtils.getStackTrace(e);
        return new ExceptionLog(uri, method, description, error, userAgent);
    }

    private String getDescriptionFromAnnotations(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        OperationLogger operationLogger = method.getAnnotation(OperationLogger.class);
        if (operationLogger != null) {
            return operationLogger.value();
        }
        VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
        if (visitLogger != null) {
            return visitLogger.value().getBehavior();
        }
        return "";
    }
}
