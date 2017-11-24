package com.sitv.skyshop.order.domain.comment;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.OrderItem;
import com.sitv.skyshop.product.domain.product.Product;

public class OrderCommentItem extends DomainObject {

	private OrderComment orderComment;
	
	private OrderItem orderItem;
	
	private Product product;
	
	private String content;
	
	private CommentType commentType;
	
	protected OrderCommentItem() {
	}

	public OrderComment getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(OrderComment orderComment) {
		this.orderComment = orderComment;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
	}
	
}
