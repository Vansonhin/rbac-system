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
import com.baizhan.basic.service.BasicModularService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/modular")
@Slf4j
public class BasicModularController {
	
	@Autowired
	private BasicModularService modularService;
	
	
	/**
	 * 跳转到编辑模块页面
	 * @return
	 */
	@RequestMapping(value = "/modularToEdit/{moduarId}",method = {RequestMethod.GET,RequestMethod.POST})
	
	public String modularToEdit(@PathVariable Integer modularId,Model model) {
		log.debug("--跳转到编辑页面--:"+modularId);
		BasicModular modular = modularService.findModularById(modularId);
		model.addAttribute("modular", modular);
		return "manager/modularEdit";
	}
	
	/**
	 * 编辑模块
	 * @return
	 */
	@PostMapping(value = "/modularEdit")
	public String modularEdit(BasicModular modular,Model model) {
		log.debug("--增加模块--");
		try {
		  modularService.editModular(modular);
		  model.addAttribute("modular_edit_msg", "编辑模块成功");
		} catch (Exception e) {
			model.addAttribute("modular_edit_msg", "编辑模块失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/modular//modularToEdit/"+modular.getModularId();
	}
	
	
	
	/**
	 * 跳转到增加模块页面
	 * @return
	 */
	@RequestMapping(value = "/modularToAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public String modularToAdd() {
		log.debug("--跳转到增加页面--");

		return "manager/modularAdd";
	}
	
	
	
	/**
	 * 增加模块
	 * @return
	 */
	@PostMapping(value = "/modularAdd")
	public String modularAdd(BasicModular modular,Model model) {
		log.debug("--增加模块--");
		try {
		  modularService.saveModular(modular);
		  model.addAttribute("modular_add_msg", "增加模块成功");
		} catch (Exception e) {
			model.addAttribute("modular_add_msg", "增加模块失败");
			e.printStackTrace();
		}
	    //插入成功后，跳转到跳转方法
		return "forward:/modular/modularToAdd";
	}
	
	/**
	 * 跳转到模块列表页面
	 * @param index
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/modularList/{index}")
	public String modularList(@PathVariable(required = false) Integer index, Model model) {
		log.debug("--跳转到模块列表--");
		if (index==null) {
			index=0;
		}
		
		Page<BasicModular> modularPage = modularService.findAllByPage(index, 5);
		model.addAttribute("modularPage", modularPage);
		return "manager/modularList";
	}
	
	/**
	 * 模块删除
	 * @param modularId
	 * @return
	 */
	@GetMapping(value = "/modularRemove/{modularId}")
	public String modularRemove(@PathVariable Integer modularId) {
		log.debug("-删除模块--："+modularId);
		try {
			modularService.removeModularById(modularId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//因为不想返回数据到页面，所以使用重定向
		return "redirect:/modular/modularList/0";
	}
	
	@GetMapping(value = "/removeAllByIds")
	public String removeAllByIds(Integer[] modularIds) {
		log.debug("-批量删除-"+Arrays.toString(modularIds));
		try {
			modularService.removeModularByIds(modularIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/modular/modularList/0";
	}

}
