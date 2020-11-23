package com.myProject.EndOfTheJob.config;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.myProject.EndOfTheJob.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/index").permitAll()
				// 对静态文件统一进行放行comments
				.antMatchers("/css/**").permitAll().antMatchers("/img/**").permitAll().antMatchers("/js/**").permitAll()
				.antMatchers("/fonts/**").permitAll().antMatchers("/checkRepeatName").permitAll()
				.antMatchers("/addusers").permitAll().antMatchers("/register/**").permitAll().antMatchers("/login/**")
				.permitAll().antMatchers("/shopping/**").hasRole("common").antMatchers("/details/**").permitAll()
				.antMatchers("/comments/**").hasRole("common")
				.antMatchers("/uploadFile").hasRole("admin")
				.antMatchers("/admin/**").hasRole("admin").anyRequest().authenticated();
//		 自定义用户登录控制
		/*
		 * http.formLogin() .loginPage("/login").permitAll()
		 * .usernameParameter("username").passwordParameter("password")
		 * .defaultSuccessUrl("/index") .failureUrl("/login?error");
		 */

		// 2、自定义用户登录控制
		http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, Authentication authentication)
							throws IOException, ServletException {
						String url = httpServletRequest.getParameter("url");
						// 获取被拦截的原始访问路径
						RequestCache requestCache = new HttpSessionRequestCache();
						SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
						if (savedRequest != null) {
							// 如果存在原始拦截路径，登录成功后重定向到原始访问路径
							httpServletResponse.sendRedirect(savedRequest.getRedirectUrl());
						} else if (url != null && !url.equals("")) {
							// 跳转到之前所在页面
							URL fullURL = new URL(url);
							httpServletResponse.sendRedirect(fullURL.getPath());
						} else {
							// 直接登录的用户，根据用户角色分别重定向到后台首页和前台首页
							Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
							boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_admin"));
							if (isAdmin) {
								httpServletResponse.sendRedirect("/admin");
							} else {
								httpServletResponse.sendRedirect("/index");
							}
						}
					}
				})
				// 用户登录失败处理
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, AuthenticationException e)
							throws IOException, ServletException {
						// 登录失败后，取出原始页面url并追加在重定向路径上
						String url = httpServletRequest.getParameter("url");
						httpServletResponse.sendRedirect("/login?error&url=" + url);
					}
				});
		// 5、针对访问无权限页面出现的403页面进行定制处理
		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
					AccessDeniedException e) throws IOException, ServletException {
				// 如果是权限访问异常，则进行拦截到指定错误页面
				RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/errorPage/error_403");
				dispatcher.forward(httpServletRequest, httpServletResponse);
			}
		});
		// 自定义用户退出

		http.logout().logoutUrl("/mylogout").logoutSuccessUrl("/index");

//	  定制remember_me记住我功能
		http.rememberMe().rememberMeParameter("rememberme").tokenValiditySeconds(200)
				.tokenRepository(tokenRepositoryImpl());
	}

//持久化token存储
	@Bean
	public JdbcTokenRepositoryImpl tokenRepositoryImpl() {
		JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
		jr.setDataSource(dataSource);
		return jr;
	}

}
