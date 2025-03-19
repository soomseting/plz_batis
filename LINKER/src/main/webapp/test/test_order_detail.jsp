<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.LINKER.orderDetail.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OrderDetailDAO 테스트</title>
</head>
<body>
    <h2>OrderDetailDAO 테스트</h2>

    <h3>1. 주문 상세 추가</h3>
    <%
        OrderDetailDAO orderDAO = OrderDetailDAO.getInstance();
        OrderDetailDTO orderDTO = new OrderDetailDTO();
        orderDTO.setOrderID(5001);
        orderDTO.setProductDetailId(1);
        orderDTO.setProductCnt(2);

        orderDAO.addOrderDetail(orderDTO);
        out.println("<p>주문 상세 추가 완료! (Order ID: 5001, Product ID: 1, Count: 2)</p>");
    %>

    <h3>2. 주문 상세 조회</h3>
    <form method="get">
        조회할 ORDER_ID: <input type="text" name="order_id">
        <input type="submit" value="조회">
    </form>

    <%
        if (request.getParameter("order_id") != null) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            OrderDetailDTO orderDetail = orderDAO.getOrderDetailByOrderId(orderId);

            if (orderDetail.getOrderID() != 0) {
                out.println("<p>주문 ID: " + orderDetail.getOrderID() + ", 상품 ID: " + orderDetail.getProductDetailId() + ", 수량: " + orderDetail.getProductCnt() + "</p>");
            } else {
                out.println("<p>주문 상세 정보가 없습니다.</p>");
            }
        }
    %>

    <h3>3. 주문 상세 삭제</h3>
    <form method="post">
        삭제할 ORDER_ID: <input type="text" name="order_id">
        <input type="submit" value="삭제">
    </form>

    <%
        if (request.getMethod().equalsIgnoreCase("POST")) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            int result = orderDAO.deleteOrderDetail(orderId);

            if (result > 0) {
                out.println("<p>주문 상세 삭제 성공! (ORDER_ID: " + orderId + ")</p>");
            } else {
                out.println("<p>삭제 실패! 해당 ORDER_ID가 존재하지 않습니다.</p>");
            }
        }
    %>

</body>
</html>
