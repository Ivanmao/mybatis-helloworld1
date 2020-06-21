package com.guoh.mybatis.helloworld1;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 二级缓存测试
	 */
	public void testL2Cache() {
		logger.info("===testL2Cache");

		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		User user1 = null;

		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

			user1 = userMapper.selectByPrimaryKey(1L);
			user1.setName("doudou");

			User user2 = userMapper.selectByPrimaryKey(1L);

			System.out.println("doudou".equals(user2.getName()));
			System.out.println(user1.equals(user2));

		} finally {
			sqlSession.close();
			// sqlsession关闭后，会将数据缓存到二级缓存
		}

		sqlSession = MyBatisSqlSessionFactory.openSession();

		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user3 = userMapper.selectByPrimaryKey(1L);
			System.out.println("doudou".equals(user3.getName()));
			
			System.out.println("==="+user3.getName());
			System.out.println(user1.equals(user3));

			User user4 = userMapper.selectByPrimaryKey(1L);
			System.out.println(user3.equals(user4));   // readonly默认false。不是同一个对象
			System.out.println("---"+user4.getName());
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
	public long insertUser() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

			User user = new User();
			user.setAge(18);
			user.setName("ribery");

			int result = userMapper.insertUser(user);
			logger.info("---result:{}", result);
			
			logger.info("---user.id={}",user.getId());
			
			sqlSession.commit();

			return user.getId();

		} finally {
			sqlSession.close();
		}
	}
	

}
