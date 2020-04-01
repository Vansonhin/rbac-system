package com.baizhan.basic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.baizhan.basic.pojo.BasicRole;


public interface BasicRoleRepository extends JpaRepository<BasicRole, Integer> {
	
	/**
	 * 通过编号查询角色
	 * @param roleId
	 * @return
	 */
	BasicRole findByRoleId(Integer roleId);
	

	
	/**
	 * 删除模块通过编号的数组
	 * @param roleIds
	 * @return
	 */
	@Query("delete from BasicRole m where m.roleId in (:roleIds)")
	@Transactional
	@Modifying //dml操作，必须加上Modifying
	int removeByRoleIds(Integer[] roleIds);
}
