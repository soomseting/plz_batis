package com.LINKER.member.model;

import java.sql.Timestamp;

public class MemberDTO {
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String phone;
	private Timestamp createdAt;
	private String memCode;
	private int cartId;
	private int userPoint;
	
	public MemberDTO() {
	}

	public MemberDTO(int userId, String userName, String email, String password, String phone, Timestamp createdAt,
			String memCode, int cartId, int userPoint) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.createdAt = createdAt;
		this.memCode = memCode;
		this.cartId = cartId;
		this.userPoint = userPoint;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	
	
	
	
}
