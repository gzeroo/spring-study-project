<%@ page import="hello.servlet.web.domain.MemberRepository" %>
<%@ page import="hello.servlet.web.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- HTML 을 동적으로 만들기 위해 JSP 를 사용한다.--%>
<%-- '  <% %>  '  자바 쓸 수 있음 --%>
<%-- jsp도 서블릿이다. / was가 스레드에 일을 할당해서 스레드가 서블릿을 읽는다. --%>

<%
  // request와 respone 객체를 사용할 수 있다.
  MemberRepository memberRepository = MemberRepository.getInstance();
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = Member.builder()
          .username(username)
          .age(age).build();

  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <ul>
    <li>id=<%=member.getId()%> </li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
  </ul>
<a href="/index.html">메인으로 돌아가기</a>
</body>
</html>
