package com.LINKER.orderProduct.model;

import java.sql.Timestamp;

public class OrderProductDTO {

	public int orderId;
	public String selectedOptions;
	public Timestamp orderDate;
	public int userId;
	public String orderProductStatus;
	
	public OrderProductDTO() {
	}

	public OrderProductDTO(int orderId, String selectedOptions, Timestamp orderDate, int userId,
			String orderProductStatus) {
		super();
		this.orderId = orderId;
		this.selectedOptions = selectedOptions;
		this.orderDate = orderDate;
		this.userId = userId;
		this.orderProductStatus = orderProductStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(String selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderProductStatus() {
		return orderProductStatus;
	}

	public void setOrderProductStatus(String orderProductStatus) {
		this.orderProductStatus = orderProductStatus;
	}

	
	
	
}
