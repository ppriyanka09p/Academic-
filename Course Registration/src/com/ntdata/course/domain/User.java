package com.nttdata.course.domain;

public class User {

	private String UserId;
	private String UserName;
	private String Password;
	private String RoleId;
	
	public User(String uid,String uname,String pass,String rid)
	{
		UserId=uid;
		UserName=uname;
		Password=pass;
		RoleId=rid;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

		
}
