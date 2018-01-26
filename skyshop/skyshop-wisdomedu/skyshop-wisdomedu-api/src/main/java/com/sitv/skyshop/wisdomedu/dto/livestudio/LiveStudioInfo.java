/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;
import com.sitv.skyshop.wisdomedu.dto.user.UserInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -2199470058986480762L;

	private String introduction;
	private String headimg;
	private String backgroundimg;
	private String link;
	private UserInfo creator;
	private UserInfo admin;
	private LiveStudioTypeInfo type;

	private int followCount;
	private BigDecimal income;

	public LiveStudioInfo() {
	}

	public LiveStudioInfo(Long id, String name, String code, String introduction, String headimg, String backgroundimg, String link, UserInfo creator, UserInfo admin,
	                LiveStudioTypeInfo type, Calendar createTime) {
		super(id, code, name, "", createTime, null);
		this.introduction = introduction;
		this.headimg = headimg;
		this.backgroundimg = backgroundimg;
		this.link = link;
		this.creator = creator;
		this.admin = admin;
		this.type = type;
	}

	public static LiveStudioInfo create(LiveStudio liveStudio) {
		if (liveStudio == null) {
			return null;
		}
		return new LiveStudioInfo(liveStudio.getId(), liveStudio.getName(), liveStudio.getCode(), liveStudio.getIntroduction(), liveStudio.getHeadimg(),
		                liveStudio.getBackgroundimg(), liveStudio.getLink(), UserInfo.create(liveStudio.getCreator()), UserInfo.create(liveStudio.getAdmin()),
		                LiveStudioTypeInfo.create(liveStudio.getType()), liveStudio.getCreateTime());
	}

	public static List<LiveStudioInfo> creates(List<LiveStudio> list) {
		List<LiveStudioInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudio u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioInfo.create(u));
		}
		return infos;
	}
}
