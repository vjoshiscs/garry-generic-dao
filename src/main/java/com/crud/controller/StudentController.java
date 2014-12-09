/**
 * 
 */
package com.crud.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.model.Student;
import com.crud.pagination.Page;
import com.crud.service.StudentService;

/**
 * @author Vishal Joshi
 *
 */
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		Student student = new Student();
		map.put("student", student);
		map.put("studentList", studentService.findAllStudent(student.getPage()));
		map.put("page", student.getPage());
		return "student";
	}

	@RequestMapping(value = "/student.do", method = RequestMethod.POST)
	public String doAction(@ModelAttribute Page page, BindingResult bindingResult1,
			@ModelAttribute Student student, BindingResult bindingResult,
			@RequestParam String action, Map<String, Object> map) {
		Student studentResult = new Student();
		List<Student> studentList = new ArrayList<Student>();
		switch (action.toLowerCase()) { // only in Java 7 you can put string in
										// Switch
		case "add":
			studentService.add(student);
			studentResult = student;
			studentList = studentService.findAllStudent(student.getPage());
			break;
		case "edit":
			studentService.update(student);
			studentResult = student;
			studentList = studentService.findAllStudent(student.getPage());
			break;
		case "delete":
			studentService.delete(student.getStudentId());
			studentResult = new Student();
			studentList = studentService.findAllStudent(student.getPage());
			break;
		case "search":
			Student searchedStudent = studentService.findStudentById(student
					.getStudentId());
			studentResult = searchedStudent != null ? searchedStudent
					: new Student();
			studentList = studentService.findAllStudent(student.getPage());
			break;
		case "next":
			System.out.println("Current Page :: "+page.getCurrentPageNo());
			// student.setPage(new Page(student.getPage().getCurrentPageNo(), 0,
			// 0, 0, student.getPage().getStartIndex()));
			studentList = studentService.findAllStudent(page);
			break;
		case "previous":
			//student.setPage(new Page(student.getPage().getCurrentPageNo(), 0,
			//		0, 0, student.getPage().getStartIndex()));
			studentList = studentService.findAllStudent(page);
			break;

		}

		map.put("student", studentResult);
		map.put("studentList", studentList);
		map.put("page", page);
		return "student";
	}
	
	@RequestMapping(value = "/studentSearch.do", method = RequestMethod.POST)
	public String doSearch(@ModelAttribute Page page, BindingResult bindingResult1,
			@ModelAttribute Student student, BindingResult bindingResult,
			@RequestParam String action, Map<String, Object> map) {
		
		switch (action.toLowerCase()) {
		case "columnsearch":
			System.out.println("Current Page :: "+page.getCurrentPageNo());
			// student.setPage(new Page(student.getPage().getCurrentPageNo(), 0,
			// 0, 0, student.getPage().getStartIndex()));
			Map<String,Object> parameters = student.getColumnName(student);
			map.put("studentList",studentService.findAllByColumnSearch(parameters, page));
			break;
		
		}
		map.put("page", page);
		return "student";
	}

}
