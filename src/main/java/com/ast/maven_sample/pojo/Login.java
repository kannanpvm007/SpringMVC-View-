package com.ast.maven_sample.pojo;

public class Login {
	private String username;
	private String password;
	public Login()
	{
		System.out.println("Inside Login POJO");
	}  
	  public Login(String userName, String password) {
	        this.username = userName;
	        this.password = password;
	    }
	  public String getUsername() {
	  return username;
	  }
	  public void setUsername(String username) {
	  this.username = username;
	  }
		  public String getPassword() {
	  return password;
	  }
	  public void setPassword(String password) {
	  this.password = password;
	  }

}
