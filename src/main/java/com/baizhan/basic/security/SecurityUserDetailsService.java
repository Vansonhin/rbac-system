package com.baizhan.basic.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.baizhan.basic.pojo.BasicPermission;
import com.baizhan.basic.pojo.BasicUser;
import com.baizhan.basic.service.BasicUserService;

public class SecurityUserDetailsService implements UserDetailsService{
	
	@Autowired
	private BasicUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//第一步：获得数据
		BasicUser user = userService.findUserByAccount(username);
		//第二步：封装数据
		List<BasicPermission> permissions = user.getRole().getPermissions();
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		for (BasicPermission permission : permissions) {
			//创建一个权限对象
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permission.getPermissionWord());
			authorities.add(authority);
		}
		//判断是否被禁用户
		boolean enabled=true;
		if (user.getUserStatus()!=1) {
			enabled=false;
		}

		SecurityUserDetails userDetails=new SecurityUserDetails(username, user.getUserPassword(), authorities, enabled,user);
		return userDetails;
	}

}
