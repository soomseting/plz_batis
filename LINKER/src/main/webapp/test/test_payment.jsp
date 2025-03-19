<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.LINKER.payment.model.PaymentDAO, com.LINKER.payment.model.PaymentDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment DAO Test</title>
</head>
<body>
    <h2>Payment DAO 테스트 페이지</h2>
    
    <%! PaymentDAO dao = PaymentDAO.getInstance(); %>
    
    <h3>결제 정보 입력</h3>
    <form action="" method="post">
        결제 금액: <input type="number" name="paymentAmount" required><br>
        결제 방법: <input type="text" name="paymentMethod" required><br>
        결제 상태: <input type="text" name="paymentStatus" required><br>
        장바구니 ID: <input type="number" name="cartId" required><br>
        <input type="submit" name="submit" value="결제 추가">
    </form>
    
    <%
        if (request.getParameter("submit") != null) {
            int paymentAmount = Integer.parseInt(request.getParameter("paymentAmount"));
            String paymentMethod = request.getParameter("paymentMethod");
            String paymentStatus = request.getParameter("paymentStatus");
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            
            PaymentDTO dto = new PaymentDTO();
            dto.setPaymentAmount(paymentAmount);
            dto.setPaymentMethod(paymentMethod);
            dto.setPaymentStatus(paymentStatus);
            dto.setCartId(cartId);
            
            dao.writePayment(dto);
            out.println("<p>결제 정보가 추가되었습니다.</p>");
        }
    %>
    
    <h3>결제 정보 조회</h3>
    <form action="" method="get">
        장바구니 ID: <input type="number" name="cartIdQuery" required>
        <input type="submit" value="조회">
    </form>
    
    <%
        String cartIdQuery = request.getParameter("cartIdQuery");
        if (cartIdQuery != null) {
            int cartId = Integer.parseInt(cartIdQuery);
            List<PaymentDTO> paymentList = dao.getPayment(cartId);
            
            if (!paymentList.isEmpty()) {
                out.println("<table border='1'><tr><th>ID</th><th>금액</th><th>방법</th><th>상태</th><th>날짜</th><th>장바구니 ID</th></tr>");
                for (PaymentDTO p : paymentList) {
                    out.println("<tr><td>" + p.getPaymentId() + "</td><td>" + p.getPaymentAmount() + "</td><td>" + p.getPaymentMethod() + "</td><td>" + p.getPaymentStatus() + "</td><td>" + p.getPaymentDate() + "</td><td>" + p.getCartId() + "</td></tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>해당 장바구니 ID의 결제 내역이 없습니다.</p>");
            }
        }
    %>
</body>
</html>
