package com.baizhan.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.baizhan.basic.pojo.BasicUser;
import com.baizhan.basic.security.SecurityUserDetails;
import com.baizhan.basic.service.BasicUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BasicSettingController {
	
	@Autowired
	private BasicUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/settings")
	public String toSettings() {
		log.debug("--跳转到修改密码列表--");
		return "manager/setting";
	}
	
	@PostMapping(value = "/settingsSubmit")
	public String settingsSubmit(String sourcePassword ,String newPassword,String confirmPassword,HttpServletRequest request) {
		log.debug("--修改密码提交--");
		try {
			//第一步：判断原密码是否正确,SecurityContextHolder可以直接获得当前登录用户的信息
			SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			BasicUser user = userDetails.getUser();
			//判断明文密码，和密文密码是否对应
			boolean matches = passwordEncoder.matches(sourcePassword, user.getUserPassword());
			if (!matches) {
			   request.setAttribute("settings_msg", "原密码不正确，请重新输入");	
				return "manager/setting";
			}
			//第二步：新密码和确认密码不一致，返回
			if (!newPassword.equals(confirmPassword)) {
				  request.setAttribute("settings_msg", "新密码和确认密码不一致，请重新输入");	
				  return "manager/setting";
			}
			
			//将当前用户的密码修改了。更新数据
			user.setUserPassword(passwordEncoder.encode(newPassword));
			userService.editUser(user);
		} catch (Exception e) {
			request.setAttribute("settings_msg", "出现未知异常，联系管理员");
			e.printStackTrace();
			return "manager/setting";
		}
		request.setAttribute("settings_msg", "修改密码成功");
		return "manager/setting";
	
	}
}
