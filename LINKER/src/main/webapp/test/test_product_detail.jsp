<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.LINKER.productDetail.model.ProductDetailDAO, com.LINKER.productDetail.model.ProductDetailDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Detail Test</title>
</head>
<body>
    <h2>Product Detail DAO 테스트 페이지</h2>

    <h3>1. 전체 제품 목록</h3>
    <%
        ProductDetailDAO dao = ProductDetailDAO.getInstance();
        List<ProductDetailDTO> allProducts = dao.getProductDetail();
        for (ProductDetailDTO product : allProducts) {
    %>
        <p>ID: <%= product.getProductDetailId() %> | 설명: <%= product.getProductDescription() %> | 가격: <%= product.getProductPrice() %></p>
    <%
        }
    %>

    <h3>2. user_id가 1인 제품 목록</h3>
    <%
        List<ProductDetailDTO> userProducts = dao.getProductDetailByUserID(1);
        for (ProductDetailDTO product : userProducts) {
    %>
        <p>ID: <%= product.getProductDetailId() %> | 설명: <%= product.getProductDescription() %> | 가격: <%= product.getProductPrice() %></p>
    <%
        }
    %>

    <h3>3. 특정 제품 유형 (예: '전자기기') 검색</h3>
    <%
        String productType = "전자기기"; 
        List<ProductDetailDTO> typeProducts = dao.getProductDetailByProductType(productType);
        for (ProductDetailDTO product : typeProducts) {
    %>
        <p>ID: <%= product.getProductDetailId() %> | 설명: <%= product.getProductDescription() %> | 가격: <%= product.getProductPrice() %></p>
    <%
        }
    %>

    <h3>4. 특정 제품 상세보기 (product_detail_id = 1)</h3>
    <%
        int productDetailId = 1;
        ProductDetailDTO detailProduct = dao.getProductDetailByID(productDetailId);
    %>
        <p>ID: <%= detailProduct.getProductDetailId() %> | 설명: <%= detailProduct.getProductDescription() %> | 가격: <%= detailProduct.getProductPrice() %></p>

    <h3>5. 새로운 제품 추가</h3>
    <form method="post">
        설명: <input type="text" name="description"><br>
        연락처: <input type="text" name="contact"><br>
        가격: <input type="number" name="price"><br>
        유형: <input type="text" name="type"><br>
        <input type="submit" value="등록">
    </form>

    <%
        if (request.getMethod().equalsIgnoreCase("POST")) {
            ProductDetailDTO newProduct = new ProductDetailDTO();
            newProduct.setProductDescription(request.getParameter("description"));
            newProduct.setProductContactInfo(request.getParameter("contact"));
            newProduct.setProductPrice(Integer.parseInt(request.getParameter("price")));
            newProduct.setProductType(request.getParameter("type"));
            newProduct.setUserID(1); // user_id는 1로 고정
            
            dao.writeProductDetail(newProduct);
            out.println("<p>제품이 성공적으로 추가되었습니다!</p>");
        }
    %>

</body>
</html>
