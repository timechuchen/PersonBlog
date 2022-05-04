package ltd.chuchen.aspect;

import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.VisitLog;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.VisitLogService;
import ltd.chuchen.utils.AopUtil;
import ltd.chuchen.utils.JacksonUtil;
import ltd.chuchen.utils.RedisUtil;
import ltd.chuchen.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description AOP 访问日志
 */
@Component
@Aspect
public class VisitLogAspect {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    VisitLogService visitLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(visitLogger)")
    public void logPointcut(VisitLogger visitLogger) {
    }

    @Around(value = "logPointcut(visitLogger)", argNames = "joinPoint,visitLogger")
    public Object logAround(ProceedingJoinPoint joinPoint,VisitLogger visitLogger) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Result result = (Result) joinPoint.proceed();
        int times = (int) (System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        //获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //校验访客标识码
        String identification = checkIdentification(request);
        //记录访问日志
        VisitLog visitLog = handleLog(joinPoint, visitLogger, request, times, identification);
        visitLogService.saveVisitLog(visitLog);
        return result;
    }

    private String checkIdentification(HttpServletRequest request) {
        String identification = request.getHeader("identification");
        if(identification == null) {
            //请求头中没有identification，一般来说是不会的
            identification = "00000000";
        }else {
            //校验Redis中是否存在uuid
            boolean redisHas = redisUtil.sHasKey(RedisKeyConstant.IDENTIFICATION_SET, identification);
            if(!redisHas) {
                redisUtil.sSet(RedisKeyConstant.IDENTIFICATION_SET,identification);
            }
        }
        return identification;
    }

    private VisitLog handleLog(ProceedingJoinPoint joinPoint, VisitLogger visitLogger, HttpServletRequest request, int times, String identification) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        Map<String, Object> requestParams = AopUtil.getRequestParams(joinPoint);
        VisitLog log = new VisitLog();
        log.setUuid(identification).setUri(uri).setTimes(times).setMethod(method).setBehavior(visitLogger.value().getBehavior()).setContent(visitLogger.value().getContent()).setCreateTime(new Date());
        log.setParam(StringUtils.substring(JacksonUtil.writeValueAsString(requestParams), 0, 2000));
        return log;
    }
}
