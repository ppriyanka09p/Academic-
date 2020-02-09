package com.nttdata.course.service;

import java.util.ArrayList;
import java.util.List;

import com.nttdata.course.dao.AdminDAO;
import com.nttdata.course.dao.CourseDAOException;
import com.nttdata.course.dao.StudentDAO;
import com.nttdata.course.dao.UserDAO;
import com.nttdata.course.domain.Course;
import com.nttdata.course.domain.CoursePreference;
import com.nttdata.course.domain.Professor;
import com.nttdata.course.domain.StudPreference;
import com.nttdata.course.domain.Student;
import com.nttdata.course.domain.User;

public class CourseRegFacade {

	public User validateUser(String usid,String pawd)throws CourseRegException
	{
		UserDAO udao=new UserDAO();
		User u=null;
		try {
		 u=	udao.validateUser(usid, pawd);
		} catch (CourseDAOException e) {
			throw new CourseRegException("invalid user");
		}
		
		return u;
		
	}
	
	public String saveProfessor(Professor p)throws CourseRegException
	{
		AdminDAO adao=new AdminDAO();
		String s=null;
		try {
			s=adao.saveProfessor(p);
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public String saveCourse(Course c)throws CourseRegException
	{
		AdminDAO adao=new AdminDAO();
		String sc=null;
		try {
			sc=adao.saveCourse(c);
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sc;
		
	}
	public String saveStudent(Student s)throws CourseRegException
	{
		AdminDAO adao=new AdminDAO();
		String ao=null;
		try {
			ao=adao.saveStudent(s);
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ao;
	}
	
	public List getAllProf()throws CourseRegException
	{
		AdminDAO adao=new AdminDAO();
		List l=new ArrayList();
		try {
			l=adao.getAllProf();
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	
	public List getAllDept()throws CourseRegException
	{
		AdminDAO adao=new AdminDAO();
		List li=new ArrayList();
		try {
			li=adao.getAllProf();
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
		
	}
	
	public List<Course> getAllCourses()throws CourseRegException
	{
		StudentDAO sdao=new StudentDAO();
		List<Course> cl=new ArrayList<Course>();
		try {
			cl=sdao.getAllCourses();
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cl;
		
	}
	
	public int saveCoursePref(StudPreference spre,CoursePreference cpre)throws CourseDAOException
	{
		StudentDAO sdao=new StudentDAO();
		int spc=0;
		try {
			spc=sdao.saveCoursePref(spre, cpre);
		} catch (CourseDAOException e) {
			throw new CourseDAOException("courses filled");

		}
		return spc;
	}
	
	public List<CoursePreference> getPrefCourses(String sid)throws CourseRegException
	{
		StudentDAO sdao=new StudentDAO();
		List<CoursePreference> licp=new ArrayList<CoursePreference>();
		try {
			licp=sdao.getPrefCourses(sid);
		} catch (CourseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return licp;
	}
	
	
}
