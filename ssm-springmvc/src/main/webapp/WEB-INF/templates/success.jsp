<%--
  Created by IntelliJ IDEA.
  User: okc
  Date: 2023/11/15
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>success</h1>
    <%-- 测试在.jsp页面动态获取共享域数据 --%>
    <p style="color: red;font-size: 20px;font-weight: bold">${requestData}</p>
    <p style="color: red;font-size: large;font-weight: bold">${sessionData}</p>
    <p style="color: red;font-size: medium;font-weight: bold">${applicationData}</p>
</body>
</html>
