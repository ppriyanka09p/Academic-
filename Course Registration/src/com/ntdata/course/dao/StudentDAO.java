package com.nttdata.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nttdata.dbfw.*;
import com.nttdata.course.domain.*;

public class StudentDAO {
	public static final Logger LOG = Logger.getLogger(StudentDAO.class);
	private Connection establishConnection() throws ClassNotFoundException,SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "Nttdata@123";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, username, password);
		if(con!=null)
		{
			LOG.info("Connected successfully");
		}
		
		return con;
	}
	
	public List<Course> getAllCourses()throws CourseDAOException
	{
		List<Course> courselist=new ArrayList<Course>();
		Connection con=null;
		try 
		{
			 con = establishConnection();
			courselist = DBHelper.executeSelect(con, SqlMapper.ALL_COURSES,SqlMapper.MAP_COURSE);
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
		return courselist;
	}
		
	
	public int saveCoursePref(final StudPreference spr,final CoursePreference cpr) throws CourseDAOException {
		int result = 0;
		if(spr.getCoursesFilled()==0)
		{
			
		Connection con = null;
		ParamMapper mapParam = new ParamMapper() {
    
			@Override
			public void mapParams(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, spr.getStudId());
				pStmt.setString(2, cpr.getCourseId());
				pStmt.setString(3,cpr.getPreference());
		
			}
		};
		try {
			con = establishConnection();
			result=DBHelper.executeUpdate(con, SqlMapper.SAVE_COURSE_PREF, mapParam);

		} catch (ClassNotFoundException e) {
			throw new CourseDAOException(e);
		} catch (SQLException e) {
			throw new CourseDAOException(e);
		} catch (DBFWException e) {
			throw new CourseDAOException(e);
		}
		}
		return result;
	}
	

	public List<CoursePreference> getPrefCourses(final String studId) throws CourseDAOException {
	Connection con = null;
	List<CoursePreference> preflist = null;
	ParamMapper mapParam = new ParamMapper() {
		@Override
		public void mapParams(PreparedStatement pStmt) throws SQLException {
			pStmt.setString(1, studId);
		}
	};
	try {
		con = establishConnection();
		preflist = DBHelper.executeSelect(con, SqlMapper.PREF_COURSES,SqlMapper.MAP_PREFCOURSES, mapParam);

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
	finally 
	{
		try 
		{
			con.close();
		} 
		catch (SQLException e) {
			throw new CourseDAOException(e);
		}
	}

	return preflist;
}
			
}
