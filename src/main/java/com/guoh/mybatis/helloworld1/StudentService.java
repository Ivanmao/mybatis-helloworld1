package com.guoh.mybatis.helloworld1;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<Student> findAllStudents() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.findAllStudents();
		} finally {
			// If sqlSession is not closed
			// then database Connection associated this sqlSession will not be
			// returned to pool
			// and application may run out of connections.
			sqlSession.close();
		}
	}

	public Student findStudentById(Integer studId) {
		logger.debug("Select Student By ID :{}", studId);

		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.findStudentById(studId);
		} finally {
			sqlSession.close();
		}
	}

	public void createStudent(Student student) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			studentMapper.insertStudent(student);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	
	/**
	 * 一级缓存测试
	 */
	public void testL1Cache() {

		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();

		Student student1 = null;

		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			student1 = studentMapper.findStudentById(1);
			student1.setName("doudou");

			Student student2 = studentMapper.findStudentById(1);

//			Assert.assertEquals("doudou", student2.getName());
			
			System.out.println("doudou".equals(student2.getName()));
			
//			Assert.assertEquals(student1, student2); // 同一个实例
			System.out.println(student1.equals(student2));

		} finally {
			sqlSession.close();
		}

		sqlSession = MyBatisSqlSessionFactory.openSession();

		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			Student student3 = studentMapper.findStudentById(1);

//			Assert.assertEquals(student1, student3); // 不同的实例
			
			System.out.println(student1.equals(student3));

			studentMapper.deleteById(2); // 删除id=2

			Student student4 = studentMapper.findStudentById(1);
//			Assert.assertEquals(student3, student4);
			
			System.out.println(student4.equals(student3));
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
}
