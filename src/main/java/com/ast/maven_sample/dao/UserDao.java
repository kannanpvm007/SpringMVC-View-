package com.ast.maven_sample.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ast.maven_sample.pojo.Login;
import com.ast.maven_sample.pojo.User;
@Repository
public interface UserDao {
	 
	 public Login validateUser(String username,String password);
	 public User createBaseFolder(String foldername);
	 public User createFolder(String foldername, String username);
	 public User userRegister(User user1);
	 public User getUserBaseDir(String username);
	 public List<User> checkSubDirectory(String username);
	 public User insertFileName(String username,String filename,String subfoldname);
	 public List<User> getUserDir(String username);
	 public List<User> getUserFile(String username);
	// public User deleteFile(String username,String filename);
	 public User deleteFunc(String username,String filename);
}