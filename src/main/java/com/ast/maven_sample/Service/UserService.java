package com.ast.maven_sample.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ast.maven_sample.pojo.*;
@Service
public interface UserService {
	
	public abstract Login validateUser(String username,String password);
	public abstract User createBaseFolder(String foldername);
	public abstract User createFolder(String foldername, String username);
	public abstract User userRegister(User user1);
	public abstract User getUserBaseDir(String username);
	public abstract List<User> checkSubDirectory(String username);
	public abstract User insertFileName(String username,String foldername,String subfoldname);
	public abstract List<User> getUserDir(String username);
	public abstract List<User> getUserFile(String username);	
	public abstract List<User> getUserSubDir(String username,String SelectedFolderName);
	public abstract List<User> getUserSubFile(String username,String SelectedFolderName);	
	//public abstract User deleteFile(String username,String filename);
	public abstract User deleteFunc(String username,String filename);
}
