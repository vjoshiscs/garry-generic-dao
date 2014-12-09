/**
 * 
 */
package com.crud.service;

import java.util.List;
import java.util.Map;

import com.crud.model.Student;
import com.crud.pagination.Page;

/**
 * @author Vishal Joshi
 *
 */
public interface StudentService {

	public Student add(Student student);

	public Student update(Student student);

	public void delete(int studentId);

	public Student findStudentById(int studentId);

	public List<Student> findAllStudent(Page page);
	
	public List<Student> findAllByColumnSearch(Map<String,Object> parameters, Page page);

}
