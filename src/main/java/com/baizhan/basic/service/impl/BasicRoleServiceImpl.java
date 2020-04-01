package com.baizhan.basic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.baizhan.basic.pojo.BasicRole;
import com.baizhan.basic.repository.BasicRoleRepository;
import com.baizhan.basic.service.BasicRoleService;

@Service
public class BasicRoleServiceImpl implements BasicRoleService {
	
	@Autowired
	private BasicRoleRepository roleRepository;

	@Override
	public List<BasicRole> findAllRole() {
		
		return roleRepository.findAll();
	}

	@Override
	public Page<BasicRole> findAllByPage(int index, int size) {
		//技巧：如果发现传入的是一个接口，我们需要查看它是否有子类
		PageRequest pageRequest = PageRequest.of(index, size);
	
		Page<BasicRole> page = roleRepository.findAll(pageRequest);

		return page;
	}

	@Override
	@Transactional //对数据库操作更新的操作，增删改（需要加上事务处理注解）
	public BasicRole saveRole(BasicRole role) {
		BasicRole basicRole = roleRepository.saveAndFlush(role);
		return basicRole;
	}

	@Override
	public BasicRole findRoleById(Integer roleId) {
		BasicRole basicRole = roleRepository.findByRoleId(roleId);
		return basicRole;
	}

	@Override
	@Transactional //持久化对象，设置的它的属性，再提交事务，会自动更新设置的修改过的属性
	public BasicRole editRole(BasicRole role) {
		//通过查询，获得持久化对象
		BasicRole basicRole = roleRepository.findByRoleId(role.getRoleId());
		//basicRole.setRoleName(role.getRoleName());
		//basicRole.setRoleSort(role.getRoleSort());
		BeanUtils.copyProperties(role, basicRole);
		
		return basicRole;
	}

	@Override
	@Transactional
	public void removeRoleById(Integer roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	@Transactional
	public void removeRoleByIds(Integer[] roleIds) {
		roleRepository.removeByRoleIds(roleIds);
		
	}

}
