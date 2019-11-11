<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UploadFilePage</title>
</head>
<body>
<form id="UserForm" modelAttribute="userForm" action="upload" method="post">
<table align="left">
            
              
                        	
                <tr>
                <td>
                <u>Upload File</u>
                </td>
                </tr>
                <tr><td>Select the file to upload </td>
                	<td><input type="file" name="file" id="file" />
                
                </td>
                <tr>
                <td align="center"><input type="submit" id="upload" name="upload" value="upload"/>
                
                        	<input type="reset" id="reset" name="reset" value="reset"/>
                        	</td>
                </tr>           	
              
                        	  </table>
                                      	 
</form>
</body>
</html>