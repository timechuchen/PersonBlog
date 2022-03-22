package ltd.chuchen.config;

import ltd.chuchen.filter.JwtAuthenticationTokenFilter;
import ltd.chuchen.handler.MyAccessDeniedHandlerImpl;
import ltd.chuchen.handler.MyAuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: Spring Security配置类
 * @Author: chuchen
 * @Date: 2022-03-19
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAuthenticationEntryPointImpl myAuthenticationEntryPoint;
	@Autowired
	private MyAccessDeniedHandlerImpl myAccessDeniedHandler;
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//禁用 csrf 防御
				.csrf().disable()
				//基于Token，不创建会话
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/api/admin/login").anonymous()
				.antMatchers(HttpMethod.GET, "/api/admin/**").hasAnyAuthority("admin", "visitor")
				.antMatchers("/api/admin/**").hasAuthority("admin")
				//除了上面的请求全部放行
				.anyRequest().permitAll();
		//添加过滤器
		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		//添加异常处理器
		http.exceptionHandling()
				//认证失败处理器
				.authenticationEntryPoint(myAuthenticationEntryPoint)
				//授权失败处理器
				.accessDeniedHandler(myAccessDeniedHandler);
	}
}
