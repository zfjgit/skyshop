/**
 *
 */
package com.sitv.skyshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.IBaseService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月6日
 */
@Slf4j
@CrossOrigin
public abstract class BaseController<S extends IBaseService<I>, I extends Dto> {

	@Autowired
	protected S service;

	@Autowired
	protected HttpServletRequest request;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseInfo<I> get(@NotBlank @Min(0) @RequestParam String id) {
		log.debug("GET:id=" + id);
		return ResponseInfo.SUCCESS(service.getOne(Long.valueOf(id)));
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseInfo<PageInfo<I>> search(@Valid @ModelAttribute SearchParamInfo<I> paramInfo) {
		log.debug("SEARCH:param=" + paramInfo);
		return ResponseInfo.SUCCESS(service.search(paramInfo));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseInfo<I> create(@Valid @ModelAttribute I info) {
		log.debug("CREATE:T=" + info);
		service.createOne(info);
		return ResponseInfo.CREATED_SUCCESS();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseInfo<I> update(@Valid @ModelAttribute I info) {
		log.debug("UPDATE:T=" + info);
		service.updateOne(info);
		return ResponseInfo.UPDATED_SUCCESS();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResponseInfo<I> delete(@NotBlank @Min(0) @RequestParam String id) {
		log.debug("DELETE:id=" + id);
		service.deleteOne(Long.valueOf(id));
		return ResponseInfo.DELETED_SUCCESS();
	}
}
