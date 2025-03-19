package com.LINKER.productDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailDAO {

	private static ProductDetailDAO instance = new ProductDetailDAO();
	
	private ProductDetailDAO() {
	}
	
	public static ProductDetailDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	
	//제품 상세 글쓰기
	public void writeProductDetail(ProductDetailDTO dto){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO PRODUCT_DETAIL (PRODUCT_DETAIL_ID, PRODUCT_IMAGE, PRODUCT_DESCRIPTION, "
                + "PRODUCT_CONTACT_INFO, PRODUCT_PRICE, PRODUCT_TYPE, USER_ID) "
                + "VALUES (product_detail_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setBytes(1, dto.productImage);
			pstmt.setString(2, dto.productDescription);
			pstmt.setString(3, dto.productContactInfo);
			pstmt.setInt(4, dto.productPrice);
			pstmt.setString(5, dto.productType);
			pstmt.setInt(6, dto.userID);
			
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
	
	
	//제품 상세 목록 (전체 반환)
	public List<ProductDetailDTO> getProductDetail() {
		//반환용 리스트
        List<ProductDetailDTO> productDetailList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT PRODUCT_DETAIL_ID, PRODUCT_IMAGE, PRODUCT_DESCRIPTION, PRODUCT_CONTACT_INFO," +
        			 "PRODUCT_CREATED_AT, PRODUCT_PRICE, PRODUCT_TYPE, USER_ID " +
                     "FROM PRODUCT_DETAIL ORDER BY PRODUCT_CREATED_AT DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDetailDTO dto = new ProductDetailDTO();
                dto.setProductDetailId(rs.getInt("product_detail_id"));
                dto.setProductImage(rs.getBytes("product_image"));
                dto.setProductDescription(rs.getString("product_description"));
                dto.setProductContactInfo(rs.getString("product_contact_info"));
                dto.setProductCreatedAt(rs.getTimestamp("product_created_at"));
                dto.setProductPrice(rs.getInt("product_price"));
                dto.setProductType(rs.getString("product_type"));
                dto.setUserID(rs.getInt("user_id"));
                productDetailList.add(dto); 
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

        return productDetailList;
    }
	
	
	//제품 상세 목록 (작가 아이디 받아서, 그 작가의 제품 상세 목록만 반환)
	public List<ProductDetailDTO> getProductDetailByUserID(int userId) {
		//반환용 리스트
        List<ProductDetailDTO> productDetailList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT PRODUCT_DETAIL_ID, PRODUCT_IMAGE, PRODUCT_DESCRIPTION, PRODUCT_CONTACT_INFO," +
        			 "PRODUCT_CREATED_AT, PRODUCT_PRICE, PRODUCT_TYPE, USER_ID " +
                     "FROM PRODUCT_DETAIL WHERE USER_ID = ? ORDER BY PRODUCT_CREATED_AT DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDetailDTO dto = new ProductDetailDTO();
                dto.setProductDetailId(rs.getInt("product_detail_id"));
                dto.setProductImage(rs.getBytes("product_image"));
                dto.setProductDescription(rs.getString("product_description"));
                dto.setProductContactInfo(rs.getString("product_contact_info"));
                dto.setProductCreatedAt(rs.getTimestamp("product_created_at"));
                dto.setProductPrice(rs.getInt("product_price"));
                dto.setProductType(rs.getString("product_type"));
                dto.setUserID(rs.getInt("user_id"));
                productDetailList.add(dto); 
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

        return productDetailList;
    }
	
	
	//제품 목록 반환(제품 유형 입력 받아서, 해당 유형의 제품 상세 목록만을 반환)
	public List<ProductDetailDTO> getProductDetailByProductType(String productType) {
		//반환용 리스트
        List<ProductDetailDTO> productDetailList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT PRODUCT_DETAIL_ID, PRODUCT_IMAGE, PRODUCT_DESCRIPTION, PRODUCT_CONTACT_INFO," +
        			 "PRODUCT_CREATED_AT, PRODUCT_PRICE, PRODUCT_TYPE, USER_ID " +
                     "FROM PRODUCT_DETAIL WHERE PRODUCT_TYPE = ? ORDER BY PRODUCT_CREATED_AT DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productType);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDetailDTO dto = new ProductDetailDTO();
                dto.setProductDetailId(rs.getInt("product_detail_id"));
                dto.setProductImage(rs.getBytes("product_image"));
                dto.setProductDescription(rs.getString("product_description"));
                dto.setProductContactInfo(rs.getString("product_contact_info"));
                dto.setProductCreatedAt(rs.getTimestamp("product_created_at"));
                dto.setProductPrice(rs.getInt("product_price"));
                dto.setProductType(rs.getString("product_type"));
                dto.setUserID(rs.getInt("user_id"));
                productDetailList.add(dto); 
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

        return productDetailList;
    }
	
	
	
	//제품 상세 내용 (제품 아이디 받아서 한개만)
	public ProductDetailDTO getProductDetailByID(int productDetailId) {
		
		//반환용 dto
		ProductDetailDTO dto = new ProductDetailDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT PRODUCT_DETAIL_ID, PRODUCT_IMAGE, PRODUCT_DESCRIPTION, PRODUCT_CONTACT_INFO," +
   			 	"PRODUCT_CREATED_AT, PRODUCT_PRICE, PRODUCT_TYPE, USER_ID " +
                "FROM PRODUCT_DETAIL WHERE PRODUCT_DETAIL_ID = ? ORDER BY PRODUCT_CREATED_AT DESC";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productDetailId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setProductDetailId(rs.getInt("product_detail_id"));
                dto.setProductImage(rs.getBytes("product_image"));
                dto.setProductDescription(rs.getString("product_description"));
                dto.setProductContactInfo(rs.getString("product_contact_info"));
                dto.setProductCreatedAt(rs.getTimestamp("product_created_at"));
                dto.setProductPrice(rs.getInt("product_price"));
                dto.setProductType(rs.getString("product_type"));
                dto.setUserID(rs.getInt("user_id"));
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
	
}
