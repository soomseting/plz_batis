package com.LINKER.payment.model;

import java.sql.Timestamp;

public class PaymentDTO {

	public int paymentId;
	public int paymentAmount;
	public String paymentMethod;
	public String paymentStatus;
	public Timestamp paymentDate;
	public int cartId;
	
	public PaymentDTO() {
	}
	
	public PaymentDTO(int paymentId, int paymentAmount, String paymentMethod, String paymentStatus,
			Timestamp paymentDate, int cartId) {
		super();
		this.paymentId = paymentId;
		this.paymentAmount = paymentAmount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.cartId = cartId;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	
}
