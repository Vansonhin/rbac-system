package com.baizhan.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.baizhan.basic.security.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//配置校验
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//请求的设置,必须要禁止csrf。否则，请求必须要带token参数
		http.csrf().disable().authorizeRequests()
		  .antMatchers("/css/**","/js/**","/images/**","/lib/**").permitAll()
		  .anyRequest().authenticated()
		.and()
		//配置登录出来
		.formLogin().loginPage("/login").permitAll()
		.and()
		//配置注销处理   ,修改注销后的跳转路径，否则路径带参数，会导致登录页面的校验标签，当次失效。                                   
		.logout().logoutSuccessUrl("/login").deleteCookies("JSESSIONID").clearAuthentication(true);
	}

	

	//配置授权
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		
		//PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//UserDetails userDetails = User.withUsername("admin").password(encoder.encode("123456")).authorities("USER_ADD").build();

		//return new InMemoryUserDetailsManager(userDetails);
		return new SecurityUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
