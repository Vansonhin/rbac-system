package com.baizhan.basic.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.baizhan.basic.pojo.BasicPermission;

public interface BasicPermissionService {
	
	
	/**
	 * 通过父权限编号查询权限
	 * @param parentId
	 * @return
	 */
	List<BasicPermission> findByPermissionParentId(Integer parentId);
	
	/**
	 * 查询所有的权限
	 * @return
	 */
	List<BasicPermission> findAllPermission();
	
	/**
	 * 分页查询所有的权限
	 * @param index
	 * @param size
	 * @return
	 */
	Page<BasicPermission> findAllByPage(int index,int size);
	
	/**
	 * 增加权限
	 * @param permission
	 * @return
	 */
	BasicPermission savePermission(BasicPermission permission);
	
	/**
	 * 增加权限
	 * @param permission
	 * @return
	 */
	BasicPermission findPermissionById(Integer permissionId);
	
	
	/**
	 * 编辑权限，注意，必须包括ID
	 * @param permission
	 * @return
	 */
	BasicPermission editPermission(BasicPermission permission);
	
	/**
	 * 通过编号删除权限
	 * @param permissionId
	 * @return
	 */
	void removePermissionById(Integer permissionId);
	
	/**
	 * 通过权限编号删除
	 * @param permissionIds
	 */
	void removePermissionByIds(Integer[] permissionIds);
	

}
