<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.LINKER.member.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MemberDAO 테스트</title>
</head>
<body>
    <h1>MemberDAO 테스트</h1>
    <%
        MemberDAO dao = MemberDAO.getInstance();

        // 회원가입 테스트
        MemberDTO newMember = new MemberDTO(0, "Test User", "test@example.com", "password123", "010-1234-5678", new Timestamp(System.currentTimeMillis()), "M001", 0, 1000);
        dao.join(newMember);
        out.println("<p>회원가입 완료: " + newMember.getEmail() + "</p>");

        // 로그인 테스트
        MemberDTO loggedInUser = dao.login("test@example.com", "password123");
        if (loggedInUser != null) {
            out.println("<p>로그인 성공: " + loggedInUser.getUserName() + "</p>");
        } else {
            out.println("<p>로그인 실패</p>");
        }

        // 이메일 중복 체크 테스트
        int emailCheck = dao.emailDuplicationCheck("test@example.com");
        out.println("<p>이메일 중복 체크 결과 (1: 중복, 0: 없음): " + emailCheck + "</p>");

        // 회원 정보 수정 테스트
        newMember.setUserName("Updated User");
        newMember.setPassword("newpassword");
        int updateResult = dao.modify(newMember);
        out.println("<p>회원 정보 수정 결과: " + updateResult + "</p>");

        // 회원 목록 조회 테스트
        List<MemberDTO> members = dao.getMemberList();
        out.println("<h2>회원 목록</h2>");
        for (MemberDTO member : members) {
            out.println("<p>회원 ID: " + member.getUserId() + ", 이름: " + member.getUserName() + ", 이메일: " + member.getEmail() + "</p>");
        }

        // 포인트 전송 테스트 (ID 1 -> ID 2로 500 포인트 전송)
        int transferResult = dao.transferPoint(1, 50, 500);
        out.println("<p>포인트 전송 결과 (1: 성공, 0: 실패): " + transferResult + "</p>");

        // 회원 삭제 테스트
        int deleteResult = dao.delete("test@example.com");
        out.println("<p>회원 삭제 결과: " + deleteResult + "</p>");
    %>
</body>
</html>
