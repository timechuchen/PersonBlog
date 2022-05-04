package ltd.chuchen.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description AOP工具类
 */
public class AopUtil {
    private static final Set<String> ignoreParams = new HashSet<>() {
        {
            add("jwt");
        }
    };

    /**
     * 获取请求参数
     * @param joinPoint  JoinPoint
     * @return Map
     */
    public static Map<String, Object> getRequestParams(JoinPoint joinPoint) {
        Map<String, Object> map = new LinkedHashMap<>();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (!isIgnoreParams(parameterNames[i]) && !isFilterObject(args[i])) {
                map.put(parameterNames[i], args[i]);
            }
        }
        return map;
    }

    /**
     * consider if the data is file, httpRequest or response
     *
     * @param o the data
     * @return if match return true, else return false
     */
    private static boolean isFilterObject(final Object o) {
        return o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof MultipartFile;
    }

    /**
     * 判断是否忽略参数
     *
     * @param params 参数
     */
    private static boolean isIgnoreParams(String params) {
        return ignoreParams.contains(params);
    }
}
