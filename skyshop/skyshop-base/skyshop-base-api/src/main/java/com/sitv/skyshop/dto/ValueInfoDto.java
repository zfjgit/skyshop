/**
 * 
 */
package com.sitv.skyshop.dto;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
public abstract class ValueInfoDto extends Dto {
	
	private static final long serialVersionUID = 8643864180652469826L;
	
	private Long id;
	
	protected ValueInfoDto() {
	}
	
	protected ValueInfoDto(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
