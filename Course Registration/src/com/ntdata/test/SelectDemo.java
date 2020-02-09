package com.nttdata.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.dbcon.ConnectionHolder;
import com.nttdata.dbcon.DBConnectionException;

public class SelectDemo {

	public static void main(String[] args) {
		Connection con = null;
		ConnectionHolder ch = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			pstmt = con
					.prepareStatement("select sname,totalmark,grade from Students where sid=?");
			pstmt.setInt(1, 101);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Sname:" + rs.getString(1));
				System.out.println("Totalmarks:" + rs.getInt(2));
				System.out.println("grade:" + rs.getString(3));
			}
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
