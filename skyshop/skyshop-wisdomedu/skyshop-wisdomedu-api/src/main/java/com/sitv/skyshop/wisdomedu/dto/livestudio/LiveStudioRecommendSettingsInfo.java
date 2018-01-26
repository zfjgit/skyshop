/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioRecommendSettings;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioRecommendSettingsInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 5680306633328967537L;
	private LiveStudioInfo liveStudio;
	private int order;

	/**
	 *
	 */
	public LiveStudioRecommendSettingsInfo() {
	}

	/**
	 * @param liveStudio
	 * @param order
	 */
	public LiveStudioRecommendSettingsInfo(Long id, LiveStudioInfo liveStudio, int order) {
		super(id, "");
		this.liveStudio = liveStudio;
		this.order = order;
	}

	public static LiveStudioRecommendSettingsInfo create(LiveStudioRecommendSettings c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioRecommendSettingsInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getOrder());
	}

	public static List<LiveStudioRecommendSettingsInfo> creates(List<LiveStudioRecommendSettings> list) {
		List<LiveStudioRecommendSettingsInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioRecommendSettings u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioRecommendSettingsInfo.create(u));
		}
		return infos;
	}
}
