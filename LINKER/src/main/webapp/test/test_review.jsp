<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.LINKER.review.model.ReviewDAO" %>
<%@ page import="com.LINKER.review.model.ReviewDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성 및 조회</title>
</head>
<body>
    <h2>리뷰 작성</h2>
    <form action="test_review.jsp" method="post">
        <label>상품 ID: <input type="number" name="productDetailId" required></label><br>
        <label>사용자 ID: <input type="number" name="userId" required></label><br>
        <label>리뷰 내용: <textarea name="reviewContent" required></textarea></label><br>
        <input type="submit" value="리뷰 작성">
    </form>

    <hr>

    <h2>리뷰 목록</h2>
    <form action="test_review.jsp" method="get">
        <label>상품 ID: <input type="number" name="searchProductId" required></label>
        <input type="submit" value="리뷰 조회">
    </form>

    <%
        String method = request.getMethod();
		
    	//한글 안깨지고 입력 받기 위해서 필요
    	request.setCharacterEncoding("UTF-8");
        // 리뷰 작성 처리 (POST 요청)
        if ("POST".equalsIgnoreCase(method)) {
            int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String reviewContent = request.getParameter("reviewContent");

            ReviewDTO review = new ReviewDTO();
            review.setProductDetailId(productDetailId);
            review.setUserId(userId);
            review.setReviewContent(reviewContent);

            ReviewDAO.getInstance().writeReview(review);
            out.println("<p>리뷰가 성공적으로 작성되었습니다!</p>");
        }

        // 리뷰 목록 조회 처리 (GET 요청)
        String searchProductIdStr = request.getParameter("searchProductId");
        if (searchProductIdStr != null && !searchProductIdStr.isEmpty()) {
            int searchProductId = Integer.parseInt(searchProductIdStr);
            List<ReviewDTO> reviewList = ReviewDAO.getInstance().getReviewsByProductId(searchProductId);
    %>
            <table border="1">
                <tr>
                    <th>리뷰 ID</th>
                    <th>상품 ID</th>
                    <th>사용자 ID</th>
                    <th>리뷰 내용</th>
                    <th>작성 날짜</th>
                </tr>
                <%
                    for (ReviewDTO review : reviewList) {
                %>
                        <tr>
                            <td><%= review.getReviewId() %></td>
                            <td><%= review.getProductDetailId() %></td>
                            <td><%= review.getUserId() %></td>
                            <td><%= review.getReviewContent() %></td>
                            <td><%= review.getReviewCreatedAt() %></td>
                        </tr>
                <%
                    }
                %>
            </table>
    <%
        }
    %>

</body>
</html>