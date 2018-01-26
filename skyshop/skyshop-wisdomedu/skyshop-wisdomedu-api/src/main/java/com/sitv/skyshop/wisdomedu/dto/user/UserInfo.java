/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.user.User;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 3865021267533499852L;

	private String introduction;
	private String headimg;
	private String openid;
	private String payOpenid;

	/**
	 *
	 */
	public UserInfo() {
	}

	public UserInfo(Long id, String name, String introduction, String headimg, String openid, String payOpenid, Calendar createTime) {
		super(id, name);
		this.introduction = introduction;
		this.headimg = headimg;
		this.openid = openid;
		this.payOpenid = payOpenid;

		setCreateTime(createTime);
	}

	public static UserInfo create(User u) {
		if (u == null) {
			return null;
		}
		return new UserInfo(u.getId(), u.getName(), u.getIntroduction(), u.getHeadimg(), u.getOpenid(), u.getPayOpenid(), u.getCreateTime());
	}

	public static List<UserInfo> creates(List<User> list) {
		List<UserInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (User u : list) {
			if (u == null) {
				continue;
			}
			infos.add(UserInfo.create(u));
		}
		return infos;
	}
}
