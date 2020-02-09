package com.nttdata.course.domain;

public class Professor {

	private String ProfId;
	private String ProfName;
	private String DeptId;
	
	public Professor(String pid,String pname,String did)
	{
		ProfId=pid;
		ProfName=pname;
		DeptId=did;
	}
	
	public Professor(String pname,String did)
	{
		ProfName=pname;
		DeptId=did;
		
	}

	public String getProfId() {
		return ProfId;
	}

	public void setProfId(String profId) {
		ProfId = profId;
	}

	public String getProfName() {
		return ProfName;
	}

	public void setProfName(String profName) {
		ProfName = profName;
	}

	public String getDeptId() {
		return DeptId;
	}

	public void setDeptId(String deptId) {
		DeptId = deptId;
	}
	
	
	}
