package ltd.chuchen.aspect;

import ltd.chuchen.annotation.OperationLogger;
import ltd.chuchen.entity.OperationLog;
import ltd.chuchen.service.OperationLogService;
import ltd.chuchen.utils.AopUtil;
import ltd.chuchen.utils.JacksonUtil;
import ltd.chuchen.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description AOP 操作日志
 */
@Component
@Aspect
public class OperationLogAspect {

    @Autowired
    private OperationLogService operationLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(operationLogger)")
    public void logPointcut(OperationLogger operationLogger) {
    }

    /**
     * 配置环绕通知
     */
    @Around(value = "logPointcut(operationLogger)", argNames = "joinPoint,operationLogger")
    public Object logAround(ProceedingJoinPoint joinPoint, OperationLogger operationLogger) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Object result = joinPoint.proceed();
        int times = (int) (System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        OperationLog operationLog = handleLog(joinPoint, operationLogger, times);
        boolean b = operationLogService.saveOperationLog(operationLog);
        if(!b){
            throw new Exception("保存操作日志失败");
        }
        return result;
    }

    private OperationLog handleLog(ProceedingJoinPoint joinPoint, OperationLogger operationLogger, int times) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String username = "初晨";
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String description = operationLogger.value();
        String userAgent = request.getHeader("User-Agent");
        return new OperationLog(username, uri, method, description, times, userAgent);
    }
}
