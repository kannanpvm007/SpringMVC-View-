package com.ast.maven_sample.pojo;



public class UserDemoPojo {
	private String username,pasword,comapnyName,location;
	private int id,phone;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public String getComapnyName() {
		return comapnyName;
	}
	public void setComapnyName(String comapnyName) {
		this.comapnyName = comapnyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Loginpojo [username=" + username + ", pasword=" + pasword + ", comapnyName=" + comapnyName
				+ ", location=" + location + ", id=" + id + ", phone=" + phone + "]";
	}

}
