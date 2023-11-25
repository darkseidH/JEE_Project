<%--
  Created by IntelliJ IDEA.
  User: darkseid
  Date: 11/25/23
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    if(session.getAttribute("email") == null){
        response.sendRedirect("index.jsp");
    }
%>>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    You are logged in as <%= session.getAttribute("role") %>
</body>
</html>
