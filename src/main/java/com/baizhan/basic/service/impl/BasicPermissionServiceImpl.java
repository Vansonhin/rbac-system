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
import com.baizhan.basic.service.BasicPermissionService;

@Service
public class BasicPermissionServiceImpl implements BasicPermissionService{

	@Autowired
	private BasicPermissionRepository permissionRepository;
	@Autowired
	private BasicModularRepository modularRepository;

	@Override
	public List<BasicPermission> findAllPermission() {
		
		return permissionRepository.findAll();
	}

	@Override
	public Page<BasicPermission> findAllByPage(int index, int size) {
		//技巧：如果发现传入的是一个接口，我们需要查看它是否有子类
		PageRequest pageRequest = PageRequest.of(index, size);
	
		Page<BasicPermission> page = permissionRepository.findAll(pageRequest);
		
		List<BasicPermission> permissions = page.getContent();
		for (BasicPermission basicPermission : permissions) {
			//1.拼接所属模块
			BasicModular modular = modularRepository.findByModularId(basicPermission.getModularId());
			basicPermission.setModular(modular);
			//2.拼接父权限,如果父权限是0，是菜单权限，需要新建一个对象。
			if (basicPermission.getPermissionParent()==0) {
				BasicPermission topPermission=new BasicPermission();
				topPermission.setPermissionName("顶级菜单");
				basicPermission.setParentPermission(topPermission);
			}else {
				BasicPermission permission = permissionRepository.findByPermissionId(basicPermission.getPermissionParent());
				basicPermission.setParentPermission(permission);
			}
			
			
		}

		return page;
	}

	@Override
	@Transactional //对数据库操作更新的操作，增删改（需要加上事务处理注解）
	public BasicPermission savePermission(BasicPermission permission) {
		BasicPermission basicPermission = permissionRepository.saveAndFlush(permission);
		return basicPermission;
	}

	@Override
	public BasicPermission findPermissionById(Integer permissionId) {
		BasicPermission basicPermission = permissionRepository.findByPermissionId(permissionId);
		return basicPermission;
	}

	@Override
	@Transactional //持久化对象，设置的它的属性，再提交事务，会自动更新设置的修改过的属性
	public BasicPermission editPermission(BasicPermission permission) {
		//通过查询，获得持久化对象
		BasicPermission basicPermission = permissionRepository.findByPermissionId(permission.getPermissionId());

		BeanUtils.copyProperties(permission, basicPermission);
		
		return basicPermission;
	}

	@Override
	@Transactional
	public void removePermissionById(Integer permissionId) {
		permissionRepository.deleteById(permissionId);
	}

	@Override
	@Transactional
	public void removePermissionByIds(Integer[] permissionIds) {
		permissionRepository.removeByPermissionIds(permissionIds);
		
	}

	@Override
	public List<BasicPermission> findByPermissionParentId(Integer parentId) {
		
		return permissionRepository.findByPermissionParent(parentId);
	}

}
