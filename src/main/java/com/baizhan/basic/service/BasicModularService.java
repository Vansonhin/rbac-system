package com.baizhan.basic.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.baizhan.basic.pojo.BasicModular;

public interface BasicModularService {
	
	/**
	 * 查询所有的模块
	 * @return
	 */
	List<BasicModular> findAllModular();
	
	/**
	 * 查询所有的模块，模块里面包含权限
	 * @return
	 */
	List<BasicModular> findAllModularIncludePermission();
	
	/**
	 * 分页查询所有的模块
	 * @param index
	 * @param size
	 * @return
	 */
	Page<BasicModular> findAllByPage(int index,int size);
	
	/**
	 * 增加模块
	 * @param modular
	 * @return
	 */
	BasicModular saveModular(BasicModular modular);
	
	/**
	 * 增加模块
	 * @param modular
	 * @return
	 */
	BasicModular findModularById(Integer modularId);
	
	
	/**
	 * 编辑模块，注意，必须包括ID
	 * @param modular
	 * @return
	 */
	BasicModular editModular(BasicModular modular);
	
	/**
	 * 通过编号删除模块
	 * @param modularId
	 * @return
	 */
	void removeModularById(Integer modularId);
	
	/**
	 * 通过模块编号删除
	 * @param modularIds
	 */
	void removeModularByIds(Integer[] modularIds);
	
	
	

}
