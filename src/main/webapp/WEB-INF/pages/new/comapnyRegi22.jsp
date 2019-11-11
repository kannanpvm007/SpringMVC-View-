
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
        	 <a href="login2">Home</a>
        	</td>
        	</tr>
        	</table>
            <form id="CompanyRegi" modelAttribute="companyRegi" action="comapnyRegi1" method="post">
                <table align="center">
                    <tr>
                        <td>
                      
                            <form:label path="id">Company ID: </form:label>
                        </td>
                        <td>
                            <input type="text" path="id" name="id" id="id" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="company">Company Name:</form:label>
                        </td>
                        <td>
                            <input type="text" path="Comapny" name="company" id="company" />
                        </td>
                    <tr>
                        <td>
                            <form:label path="location">Location:</form:label>
                        </td>
                        <td>
                            <input type="text" path="location" name="location" id="location" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="note"> Nots:</form:label>
                        </td>
                        <td>
                            <input type="text" path="note" name="note" id="note" />
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td>
                            <form:label path="delivaryDate">Delivary Date:</form:label>
                        </td>
                        <td>
                            <input type="date" path="delivaryDate" name="delivaryDate" id="delivaryDate" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                        	<input type="submit" id="Send" name="send" value="Login"/>
                        <!-- 	<input type="submit" id="reset" name="reset" value="Reset"/> -->
                       ${done}
                            
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