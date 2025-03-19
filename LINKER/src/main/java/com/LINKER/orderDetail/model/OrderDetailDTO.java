package com.LINKER.orderDetail.model;

public class OrderDetailDTO {

	public int orderID;
	public int productDetailId;
	public int productCnt;
	
	public OrderDetailDTO() {
	}

	public OrderDetailDTO(int orderID, int productDetailId, int productCnt) {
		super();
		this.orderID = orderID;
		this.productDetailId = productDetailId;
		this.productCnt = productCnt;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	public int getProductCnt() {
		return productCnt;
	}

	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}
	
	
}
