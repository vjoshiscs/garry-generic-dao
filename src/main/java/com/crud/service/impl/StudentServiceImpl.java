package com.crud.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dao.GenericDAO;
import com.crud.model.Student;
import com.crud.pagination.Page;
import com.crud.service.StudentService;

/**
 * 
 * @author Vishal Joshi
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	private Logger logger = Logger.getLogger(StudentServiceImpl.class);

	@Autowired
	private GenericDAO<Student, Long> studentDao;
	
	
	@Transactional
	public Student add(Student student) {
		logger.debug("In add()");
	    logger.info("Adding student details for student First Name - " + student.getFirstName());
	    try {
	        return studentDao.create(student);
	      } catch (Exception e) {
	        logger.error("Error adding student details", e);
	      }
	      return null;
	}

	@Transactional
	public Student update(Student student) {
		logger.debug("In update()");
	    logger.info("Adding student details for student First Name - " + student.getFirstName());
	    try {
	        return studentDao.merge(student);
	      } catch (Exception e) {
	        logger.error("Error updaing student details", e);
	      }
	      return null;
	}

	@Transactional
	public void delete(int studentId) {
	    logger.debug("In delete()");
	    try {
	      Student student = studentDao.findById(studentId);
	      if(student != null) {
	    	  studentDao.delete(student);
	      }
	    } catch (Exception e) {
	      logger.error("Error removing student by id - " + studentId, e);
	    }
	  }

	@Transactional
	public Student findStudentById(int studentId) {
		logger.debug("In findStudentById()");
		try {
		return studentDao.findById(studentId);
		} catch (Exception e) {
		      logger.error("Error in finding student by id - " + studentId, e);
		}
		return null;
	}

	@Transactional
	public List<Student> findAllStudent(Page page) {
	    logger.debug("In findAllStudent()");
	    try {
	      return studentDao.findAll(page, "findAllByPage");
	    } catch (Exception e) {
	      logger.error("Error getting student list.", e);
	    }
	    return null;
	  }
	
	@Transactional
	public List<Student> findAllByColumnSearch(Map<String,Object> parameters, Page page){
		logger.debug("In findAllByColumnSearch(Page page)");
		List<Student> students = new ArrayList<Student>();
	    try {
	       List<?> studentList =  studentDao.findColumnBased(parameters, page);
	       for (Iterator<?> iterator = 
	    		   studentList.iterator(); iterator.hasNext();){
	    	   Student student = (Student) iterator.next(); 
	    	   students.add(student);
	       }
	       return students;
	    } catch (Exception e) {
	      logger.error("Error getting student list.", e);
	    }
	    return null;
	  }
	
	public static void main(String[] args) {
		StudentServiceImpl impl = new StudentServiceImpl();
		Student student = new Student();
		student.setFirstName("vishal");
		student.setLastName("");
		student.setStudentId(1);
		Student studentList = impl.findStudentById(student.getStudentId());
			System.out.println(studentList);
	}
}
