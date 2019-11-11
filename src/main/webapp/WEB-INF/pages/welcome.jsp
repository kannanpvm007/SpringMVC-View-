<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*" %>
<%@ page import="com.ast.maven_sample.Utils.Constants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="main/webapp/WEB-INF/js/validation.js"></script>
 <title>Welcome</title>
</head>
<body> 

<table align="right">
<tr>
 <td> Welcome ${firstname} </td>
 <td>
        	<form id="Logout" action="logout" method="post">
        	<input type="image" src="images/logout.png" alt="Submit" width="25" height="25" title="Logout">
        	<img src="images/logout.png">
        	</form>
        	 </td>
        	 </tr>
</table>


<form id="UserOpt" method="post">
<table align="left" width="100%" cellspacing="0" cellpadding="0" height="100%" style='padding-left:8px;'>
 <tr><td width="25%">
 
<button type="button" id="createfolder" onclick="createFunction();" style="background-color:white"><img src= "${Constants.IMG_PATH}/newfolder.png" name="createfolder" alt="New Folder" width="30" height="30" title="New Folder"></button>
<button type="button" id="uploadbutton" data-toggle="modal" data-target="#myModal" style="background-color:white"><img src= "${Constants.IMG_PATH}/upload.jpg" alt="Upload File" width="30" height="30" title="Upload File"></button>

<button type="button" id="delete" onclick="delFunction();" style="background-color:white"><img src= "${Constants.IMG_PATH}/delete.png" name="delete" alt="Delete" width="30" height="30" title="Delete"></button>
 <input type="hidden" name="myFold" id="myFold" value=""/>
 
<button type="button" id="download" onclick="downloadFunction();" style="background-color:white"><img src= "${Constants.IMG_PATH}/download1.jpg" name="download" alt="Download File" width="30" height="30" title="Download File"></button>
 <input type="hidden" name="myDownFile" id="myDownFile" value=""/> 
 </td>

   <td width="50%"><h3>/Root/${firstname} </h3></td>
          

 </tr>
 </table>
</form>
           
<form id="UserForm" action="userdir" method="post">
<table align="left">
            
              
                <tr>
                <td>
               	<h4> Name</h4>
                </td>
                </tr>
                <tr>
                <td>
                <%List str = (List)request.getAttribute("dirlist");%>
               <%if(str.size()==0)
               {%>
            	   This folder is empty
              <% }
               else
               {%>
              <input type="hidden" name="myFolder" id="myFolder" value=""/>
                </td>
                </tr>
             
             <%for(int i=0;i<str.size();i++) 
             {
             %>
               	<tr>
             		 <td id="txtfold<%=i%>">	
            <a onclick="selectfold('<%=i%>','<%=str.size()%>','<%=str.get(i)%>'); defaultfile();" ondblclick="folderfunction('<%=str.get(i)%>');" onmouseout="defaultcolor1('<%=i%>');">
           <input type="image" src= "${Constants.IMG_PATH}/folder.png" name="folderselect" alt="Submit" width="25" height="25">
               
               <%out.println(str.get(i));%> </input></a>
                
               
               
                            </td>
</tr>
<%
             }
               }
%>
</form>
  <div style="clear:both;"></div>
   <form id="UserFile" modelAttribute="userfile" action="userfile" method="post">
		                <tr>
                <td>
                <%List str1 = (List)request.getAttribute("filelist");%>
                <%if(str1.size()==0)
               {%>
            	  
              <% }
               else
               {%>
              
             	</td>
             	</tr>      
               
               
                      <%for(int i=0;i<str1.size();i++) 
             {
             %>
             				 <tr>
               <td id="txtfile<%=i%>">		
             		        					
    			<a onclick="selectfile('<%=i%>','<%=str1.size()%>','<%=str1.get(i)%>'); defaultfold();" ondblclick="filefunction('<%=str1.get(i)%>');" onmouseout="defaultcolor('<%=i%>');">
    			<input type="image" src= "${Constants.IMG_PATH}/File.png" name="fileselect" alt="Submit" width="25" height="25">               
               <%out.println(str1.get(i));%> </a>  
                <input type="hidden" name="myFile" id="myFile" value="<%=str1.get(i)%>"/>
            
				</input>
               </td>
             </tr>
               <%} %>  
            
               <%} %>
             	               	        
                       	  </table>
                                      	 
