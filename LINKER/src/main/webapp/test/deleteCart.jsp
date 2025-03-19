<%@ page import="com.LINKER.cart.model.CartDAO" %>
<%
    int cartID = Integer.parseInt(request.getParameter("cartID"));

    CartDAO.getInstace().deleteCart(cartID);

    response.sendRedirect("test_cart.jsp"); // 삭제 후 목록으로 리다이렉트
%>