<%@ page import="com.LINKER.cart.model.CartDAO" %>
<%@ page import="com.LINKER.cart.model.CartDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    int buyerID = Integer.parseInt(request.getParameter("buyerID"));
    int productDetailID = Integer.parseInt(request.getParameter("productDetailID"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));

    CartDTO dto = new CartDTO();
    dto.setBuyerID(buyerID);
    dto.setProductDetailID(productDetailID);
    dto.setQuantity(quantity);

    CartDAO.getInstace().addCart(dto);

    response.sendRedirect("test_cart.jsp"); // 추가 후 목록으로 리다이렉트
%>