/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.user.UserLiveStudio;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserLiveStudioInfo extends FullInfoDto {

	private static final long serialVersionUID = -2170997239248204952L;
	private UserInfo user;
	private LiveStudioInfo liveStudio;
	private boolean isLiveNotice;

	public UserLiveStudioInfo() {
	}

	public UserLiveStudioInfo(Long id, UserInfo user, LiveStudioInfo liveStudio, boolean isLiveNotice, Calendar createTime) {
		super(id, createTime, null);
		this.user = user;
		this.liveStudio = liveStudio;
		this.isLiveNotice = isLiveNotice;
	}

	public static UserLiveStudioInfo create(UserLiveStudio t) {
		if (t == null) {
			return null;
		}
		return new UserLiveStudioInfo(t.getId(), UserInfo.create(t.getUser()), LiveStudioInfo.create(t.getLiveStudio()), t.isLiveNotice(), t.getCreateTime());
	}

	public static List<UserLiveStudioInfo> creates(List<UserLiveStudio> list) {
		List<UserLiveStudioInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (UserLiveStudio userLiveStudio : list) {
			if (userLiveStudio == null) {
				continue;
			}
			infos.add(UserLiveStudioInfo.create(userLiveStudio));
		}
		return infos;
	}
}
