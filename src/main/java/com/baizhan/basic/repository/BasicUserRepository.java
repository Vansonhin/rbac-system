package com.baizhan.basic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.baizhan.basic.pojo.BasicUser;

public interface BasicUserRepository extends JpaRepository<BasicUser, Integer> {
	
	/**
	 * 通过账号查询用户
	 * @param userAccount
	 * @return
	 */
	BasicUser findByUserAccount(String userAccount);
	
	/**
	 * 通过编号查询用户
	 * @param userId
	 * @return
	 */
	BasicUser findByUserId(Integer userId);
	
	/**
	 * 删除用户通过编号的数组
	 * @param userIds
	 * @return
	 */
	@Query("delete from BasicUser m where m.userId in (:userIds)")
	@Transactional
	@Modifying //dml操作，必须加上Modifying
	int removeByUserIds(Integer[] userIds);
}
