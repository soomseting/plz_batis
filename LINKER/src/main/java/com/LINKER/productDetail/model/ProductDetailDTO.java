package com.LINKER.productDetail.model;

import java.sql.Timestamp;

public class ProductDetailDTO {

	public int productDetailId;
	//이미지 타입 BLOB 어케하지??
	public byte[] productImage;
	public String productDescription;
	public String productContactInfo;
	public Timestamp productCreatedAt;
	public int productPrice;
	public String productType;
	public int userID;
	
	public ProductDetailDTO() {
	}

	public ProductDetailDTO(int productDetailId, byte[] productImage, String productDescription,
			String productContactInfo, Timestamp productCreatedAt, int productPrice, String productType, int userID) {
		super();
		this.productDetailId = productDetailId;
		this.productImage = productImage;
		this.productDescription = productDescription;
		this.productContactInfo = productContactInfo;
		this.productCreatedAt = productCreatedAt;
		this.productPrice = productPrice;
		this.productType = productType;
		this.userID = userID;
	}

	public int getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContactInfo() {
		return productContactInfo;
	}

	public void setProductContactInfo(String productContactInfo) {
		this.productContactInfo = productContactInfo;
	}

	public Timestamp getProductCreatedAt() {
		return productCreatedAt;
	}

	public void setProductCreatedAt(Timestamp productCreatedAt) {
		this.productCreatedAt = productCreatedAt;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
	
}
