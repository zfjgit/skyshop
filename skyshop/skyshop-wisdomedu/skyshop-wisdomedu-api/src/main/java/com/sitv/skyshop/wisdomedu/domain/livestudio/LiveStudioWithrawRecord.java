/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioWithrawRecord extends DomainObject {

	private LiveStudio liveStudio;
	private String bankUser;
	private String bankName;
	private String bankNum;
	private BigDecimal amount;
	private BigDecimal beforeAmount;
	private BigDecimal afterAmount;
	private WithrawStatus status;

	@Getter
	public enum WithrawStatus implements BaseEnum<WithrawStatus, String> {
	    // 1-2-3,1-2-4
		NEW("1", "待处理"), PROCESSING("2", "处理中"), DONE("3", "完成"), FAILED("4", "失败");

		private String code;
		private String name;

		private WithrawStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
