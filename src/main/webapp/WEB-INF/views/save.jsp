<%@ page import="hello.servlet.web.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%> </li>--%>
<%--  모델에 담겨진 키로 필드를 불러올 수 있따! --%>
    <li>id= ${member.id} </li>
    <li>username= ${member.username}</li>
    <li>age= ${member.age}</li>
</ul>
<a href="/index.html">메인으로 돌아가기</a>
</body>
</html>