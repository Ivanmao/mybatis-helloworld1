package com.guoh.mybatis.helloworld1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {
	private static UserService userService;

	@BeforeClass
	public static void setup() {
		userService = new UserService();
	}

	@AfterClass
	public static void teardown() {
		userService = null;
	}

	@Test
	public void testL2Cache() {
		userService.testL2Cache();
	}
	
	@Test
	public void testInsertUser() {
		userService.insertUser();
	}
}
