package com.baizhan.basic.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baizhan.basic.pojo.BasicUser;

public class SecurityUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	//将自定义的当前用户的信息放在返回认证对象里面
	private BasicUser user;


	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	
	

	public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			boolean enabled,BasicUser user) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.user=user;
	}
	
	public BasicUser getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
