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
import com.baizhan.basic.pojo.BasicPermission;
import com.baizhan.basic.service.BasicModularService;
import com.baizhan.basic.service.BasicPermissionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/permission")
@Slf4j
public class BasicPermissionController {
	
	@Autowired
	private BasicPermissionService permissionService;
	
	@Autowired
	private BasicModularService modularService;
	
	/**
	 * 跳转到编辑权限页面
	 * @return
	 */
	@RequestMapping(value = "/permissionToEdit/{permissionId}",method = {RequestMethod.GET,RequestMethod.POST})
	
	public String permissionToEdit(@PathVariable Integer permissionId,Model model) {
		log.debug("--跳转到编辑页面--:"+permissionId);
		//模块数据
		List<BasicModular> modulars = modularService.findAllModular();
		model.addAttribute("modulars", modulars);
		//父权限，需求，只有菜单权限才有子菜单
		List<BasicPermission> topPermissions = permissionService.findByPermissionParentId(0);
		model.addAttribute("topPermissions", topPermissions);
		
		BasicPermission permission = permissionService.findPermissionById(permissionId);
		model.addAttribute("permission", permission);
		return "manager/permissionEdit";
	}
	
	/**
	 * 编辑权限
	 * @return
	 */
	@PostMapping(value = "/permissionEdit")
	public String permissionEdit(BasicPermission permission,Model model) {
		log.debug("--编辑权限--");
		try {
		  permissionService.editPermission(permission);
		  model.addAttribute("permission_edit_msg", "编辑权限成功");
		} catch (Exception e) {
			model.addAttribute("permission_edit_msg", "编辑权限失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/permission//permissionToEdit/"+permission.getPermissionId();
	}
	
	
	
	/**
	 * 跳转到增加权限页面
	 * @return
	 */
	@RequestMapping(value = "/permissionToAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public String permissionToAdd(Model model) {
		//模块数据
		List<BasicModular> modulars = modularService.findAllModular();
		model.addAttribute("modulars", modulars);
		//父权限，需求，只有菜单权限才有子菜单
		List<BasicPermission> topPermissions = permissionService.findByPermissionParentId(0);
		model.addAttribute("topPermissions", topPermissions);
		
		log.debug("--跳转到增加页面--");
		return "manager/permissionAdd";
	}
	
	
	
	/**
	 * 增加权限
	 * @return
	 */
	@PostMapping(value = "/permissionAdd")
	public String permissionAdd(BasicPermission permission,Model model) {
		log.debug("--增加权限--");
		try {
		  permissionService.savePermission(permission);
		  model.addAttribute("permission_add_msg", "增加权限成功");
		} catch (Exception e) {
			model.addAttribute("permission_add_msg", "增加权限失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/permission/permissionToAdd";
	}
	
	/**
	 * 跳转到权限列表页面
	 * @param index
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/permissionList/{index}")
	public String permissionList(@PathVariable(required = false) Integer index, Model model) {
		log.debug("--跳转到权限列表--");
		if (index==null) {
			index=0;
		}
		
		Page<BasicPermission> permissionPage = permissionService.findAllByPage(index, 5);
		model.addAttribute("permissionPage", permissionPage);
		return "manager/permissionList";
	}
	
	/**
	 * 权限删除
	 * @param permissionId
	 * @return
	 */
	@GetMapping(value = "/permissionRemove/{permissionId}")
	public String permissionRemove(@PathVariable Integer permissionId) {
		log.debug("-删除权限--："+permissionId);
		try {
			permissionService.removePermissionById(permissionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//因为不想返回数据到页面，所以使用重定向
		return "redirect:/permission/permissionList/0";
	}
	
	@GetMapping(value = "/removeAllByIds")
	public String removeAllByIds(Integer[] permissionIds) {
		log.debug("-批量删除-"+Arrays.toString(permissionIds));
		try {
			permissionService.removePermissionByIds(permissionIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/permission/permissionList/0";
	}

}
