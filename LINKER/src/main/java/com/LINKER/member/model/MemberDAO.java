package com.LINKER.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 테스트용 로컬
	//private String url = "jdbc:oracle:thin:@172.30.1.58:1521:xe";
	private String uid = "LINKER";
	private String upw = "LINKER";
	
	//로그인 기능 (이메일, 비밀번호 받으면 유저 정보 반환)
	public MemberDTO login(String userEmail, String password) { 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ? AND PASSWORD = ?";
		
		//반환용 DTO
		MemberDTO dto = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String phone = rs.getString("phone");
				Timestamp createdAt = rs.getTimestamp("created_at");
				String memCode = rs.getString("mem_code");
				int cartId = rs.getInt("cart_id");
				int userPoint = rs.getInt("user_point");
				
				dto = new MemberDTO(userId, userName, userEmail, password, phone, createdAt, memCode, cartId, userPoint);
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
	
	//회원가입 메서드
	public void join(MemberDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO MEMBER(USER_ID, USER_NAME, EMAIL, PASSWORD, "
				+ "PHONE, CREATED_AT, MEM_CODE) "
				+ "VALUES(member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getPhone());
			pstmt.setTimestamp(5, dto.getCreatedAt());
			pstmt.setString(6, dto.getMemCode());
			
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
	
	//이메일 중복 체크 메서드
	public int emailDuplicationCheck(String email) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				result = 1; //중복의 의미
			}else {
				result = 0;
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
		
		return result;
	}
	
	//회원 정보 수정
	public int modify(MemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE MEMBER SET USER_NAME = ?, PASSWORD = ?, PHONE = ? "
				+ "WHERE EMAIL = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			
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
	
	
	//회원 삭제
	public int delete(String email) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM MEMBER WHERE EMAIL = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
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
	
	//모든 회원 목록 보기
	public List<MemberDTO> getMemberList() {
		//반환용 리스트
        List<MemberDTO> memberList = new ArrayList<>();
        
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        
        String sql = "SELECT USER_ID, USER_NAME, EMAIL, PASSWORD, PHONE,"+ 
        			"CREATED_AT, MEM_CODE, CART_ID, USER_POINT " +
                     "FROM MEMBER";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.setUserId(rs.getInt("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setEmail(rs.getString("email"));
                dto.setPassword(rs.getString("password"));
                dto.setPhone(rs.getString("phone"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
                dto.setMemCode(rs.getString("mem_code"));
                dto.setCartId(rs.getInt("cart_id"));
                
                memberList.add(dto); 
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

        return memberList;
    }
	
	//포인트 차감, 추가 (구매자ID, 판매자ID, 가격 받아서 포인트에 적용)
	public int transferPoint(int buyerId, int sellerId, int price) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement deductStmt = null;
        PreparedStatement addStmt = null;
		
		String deductPointsSql = "UPDATE MEMBER SET user_point = user_point - ? "
				+ "WHERE user_id = ? AND user_point >= ?";
        String addPointsSql = "UPDATE MEMBER SET user_point = user_point + ? "
        		+ "WHERE user_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			conn.setAutoCommit(false); // 트랜잭션 시작
			
			// 포인트 차감
            deductStmt = conn.prepareStatement(deductPointsSql);
            deductStmt.setInt(1, price);
            deductStmt.setInt(2, buyerId);
            deductStmt.setInt(3, price);
            int deductRows = deductStmt.executeUpdate();
            
            if (deductRows == 0) {
                conn.rollback(); // 포인트 부족하면 롤백
                return result;
            }

            // 포인트 추가
            addStmt = conn.prepareStatement(addPointsSql);
            addStmt.setInt(1, price);
            addStmt.setInt(2, sellerId);
            int addRows = addStmt.executeUpdate();
            
            if (addRows == 0) {
                conn.rollback(); // 받는 유저가 없으면 롤백
                return result;
            }
            
            conn.commit(); // 정상 처리 시 커밋
            result = 1;
            return result; //결제 성공시 1, 실패시 0 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {
                if (deductStmt != null) deductStmt.close();
                if (addStmt != null) addStmt.close();
                conn.setAutoCommit(true);
            } catch (Exception e2) {
            }
        }
		
		return result;
	}
	
}
