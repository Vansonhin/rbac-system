package com.baizhan.basic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baizhan.basic.pojo.BasicModular;
import com.baizhan.basic.pojo.BasicRole;
import com.baizhan.basic.service.BasicModularService;
import com.baizhan.basic.service.BasicRoleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/role")
@Slf4j
public class BasicRoleController  {
	
	@Autowired
	private BasicRoleService roleService;
	
	@Autowired
	private BasicModularService modularService;
	
	
	/**
	 * 跳转到编辑角色页面
	 * @return
	 */
	@RequestMapping(value = "/roleToEdit/{roleId}",method = {RequestMethod.GET,RequestMethod.POST})
	
	public String roleToEdit(@PathVariable Integer roleId,Model model) {
		log.debug("--跳转到编辑页面--:"+roleId);
		BasicRole role = roleService.findRoleById(roleId);
		List<BasicModular> modulars = modularService.findAllModularIncludePermission();
		model.addAttribute("modulars", modulars);
		model.addAttribute("role", role);
		return "manager/roleEdit";
	}
	
	/**
	 * 编辑角色
	 * @return
	 */
	@PostMapping(value = "/roleEdit")
	public String roleEdit(BasicRole role,Integer[] permissionIds,Model model) {
		log.debug("--增加角色--");
		try {
			StringBuilder builder=new StringBuilder(Arrays.toString(permissionIds));
			builder.delete(0, 1);
			builder.delete(builder.length()-1,builder.length());
			role.setRolePermissions(builder.toString());
		  roleService.editRole(role);
		  model.addAttribute("role_edit_msg", "编辑角色成功");
		} catch (Exception e) {
			model.addAttribute("role_edit_msg", "编辑角色失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/role//roleToEdit/"+role.getRoleId();
	}
	
	
	
	/**
	 * 跳转到增加角色页面
	 * @return
	 */
	@RequestMapping(value = "/roleToAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public String roleToAdd(Model model) {
		log.debug("--跳转到增加页面--");
		List<BasicModular> modulars = modularService.findAllModularIncludePermission();
		model.addAttribute("modulars", modulars);
		return "manager/roleAdd";
	}
	
	
	
	/**
	 * 增加角色
	 * @return
	 */
	@PostMapping(value = "/roleAdd")
	public String roleAdd(BasicRole role,Integer[] permissionIds,Model model) {
		log.debug("--增加角色--"+role+"权限编号："+Arrays.toString(permissionIds));
		StringBuilder builder=new StringBuilder(Arrays.toString(permissionIds));
		builder.delete(0, 1);
		builder.delete(builder.length()-1,builder.length());
		try {
			log.debug(builder.toString());
			role.setRolePermissions(builder.toString());
		  roleService.saveRole(role);
		  
		  model.addAttribute("role_add_msg", "增加角色成功");
		} catch (Exception e) {
			model.addAttribute("role_add_msg", "增加角色失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/role/roleToAdd";
	}
	
	/**
	 * 跳转到角色列表页面
	 * @param index
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/roleList/{index}")
	public String roleList(@PathVariable(required = false) Integer index, Model model) {
		log.debug("--跳转到角色列表--");
		if (index==null) {
			index=0;
		}
		
		Page<BasicRole> rolePage = roleService.findAllByPage(index, 5);
		model.addAttribute("rolePage", rolePage);
		return "manager/roleList";
	}
	
	/**
	 * 角色删除
	 * @param roleId
	 * @return
	 */
	@GetMapping(value = "/roleRemove/{roleId}")
	public String roleRemove(@PathVariable Integer roleId) {
		log.debug("-删除角色--："+roleId);
		try {
			roleService.removeRoleById(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//因为不想返回数据到页面，所以使用重定向
		return "redirect:/role/roleList/0";
	}
	
	@GetMapping(value = "/removeAllByIds")
	public String removeAllByIds(Integer[] roleIds) {
		log.debug("-批量删除-"+Arrays.toString(roleIds));
		try {
			roleService.removeRoleByIds(roleIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/role/roleList/0";
	}

}
