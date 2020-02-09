package com.nttdata.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.course.domain.Course;
import com.nttdata.course.domain.User;
import com.nttdata.dbfw.DBFWException;
import com.nttdata.dbfw.DBHelper;
import com.nttdata.dbfw.ParamMapper;

public class UserDAO {

	private Connection establishConnection() throws ClassNotFoundException,
			SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "Nttdata@123";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, username, password);
		if (con != null) {
			System.out.println("Connected successfully");
		}

		return con;

	}

	public User validateUser(final String uid, final String pwd) throws CourseDAOException {
		Connection con = null;
		User u1 = null;
		List<User> vlist = new ArrayList<User>();
		try {
			con = establishConnection();
		} catch (ClassNotFoundException e) {
			throw new CourseDAOException();
		} catch (SQLException e) {
			throw new CourseDAOException();
		}

		ParamMapper mapparam = new ParamMapper() {
			@Override
			public void mapParams(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, uid);

			}
		};

		try {
			vlist = DBHelper.executeSelect(con, SqlMapper.VALIDATE_USER,
					SqlMapper.MAP_STUDENT, mapparam);
			String u = vlist.get(0).getPassword();
			// System.out.println(u);

			if (u.equals(pwd)) {

				u1 = new User(vlist.get(0).getUserId(), vlist.get(0)
						.getUserName(), vlist.get(0).getPassword(), vlist
						.get(0).getRoleId());
				return u1;

			}

			else {
				throw new CourseDAOException("invalid user");
			}

		} 
		

	catch (DBFWException e) {
			throw new CourseDAOException();
		}
		
	catch (Exception e) {
			throw new CourseDAOException("invalid user");
		}
		
	
	}

}
