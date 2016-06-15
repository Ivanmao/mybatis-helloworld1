package com.guoh.mybatis.helloworld1;

import java.util.List;

public interface StudentMapper {
	List<Student> findAllStudents();

	Student findStudentById(Integer id);

	void insertStudent(Student student);
}
