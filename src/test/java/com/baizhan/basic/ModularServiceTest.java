package com.baizhan.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.baizhan.basic.pojo.BasicModular;
import com.baizhan.basic.service.BasicModularService;

@SpringBootTest
public class ModularServiceTest {
	
	@Autowired
	private BasicModularService modularService;
	
	@Test
	public void findByPage() {
		Page<BasicModular> page = modularService.findAllByPage(2, 5);
		System.out.println("每页记录数："+page.getSize());
		System.out.println("当前索引："+page.getNumber());
		System.out.println("总页面数据："+page.getTotalPages());
		System.out.println("数据:"+page.getContent());
	}
	
	@Test
	public void editModular() {
		BasicModular modular=new BasicModular();
		modular.setModularId(13);
		modular.setModularName("KKK");
		modular.setModularSort(15);
		modularService.editModular(modular);
	}

}
