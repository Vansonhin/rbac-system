package com.baizhan.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baizhan.basic.pojo.BasicUser;
import com.baizhan.basic.service.BasicUserService;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	private BasicUserService basicUserService;

	@Test
	void contextLoads() {
		try {
			BasicUser user = basicUserService.findUserByAccount("admin");
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
