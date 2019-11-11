<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Form</title>
</head>
<body>
<table align="right">
        	<tr>
        	<td>
        	 <a href="login">Home</a>
        	</td>
        	</tr>
        	</table>
            <form id="registerForm" modelAttribute="register" action="registerProcess" method="post">
                <table align="center">
                    <tr>
                        <td>
                            <form:label path="username">Username: </form:label>
                        </td>
                        <td>
                            <input type="text" path="username" name="username" id="username" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password:</form:label>
                        </td>
                        <td>
                            <input type="password" path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Confirm Password:</form:label>
                        </td>
                        <td>
                            <input type="password" path="cpassword" name="cpassword" id="cpassword" />
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
                    <td style="font-style: italic; color: red;">${message2}</td>
                </tr>
            </table>

</body>
</html>