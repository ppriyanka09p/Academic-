package com.nttdata.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.dbfw.ResultMapper;
import com.nttdata.course.domain.*;

public class SqlMapper {

public static final String VALIDATE_USER="select userId,username,Password,roleId from user1 where userId=?";

public static final ResultMapper MAP_STUDENT = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		User u  = new User(rs.getString("userId"),rs.getString("username"),rs.getString("Password"),rs.getString("roleId"));
		return u;
	}
};


public static final String PROFESSOR_SEQ = "select prof_seq.nextval from dual";

static final ResultMapper PROFESSOR_MAP = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		
		return rs.getString(1);
	}
};

public static final String SAVE_PROFESSOR="insert into Professor(profId,profname,deptId) values (?,?,?)";

public static final String COURSE_SEQ1="select course_seq1.nextval from dual";

static final ResultMapper COURSE_SEQ_MAP= new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		
		return rs.getString(1);
		
	}
};

public static final String SAVE_COURSE="insert into Course(courseId,coursename,profid) values (?,?,?)";

public static final String STUDENT_SEQ2="select student_seq2.nextval from dual";

static final ResultMapper STUDENT_MAP = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		System.out.println(rs.getString(1));
		return rs.getString(1);
	}
};

public static final String SAVE_STUDENT="insert into Student(studId ,studname,addressid,Dob,Degree) values (?,?,?,?,?)";

public static final String ALL_DEPT="select deptId,deptname from Dept";

public static final ResultMapper MAP_DEPT = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		Department depart = new Department(rs.getString("deptId"),rs.getString("deptname"));
		return depart;
	}
};

public static final String ALL_PROF="select profId,profname,Deptid from Professor";

public static final ResultMapper MAP_PROF_DETAILS = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		Professor prof = new Professor(rs.getString("profId"),rs.getString("profname"),rs.getString("Deptid"));
		return prof;
	}
};
	
public static final String ALL_COURSES="select courseId,coursename,profid from Course";

public static final ResultMapper MAP_COURSE = new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		Course course = new Course(rs.getString("CourseId"),rs.getString("Coursename"),rs.getString("profid"));
		return course;
	}
};

public static final String SAVE_COURSE_PREF = "insert into Stud_Course(studId,courseId,preference) values(?,?,?)";

public static final String PREF_COURSES="select courseId, preference from Stud_Course where studId=?";

public static final ResultMapper MAP_PREFCOURSES= new ResultMapper() {

	@Override
	public Object mapRows(ResultSet rs) throws SQLException {
		
		CoursePreference cp = new CoursePreference(	rs.getString("courseId"),rs.getString("preference"));
		
		
		return cp;
	}
};



}
