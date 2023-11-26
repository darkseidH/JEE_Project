<%--
  Created by IntelliJ IDEA.
  User: darkseid
  Date: 11/25/23
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
You are logged in as <%= session.getAttribute("role") %>
</body>
</html>
