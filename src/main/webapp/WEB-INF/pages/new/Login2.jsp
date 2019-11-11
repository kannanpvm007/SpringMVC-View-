<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body bgcolor="#D7BDE2">

            <form id="loginForm" modelAttribute="login2" action="companydil" method="post" enctype="multipart/form-data">
                <table align="center">
                    <tr>
                        <td>
                            <form:label path=userNmae>Username: </form:label>
                        </td>
                        <td>
                            <input type="text" path="userNmae" name="userNmae" id="userNmae" maxlength="10" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password:</form:label>
                        </td>
                        <td>
                            <input type="password" path="password" name="password" id="password" maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                        	<input type="submit" id="login" name="login" value="Login"/>
                        	<input type="submit" id="reset" name="reset" value="Reset"/>
                            
                        </td>
                    </tr>
                    <tr></tr>
                    
                </table>
            </form>
            <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
                <tr>
                <td>
                <h3>New User?<a href="register"> Register Here..</a></h3>
                </td>
                </tr>
            </table>
            
            
</body>
</html>