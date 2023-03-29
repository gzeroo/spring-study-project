<%--// 자바 코드 넣을 수 있음(밑에 내용) / Ctrl + ' / ' 누르면 참조 단축키 해줌--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--// 해당 파일은 jsp 임을 알림--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username">
    age: <input type="text" name="age">
    <button type="submit">전송</button>
</form>
</body>
</html>
