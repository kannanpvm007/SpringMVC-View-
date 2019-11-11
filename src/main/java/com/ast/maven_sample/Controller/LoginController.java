package com.ast.maven_sample.Controller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ast.maven_sample.Service.UserService;
import com.ast.maven_sample.Service.UserServiceImpl;
import com.ast.maven_sample.pojo.Login;
import com.ast.maven_sample.pojo.User;
@Controller
public class LoginController {
	
	@Autowired
	 UserService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	  public ModelAndView showTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
	   mav.setViewName("index");
	  
	    return mav;
	  }
	  @RequestMapping(value = "/login12", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("login", new Login());
	    mav.setViewName("home");
	    return mav;
	  }
	  @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	  public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("Login");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegisterPage(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("Register");
	    return mav;
	  }
	  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView registerProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("register") User user1)
	 {
		  System.out.println("Entering into UserRegister");
		  ModelAndView mav = new ModelAndView();
		  String password=user1.getPassword();
			String cpassword=user1.getCpassword();
			System.out.println("Password"+password);
			System.out.println("CPassword"+cpassword);
			if(password.equals(cpassword))
			{
				User userdet = userService.userRegister(user1);
				User userfolder=userService.createBaseFolder(user1.getusername());
				mav = new ModelAndView();
			    mav.setViewName("Register");
			    mav.addObject("message2", "Registered Successfully");
			}
			else
			{
				mav = new ModelAndView();
			    mav.setViewName("Register");
			    mav.addObject("message2", "Password and Confirm password does not match!!");
			}
		  
		  return mav;
		  
	 }
	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login)
	 {
		 
		System.out.println("Entering into Logintest");
		System.out.println("UserName"+login.getUsername());
		System.out.println("Password"+login.getPassword());
		
	    Login login2 = userService.validateUser(login.getUsername(),login.getPassword());
	    HttpSession session=request.getSession();  
        session.setAttribute("USERNAME",login.getUsername());  
	    ModelAndView mav = null;
	    if (null != login2) {
	    mav=viewDetails(request,response);	
	    }
	     else {
	    mav = new ModelAndView();
	    mav.setViewName("Login");;
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	  
	  @RequestMapping(value = "/logout", method = RequestMethod.POST)
	  public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response)
	 {
		  HttpSession session=request.getSession();  
          session.invalidate();
          response.setHeader("Cache-Control","no-cache"); 
          response.setHeader("Cache-Control","no-store"); 
          response.setDateHeader("Expires", 0); 
          ModelAndView mav = new ModelAndView();
          mav.setViewName("Login");
  	    	return mav;
          
	 }
	  
	  @RequestMapping(value = "/viewDetails", method = RequestMethod.POST)
	  public ModelAndView viewDetails(HttpServletRequest request, HttpServletResponse response)
	 {
		  System.out.println("Enter into view details page");
		  HttpSession session=request.getSession(false);
			 String username=(String)session.getAttribute("USERNAME");
			 System.out.println("Session Username"+username);
		  	ModelAndView mav = null;
		    List<User> user1=userService.getUserDir(username);
		    System.out.println("After getting userdetails");
		    List<String> dir=new ArrayList<>();
		    List<User> user2=userService.getUserFile(username);
		    List<String> file=new ArrayList<>();
		    List<String> dir1=new ArrayList<>();
		    List<String> file1=new ArrayList<>();
		    
		    if(user1.size()==0 && user2.size()==0 )
		    {
		    	System.out.println("UserSubDir value null");
		    	mav = new ModelAndView();
			    mav.setViewName("welcome");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("dirlist2",dir1);
			    mav.addObject("filelist",file);
			    mav.addObject("filelist2",file1);
		    }
		    if(user1.size()>0 && user2.size()>0 )
		    {
		    	System.out.println("Enter into both condition get true");
		     for(int i=0;i<user1.size();i++)
		    {
		     	dir.add(user1.get(i).getfoldername());
		    	System.out.println("Dir List value"+dir.get(i));
		    	    
		    }
		    for(int i=0;i<user2.size();i++)
		    {
		     	file.add(user2.get(i).getFileName());
		    	System.out.println("File List value"+file.get(i));
		    	    
		    }
		    
		    mav = new ModelAndView();
		    mav.setViewName("welcome");
		    mav.addObject("firstname", username);
		    mav.addObject("dirlist",dir);
		    mav.addObject("dirlist2",dir1);
		    mav.addObject("filelist",file);
		    mav.addObject("filelist2",file1);
		    }
		    if(user1.size()>0 && user2.size()==0 )
		    {
		    	System.out.println("Enter into dir condition get true");
		    	for(int i=0;i<user1.size();i++)
			    {
			     	dir.add(user1.get(i).getfoldername());
			    	System.out.println("Dir List value"+dir.get(i));
			    	    
			    }
		    	mav = new ModelAndView();
			    mav.setViewName("welcome");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("dirlist2",dir1);
			    mav.addObject("filelist",file);
			    mav.addObject("filelist2",file1);
		    }
		    
		    if(user1.size()==0 && user2.size()>0 )
		    {
		    	System.out.println("Enter into file condition get true");
		    	for(int i=0;i<user2.size();i++)
			    {
			     	file.add(user2.get(i).getFileName());
			    	System.out.println("File List value"+file.get(i));
			    	    
			    }
		    	mav = new ModelAndView();
			    mav.setViewName("welcome");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("dirlist2",dir1);
			    mav.addObject("filelist",file);
			    mav.addObject("filelist2",file1);
		    }
		    return mav; 
		    
	 }
	  
	  
	  
	  public String[] listOfFilesFolder( String dirname)
		{
			System.out.println("Entering into list Function"+dirname);
			File folder = new File(dirname+"/");
			File[] listOfFiles = folder.listFiles();
			String[] filename=new String[20];
			System.out.println("folder name"+folder.getName());
			System.out.println("folder-listfiles"+listOfFiles.length);
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    System.out.println("File " + listOfFiles[i].getName());
			    filename[i]=listOfFiles[i].getName();
			    System.out.println("File Name" + filename[i]);
			  } else if (listOfFiles[i].isDirectory()) {
			    System.out.println("Directory " + listOfFiles[i].getName());
			  }
			}
			return filename;
		}
	  
		}
