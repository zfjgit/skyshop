/**
 * 
 */
package com.sitv.skyshop.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.user.domain.RegisterType;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
public class RegisterTypeInfo extends SimpleInfoDto {

	private static final long serialVersionUID = 2570774785448417296L;

	public RegisterTypeInfo(Long id, String code, String name) {
		super(id, code, name);
	}

	public static RegisterTypeInfo create(RegisterType registerType) {
		if(registerType == null) {
			return null;
		}
		return new RegisterTypeInfo(registerType.getId(), registerType.getCode(), registerType.getName());
	}

	public static List<RegisterTypeInfo> creates(List<RegisterType> registerTypes) {
		List<RegisterTypeInfo> registerTypeInfos = new ArrayList<>();
		
		if(registerTypes == null) {
			return registerTypeInfos;
		}
		
		for (RegisterType registerType : registerTypes) {
			registerTypeInfos.add(create(registerType));
		}
		
		return registerTypeInfos;
	}
}
