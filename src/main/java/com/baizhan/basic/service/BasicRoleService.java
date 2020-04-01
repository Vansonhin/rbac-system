package com.baizhan.basic.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.baizhan.basic.pojo.BasicRole;

public interface BasicRoleService  {
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	List<BasicRole> findAllRole();
	
	/**
	 * 分页查询所有的角色
	 * @param index
	 * @param size
	 * @return
	 */
	Page<BasicRole> findAllByPage(int index,int size);
	
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	BasicRole saveRole(BasicRole role);
	
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	BasicRole findRoleById(Integer roleId);
	
	
	/**
	 * 编辑角色，注意，必须包括ID
	 * @param role
	 * @return
	 */
	BasicRole editRole(BasicRole role);
	
	/**
	 * 通过编号删除角色
	 * @param roleId
	 * @return
	 */
	void removeRoleById(Integer roleId);
	
	/**
	 * 通过角色编号删除
	 * @param roleIds
	 */
	void removeRoleByIds(Integer[] roleIds);

}
