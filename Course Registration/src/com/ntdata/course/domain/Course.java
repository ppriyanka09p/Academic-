package com.nttdata.course.domain;

public class Course {
	
	private String CourseId;
	private String CourseName;
	private String ProfId;
	
	public Course(String cid,String cname,String pid)
	{
		CourseId=cid;
		CourseName=cname;
		ProfId=pid;
	}
	
	public Course(String cname,String pid)
	{
		
}

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getProfId() {
		return ProfId;
	}

	public void setProfId(String profId) {
		ProfId = profId;
	}
	
	
}