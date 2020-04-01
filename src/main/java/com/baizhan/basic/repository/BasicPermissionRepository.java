package com.baizhan.basic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.baizhan.basic.pojo.BasicPermission;

public interface BasicPermissionRepository extends JpaRepository<BasicPermission, Integer> {
	
	/**
	 * 通过编号查询权限
	 * @param permissionId
	 * @return
	 */
	BasicPermission findByPermissionId(Integer permissionId);
	
	/**
	 * 通过父权限编号，查询它的子权限
	 * @param permissionParent
	 * @return
	 */
	List<BasicPermission> findByPermissionParent(Integer permissionParent);
	
	/**
	 * 通过模块编号查询权限
	 * @param modularId
	 * @return
	 */
	List<BasicPermission> findByModularId(Integer modularId);

	
	
	/**
	 * 删除权限通过编号的数组
	 * @param permissionIds
	 * @return
	 */
	@Query("delete from BasicPermission m where m.permissionId in (:permissionIds)")
	@Transactional
	@Modifying //dml操作，必须加上Modifying
	int removeByPermissionIds(Integer[] permissionIds);
	
	/**
	 * 通过编号查询权限
	 * @param ids
	 * @return
	 */
	@Query("select p from BasicPermission p where p.permissionId in (:ids)")
	List<BasicPermission> findByPermissionIds(Integer[] ids);

}
