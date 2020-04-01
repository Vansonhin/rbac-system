package com.baizhan.basic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.baizhan.basic.pojo.BasicModular;
import com.baizhan.basic.pojo.BasicPermission;
import com.baizhan.basic.repository.BasicModularRepository;
import com.baizhan.basic.repository.BasicPermissionRepository;
import com.baizhan.basic.service.BasicModularService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BasicModularServiceImpl implements BasicModularService {
	
	@Autowired
	private BasicModularRepository modularRepository;
	
	@Autowired
	private BasicPermissionRepository permissionRepository;

	@Override
	public List<BasicModular> findAllModular() {
		
		return modularRepository.findAll();
	}

	@Override
	public Page<BasicModular> findAllByPage(int index, int size) {
		//技巧：如果发现传入的是一个接口，我们需要查看它是否有子类
		PageRequest pageRequest = PageRequest.of(index, size);
	
		Page<BasicModular> page = modularRepository.findAll(pageRequest);

		return page;
	}

	@Override
	@Transactional //对数据库操作更新的操作，增删改（需要加上事务处理注解）
	public BasicModular saveModular(BasicModular modular) {
		BasicModular basicModular = modularRepository.saveAndFlush(modular);
		return basicModular;
	}

	@Override
	public BasicModular findModularById(Integer modularId) {
		BasicModular basicModular = modularRepository.findByModularId(modularId);
		return basicModular;
	}

	@Override
	@Transactional //持久化对象，设置的它的属性，再提交事务，会自动更新设置的修改过的属性
	public BasicModular editModular(BasicModular modular) {
		//通过查询，获得持久化对象
		BasicModular basicModular = modularRepository.findByModularId(modular.getModularId());
		//basicModular.setModularName(modular.getModularName());
		//basicModular.setModularSort(modular.getModularSort());
		BeanUtils.copyProperties(modular, basicModular);
		
		return basicModular;
	}

	@Override
	@Transactional
	public void removeModularById(Integer modularId) {
		modularRepository.deleteById(modularId);
	}

	@Override
	@Transactional
	public void removeModularByIds(Integer[] modularIds) {
		modularRepository.removeByModularIds(modularIds);
		
	}

	@Override
	public List<BasicModular> findAllModularIncludePermission() {
		List<BasicModular> modulars = modularRepository.findAll();
		//每个模块要封装权限数据
		for (BasicModular basicModular : modulars) {
			List<BasicPermission> permissions = permissionRepository.findByModularId(basicModular.getModularId());
			log.debug("模块权限："+permissions.toString());
			basicModular.setPermissions(permissions);
		}
		
		return modulars;
	}

}
