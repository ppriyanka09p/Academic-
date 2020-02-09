package com.nttdata.dbfw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.course.domain.Course;

public class DBHelper {
	public DBHelper()
	{}
	public static List executeSelect(Connection con, String query,ResultMapper outMap) throws DBFWException {
		List result = new ArrayList();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = con.prepareStatement(query);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Object res = outMap.mapRows(rs);
				result.add(res);
			}
		} catch (SQLException e) {
			throw new DBFWException("cannot execute select statement", e);

		} finally {
			try {
				rs.close();
				pStmt.close();
			} catch (SQLException e) {
				throw new DBFWException("couldnt release resource", e);
			}

		}
		return result;
	}
	
	public static int executeUpdate(Connection con, String query,ParamMapper inMap) throws DBFWException 
	{
		PreparedStatement pStmt = null;
		int result = 0;

		try {
			pStmt = con.prepareStatement(query);
			inMap.mapParams(pStmt);
			result = pStmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			throw new DBFWException(e);
		} 
		finally {
			
			try 
			{
				pStmt.close();
			} 
			catch (SQLException e) {
				throw new DBFWException(e);
			}
		}

		return result;

	}
	

	
	public static List executeSelect(Connection con, String query,
			ResultMapper outMap, ParamMapper inMap) throws DBFWException {
		List result = new ArrayList();
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = con.prepareStatement(query);
			inMap.mapParams(pStmt);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Object obj = outMap.mapRows(rs);
				result.add(obj);
			}
		} catch (SQLException e) {

			throw new DBFWException(e);
		} finally {
			try {
				rs.close();
				pStmt.close();
			} catch (SQLException e) {
				throw new DBFWException(e);
			}

		}
		return result;

	}

}
