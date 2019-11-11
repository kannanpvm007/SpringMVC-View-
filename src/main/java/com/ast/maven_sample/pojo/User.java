package com.ast.maven_sample.pojo;

public class User {
	private String username;
	private String password;
	private String cpassword;
	private String userbasedir;
	private String foldername;
	private boolean flag;
	private String filename;
	private int parent;
	private int id;

	public User()
	{
		//System.out.println("Inside user POJO");
	}  
	public User(String userName,String userBasedir)
	{
		this.username=userName;
		this.userbasedir=userBasedir;
	}
	  
	public int getParent()
	{
		return parent;
	}
	public void setParent(int Parent)
	{
		this.parent=Parent;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int ID)
	{
		this.id=ID;
	}
	 public String getCpassword() {
		  return cpassword;
		  }
		  public void setCpassword(String Cpassword) {
		  this.cpassword = Cpassword;
		  }

	public User(String foldername) {
	        this.foldername = foldername;
	       	    }
	public User(int id) {
		this.id=id;
	}
	
	 public String getPassword() {
		  return password;
		  }
		  public void setPassword(String password) {
		  this.password = password;
		  }

	 
	public String getusername() {
		  return username;
		  }
		 
		public void setusername(String 
				Username) {
		  this.username = Username;
		  }
		
	public String getUserbasedir() {
			  return userbasedir;
			  }
			 
	public void setUserbasedir(String 
			Userbasedir) {
			  this.userbasedir = Userbasedir;
			  }
		
	public String getfoldername() {
	  return foldername;
	  }
	 
	public void setfoldername(String 
			  Foldername) {
	  this.foldername = Foldername;
	  }
	 
	public String getFileName() {
	 return filename;
		  }
		
	public void setFileName(String fileName) {
		  this.filename = fileName;
		  }
	 
	public boolean getflag()
	  {
		  return flag;
	  }
	  
	public void setflag(boolean Flag)
	  {
		  this.flag= Flag;
	  }
}
