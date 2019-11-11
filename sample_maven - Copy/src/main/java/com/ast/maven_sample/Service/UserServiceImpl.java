package com.ast.maven_sample.Service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.maven_sample.dao.UserDaoImpl;
import com.ast.maven_sample.pojo.Login;
import com.ast.maven_sample.pojo.User;
@Service
public class UserServiceImpl implements UserService{

	@Autowired()
	UserDaoImpl userDao;
	
	public Login validateUser(String username, String password)
	{
		return userDao.validateUser(username, password);
	}
	public User createBaseFolder(String foldername)
	{
		return userDao.createBaseFolder(foldername);
}
	
	public User createFolder(String foldername, String username)
	{
		return userDao.createFolder(foldername,username);
	}

	public User userRegister(User user1)
	{
		return userDao.userRegister(user1);
	}
	
	public User getUserBaseDir(String username)
	{
		return userDao.getUserBaseDir(username);
	}
	/*public User deleteFile(String username,String filename)
	{
		return userDao.deleteFile(username,filename);
	}*/
	public User deleteFunc(String username,String filename)
	{
		return userDao.deleteFunc(username,filename);
	}

	public User insertFileName(String username,String filename,String subfoldname)
	{
		return userDao.insertFileName(username,filename,subfoldname);
	}
	public List<User> getUserDir(String username)
	{
		return userDao.getUserDir(username);
	}
	public List<User> getUserFile(String username)
	{
		return userDao.getUserFile(username);
	}
	
	
	public List<User> getUserSubDir(String username,String SelectedFolderName)
	{
		return userDao.getUserSubDir(username,SelectedFolderName);
	}
	public List<User> getUserSubFile(String username, String SelectedFolderName)
	{
		return userDao.getUserSubFile(username,SelectedFolderName);
	}
	public List<User> checkSubDirectory(String username)
	{
		return userDao.checkSubDirectory(username);
	}

	
}
