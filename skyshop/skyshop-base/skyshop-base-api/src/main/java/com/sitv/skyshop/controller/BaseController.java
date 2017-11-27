/**
 *
 */
package com.sitv.skyshop.controller;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
public abstract class BaseController<S extends IBaseService<I>, I extends Dto> {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected S service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<I> get(@NotBlank @DecimalMin("1") @PathVariable String id) {
		log.debug("GET:id=" + id);
		return ResponseInfo.SUCCESS(service.getOne(Long.valueOf(id)));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<I>> search(@Valid @ModelAttribute SearchParamInfo<I> paramInfo) {
		log.debug("SEARCH:param=" + paramInfo);
		return ResponseInfo.SUCCESS(service.search(paramInfo));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<I> create(@Valid @ModelAttribute I info) {
		log.debug("CREATE:T=" + info);
		service.createOne(info);
		return ResponseInfo.CREATED_SUCCESS();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<I> update(@Valid @ModelAttribute I info) {
		log.debug("UPDATE:T=" + info);
		service.updateOne(info);
		return ResponseInfo.UPDATED_SUCCESS();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<I> delete(@NotBlank @DecimalMin("1") @PathVariable String id) {
		log.debug("DELETE:id=" + id);
		service.deleteOne(Long.valueOf(id));
		return ResponseInfo.DELETED_SUCCESS();
	}

}
