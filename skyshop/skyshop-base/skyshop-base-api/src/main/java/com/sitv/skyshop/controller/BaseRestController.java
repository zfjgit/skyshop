/**
 *
 */
package com.sitv.skyshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.IBaseService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
@Slf4j
@CrossOrigin
public abstract class BaseRestController<S extends IBaseService<I>, I extends Dto> {

	@Autowired
	protected S service;

	@Autowired
	protected HttpServletRequest request;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<I> get(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("GET:id=" + id);
		return ResponseInfo.SUCCESS(service.getOne(Long.valueOf(id)));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<I>> search(@NotNull @Valid @ModelAttribute SearchParamInfo<I> searchParamInfo, @NotNull @Valid @ModelAttribute I info) {
		searchParamInfo.setParam(info);
		log.debug("SEARCH:param=" + searchParamInfo);
		return ResponseInfo.SUCCESS(service.search(searchParamInfo));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<I> create(@Valid @NotNull @ModelAttribute I info) {
		log.debug("CREATE:T=" + info);
		service.createOne(info);
		return ResponseInfo.CREATED_SUCCESS(info);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<I> update(@Valid @NotNull @ModelAttribute I info) {
		log.debug("UPDATE:T=" + info);
		service.updateOne(info);
		return ResponseInfo.UPDATED_SUCCESS(info);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<I> delete(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("DELETE:id=" + id);
		service.deleteOne(Long.valueOf(id));
		return ResponseInfo.DELETED_SUCCESS();
	}

}
