package com.baizhan.basic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.baizhan.basic.pojo.BasicModular;
import com.baizhan.basic.pojo.BasicPermission;
import com.baizhan.basic.pojo.BasicRole;
import com.baizhan.basic.pojo.BasicUser;
import com.baizhan.basic.repository.BasicModularRepository;
import com.baizhan.basic.repository.BasicPermissionRepository;
import com.baizhan.basic.repository.BasicRoleRepository;
import com.baizhan.basic.repository.BasicUserRepository;
import com.baizhan.basic.service.BasicUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BasicUserServiceImpl implements BasicUserService {
	
	
	@Autowired
	private BasicUserRepository userRepository;
	@Autowired
	private BasicRoleRepository roleRepository;
	@Autowired
	private BasicPermissionRepository permissionRepository;
	@Autowired
	private BasicModularRepository modularRepository;
	
	@Override
	public Page<BasicUser> findAllByPage(int index, int size) {
		//技巧：如果发现传入的是一个接口，我们需要查看它是否有子类
		PageRequest pageRequest = PageRequest.of(index, size);
	
		Page<BasicUser> page = userRepository.findAll(pageRequest);
		//给分页数据 设置其他数据
		List<BasicUser> users = page.getContent();
		for (BasicUser basicUser : users) {
			//设置角色数据
			BasicRole role = roleRepository.findByRoleId(basicUser.getRoleId());
			basicUser.setRole(role);
			//设置状态值
			if(basicUser.getUserStatus()==1) {
				basicUser.setUserStatusString("可用");
			}else {
				basicUser.setUserStatusString("禁用");
			}
		}
		

		return page;
	}

	@Override
	@Transactional //对数据库操作更新的操作，增删改（需要加上事务处理注解）
	public BasicUser saveUser(BasicUser user) {
		BasicUser basicUser = userRepository.saveAndFlush(user);
		return basicUser;
	}

	@Override
	public BasicUser findUserById(Integer userId) {
		BasicUser basicUser = userRepository.findByUserId(userId);
		return basicUser;
	}

	@Override
	@Transactional //持久化对象，设置的它的属性，再提交事务，会自动更新设置的修改过的属性
	public BasicUser editUser(BasicUser user) {
		//通过查询，获得持久化对象
		BasicUser basicUser = userRepository.findByUserId(user.getUserId());
	
	    //传到进来的数据复制到持久化对象
		BeanUtils.copyProperties(user, basicUser);
		
		return basicUser;
	}

	@Override
	@Transactional
	public void removeUserById(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	@Transactional
	public void removeUserByIds(Integer[] userIds) {
		userRepository.removeByUserIds(userIds);
		
	}

	@Override
	public BasicUser findUserByAccount(String account) {
		//第一步：获得用户信息
		BasicUser user = userRepository.findByUserAccount(account);
		//第二步：获得用户的角色信息
		BasicRole role = roleRepository.findByRoleId(user.getRoleId());
		user.setRole(role);
		//第三步：查询权限信息
		String permissionsString = role.getRolePermissions();
		Integer[] permissionIds = this.stringToIntArr(permissionsString);
		List<BasicPermission> permissions = permissionRepository.findByPermissionIds(permissionIds);
		role.setPermissions(permissions);
		//第四步：查询当前用户权限对应模块。
		//因为，权限和模块的，多对一关系。就意味着。多个权限可以对应一个模块，但是当前用户的模块不能重复。所以需要处理去重
		List<BasicModular> modulars=new ArrayList<>();
		
		//思路：在增加模块之前，先判断该模块是存在了。如果存在了就不增加了。
		
	    for (BasicPermission p : permissions) {
	    	//如果：为false，表示原来的集合，没有该Id 
	    	boolean flag=false;
	    	for (BasicModular basicModular : modulars) {
				Integer modularId = p.getModularId();
				if (modularId==basicModular.getModularId()) {
					//如果存在相同的，ID已经重复了
					 flag=true;
					 break;
				}
			}
	    	
	    	//对比后，如果确定原来的集合没有该条数据，增加数据
	    	if (flag==false) {
	    		BasicModular modular = modularRepository.findByModularId(p.getModularId());
	    		modulars.add(modular);
				
			}
			
		}
		
		user.setModulars(modulars);
		
		
		
		log.debug(user.toString());
		return user;
	}
	
	private Integer[] stringToIntArr(String string) {
		String[] split = string.split(",");
		Integer[] arr= new Integer[split.length];
		for(int i=0;i<split.length;i++) {
			//注意去除空格
			arr[i]=Integer.parseInt(split[i].trim());
	
		}
		log.debug("字符串转数值："+Arrays.toString(arr));
		return arr;
	}

	
}
