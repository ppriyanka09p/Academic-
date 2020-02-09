package com.nttdata.course.domain;

import java.util.Date;

public class Student {
	private String StudentId;
	private String StudentName;
	private String Address;
	private Date Dob;
	private String Degree;
	
	public Student(String sid,String sname,String add,Date db,String deg )
	{
		 StudentId=sid;
		 StudentName=sname;
		 Address=add;
		 Dob=db;
		 Degree=deg;
	}
	
	public Student(String sname,String add,Date db,String deg )
	{
		}

	public String getStudentId() {
		return StudentId;
	}

	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getDegree() {
		return Degree;
	}

	public void setDegree(String degree) {
		Degree = degree;
	}
	
		
}
