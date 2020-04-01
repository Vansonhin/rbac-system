package com.baizhan.basic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.baizhan.basic.pojo.BasicModular;

public interface BasicModularRepository extends JpaRepository<BasicModular, Integer> {
	
	/**
	 * 通过编号查询模块
	 * @param modularId
	 * @return
	 */
	BasicModular findByModularId(Integer modularId);
	
	/**
	 * 删除模块通过编号的数组
	 * @param modularIds
	 * @return
	 */
	@Query("delete from BasicModular m where m.modularId in (:modularIds)")
	@Transactional
	@Modifying //dml操作，必须加上Modifying
	int removeByModularIds(Integer[] modularIds);
	
}
