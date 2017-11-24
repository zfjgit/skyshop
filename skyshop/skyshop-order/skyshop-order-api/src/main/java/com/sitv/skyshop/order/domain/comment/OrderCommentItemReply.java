package com.sitv.skyshop.order.domain.comment;

import com.sitv.skyshop.domain.DomainObject;

public class OrderCommentItemReply extends DomainObject {

	private OrderCommentItem commentItem;
	
	private String content;
	
	protected OrderCommentItemReply() {
	}

	public OrderCommentItem getCommentItem() {
		return commentItem;
	}

	public void setCommentItem(OrderCommentItem commentItem) {
		this.commentItem = commentItem;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
