package com.baizhan.basic.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baizhan.basic.pojo.BasicRole;
import com.baizhan.basic.pojo.BasicUser;
import com.baizhan.basic.service.BasicRoleService;
import com.baizhan.basic.service.BasicUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class BasicUserController {
	
	@Autowired
	private BasicUserService userService;
	
	@Autowired
	private BasicRoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * 跳转到编辑用户页面
	 * @return
	 */
	@RequestMapping(value = "/userToEdit/{userId}",method = {RequestMethod.GET,RequestMethod.POST})
	
	public String userToEdit(@PathVariable Integer userId,Model model) {
		log.debug("--跳转到编辑页面--:"+userId);
		BasicUser user = userService.findUserById(userId);
		//用户编辑，有角色列表，所以需要查询所有的角色
		List<BasicRole> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		return "manager/userEdit";
	}
	
	/**
	 * 编辑用户
	 * @return
	 */
	@PostMapping(value = "/userEdit")
	public String userEdit(BasicUser user,Model model) {
		log.debug("--增加用户--");
		try {
			String encode = passwordEncoder.encode(user.getUserPassword());
			user.setUserPassword(encode);
			user.setUserCreateDate(new Date());
		  userService.editUser(user);
		  model.addAttribute("user_edit_msg", "编辑用户成功");
		} catch (Exception e) {
			model.addAttribute("user_edit_msg", "编辑用户失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/user/userToEdit/"+user.getUserId();
	}
	
	
	
	/**
	 * 跳转到增加用户页面
	 * @return
	 */
	@RequestMapping(value = "/userToAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public String userToAdd(Model model) {
		//增加页面里面需要选择roles
		List<BasicRole> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		log.debug("--跳转到增加页面--");
		return "manager/userAdd";
	}
	
	
	
	/**
	 * 增加用户
	 * @return
	 */
	@PostMapping(value = "/userAdd")
	public String userAdd(BasicUser user,Model model) {
		log.debug("--增加用户--");
		try {
			//创建日期
		  user.setUserCreateDate(new Date());
		  String encode = passwordEncoder.encode(user.getUserPassword());
		  //密码需要先加密
		  user.setUserPassword(encode);
		  userService.saveUser(user);
		  model.addAttribute("user_add_msg", "增加用户成功");
		} catch (Exception e) {
			model.addAttribute("user_add_msg", "增加用户失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/user/userToAdd";
	}
	
	/**
	 * 跳转到用户列表页面
	 * @param index
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/userList/{index}")
	public String userList(@PathVariable(required = false) Integer index, Model model) {
		log.debug("--跳转到用户列表--");
		if (index==null) {
			index=0;
		}
		
		Page<BasicUser> userPage = userService.findAllByPage(index, 5);
		model.addAttribute("userPage", userPage);
		return "manager/userList";
	}
	
	/**
	 * 用户删除
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/userRemove/{userId}")
	public String userRemove(@PathVariable Integer userId) {
		log.debug("-删除用户--："+userId);
		try {
			userService.removeUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//因为不想返回数据到页面，所以使用重定向
		return "redirect:/user/userList/0";
	}
	
	@GetMapping(value = "/removeAllByIds")
	public String removeAllByIds(Integer[] userIds) {
		log.debug("-批量删除-"+Arrays.toString(userIds));
		try {
			userService.removeUserByIds(userIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/user/userList/0";
	}

	


}
