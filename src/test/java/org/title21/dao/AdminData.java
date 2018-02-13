package org.title21.dao;

/*
 * 
 * This class will hold data like employeeID, 
 * employeeName, groupName etc so that it can be useful in executing other tests.
 * 
 */

public class AdminData {
	
	private String employeeName;
	
	private String employeeID;

	private String groupName;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
