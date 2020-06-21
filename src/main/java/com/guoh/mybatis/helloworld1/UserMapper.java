package com.guoh.mybatis.helloworld1;

public interface UserMapper {
	User selectByPrimaryKey(Long id);
	
	int insertUser(User user);
}