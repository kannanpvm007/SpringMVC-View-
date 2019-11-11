package com.ast.maven_sample.Controller;

import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ast.maven_sample.Service.UserService;

import com.ast.maven_sample.pojo.User;
import com.ast.maven_sample.Utils.Constants;


@Controller
public class UserController extends HttpServlet {
	@Autowired
	UserService userService;
	@RequestMapping(value = "/createfolder", method = RequestMethod.POST)
	 public ModelAndView createfolder(HttpServletRequest request, HttpServletResponse response) throws IOException
	 {
		HttpSession session=request.getSession(false);  
		String foldername = JOptionPane.showInputDialog(null, "Enter the Folder Name");
		
		System.out.println("Entering into UserPage");
		System.out.println("FolderName"+ foldername);
		
		 ModelAndView mav = null;
	        
	        String name=(String)session.getAttribute("USERNAME");  
	        System.out.println("SESSION username"+ name);
	        if(foldername==null)
	        {
	        	System.out.println("Enter into null condition");
	        	mav=viewDetails(request, response);
	        }
	        else
	        {	
	        if(foldername.isEmpty())
	        {
	        	JOptionPane.showMessageDialog(null,"Please enter the Folder name");
	        	mav=viewDetails(request, response);
	 		    
	        }
	        else
	        {
	        	 User user3 = userService.createFolder(foldername,name);
	 	        if(user3.getflag()==true)
	 	        {
	 	        System.out.println("Folder name afterservice"+ foldername);
	 	       	mav=viewDetails(request, response); 
	 	        }
	 	        else
	 	        {
	 	        	JOptionPane.showMessageDialog(null,"Foldername alerady exists... Try Another");
	 	        		mav=viewDetails(request, response);
	 		 		   
	 	        }
	        	 
	        }
	        }
		   
	        return mav;
		
	 }
	
	 @RequestMapping(value = "/viewDetail", method = RequestMethod.POST)
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
   
