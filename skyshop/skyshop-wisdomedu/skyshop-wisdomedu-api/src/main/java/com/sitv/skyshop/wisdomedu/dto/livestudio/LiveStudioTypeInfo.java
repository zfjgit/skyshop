/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioTypeInfo extends SimpleInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 8114061629244780617L;

	private LiveStudioTypeInfo parent;
	private int order;

	public LiveStudioTypeInfo() {
	}

	public LiveStudioTypeInfo(Long id, String name, LiveStudioTypeInfo parent, int order) {
		super(id, name);
		this.parent = parent;
		this.order = order;
	}

	public static LiveStudioTypeInfo create(LiveStudioType t) {
		if (t == null) {
			return null;
		}
		return new LiveStudioTypeInfo(t.getId(), t.getName(), LiveStudioTypeInfo.create(t.getParent()), t.getOrder());
	}

	public static List<LiveStudioTypeInfo> creates(List<LiveStudioType> list) {
		List<LiveStudioTypeInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioType liveStudioType : list) {
			if (liveStudioType == null) {
				continue;
			}
			infos.add(LiveStudioTypeInfo.create(liveStudioType));
		}
		return infos;
	}
}
