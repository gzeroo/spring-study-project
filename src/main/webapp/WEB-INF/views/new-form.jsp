<%--// 자바 코드 넣을 수 있음(밑에 내용) / Ctrl + ' / ' 누르면 참조 단축키 해줌--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--// 해당 파일은 jsp 임을 알림--%>
<%-- ' / ' 절대 경로 ,  경로에 '/' 가 없으면 상대 경로: 현재 폴더에 있는 파일로 감 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="save" method="post">
    username: <input type="text" name="username">
    age: <input type="text" name="age">
    <button type="submit">전송</button>
</form>
</body>
</html>
