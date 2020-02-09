package com.nttdata.course.domain;

public class Department {

	private String DeptId;
	private String DeptName;
	
	public Department(String did,String dname)
	{
		DeptId=did;
		DeptName=dname;
	}

	public String getDeptId() {
		return DeptId;
	}

	public void setDeptId(String deptId) {
		DeptId = deptId;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	
}
