<!-- join.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.LINKER.member.model.MemberDAO, com.LINKER.member.model.MemberDTO, java.sql.Timestamp" %>
<%
	request.setCharacterEncoding("UTF-8");
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String memCode = request.getParameter("memCode");
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		
        int result = MemberDAO.getInstance().emailDuplicationCheck(email);
        if(result == 1){
        	out.println("중복되는 이메일입니다. 다시 입력 바랍니다.");
        }else {
        MemberDTO newMember = new MemberDTO(0, userName, email, password, phone, createdAt, memCode, 0, 1000000);
        MemberDAO.getInstance().join(newMember);

        out.println("회원가입 성공!");
        }
        
    }
%>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <form method="post">
        이름: <input type="text" name="userName"><br>
        이메일: <input type="text" name="email"><br>
        비밀번호: <input type="password" name="password"><br>
        전화번호: <input type="text" name="phone"><br>
        회원 유형: <input type="text" name="memCode"><br>
        <input type="submit" value="회원가입">
    </form>
</body>
</html>
