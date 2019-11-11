<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Folder Page</title>
</head>
<body>
<form id="UserForm" modelAttribute="userForm" action="createfolder" method="post">
<table>
<tr><td><u>Create Folder</u></td></tr>
                <tr >
                <td> <form:label path="foldername">Enter the folder name:</form:label>
                </td>
                <td><input type="text" path="foldername" name="foldername" id="foldername" />
                </td>
              </tr>
              <tr>
             
                    <td style="font-style: italic; color: red;">${message}</td>
               
              </tr>
              <tr>
                       <td align="center">
                        	<input type="submit" id="createfolder" name="createfolder" value="createfolder"/>
                           	<input type="reset" id="reset" name="reset" value="reset"/>
                        	</td>
                        	</tr>
</table>
</form>
</body>
</html>