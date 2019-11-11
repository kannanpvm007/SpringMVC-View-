<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.*" %>
    <%@ page import="com.ast.maven_sample.Utils.Constants" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style > blink {
  -webkit-animation: 0.5s linear infinite condemned_blink_effect; // for Safari 4.0 - 8.0
  animation: 2s linear infinite condemned_blink_effect;
}
@-webkit-keyframes condemned_blink_effect { // for Safari 4.0 - 8.0
  0% {
    visibility: hidden;
  }
  50% {
    visibility: hidden;
  }
  100% {
    visibility: visible;
  }
}
@keyframes condemned_blink_effect {
  0% {
    visibility: hidden;
  }
  50% {
    visibility: hidden;
  }
  100% {
    visibility: visible;
  }
}</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ATS  clients</title>
</head>
<body> <center>
 <a href="comapnyRegi">Home</a>

<blink>  <h3><font color="green"> <p>${done}</p> </font>  </h3></center> </blink>

<table align="Center">
     <tr>

    <c:forEach items="${alldil}" var="data">
    <p>Comapny id: ${data.id}</p><br/>
    <p> Comapny name :${data.company}</p><br/>
    <p>Comapny palce :${data.location}</p><br/>
    <p> qury : ${data.note}</p><br/>
    <p> date: ${data.delivaryDate} </p><br/>
    <%--  <p> cont: ${data.size()} --%>
    <p>_____________ </p> <br/> <center>
   
  
</c:forEach>

     
     
     <td>  </td></tr> 
     
     
     
     
     
</table>
</body>
</html>