package com.LINKER.payment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

	private static PaymentDAO instance = new PaymentDAO();
	
	private PaymentDAO() {
	}
	
	public static PaymentDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	
	//결제 정보 입력
	public void writePayment(PaymentDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO PAYMENT(PAYMENT_ID, PAYMENT_AMOUNT, PAYMENT_METHOD, "
				+ "PAYMENT_STATUS, PAYMENT_DATE, CART_ID) "
				+ "VALUES(payment_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPaymentAmount());
			pstmt.setString(2, dto.getPaymentMethod());
			pstmt.setString(3, dto.getPaymentStatus());
			pstmt.setTimestamp(4, dto.getPaymentDate());
			pstmt.setInt(5, dto.getCartId());
			
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
	
	
	//결제 정보 목록
	public List<PaymentDTO> getPayment(int cartId) {
		//반환용 리스트
        List<PaymentDTO> paymentList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        String sql = "SELECT PAYMENT_ID, PAYMENT_AMOUNT, PAYMENT_METHOD, PAYMENT_STATUS, PAYMENT_DATE, CART_ID " +
                     "FROM PAYMENT WHERE CART_ID = ?";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
                PaymentDTO dto = new PaymentDTO();
                dto.setPaymentId(rs.getInt("payment_id"));
                dto.setPaymentAmount(rs.getInt("payment_amount"));
                dto.setPaymentMethod(rs.getString("payment_method"));
                dto.setPaymentStatus(rs.getString("payment_status"));
                dto.setPaymentDate(rs.getTimestamp("payment_date"));
                dto.setCartId(rs.getInt("cart_id"));
                
                paymentList.add(dto); 
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

        return paymentList; 
    }
	
}
