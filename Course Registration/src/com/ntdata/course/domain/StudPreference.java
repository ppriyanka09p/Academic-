package com.nttdata.course.domain;

import java.util.Iterator;
import java.util.List;



public class StudPreference {
	private String StudId;
	private List<CoursePreference> prefCourse;
	private int coursesFilled=0;
	
	public StudPreference(String sid,List<CoursePreference> cp )
	{
		StudId=sid;
		prefCourse=cp;
		
	}
	
	
	public void addpref(List<CoursePreference> prefCourse)
	{
		int cprimary=0;
		int csecondary=0;
		
		Iterator it=prefCourse.iterator();
	while(it.hasNext())
	{
		CoursePreference cpre=(CoursePreference)it.next();
		String a=cpre.getPreference();
		if(a.equals("P"))
		{
			cprimary++;
			if(cprimary>4)
				
				break;
			}
		else
		{
			csecondary++;
			if(csecondary>2)
				break;
		}
	}
	if(cprimary==4 && csecondary==2)
	{
		coursesFilled=1;
	}
	
	}


	public String getStudId() {
		return StudId;
	}


	public void setStudId(String studId) {
		StudId = studId;
	}


	public List<CoursePreference> getPrefCourse() {
		return prefCourse;
	}


	public void setPrefCourse(List<CoursePreference> prefCourse) {
		this.prefCourse = prefCourse;
	}


	public int getCoursesFilled() {
		return coursesFilled;
	}


	public void setCoursesFilled(int coursesFilled) {
		this.coursesFilled = coursesFilled;
	}
	
	
		
}