		    if(user1.size()==0 && user2.size()==0 )
		    {
		    	System.out.println("UserSubDir value null");
		    	mav = new ModelAndView();
			    mav.setViewName("welcome");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("filelist",file);
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
		    mav.addObject("filelist",file);
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
			    mav.addObject("filelist",file);
			
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
			   mav.addObject("filelist",file);
			  
		    }
		    return mav; 
		    
	 }
	 
	 @RequestMapping(value = "/viewSubDetail", method = RequestMethod.POST)
	  public ModelAndView viewSubDetails(HttpServletRequest request, HttpServletResponse response,String SelectedFolderName)
	 {
		  System.out.println("Enter into view Subdetails page");
		  HttpSession session=request.getSession(false);
			 String username=(String)session.getAttribute("USERNAME");
			 System.out.println("Session Username"+username);
		  	ModelAndView mav = null;
		    List<User> user1=userService.getUserSubDir(username,SelectedFolderName);
		    System.out.println("After getting userdetails");
		    List<String> dir=new ArrayList<>();
		    List<User> user2=userService.getUserSubFile(username,SelectedFolderName);
		    List<String> file=new ArrayList<>();
  
		    if(user1.size()==0 && user2.size()==0 )
		    {
		    	System.out.println("UserSubDir value null");
		    	mav = new ModelAndView();
			    mav.setViewName("SubNode1");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("filelist",file);
			    mav.addObject("subfoldname", SelectedFolderName);
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
		    mav.setViewName("SubNode1");
		    mav.addObject("firstname", username);
		    mav.addObject("dirlist",dir);
		    mav.addObject("filelist",file);
		    mav.addObject("subfoldname", SelectedFolderName);
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
			    mav.setViewName("SubNode1");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			    mav.addObject("filelist",file);
			    mav.addObject("subfoldname", SelectedFolderName);
			
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
			    mav.setViewName("SubNode1");
			    mav.addObject("firstname", username);
			    mav.addObject("dirlist",dir);
			   mav.addObject("filelist",file);
			   mav.addObject("subfoldname", SelectedFolderName);
		    }
		    return mav; 
		    
	 }
	
	@RequestMapping(value = "/userfile", method = RequestMethod.POST)
	 public ModelAndView openFile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException
	 {
		String SelectedFileName=request.getParameter("myFile");
		System.out.println("SelectedFileName"+SelectedFileName);
		ModelAndView mav = null;
		if(SelectedFileName.isEmpty())
		{	
			mav=viewDetails(request, response);
		   	

		}
		else
		{
			HttpSession session=request.getSession(false);
			 String name=(String)session.getAttribute("USERNAME"); 
			 User user1=userService.getUserBaseDir(name);
			 String path=user1.getUserbasedir()+'/'+SelectedFileName;
			System.out.println("Path"+path);
			System.out.println("SelectedFileName"+SelectedFileName);
			File file = new File(path);
			if(!Desktop.isDesktopSupported()){
	            System.out.println("Desktop is not supported");
	                 }
	        
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists()) 
	        	{
	        	desktop.open(file);
	        	}
	        Thread.sleep(2000);
	        mav=viewDetails(request, response);

		}
		return mav;
	 }
	@RequestMapping(value = "/usersubfile", method = RequestMethod.POST)
	 public ModelAndView openSubFile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException
	 {
		String SelectedFileName=request.getParameter("myFile");
		String SubFoldName=request.getParameter("mySubFold");
		System.out.println("SubFolderName"+SubFoldName);
		System.out.println("SelectedFileName"+SelectedFileName);
		ModelAndView mav = null;
		if(SelectedFileName.isEmpty())
		{	
			mav=viewSubDetails(request, response,SubFoldName);
		    mav.addObject("filemessage","Please double click the file name to open");	

		}
		else
		{
			HttpSession session=request.getSession(false);
			String name=(String)session.getAttribute("USERNAME"); 
			User user1=userService.getUserBaseDir(name);
			String path=user1.getUserbasedir()+'/'+SubFoldName+'/'+SelectedFileName;
			System.out.println("Path"+path);
			File file = new File(path);
			if(!Desktop.isDesktopSupported()){
	            System.out.println("Desktop is not supported");
	                 }
	        
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists()) 
	        	{
	        	desktop.open(file);
	        	}
	        Thread.sleep(2000);
	        mav=viewSubDetails(request, response,SubFoldName);

		}
		return mav;
	 }
	@RequestMapping(value = "/userdir", method = RequestMethod.POST)
	 public ModelAndView openFolder(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException
	 {
		System.out.println("Entering into userdirectory page");
		String SelectedFolderName=request.getParameter("myFolder");
		System.out.println("SelectedFolderName"+SelectedFolderName);
		ModelAndView mav = null;
		if(SelectedFolderName.isEmpty())
		{	
			mav=viewDetails(request, response);
			
		}
		else
		{
			mav=viewSubDetails(request,response,SelectedFolderName);
		
		  	}
		return mav;
	 }

	@RequestMapping(value = "/deletesubfile", method = RequestMethod.POST)
	 public ModelAndView deletesubfile(HttpServletRequest request, HttpServletResponse response)
	 {
		 
		System.out.println("Entering into Delete Page");
		String SelectedFileName=request.getParameter("myFile");
		System.out.println("Selected Value"+SelectedFileName);
		String SubFoldName=request.getParameter("mySubFold");
		System.out.println("SubFolderName"+SubFoldName);
		ModelAndView mav = null;
		HttpSession session=request.getSession(false);  
		String name=(String)session.getAttribute("USERNAME");
		if(SelectedFileName.equals("undefined"))
		{
			JOptionPane.showMessageDialog(null,"Please select a File or Folder");
			mav=viewSubDetails(request,response,SubFoldName);	
			  
        }
        		
		else
		{
		User user1=userService.getUserBaseDir(name);
		String path=user1.getUserbasedir()+'/'+SubFoldName+'/'+SelectedFileName;
		System.out.println("Path"+path);
		int input = JOptionPane.showConfirmDialog(null, "Do you want to delete the "+ SelectedFileName+" ?");
        // 0=yes, 1=no, 2=cancel
        System.out.println(input);
        if(input==0)
        {
		
		File file = new File(path); 
		if(file.delete()) 
       { 
           System.out.println("File deleted successfully"); 
           User del=userService.deleteFunc(name,SelectedFileName);
           mav=viewSubDetails(request,response,SubFoldName);
           mav.addObject("subfoldname", SubFoldName);
		 
       } 
       else
       { 
           System.out.println("Failed to delete the file");
           mav=viewSubDetails(request,response,SubFoldName);
           mav.addObject("subfoldname",SubFoldName );
		   
       } 
        }
        else
        {
        	mav=viewSubDetails(request,response,SubFoldName);
            mav.addObject("subfoldname", SubFoldName);
        }
		
		}
		    
	        return mav;
		
	 }
	public void deleteRecursive(File path){
	    File[] c = path.listFiles();
	    System.out.println("Cleaning out folder:" + path.toString());
	     for (File file : c){
	        if (file.isDirectory()){
	            System.out.println("Deleting file:" + file.toString());
	            deleteRecursive(file);
	            file.delete();
	        } else {
	            file.delete();
	            System.out.println("Deleting file:" + file.toString());
	        }
	    }
	    path.delete();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	 public ModelAndView deleteFunc(HttpServletRequest request, HttpServletResponse response)
	 {
		System.out.println("Entering into Delete Folder Page");
		String SelectedFileName=request.getParameter("myFold");
		System.out.println("Selected Value"+SelectedFileName);
		ModelAndView mav = null;
		if(SelectedFileName.equals("undefined"))
		{
			JOptionPane.showMessageDialog(null,"Please select a File or Folder");
			mav=viewDetails(request,response);	
			  
        }
        		
		else
		{
			int input = JOptionPane.showConfirmDialog(null, "Do you want to delete the "+ SelectedFileName+" ?");
	        // 0=yes, 1=no, 2=cancel
	        System.out.println(input);
	        if(input==0)
	        {
			
			HttpSession session=request.getSession(false);  
			String name=(String)session.getAttribute("USERNAME");
			System.out.println("Name"+name);
			User user1=userService.getUserBaseDir(name);
			
			String path=user1.getUserbasedir()+'/'+SelectedFileName;
				File file = new File(path); 
				if(file.delete())
				{
					System.out.println("File deleted successfully"); 
				}
				else
				{	
				deleteRecursive(file);
				System.out.println("Recursively deleted successfully"); 
				}
				User del=userService.deleteFunc(name,SelectedFileName);
				mav=viewDetails(request,response);
	        }
	        else
	        {
	        	mav=viewDetails(request,response);
	        }
		}
        return mav;
       } 
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	 public ModelAndView download(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, MalformedURLException
	 {
		System.out.println("Entering into Delete Folder Page");
		String SelectedFileName=request.getParameter("myDownFile");
		System.out.println("Selected Value"+SelectedFileName);
		ModelAndView mav = null;
		if(SelectedFileName.equals("undefined"))
		{
			JOptionPane.showMessageDialog(null,"Please select a File");
			mav=viewDetails(request,response);	
			  
       }
       		
		else
		{
			int input = JOptionPane.showConfirmDialog(null, "Do you want to download the "+ SelectedFileName+" ?");
	        // 0=yes, 1=no, 2=cancel
	        System.out.println(input);
	        if(input==0)
	        {
			
			HttpSession session=request.getSession(false);  
			String name=(String)session.getAttribute("USERNAME");
			System.out.println("Name"+name);
			User user1=userService.getUserBaseDir(name);
			
			String path="file:///"+user1.getUserbasedir();
			String FILE_URL=Constants.DOWNLOAD_PATH;
			URL url = new URL(path);
			System.out.println("Path"+url.getPath());	
			System.out.println("File"+url.getFile());
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(FILE_URL + "/" + SelectedFileName);
			byte[] buffer = new byte[4096];
	        int bytesRead = 0;

	        System.out.println("Downloading " + SelectedFileName);
	        while ((bytesRead = is.read(buffer)) != -1) {
	            fos.write(buffer, 0, bytesRead);
	        }

	        // Close destination stream
	        fos.close();
	        // Close URL stream
	        is.close();
	        JOptionPane.showMessageDialog(null,SelectedFileName+ " file downloaded in "+FILE_URL);
	        mav=viewDetails(request,response);
			 }
	        else
	        {
	        	mav=viewDetails(request,response);
	        }
		}
		return mav;
	 }
	
	@RequestMapping(value = "/downloadsub", method = RequestMethod.POST)
	 public ModelAndView downloadsub(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, MalformedURLException
	 {
		System.out.println("Entering into Delete Folder Page");
		String SelectedFileName=request.getParameter("myDownFile");
		System.out.println("Selected Value"+SelectedFileName);
		String SubFoldName=request.getParameter("mySubFold");
		System.out.println("Sub Folder Name"+SubFoldName);
		ModelAndView mav = null;
		if(SelectedFileName.equals("undefined"))
		{
			JOptionPane.showMessageDialog(null,"Please select a File");
			mav=viewSubDetails(request,response,SubFoldName);	
			  
      }
      		
		else
		{
			int input = JOptionPane.showConfirmDialog(null, "Do you want to download the "+ SelectedFileName+" ?");
	        // 0=yes, 1=no, 2=cancel
	        System.out.println(input);
	        if(input==0)
	        {
			
			HttpSession session=request.getSession(false);  
			String name=(String)session.getAttribute("USERNAME");
			System.out.println("Name"+name);
			User user1=userService.getUserBaseDir(name);
			
			String path="file:///"+user1.getUserbasedir()+'/'+SubFoldName;
			String FILE_URL=Constants.DOWNLOAD_PATH;
			URL url = new URL(path);
			System.out.println("Path"+url.getPath());	
			System.out.println("File"+url.getFile());
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(FILE_URL + "/" + SelectedFileName);
			byte[] buffer = new byte[4096];
	        int bytesRead = 0;

	        System.out.println("Downloading " + SelectedFileName);
	        while ((bytesRead = is.read(buffer)) != -1) {
	            fos.write(buffer, 0, bytesRead);
	        }

	        // Close destination stream
	        fos.close();
	        // Close URL stream
	        is.close();
	        JOptionPane.showMessageDialog(null,SelectedFileName+ " file downloaded in "+FILE_URL);
	        mav=viewSubDetails(request,response,SubFoldName);
			 }
	        else
	        {
	        	mav=viewSubDetails(request,response,SubFoldName);
	        }
		}
		return mav;
	 }
	
	
	/*@RequestMapping(value = "/deletesubfolder", method = RequestMethod.POST)
	 public ModelAndView deletesubfolder(HttpServletRequest request, HttpServletResponse response)
	 {
		 
		System.out.println("Entering into Delete Folder Page");
		String SelectedFileName=request.getParameter("myFolder");
		System.out.println("Selected Value"+SelectedFileName);
		String SubFoldName=request.getParameter("mySubFold");
		System.out.println("SubFolderName"+SubFoldName);
		ModelAndView mav = null;
		HttpSession session=request.getSession(false);  
		String name=(String)session.getAttribute("USERNAME");
		User user1=userService.getUserBaseDir(name);
		String path=user1.getUserbasedir()+'/'+SubFoldName+'/'+SelectedFileName;
		System.out.println("Path"+path);
			File file = new File(path); 
			deleteRecursive(file);
			System.out.println("File deleted successfully"); 
			User del=userService.deleteFolder(name,SelectedFileName);
			mav=viewSubDetails(request,response,SubFoldName);
			 mav.addObject("subfoldname",SubFoldName );
			mav.addObject("foldermessage","Folder deleted successfully");
		    return mav;
      } 
         */
	       
		
	 
	
	

	public static List<String> listOfFiles(String dirname,String subfoldpath)
	{
		System.out.println("Entering into File list Function"+dirname);
		List<String> results = new ArrayList<String>();
		if(subfoldpath!=null)
		{
		File folder = new File(dirname+"/"+subfoldpath+"/");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    System.out.println("File " + listOfFiles[i].getName());
			    results.add(listOfFiles[i].getName());
			    System.out.println("File String" + results);
			  }
		}
		}
		else
		{
			File folder = new File(dirname+"/");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				  if (listOfFiles[i].isFile()) {
				    System.out.println("File " + listOfFiles[i].getName());
				    results.add(listOfFiles[i].getName());
				    System.out.println("File String" + results);
				  }
			}
		}
		return results;
	}
	
	public static boolean checkFileName(String filename,List<String> filelists)
	{
		boolean result=false;
		 if(filelists.size()!=0)
         {
         	for(String element:filelists)
                 
 	    	{
 	    		System.out.println("String Element"+element);
     	
 	    		if(element.equalsIgnoreCase(filename))
 	    		{
 	    			result=true;
 	    		}
 	    		else
 	    		{
 	    		result= false;
 	    		}
 	    	}
         }
		 else
		 {
			 System.out.println("Filelists Empty");
			 result=false;
		 }
		 return result;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	 public ModelAndView uploadFile(HttpServletRequest request, HttpServletResponse response)throws IOException
	 {
		System.out.println("Entering into UploadPage");
		
		 ModelAndView mav = null;
	       	if(ServletFileUpload.isMultipartContent(request)){
				System.out.println("MultipartContent");
				
	            try {
	            	DiskFileItemFactory factory = new DiskFileItemFactory();
	            	ServletFileUpload upload = new ServletFileUpload(factory);
	                List multiparts = upload.parseRequest(request);
	                HttpSession session=request.getSession(false);  
	        		String name=(String)session.getAttribute("USERNAME"); 
	                User user1=userService.getUserBaseDir(name);
	                System.out.println("Inside try");
	                	System.out.println("After while condition");
	                	Iterator itr1=multiparts.iterator();
	                	  while(itr1.hasNext()){
	                		  FileItem item1 = (FileItem) itr1.next();
	                		  if(!item1.isFormField()){
	
	                        String name2 = new File(item1.getName()).getName();
	                        System.out.println("name2"+name2);
	                        if(name2.isEmpty()) 
	                        {
	                        	JOptionPane.showMessageDialog(null,"Please select the file");
            	        		mav=viewDetails(request, response);
	                        }
	                        else
	                        {	
	                        List<String> results = listOfFiles(user1.getUserbasedir(),null);
	                        boolean check=checkFileName(name2,results);
	                        System.out.println(check);
	                       	if(check==true)
                   	    		{
	                       		
	                       	       	JOptionPane.showMessageDialog(null,"File already exists. Try another");
	            	        		mav=viewDetails(request, response);
	                                     			
                   	    		}
                   	    		else
                   	    		{
                   	    			
                   	    				item1.write( new File(user1.getUserbasedir()+ File.separator + name2));
                   	    				User user3=userService.insertFileName(name,name2,null);
                   	    				mav=viewDetails(request, response);
                   	    		}
                   	    			
	                        }	       	    				
                   	    		}
                   	    	}
	                        }
                       	    
	            
           	            catch (Exception ex) {
           	            	JOptionPane.showMessageDialog(null,"File Upload Failed");
        	        
	            	            }
			}
	        else
	        {
	        	System.out.println("Else condition");
	        	
		    }
	        	  
	        
	        return mav;
	 
	
			}
	@RequestMapping(value = "/uploadsub", method = RequestMethod.POST)
	 public ModelAndView uploadSubFile(HttpServletRequest request, HttpServletResponse response)throws IOException
	 {
		System.out.println("Entering into UploadPage");
		
		 ModelAndView mav = null;
	       	if(ServletFileUpload.isMultipartContent(request)){
				System.out.println("MultipartContent");
				
	            try {
	            	DiskFileItemFactory factory = new DiskFileItemFactory();
	            	ServletFileUpload upload = new ServletFileUpload(factory);
	                List multiparts = upload.parseRequest(request);
	                Iterator itr=multiparts.iterator();
	               String SelectedSubFoldName=null;	        		
	        		HttpSession session=request.getSession(false);  
	        		String name=(String)session.getAttribute("USERNAME"); 
	                User user1=userService.getUserBaseDir(name);
	                System.out.println("Inside try");
	                	while(itr.hasNext()){
	                	FileItem item = (FileItem) itr.next();
	                	System.out.println("check"+item.getFieldName());
	                	if(item.getFieldName().equals("upload"))
	                	{
	                		SelectedSubFoldName=null;
	                		System.out.println("SelectedFolderName"+SelectedSubFoldName);
	                	}
	                	else if (item.getFieldName().equals("file"))
	                	{
	                		SelectedSubFoldName=null;
	                		System.out.println("SelectedFolderName"+SelectedSubFoldName);
	                	}
	                	else if (item.getFieldName().equals("uploadmsg"))
	                	{
	                		SelectedSubFoldName=null;
	                		System.out.println("SelectedFolderName"+SelectedSubFoldName);
	                	}
	                	else
	                	{
	                		SelectedSubFoldName=item.getFieldName();
	                		System.out.println("SelectedFolderName"+SelectedSubFoldName);
	                	}
	                	}
	                	System.out.println("After while condition");
	                	Iterator itr1=multiparts.iterator();
	                	  while(itr1.hasNext()){
	                		  FileItem item1 = (FileItem) itr1.next();
	                		  if(!item1.isFormField()){
	
	                        String name2 = new File(item1.getName()).getName();
	                        System.out.println("name2"+name2);
	                        if(name2.isEmpty()) 
	                        {
	                        	JOptionPane.showMessageDialog(null,"Please select the file");
            	        		mav=viewDetails(request, response);
	                        }
	                        else
	                        {	                    
	                        List<String> results = listOfFiles(user1.getUserbasedir(),SelectedSubFoldName);
	                        boolean check=checkFileName(name2,results);
                   		
	                       	if(check==true)
                   	    		{
	                       		
	                       				mav=viewSubDetails(request, response,SelectedSubFoldName);
	                       				mav.addObject("subfoldname",SelectedSubFoldName );
	                       				JOptionPane.showMessageDialog(null,"File already exists. Try another");
	                       			
                   	    		}
                   	    		else
                   	    		{
                   	    			
                   	    				item1.write( new File(user1.getUserbasedir()+File.separator+SelectedSubFoldName+ File.separator + name2));
                   	    				User user3=userService.insertFileName(name,name2,SelectedSubFoldName);
                   	    				mav=viewSubDetails(request, response,SelectedSubFoldName);
                   	    				mav.addObject("subfoldname",SelectedSubFoldName );
                   	    				
                   	    			
                   	    			
                   	    			       	    				
                   	    		}
	                        }
                   	    	}
	                        }
                       	    
	            }
           	            catch (Exception ex) {
	            	
           	            	JOptionPane.showMessageDialog(null,"File Upload Failed");
	            	            }
			}
	        else
	        {
	        	System.out.println("Else condition");
	        	JOptionPane.showMessageDialog(null,"Please select the File");
	        		mav=viewDetails(request, response);
		    }
	        	  
	        
	        return mav;
	 
	
			}
	
	

}
