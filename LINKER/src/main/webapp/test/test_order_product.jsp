<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.LINKER.orderProduct.model.OrderProductDAO, com.LINKER.orderProduct.model.OrderProductDTO, java.util.List" %>

<%
    OrderProductDAO dao = OrderProductDAO.getInstance();

    // 주문 추가 테스트
    for (int i = 1; i <= 10; i++) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setSelectedOptions("옵션" + i);
        dto.setUserId(1);
        dao.writeOrder(dto);
    }

    // 특정 유저의 주문 목록 가져오기
    int testUserId = 1; // 테스트할 사용자 ID
    List<OrderProductDTO> orderList = dao.getOrderByUserId(testUserId);

    // 특정 주문 삭제 테스트
    if (!orderList.isEmpty()) {
        int orderIdToDelete = orderList.get(0).getOrderId();
        dao.deleteOrder(orderIdToDelete);
    }

    // 특정 주문 상태 업데이트 테스트
    if (!orderList.isEmpty()) {
        int orderIdToUpdate = orderList.get(0).getOrderId();
        dao.updateOrderProductStatus(orderIdToUpdate, "배송중");
    }
%>

<html>
<head>
    <title>OrderProductDAO 테스트</title>
</head>
<body>
    <h2>주문 목록</h2>
    <table border="1">
        <tr>
            <th>주문 ID</th>
            <th>선택 옵션</th>
            <th>주문 날짜</th>
            <th>유저 ID</th>
            <th>상태</th>
        </tr>
        <%
            for (OrderProductDTO dto : orderList) {
        %>
        <tr>
            <td><%= dto.getOrderId() %></td>
            <td><%= dto.getSelectedOptions() %></td>
            <td><%= dto.getOrderDate() %></td>
            <td><%= dto.getUserId() %></td>
            <td><%= dto.getOrderProductStatus() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
