package com.ast.maven_sample.dao;
import java.io.File;
import com.ast.maven_sample.Utils.Constants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ast.maven_sample.dao.UserDao;
import com.ast.maven_sample.pojo.Login;
import com.ast.maven_sample.pojo.User;
@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired()
	JdbcTemplate jdbcTemplate;
	User user1 =new User();
	Login loggeduser;
	String base_dir=com.ast.maven_sample.Utils.Constants.base_dir; 
	public User userRegister(User user1)
	{
		System.out.println("User Register DAO");
		String usr_dir=base_dir+'/'+user1.getusername();
		String sql = "insert into login(UserName,Password) values('"+user1.getusername()+"','"+user1.getPassword()+"')";
		jdbcTemplate.execute(sql);
		String sqllen="select category_id from userdir where(category_id=(SELECT MAX(category_id) FROM userdir))";
		List<User> user2 = jdbcTemplate.query(sqllen, new IdMapper());
		String sql1="insert into userdir(category_id,UserName,RootDir,parent,node,Type) values('"+(user2.get(0).getId()+1)+"','"+user1.getusername()+"','"+usr_dir+"','"+1+"','"+null+"','"+"D"+"')";
		jdbcTemplate.execute(sql1);
		
		return user1;
	}
	
	public Login validateUser(String userName, String password) {
		System.out.println("UserNameDAO"+userName);
		System.out.println("PasswordDAO"+password);
	    String sql = "select * from login where UserName='" + userName + "' and Password='" + password + "'";
	    System.out.println("Login SQL Statement"+sql);
	    List<Login> login1 = jdbcTemplate.query(sql, new UserMapper());
	    return login1.size() > 0 ? login1.get(0) : null;
	    }
	public User getUserBaseDir(String username)
	{
		System.out.println("Enter into userBaseurl");
		String sql = "select * from userdir where UserName='" + username + "' and parent='"+1+"'";
		List<User> user1 = jdbcTemplate.query(sql, new UrlMapper());
	    return user1.get(0);
	}
	public List<User> checkSubDirectory(String username)
	{
		
		String sql = "select * from userdir where UserName='" + username + "' and parent='"+ 2 +"'";
		System.out.println("Sub directory"+sql);
	    List<User> user1 = jdbcTemplate.query(sql, new SubDirectoryMapper());
	    return user1;
	}
	public User createBaseFolder(String foldername)
	{
		String dir=com.ast.maven_sample.Utils.Constants.base_dir; 
		System.out.println("FolderNameDAO"+foldername);
		System.out.println(dir);
		String dirname=dir+'/'+foldername;
		System.out.println(dirname);
		 File file = new File(dirname);
	        if (!file.exists()) {
	        	System.out.println("Enter into IF Condition");
	        	System.out.println(file.getName());
	            if (file.mkdir()) {
	            	user1.setflag(true);
	            	user1.setfoldername(foldername);
	                System.out.println("Base Directory is created!"+foldername);
	                 } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
	        return user1;
		
	}
	public User insertFileName(String username,String filename,String subfoldname)
	{
		System.out.println("Entering into insertfilename");
		String dirname;
		User user1=getUserBaseDir(username);
		if(subfoldname!=null)
		{
		String subfoldpath=user1.getUserbasedir()+'/'+subfoldname;
		String sqllen="select category_id from userdir where(category_id=(SELECT MAX(category_id) FROM userdir))";
		List<User> user2 = jdbcTemplate.query(sqllen, new IdMapper());
		System.out.println("List size"+user2.get(0).getId());
        String sql1="insert into userdir(category_id,UserName,RootDir,parent,node,Type) values('"+(user2.get(0).getId()+1)+"','"+user1.getusername()+"','"+subfoldpath+"','"+3+"','"+filename+"','"+"F"+"')";
		System.out.println("SQL Statement"+sql1);
        jdbcTemplate.execute(sql1);
		 System.out.println("inserted successfully!"+filename);
		}
		else
		{
			
			String sqllen="select category_id from userdir where(category_id=(SELECT MAX(category_id) FROM userdir))";
			List<User> user2 = jdbcTemplate.query(sqllen, new IdMapper());
			System.out.println("List size"+user2.get(0).getId());
	        String sql1="insert into userdir(category_id,UserName,RootDir,parent,node,Type) values('"+(user2.get(0).getId()+1)+"','"+user1.getusername()+"','"+user1.getUserbasedir()+"','"+2+"','"+filename+"','"+"F"+"')";
			System.out.println("SQL Statement"+sql1);
	        jdbcTemplate.execute(sql1);
			 System.out.println("inserted successfully!"+filename);
		}
		 return user1;
		
	}
	/*public User deleteFile(String username,String filename)
	{
		System.out.println("Entering into deletefilename");
		User user1=getUserBaseDir(username);
		String sql="delete from userdir where UserName='"+username+"' and node='"+filename+"'";
		System.out.println("SQL Statement"+sql);
        jdbcTemplate.execute(sql);
		System.out.println("inserted successfully!"+filename);
		return user1;
	}*/
	
	public User deleteFunc(String username,String filename)
	{
		System.out.println("Entering into deletefunction");
		User user1=getUserBaseDir(username);
		String sql="delete from userdir where UserName='"+username+"' and node='"+filename+"'";
		System.out.println("SQL Statement"+sql);
        jdbcTemplate.execute(sql);
		System.out.println("inserted successfully!"+filename);
		return user1;
	}
	public List<User> getUserDir(String username)
	{
		String sql = "select Node from userdir where UserName='" + username + "' and parent ='"+2+"' and Type='"+"D"+"'";
		 System.out.println("Sql Statement"+sql);
	    List<User> user1 = jdbcTemplate.query(sql, new DirMapper());
	    return user1;
	    
	}
	public List<User> getUserFile(String username)
	{
		String sql = "select Node from userdir where UserName='" + username + "' and parent ='"+2+"' and Type='"+"F"+"'";
		 System.out.println("Sql Statement"+sql);
	    List<User> user1 = jdbcTemplate.query(sql, new FileMapper());
	    return user1;
	}
	
	public List<User> getUserSubDir(String username,String SelectedFolderName)
	{
		String sql = "select Node from userdir where UserName='" + username + "' and parent ='"+3+"' and Type='"+"D"+"' and RootDir Like '%"+SelectedFolderName+"'";
		 System.out.println("Sql Statement"+sql);
	    List<User> user1 = jdbcTemplate.query(sql, new DirMapper());
	    return user1;
	    
	}
	public List<User> getUserSubFile(String username,String SelectedFolderName)
	{
		String sql = "select Node from userdir where UserName='" + username + "' and parent ='"+3+"' and Type='"+"F"+"' and RootDir Like '%"+SelectedFolderName+"'";
		 System.out.println("Sql Statement"+sql);
	    List<User> user1 = jdbcTemplate.query(sql, new FileMapper());
	    return user1;
	}
	
	
	public User createFolder(String foldername, String username)
	{
		System.out.println("Enter into dao create folder");
		
		User user1=getUserBaseDir(username);
		
		String dirname;
			dirname=user1.getUserbasedir()+'/'+foldername;
			System.out.println(dirname);
			File file = new File(dirname);
	        if (!file.exists()) {
	        	System.out.println("Enter into IF Condition");
	        	System.out.println(file.getName());
	            if (file.mkdir()) {
	            	user1.setflag(true);
	            	user1.setfoldername(foldername);
	                System.out.println("Directory is created!"+foldername);
	                String sqllen="select category_id from userdir where(category_id=(SELECT MAX(category_id) FROM userdir))";
	        		List<User> user2 = jdbcTemplate.query(sqllen, new IdMapper());
	                String sql1="insert into userdir(category_id,UserName,RootDir,parent,node,Type) values('"+(user2.get(0).getId()+1)+"','"+user1.getusername()+"','"+user1.getUserbasedir()+"','"+2+"','"+foldername+"','"+"D"+"')";
	        		jdbcTemplate.execute(sql1);
	                 } else {
	                System.out.println("Failed to create directory!");
            }
        }
			
	        
		
		 
	        return user1;
	}
	
	

	class UserMapper implements RowMapper<Login> {
		  public Login mapRow(ResultSet rs, int arg1) throws SQLException {
		 // Login login =new Login();
			  String userName = rs.getString("username");
		        String password = rs.getString("password");
		    System.out.println("UserNameDB"+userName);
			System.out.println("PasswordDB"+password);
		    return new Login(userName,password);
		  }
}
	
	class UrlMapper implements RowMapper<User> {
		  public User mapRow(ResultSet rs, int arg1) throws SQLException {
			String userName = rs.getString("UserName");
		    String rootdir = rs.getString("RootDir");
		    System.out.println("UserNameDB"+userName);
			System.out.println("RootDirDB"+rootdir);
		    return new User(userName,rootdir);
		  }
	}
	class IdMapper implements RowMapper<User> {
		  public User mapRow(ResultSet rs, int arg1) throws SQLException {
			
		    int id = rs.getInt("category_id");
		    System.out.println("CategoryID"+id);
			return new User(id);
		  }
	}
		  
		  class DirMapper implements RowMapper<User> {
			  public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user1= new User();
				System.out.println("Inside Node Mapper");
				if(rs.equals(null))
				{
					return user1;
				}
				else
				{
			   String node = rs.getString("Node");
			   System.out.println("Node"+node);
			    user1.setfoldername(node);
			    

			   return user1;
				}
			  }
		  }
		  class FileMapper implements RowMapper<User> {
			  public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user1= new User();
				System.out.println("Inside file Mapper");
				if(rs.equals(null))
				{
					return user1;
				}
				else
				{
			   String node = rs.getString("Node");
			   System.out.println("Node"+node);
			    user1.setFileName(node);
			    return user1;
				}
			  }
		  }
		  
		  class SubDirectoryMapper implements RowMapper<User> {
			  public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user1= new User();
				String userName = rs.getString("UserName");
			   int parent = rs.getInt("parent");
			   user1.setusername(userName);
			   user1.setParent(parent);
			   System.out.println("username"+userName);
			   System.out.println("parent"+parent);
			   return user1;
			  }
		  }
	
}
