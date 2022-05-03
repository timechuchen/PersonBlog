package ltd.chuchen.handler;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.utils.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author chuchen
 * @Date 2022/3/21
 * @Description 权限不足异常出处理
 */
@Component
public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler  {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.create( HttpStatus.FORBIDDEN.value(),"您的权限不足，无法进行此操作");
        out.write(JacksonUtil.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
