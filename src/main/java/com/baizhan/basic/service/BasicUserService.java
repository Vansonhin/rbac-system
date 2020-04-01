package com.baizhan.basic.service;

import org.springframework.data.domain.Page;

import com.baizhan.basic.pojo.BasicUser;

public interface BasicUserService {
	
	/**
	 * 通过账号查找用户
	 * @param account
	 * @return
	 */
	BasicUser findUserByAccount(String account);
	
	/**
	 * 分页查询所有的模块
	 * @param index
	 * @param size
	 * @return
	 */
	Page<BasicUser> findAllByPage(int index,int size);
	
	/**
	 * 增加模块
	 * @param user
	 * @return
	 */
	BasicUser saveUser(BasicUser user);
	
	/**
	 * 增加模块
	 * @param user
	 * @return
	 */
	BasicUser findUserById(Integer userId);
	
	
	/**
	 * 编辑模块，注意，必须包括ID
	 * @param user
	 * @return
	 */
	BasicUser editUser(BasicUser user);
	
	/**
	 * 通过编号删除模块
	 * @param userId
	 * @return
	 */
	void removeUserById(Integer userId);
	
	/**
	 * 通过模块编号删除
	 * @param userIds
	 */
	void removeUserByIds(Integer[] userIds);
	

}
