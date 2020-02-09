package com.nttdata.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.apache.log4j.Logger;

import com.nttdata.course.domain.Course;
import com.nttdata.course.domain.Professor;
import com.nttdata.course.domain.StudPreference;
import com.nttdata.course.domain.Student;
import com.nttdata.dbfw.DBFWException;
import com.nttdata.dbfw.DBHelper;
import com.nttdata.dbfw.ParamMapper;

public class AdminDAO {
	public static final Logger LOG = Logger.getLogger(AdminDAO.class);
	private Connection establishConnection() throws ClassNotFoundException,SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "Nttdata@123";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, username, password);
		if(con!=null)
		{
	LOG.info("Connected Successfully");
		}
		
		return con;

	}
	
	
	public String saveProfessor(final Professor p) throws CourseDAOException {
		int result = 0;
		String sp=null;
		Connection con = null;
		List a1=null;
		
		try {
			con = establishConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 a1=DBHelper.executeSelect(con,SqlMapper.PROFESSOR_SEQ, SqlMapper.PROFESSOR_MAP);
			
		} catch (DBFWException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	final String a	=(String)a1.get(0);
		ParamMapper mapParam = new ParamMapper() {

			@Override
			public void mapParams(PreparedStatement pStmt) throws SQLException {
				//pStmt.setString(1, p.getProfId());
				pStmt.setString(1, a);
				pStmt.setString(2, p.getProfName());
				pStmt.setString(3, p.getDeptId());
				
			}
		};
		try {
			con = establishConnection();
			result=DBHelper.executeUpdate(con, SqlMapper.SAVE_PROFESSOR, mapParam);

		} catch (ClassNotFoundException e) {
			throw new CourseDAOException();
		} catch (SQLException e) {
			throw new CourseDAOException(e);
		} catch (DBFWException e) {
			throw new CourseDAOException(e);
		}

		if(result>0)
		{
		sp=a;
		
		}
		return sp;
	}
	
	public String saveCourse(final Course c) throws CourseDAOException {
		int result = 0;
		String sc=null;
		Connection con = null;
		List a2=null;
		try {
			con = establishConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 a2=DBHelper.executeSelect(con,SqlMapper.COURSE_SEQ1, SqlMapper.COURSE_SEQ_MAP);
			
		} catch (DBFWException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	final String a	=(String)a2.get(0);
		ParamMapper mapParam = new ParamMapper() {

			@Override
			public void mapParams(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, a);
				pStmt.setString(2, c.getCourseName());
				pStmt.setString(3, c.getProfId());
				
			}
		};
		try {
			con = establishConnection();
			result=DBHelper.executeUpdate(con, SqlMapper.SAVE_COURSE, mapParam);

		} catch (ClassNotFoundException e) {
			throw new CourseDAOException(e);
		} catch (SQLException e) {
			throw new CourseDAOException(e);
		} catch (DBFWException e) {
			throw new CourseDAOException(e);
		}

		if(result>0)
		{
		sc=c.getCourseId();	
		}
		return sc;
	}
	
	public String saveStudent(final Student c) throws CourseDAOException {
		int result = 0;
		String ss=null;
		Connection con = null;
		List ls=null;
		
		try {
			con = establishConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 ls=DBHelper.executeSelect(con,SqlMapper.STUDENT_SEQ2, SqlMapper.STUDENT_MAP);
			
		} catch (DBFWException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	final String a3	=(String)ls.get(0);
		ParamMapper mapParam = new ParamMapper() {

			@Override
			public void mapParams(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, a3);
				pStmt.setString(2, c.getStudentName());
				pStmt.setString(3, c.getAddress());
				pStmt.setDate(4, new java.sql.Date(c.getDob().getTime()));
				pStmt.setString(5, c.getDegree());
				
			}
		};
		try {
			con = establishConnection();
			result=DBHelper.executeUpdate(con, SqlMapper.SAVE_STUDENT, mapParam);

		} catch (ClassNotFoundException e) {
			throw new CourseDAOException(e);
		} catch (SQLException e) {
			throw new CourseDAOException(e);
		} catch (DBFWException e) {
			throw new CourseDAOException(e);
		}

		if(result>0)
		{
		ss=c.getStudentId();	
		}
		return ss;
	}
	
	public List<Course> getAllDept()throws CourseDAOException
	{
		List<Course> deptlist=new ArrayList<Course>();
		Connection con=null;
		try 
		{
		 con = establishConnection();
			deptlist = DBHelper.executeSelect(con, SqlMapper. ALL_DEPT,SqlMapper.MAP_DEPT);
		} 
		catch (ClassNotFoundException e) 
		{
			throw new CourseDAOException(e);
		} 
		catch (SQLException e) 
		{
			throw new CourseDAOException(e);
		} 
		catch (DBFWException e) 
		{
			throw new CourseDAOException(e);
		} 
		finally {
			try 
			{
				 con.close();
			} 
			catch (SQLException e) {
				throw new CourseDAOException(e);
			}
		}
		return deptlist;
	}
	
	public List<Course> getAllProf()throws CourseDAOException
	{
		List<Course> proflist=new ArrayList<Course>();
		Connection con=null;
		try 
		{
			 con = establishConnection();
			proflist = DBHelper.executeSelect(con, SqlMapper.ALL_PROF,SqlMapper.MAP_PROF_DETAILS );
		} 
		catch (ClassNotFoundException e) 
		{
			throw new CourseDAOException(e);
		} 
		catch (SQLException e) 
		{
			throw new CourseDAOException(e);
		} 
		catch (DBFWException e) 
		{
			throw new CourseDAOException(e);
		} 
		finally {
			try 
			{
				con.close();
			} 
			catch (SQLException e) {
				throw new CourseDAOException(e);
			}
		}
		return proflist;
	}
	
}
