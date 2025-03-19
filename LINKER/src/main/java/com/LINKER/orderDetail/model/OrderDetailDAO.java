package com.LINKER.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDetailDAO {

	private static OrderDetailDAO instance = new OrderDetailDAO();
	
	private OrderDetailDAO() {
		
	}
	
	public static OrderDetailDAO getInstance() {
		return instance;
	}
	
		private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
		//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
		private String uid = "LINKER";
		private String upw = "LINKER";
		
		//주문 상세 추가
		public void addOrderDetail(OrderDetailDTO dto) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "INSERT INTO ORDER_DETAIL(ORDER_ID, PRODUCT_DETAIL_ID, PRODUCT_CNT) "
					+ "VALUES(?, ?, ?)";
			
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, uid, upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getOrderID());
				pstmt.setInt(2, dto.getProductDetailId());
				pstmt.setInt(3, dto.getProductCnt());
				
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
		
		//주문 상세 내용 반환 (order_id 받아서 해당 내용 반환)
		public OrderDetailDTO getOrderDetailByOrderId(int orderId) {
			
			//반환용 dto
			OrderDetailDTO dto = new OrderDetailDTO();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT ORDER_ID, PRODUCT_DETAIL_ID, PRODUCT_CNT "
					+ "FROM ORDER_DETAIL WHERE ORDER_ID = ?";

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, uid, upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, orderId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto.setOrderID(rs.getInt("order_id"));
					dto.setProductDetailId(rs.getInt("product_detail_id"));
					dto.setProductCnt(rs.getInt("product_cnt"));
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
			
			return dto;
		}
		
		//주문 상세 삭제 (order_id 받아서 삭제)
		public int deleteOrderDetail(int orderId) {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "DELETE FROM ORDER_DETAIL WHERE ORDER_ID = ?";
			
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
		
		
		
		
		
}
