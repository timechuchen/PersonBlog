package ltd.chuchen.handler;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.utils.JacksonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author chuchen
 * @Date 2022/3/21
 * @Description 未登录 拒绝访问
 */
@Component
public class MyAuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.create(401, "未登录，请先登录");
        out.write(JacksonUtil.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
