<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<h2>Hello World!</h2>
<p>熟悉的空白页进行传送</p>
<% if (null != request.getSession().getAttribute("user")) {%>
<nav>
    <a href="${pageContext.request.contextPath}/main">欢迎！</a>
    <a href="${pageContext.request.contextPath}/clock">时钟play</a>
</nav>
<%} else { %>
<nav>
    <a href="${pageContext.request.contextPath}/sign">登录/注册</a>
    <a href="${pageContext.request.contextPath}/main">首页</a>
    <a href="${pageContext.request.contextPath}/clock">时钟play</a>
</nav>
<% } %>
</body>
</html>