<!-- login.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.LINKER.member.model.MemberDAO, com.LINKER.member.model.MemberDTO" %>
<%
	request.setCharacterEncoding("UTF-8");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    MemberDAO dao = MemberDAO.getInstance();
    MemberDTO member = dao.login(email, password);
%>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <form method="post">
        이메일: <input type="text" name="email"><br>
        비밀번호: <input type="password" name="password"><br>
        <input type="submit" value="로그인">
    </form>
    <%
        if (member != null) {
            out.println("로그인 성공! 환영합니다, " + member.getUserName() + "님");
        } else if (email != null) {
            out.println("로그인 실패! 이메일 또는 비밀번호가 잘못되었습니다.");
        }
    %>
</body>
</html>