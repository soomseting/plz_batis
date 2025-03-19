package com.LINKER.qna.model;

import java.sql.Timestamp;

public class QnaDTO {

	public int qnaId;
	public String qnaTitle;
	public String qnaContent;
	public String qnaStatus;
	public Timestamp qnaCreatedAt;
	public int userId;
	
	public QnaDTO() {
	}
	
	public QnaDTO(int qnaId, String qnaTitle, String qnaContent, String qnaStatus, Timestamp qnaCreatedAt, int userId) {
		super();
		this.qnaId = qnaId;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaStatus = qnaStatus;
		this.qnaCreatedAt = qnaCreatedAt;
		this.userId = userId;
	}
	
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getQnaStatus() {
		return qnaStatus;
	}
	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}
	public Timestamp getQnaCreatedAt() {
		return qnaCreatedAt;
	}
	public void setQnaCreatedAt(Timestamp qnaCreatedAt) {
		this.qnaCreatedAt = qnaCreatedAt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