</form>
<div style="clear:both;"></div>
 <br></br>
 <script>
 function selectfold(val,len,name)
{
	var txtnew='txtfold'+val;
	 document.getElementById(txtnew).style.backgroundColor = "#87cefa";
	 for(var i=0;i<len;i++)
	{
		 
		 if(i!=val)
			 {
			 txt1='txtfold'+i;
			 document.getElementById(txt1).style.backgroundColor = "";
			 }
		 else
			 {
			 txt1='txtfold'+i;
			 document.getElementById(txt1).style.backgroundColor = "#87cefa";
			 }
	}
	 sessionStorage.selctedfold=name;

		 
}

function defaultfold()
{
	
	 var x = document.querySelectorAll("[id^=txtfold]");
	 var i;
	    for (i = 0; i < x.length; i++) {
	    
	        x[i].style.backgroundColor = "";
	    }
}

function defaultfile()
{
	
	 var x = document.querySelectorAll("[id^=txtfile]");
	 var i;
	    for (i = 0; i < x.length; i++) {
	    	x[i].style.backgroundColor = "";
	    }
}

function selectfile(val,len,name)
{
	
	 var txtnew='txtfile'+val;
	 document.getElementById(txtnew).style.backgroundColor = "#87cefa";
	 for(var i=0;i<len;i++)
		{
			 
			 if(i!=val)
				 {
				 txt1='txtfile'+i;
				 document.getElementById(txt1).style.backgroundColor = "";
				 }
			 else
				 {
				 txt1='txtfile'+i;
				 document.getElementById(txt1).style.backgroundColor = "#87cefa";
				 }
		}
	 sessionStorage.selctedfold=name;
	 sessionStorage.selectedfile=name;

}


function filefunction(selectedfile)
{
	
	var selectfile = selectedfile;
	document.getElementById("myFile").value=selectfile;
	var form=document.getElementById("UserFile");
	 form.submit();  
}

function folderfunction(selectedfile1)
{
	
	 var selectfile = selectedfile1;
	 document.getElementById("myFolder").value=selectfile;
	 var form=document.getElementById("UserForm");
	 form.submit();
 
		  
}

function downloadFunction()
{
	document.getElementById("myDownFile").value=sessionStorage.selectedfile;
	sessionStorage.clear();
	document.getElementById("UserOpt").action="download";
	document.getElementById("UserOpt").submit();
	
	}

function delFunction()
{

	document.getElementById("myFold").value=sessionStorage.selctedfold;
	sessionStorage.clear();
	document.getElementById("UserOpt").action="delete";
	document.getElementById("UserOpt").submit();
		 	
}

function createFunction()
{
	 document.getElementById("UserOpt").action="createfolder";
	 	document.getElementById("UserOpt").submit();
}

function uploadFunction()
{
	 document.getElementById("UserOpt").action="upload";
	 	document.getElementById("UserOpt").submit();
}

function getfilevalue()
{
	if(document.getElementById("file")==null ||document.getElementById("file")=='')
		{
		alert('Please select the File');
		var but=document.getElementById("uploadbutton");
		but.click();
		}
	else
		{
	document.getElementById("upload").submit();
		}
	
}
 </script>
 
 <div id style="clear:both;"></div>  
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
 
      <!-- Modal content-->
      <div class="modal-content">
      
           <div class="modal-body">
      <form id="upload" action="upload" method="post" enctype="multipart/form-data">
         <table align="left">

                <tr><td>Select the file to upload </td>
                	<td><input type="file" name="file" id="file" class="btn btn-default" />
                
                </td>
                </tr>
                <tr>
                <tr>
             
                    <td style="font-style: italic; color: red;">${umessage}</td>
               
              </tr>
              <tr>
              <td>
               <button type="button" class="btn btn-default" id="upload" onclick="getfilevalue();">Upload</button>
                <input type="hidden" name="uploadmsg" id="uploadmsg" value="${umessage}"/>
             
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </td></tr>
                      	  </table>
                    </form>     	 
        </div>
        <div class="modal-footer">
      
      </div>
           
      </div>
      
    </div>
  
  </div>  
  


                                      	 

<br></br>
<br></br>
<br></br>
</body></html>