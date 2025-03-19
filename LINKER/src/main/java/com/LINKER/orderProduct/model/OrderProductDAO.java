package com.LINKER.orderProduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO {

	private static OrderProductDAO instance = new OrderProductDAO();
	
	private OrderProductDAO() {
	}
	
	public static OrderProductDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	// 주문 추가
	public void writeOrder(OrderProductDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO ORDER_PRODUCT(ORDER_ID, SELECTED_OPTIONS, USER_ID) "
				+ "VALUES(order_product_seq.NEXTVAL, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSelectedOptions());
			pstmt.setInt(2, dto.getUserId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
		
	}
	
	//주문 목록 보기(유저 아이디 입력 받아서 해당 유저의 주문 목록 보기)
	public List<OrderProductDTO> getOrderByUserId(int userId) {
		//반환용 리스트
        List<OrderProductDTO> orderList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT ORDER_ID, SELECTED_OPTIONS, ORDER_DATE, USER_ID, ORDER_PRODUCT_STATUS " +
                     "FROM ORDER_PRODUCT WHERE USER_ID = ? ORDER BY ORDER_DATE DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				OrderProductDTO dto = new OrderProductDTO();
				dto.setOrderId(rs.getInt("order_id"));
				dto.setSelectedOptions(rs.getString("selected_options"));
				dto.setOrderDate(rs.getTimestamp("order_date"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setOrderProductStatus(rs.getString("order_product_status"));
                orderList.add(dto); 
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}

        return orderList;
    }
	
	
	//주문 삭제 (order_id 입력 받아서 한개 삭제)
	public int deleteOrder(int orderId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM ORDER_PRODUCT WHERE ORDER_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			
			result = pstmt.executeUpdate(); //성공시 1, 실패시 0 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
		
		return result;
	}
	
	
	//주문 상태 업데이트
	public int updateOrderProductStatus(int orderId, String orderProductStatus) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE ORDER_PRODUCT SET ORDER_PRODUCT_STATUS = ? "
				+ "WHERE ORDER_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderProductStatus);
			pstmt.setInt(2, orderId);
			
			result = pstmt.executeUpdate(); //성공시 1반환, 실패시 0
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
		return result;
	}
	
}


















