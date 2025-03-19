package com.LINKER.requestOffer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestOfferDAO {

	private static RequestOfferDAO instance = new RequestOfferDAO();
	
	private RequestOfferDAO() {
	}
	
	public static RequestOfferDAO getInsctance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	
	//의뢰 제안 글쓰기
	public void writeRequestOffer(RequestOfferDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO REQUEST_OFFER (REQUEST_OFFER_ID, REQUEST_STATUS, REQUEST_ID, USER_ID) "
				+ "VALUES(request_offer_seq.NEXTVAL, ?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.requestStatus);
			pstmt.setInt(2, dto.requestID);
			pstmt.setInt(3, dto.getUserId());
			
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
	
	
	//의뢰 제안 목록
	public List<RequestOfferDTO> getReviewsByProductId(int requestId) {
		//반환용 리스트
        List<RequestOfferDTO> requestOfferList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT REQUEST_OFFER_ID, REQUEST_STATUS, REQUEST_OFFER_DATE, REQUEST_ID, USER_ID " +
                     "FROM REQUEST_OFFER WHERE REQUEST_ID = ? ORDER BY REQUEST_OFFER_DATE DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, requestId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
                RequestOfferDTO dto = new RequestOfferDTO();
                dto.setRequestOfferId(rs.getInt("request_offer_id"));
                dto.setRequestStatus(rs.getString("request_status"));
                dto.setRequestOfferDate(rs.getTimestamp("request_offer_date"));
                dto.setRequestID(rs.getInt("request_id"));
                dto.setUserId(rs.getInt("user_id"));
                requestOfferList.add(dto); 
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

        return requestOfferList;
    }
	
}
