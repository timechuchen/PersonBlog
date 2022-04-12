package ltd.chuchen.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import ltd.chuchen.entity.Admin;
import ltd.chuchen.entity.User;
import ltd.chuchen.utils.JWTUtil;
import ltd.chuchen.utils.RedisUtil;
import ltd.chuchen.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author chuchen
 * @Date 2022/3/21
 * @Description 解析 Token 过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        //后台管理路径外的请求直接跳过
        if (!request.getRequestURI().startsWith("/api/admin")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        String userId;
        try {
            DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
            userId = tokenInfo.getClaim("id").asString();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从 redis 中获取用户信息
        Admin o = (Admin) redisUtil.get(userId);
        if(Objects.isNull(o)) {
            throw new RuntimeException("登陆已过期");
        }
        //将用户信息存入 SecurityContextHolder
        // 获取权限信息封装到 Authentication 中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(o,null,o.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
