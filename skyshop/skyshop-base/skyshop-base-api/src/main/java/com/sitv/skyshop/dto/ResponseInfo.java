/**
 *
 */
package com.sitv.skyshop.dto;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
public class ResponseInfo<T> extends Dto {

	private static final long serialVersionUID = 7425377426618272670L;

	public static final int SUCCESS_CODE = 200;
	public static final int CREATED_SUCCESS_CODE = 201;
	public static final int UPDATED_SUCCESS_CODE = 202;
	public static final int DELETED_SUCCESS_CODE = 204;
	public static final int ARGS_ERROR_CODE = 400;
	public static final int UNAUTHORIZED_ERROR_CODE = 401;
	public static final int FORBIDDEN_ERROR_CODE = 403;
	public static final int NOT_FOUND_ERROR_CODE = 404;
	public static final int RUNTIME_ERROR_CODE = 500;

	private int code;

	private String url;

	private T data;

	private String message;

	protected ResponseInfo() {
	}

	protected ResponseInfo(T data, int code) {
		this.data = data;
		this.code = code;
	}

	protected ResponseInfo(int code) {
		this.code = code;
	}

	protected ResponseInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static <T> ResponseInfo<T> SUCCESS(T data) {
		return new ResponseInfo<>(data, SUCCESS_CODE);
	}

	public static <T> ResponseInfo<T> SUCCESS(int code, String message) {
		return new ResponseInfo<>(code, message);
	}

	public static <T> ResponseInfo<T> ARGS_ERROR(String message) {
		return new ResponseInfo<>(ARGS_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> RUNTIME_ERROR(String message) {
		return new ResponseInfo<>(RUNTIME_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> CREATED_SUCCESS(String... message) {
		if (message == null || message.length == 0) {
			return new ResponseInfo<>(CREATED_SUCCESS_CODE, "创建成功");
		}
		return new ResponseInfo<>(CREATED_SUCCESS_CODE, message[0]);
	}

	public static <T> ResponseInfo<T> UPDATED_SUCCESS(String... message) {
		if (message == null || message.length == 0) {
			return new ResponseInfo<>(CREATED_SUCCESS_CODE, "修改成功");
		}
		return new ResponseInfo<>(UPDATED_SUCCESS_CODE, message[0]);
	}

	public static <T> ResponseInfo<T> DELETED_SUCCESS(String... message) {
		if (message == null || message.length == 0) {
			return new ResponseInfo<>(CREATED_SUCCESS_CODE, "删除成功");
		}
		return new ResponseInfo<>(DELETED_SUCCESS_CODE, message[0]);
	}

	public static <T> ResponseInfo<T> UNAUTHORIZED_ERROR(String message) {
		return new ResponseInfo<>(UNAUTHORIZED_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> FORBIDDEN_ERROR(String message) {
		return new ResponseInfo<>(FORBIDDEN_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> NOT_FOUND_ERROR(String message) {
		return new ResponseInfo<>(NOT_FOUND_ERROR_CODE, message);
	}

	public int getCode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public String getUrl() {
		return url;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// public PageInfo<T> getPageInfo() {
	// return pageInfo;
	// }
	//
	// public void setPageInfo(PageInfo<T> pageInfo) {
	// this.pageInfo = pageInfo;
	// }
}
