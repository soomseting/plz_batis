package com.LINKER.requestOffer.model;

import java.sql.Timestamp;

public class RequestOfferDTO {

	public int requestOfferId;
	public String requestStatus;
	public Timestamp requestOfferDate;
	public int requestID;
	public int userId;
	
	public RequestOfferDTO() {
	}
	
	public RequestOfferDTO(int requestOfferId, String requestStatus, Timestamp requestOfferDate, int requestID,
			int userId) {
		super();
		this.requestOfferId = requestOfferId;
		this.requestStatus = requestStatus;
		this.requestOfferDate = requestOfferDate;
		this.requestID = requestID;
		this.userId = userId;
	}
	
	public int getRequestOfferId() {
		return requestOfferId;
	}
	public void setRequestOfferId(int requestOfferId) {
		this.requestOfferId = requestOfferId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public Timestamp getRequestOfferDate() {
		return requestOfferDate;
	}
	public void setRequestOfferDate(Timestamp requestOfferDate) {
		this.requestOfferDate = requestOfferDate;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
