/**
 * 
 */
package com.crud.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.crud.pagination.Page;

/**
 * @author Vishal Joshi
 *
 */
@Entity
@NamedQuery(name="findAllByPage", query = "from Student")
public class Student implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	public Student() {

	}

	public Student(int studentId, String firstName, String lastName,
			int yearLevel) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearLevel = yearLevel;
	}

	@Id
	@Column(name = "studentid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	// This will generate the id automatic
	private int studentId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "yearLevel")
	private int yearLevel;

	@Transient
	private Page page = null;

	/**
	 * @return the sturdentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param sturdentId
	 *            the sturdentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the yearLevel
	 */
	public int getYearLevel() {
		return yearLevel;
	}

	/**
	 * @param yearLevel
	 *            the yearLevel to set
	 */
	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		if (this.page != null) {
			return this.page;
		} else {
			return new Page();
		}
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	public Map<String,Object> getColumnName(Student student){
		
		Map<String,Object> columnName = new HashMap<String,Object>();
		if(student.getFirstName()!=null&&student.getFirstName()!="")
			columnName.put("firstName",student.getFirstName());
		if(student.getLastName()!=null&&student.getLastName()!="")
			columnName.put("lastName",student.getLastName());
		if(student.getStudentId()!=0)
			columnName.put("studentId",student.getStudentId());
		if(student.getYearLevel()!=0)
			columnName.put("yearLevel",student.getYearLevel());
		return columnName;
	}

}
