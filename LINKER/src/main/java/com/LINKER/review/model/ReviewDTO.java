package com.LINKER.review.model;

import java.sql.Timestamp;

public class ReviewDTO {

	public int reviewId;
	public int productDetailId;
	public String reviewContent;
	public Timestamp reviewCreatedAt;
	public int userId;
	public int reviewRating;
	
	public ReviewDTO() {
	}

	public ReviewDTO(int reviewId, int productDetailId, String reviewContent, Timestamp reviewCreatedAt, int userId,
			int reviewRating) {
		super();
		this.reviewId = reviewId;
		this.productDetailId = productDetailId;
		this.reviewContent = reviewContent;
		this.reviewCreatedAt = reviewCreatedAt;
		this.userId = userId;
		this.reviewRating = reviewRating;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Timestamp getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(Timestamp reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	
	
	
	
	
}
