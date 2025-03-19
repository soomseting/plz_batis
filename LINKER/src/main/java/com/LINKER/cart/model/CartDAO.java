package com.LINKER.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CartDAO {

	private static CartDAO instance = new CartDAO();
	
	private CartDAO() {
	}
	
	public static CartDAO getInstace() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	//장바구니 추가
	public void addCart(CartDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO CART (CART_ID, BUYER_ID, QUANTITY, PRODUCT_DETAIL_ID) "
				+ "VALUES(cart_seq.NEXTVAL, ?, ?, ?)" ;
		
		String sql2 = "UPDATE MEMBER SET CART_ID "
				+ "WHERE USER_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.buyerID);
			pstmt.setInt(2, dto.quantity);
			pstmt.setInt(3, dto.productDetailID);
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, dto.buyerID);
			
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
	
	//장바구니 목록
	public List<CartDTO> getCart(int buyerId) {
		//반환용 리스트
        List<CartDTO> cartList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT CART_ID, BUYER_ID, QUANTITY, PRODUCT_DETAIL_ID " +
                     "FROM CART WHERE BUYER_ID = ?";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buyerId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
                CartDTO dto = new CartDTO();
                dto.setCartID(rs.getInt("cart_id"));
                dto.setBuyerID(rs.getInt("buyer_id"));
                dto.setQuantity(rs.getInt("quantity"));
                dto.setProductDetailID(rs.getInt("product_detail_id"));
                cartList.add(dto); 
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

        return cartList;
    }
	
	//장바구니 삭제
	public int deleteCart(int cart_id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM CART WHERE CART_ID = ?";

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			
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

