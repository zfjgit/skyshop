/**
 *
 */
package com.sitv.skyshop.dto;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
@Getter
@Setter
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
	public static final int UPLOAD_ERROR_CODE = 405;

	public static final int RUNTIME_ERROR_CODE = 500;
	public static final int CHECKCODE_VARIFY_ERROR_CODE = 507;
	public static final int USERNAME_PASSWORD_ERROR_CODE = 508;
	public static final int VERIFYCODE_ERROR_CODE = 509;

	public static final int UNNORMAL_STATUS_ERROR_CODE = 601;
	public static final int DISCONNECTED_ERROR_CODE = 602;
	public static final int ORDER_EXPIRED_ERROR_CODE = 603;
	public static final int ORDER_NOTPAID_ERROR_CODE = 604;
	public static final int INSUFFICIENT_BALANCE_ERROR_CODE = 605;

	private int code;

	private String url;

	private T data;

	private String message;

	protected ResponseInfo() {
	}

	protected ResponseInfo(int code) {
		this.code = code;
	}

	protected ResponseInfo(int code, String message) {
		this(code);
		this.message = message;
	}

	protected ResponseInfo(T data, int code) {
		this(code);
		this.data = data;
	}

	protected ResponseInfo(T data, int code, String message) {
		this(data, code);
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

	public static <T> ResponseInfo<T> CREATED_SUCCESS(T t) {
		return new ResponseInfo<>(t, CREATED_SUCCESS_CODE, "创建成功");
	}

	public static <T> ResponseInfo<T> UPDATED_SUCCESS(T t) {
		return new ResponseInfo<>(t, UPDATED_SUCCESS_CODE, "修改成功");
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

	public static <T> ResponseInfo<T> UPLOAD_ERROR(String message) {
		return new ResponseInfo<>(UPLOAD_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> CHECKCODE_VARIFY_ERROR(String message) {
		return new ResponseInfo<>(CHECKCODE_VARIFY_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> UNNORMAL_STATUS(String message) {
		return new ResponseInfo<>(UNNORMAL_STATUS_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> DISCONNECTED(String message) {
		return new ResponseInfo<>(DISCONNECTED_ERROR_CODE, message);
	}

	public String toString() {
		return new JSONObject(this).toString();
	}

	public static <T> ResponseInfo<T> ORDER_EXPIRED_ERROR(String message) {
		return new ResponseInfo<>(ORDER_EXPIRED_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> ORDER_NOTPAID_ERROR(String message) {
		return new ResponseInfo<>(ORDER_NOTPAID_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> INSUFFICIENT_BALANCE_ERROR(String message) {
		return new ResponseInfo<>(INSUFFICIENT_BALANCE_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> USERNAME_PASSWORD_ERROR(String message) {
		return new ResponseInfo<>(USERNAME_PASSWORD_ERROR_CODE, message);
	}

	public static <T> ResponseInfo<T> VERIFYCODE_ERROR(String message) {
		return new ResponseInfo<>(VERIFYCODE_ERROR_CODE, message);
	}
}
