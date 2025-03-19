package com.LINKER.request.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

	private static RequestDAO instance = new RequestDAO();
	
	private RequestDAO() {
	}
	
	public static RequestDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	//의뢰 제안 글쓰기
	public void writeRequest(RequestDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO REQUEST (REQUEST_ID, REQUEST_TITLE, REQUEST_CONTENT, USER_ID) "
				+ "VALUES(request_seq.NEXTVAL, ?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRequestTitle());
			pstmt.setString(2, dto.getRequestContent());
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
	
	//의뢰 제안 목록 보기
	public List<RequestDTO> getReviewsByProductId(int userId) {
		//반환용 리스트
        List<RequestDTO> requestList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT REQUEST_ID, REQUEST_TITLE, REQUEST_CONTENT, REQUEST_CREATED_AT, USER_ID " +
                     "FROM REQUEST WHERE userId = ? ORDER BY REQUEST_CREATED_AT DESC";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
                RequestDTO dto = new RequestDTO();
                dto.setRequestId(rs.getInt("request_id"));
                dto.setRequestTitle(rs.getString("request_string"));
                dto.setRequestContent(rs.getString("request_content"));
                dto.setRequestCreatedAt(rs.getTimestamp("request_created_at"));
                dto.setUserId(rs.getInt("user_id"));
                requestList.add(dto); 
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

        return requestList;
    }
	
}
