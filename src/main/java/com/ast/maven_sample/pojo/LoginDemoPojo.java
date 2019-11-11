package com.ast.maven_sample.pojo;

public class LoginDemoPojo {
private int id;private String userNmae,password;



public LoginDemoPojo() {
	System.out.println("hai");
}
public LoginDemoPojo(String userNmae, String password) {
	this.userNmae =userNmae;
	this.password = password;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserNmae() {
	return userNmae;
}
public void setUserNmae(String userNmae) {
	this.userNmae = userNmae;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "DemoUserPojo [id=" + id + ", userNmae=" + userNmae + ", password=" + password + "]";
}

}
