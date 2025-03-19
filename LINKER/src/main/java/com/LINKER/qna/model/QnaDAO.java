package com.LINKER.qna.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class QnaDAO {

	private static QnaDAO instance = new QnaDAO();
	
	private QnaDAO() {
	}
	
	public static QnaDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	//QnA 글쓰기
	public void writeQna(QnaDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO QNA (QNA_ID, QNA_TITLE, QNA_CONTENT, QNA_STATUS, USER_ID) "
				+ "VALUES(qna_seq.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQnaTitle());
			pstmt.setString(2, dto.getQnaContent());
			pstmt.setString(3, dto.getQnaStatus());
			pstmt.setInt(4, dto.getUserId());
			
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
	
	//QnA 목록 가져오기
	public List<QnaDTO> getQna(int userId) {
		//반환용 리스트
        List<QnaDTO> qnaList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT QNA_ID, QNA_TITLE, QNA_CONTENT, QNA_STATUS, QNA_CREATED_AT, USER_ID " +
                     "FROM QNA WHERE USER_ID = ?";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
                QnaDTO dto = new QnaDTO();
                dto.setQnaId(rs.getInt("user_id"));
                dto.setQnaTitle(rs.getString("qna_title"));
                dto.setQnaContent(rs.getString("qna_status"));
                dto.setQnaStatus(rs.getString("qns_status"));
                dto.setQnaCreatedAt(rs.getTimestamp("qna_created_at"));
                dto.setUserId(rs.getInt("user_id"));
                
                qnaList.add(dto); 
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

        return qnaList;
    }
}
