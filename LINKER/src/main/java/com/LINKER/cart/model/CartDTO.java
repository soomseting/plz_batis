package com.LINKER.cart.model;

public class CartDTO {
	public int cartID;
	public int buyerID;
	public int quantity;
	public int productDetailID;
	
	public CartDTO() {
	}

	public CartDTO(int cartID, int buyerID, int quantity, int productDetailID) {
		super();
		this.cartID = cartID;
		this.buyerID = buyerID;
		this.quantity = quantity;
		this.productDetailID = productDetailID;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductDetailID() {
		return productDetailID;
	}

	public void setProductDetailID(int productDetailID) {
		this.productDetailID = productDetailID;
	}
	
	
}


