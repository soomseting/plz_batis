<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.LINKER.cart.model.CartDAO" %>
<%@ page import="com.LINKER.cart.model.CartDTO" %>

<%
    // 테스트용 사용자 ID (실제 시스템에서는 세션에서 가져와야 함)
    int buyerId = 1; 

    // DAO 인스턴스 생성
    CartDAO cartDAO = CartDAO.getInstace();

    // 장바구니 목록 가져오기
    List<CartDTO> cartList = cartDAO.getCart(buyerId);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니 테스트 페이지</title>
</head>
<body>

    <h2>장바구니 테스트</h2>

    <!-- 상품 추가 폼 -->
    <h3>장바구니 추가</h3>
    <form action="addCart.jsp" method="post">
        <label>상품 ID:</label>
        <input type="number" name="productDetailID" required><br>
        
        <label>수량:</label>
        <input type="number" name="quantity" required><br>
        
        <input type="hidden" name="buyerID" value="<%= buyerId %>">
        <button type="submit">장바구니 추가</button>
    </form>

    <hr>
    
    
		
    <!-- 장바구니 목록 -->
    <h3>장바구니 목록</h3>
    <table border="1">
        <tr>
            <th>장바구니 ID</th>
            <th>상품 ID</th>
            <th>수량</th>
            <th>삭제</th>
        </tr>
        <%
            if (cartList.isEmpty()) {
        %>
            <tr><td colspan="4">장바구니가 비어 있습니다.</td></tr>
        <%
            } else {
                for (CartDTO item : cartList) {
        %>
            <tr>
                <td><%= item.getCartID() %></td>
                <td><%= item.getProductDetailID() %></td>
                <td><%= item.getQuantity() %></td>
                <td>
                    <form action="deleteCart.jsp" method="post">
                        <input type="hidden" name="cartID" value="<%= item.getCartID() %>">
                        <button type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>
