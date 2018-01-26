/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioPageSettings;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioPageSettings.LinkType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioPageSettingsInfo extends FullInfoDto {

	private static final long serialVersionUID = 1932795938984119802L;
	private String img;
	private String link;
	private String title;
	private EnumInfo<LinkType, String> linkType;
	private LiveStudioInfo liveStudio;

	public LiveStudioPageSettingsInfo() {
	}

	public LiveStudioPageSettingsInfo(Long id, String img, String link, String title, EnumInfo<LinkType, String> linkType, LiveStudioInfo liveStudio) {
		super(id, "");
		this.img = img;
		this.link = link;
		this.title = title;
		this.linkType = linkType;
		this.liveStudio = liveStudio;
	}

	public static LiveStudioPageSettingsInfo create(LiveStudioPageSettings c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioPageSettingsInfo(c.getId(), c.getImg(), c.getLink(), c.getTitle(), EnumInfo.valueOf(LinkType.class, c.getLinkType().getCode()),
		                LiveStudioInfo.create(c.getLiveStudio()));
	}

	public static List<LiveStudioPageSettingsInfo> creates(List<LiveStudioPageSettings> list) {
		List<LiveStudioPageSettingsInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioPageSettings u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioPageSettingsInfo.create(u));
		}
		return infos;
	}
}
