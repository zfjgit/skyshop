/**
 * http://usejsdoc.org/
 */

var config = {
    api_server_host : 'localhost',
    api_server_port : 8080,
    api_server_contextPath : '/chairs-api-wxadmin',
    
    ui_server_host : 'localhost',
    ui_server_port : 8081,
    ui_server_contextPath : '/chairs-ui-wxadmin',
    
    is_debug : true,
    
    loginUserInfo : {},

    success_code : 200,
    created_success_code : 201,
    updated_success_code : 202,
    deleted_success_code : 204,
    args_error_code : 400,
    unauthorized_error_code : 401,
    forbidden_error_code : 403,
    not_found_error_code : 404,
    runtime_error_code : 500,
    checkcode_varify_error_code : 507,
    unnormal_status_code : 601,
    disconnected_code : 602,
    no_price_code : 603
}

config.api_server_url = 'http://' + config.api_server_host + ':' + config.api_server_port + config.api_server_contextPath;
config.ui_server_url = 'http://' + config.ui_server_host + ':' + config.ui_server_port + config.ui_server_contextPath;

function post(url, data, successfunc) {
    doAjax(url, data, successfunc, 'POST');
}

function get(url, data, successfunc) {
    doAjax(url, data, successfunc, 'GET');
}

function put(url, data, successfunc) {
    doAjax(url, data, successfunc, 'PUT');
}

function del(url, data, successfunc) {
    doAjax(url, data, successfunc, 'DELETE');
}
/**
 * 发送Ajax请求
 * 
 * @param url
 *            请求地址
 * @param data
 *            发送的数据
 * @param successfunc(jsondata,
 *            textstatus, xhr) 成功时回调函数
 * @param method
 *            method 默认为POST
 * @param async
 *            同步/异步，默认为异步
 * @param errorfunc
 *            错误处理函数
 * @param beforefunc
 *            发送之前的处理函数
 * @param completefunc
 *            完成时处理函数
 */
function doAjax(url, data, successfunc, method, async, errorfunc, beforefunc, completefunc) {
	if(config.is_debug) {
		console.log('url=' + url);
	}
    $.ajax({
        url : config.api_server_url + url,
        type : method ? method : 'post',
        async : (async + '') != 'false',
        data : data,
        dataType : 'json',
        headers : {
        	"Token-Header" : config.loginUserInfo.token,
            "X-Requested-With" : "XMLHttpRequest"
        },
        xhrFields : {
            withCredentials : true
        },
        crossDomain : true,
        success : function(result, textStatus, jqXHR) {
        	if(config.is_debug) {
        		console.log("result=" + result);
        	}
        	$.hideLoading();
            try {
                if (result) {
                    var jsondata = result;
                    if (typeof result == 'string') {
                        jsondata = $.parseJSON(result);
                    }
                    if (jsondata['code'] && (jsondata['code'] == config.unauthorized_error_code)) {
                    	$.toast('登录信息已过期，请重新登录', 'forbidden', function(){
                    		window.location.href = config.ui_server_url + '/index.html';
                    	});
                        return;
                    } else if (jsondata['code'] && jsondata['code'] == config.runtime_error_code) {
                        $.toast('操作遇到问题，请稍后再试', 'forbidden');
                        return;
                    }
                    if (successfunc && typeof successfunc == 'function') {
                        successfunc(jsondata, textStatus, jqXHR);
                    }
                } else {
                	$.toast('操作遇到问题，请稍后再试', 'forbidden');
                }
            } catch (e) {
                alert(e);
            }
        },
        error : function(xhr, textStatus, errorThrown) {
        	$.hideLoading();
        	
        	if(xhr && xhr.status == config.not_found_error_code) {
        		$.toast('没有获取到请求的信息', 'forbidden');
        	} else if(xhr && xhr.status == config.unauthorized_error_code) {
        		$.toast('登录信息已过期，请重新登录', 'forbidden', function(){
            		window.location.href = config.ui_server_url + '/index.html';
            	});
        		return;
        	} else {
        		$.toast('操作遇到问题，请稍后再试：' + (xhr ? xhr.status : ''), 'forbidden');
        	}
        	
            if (errorfunc && typeof errorfunc == 'function') {
                errorfunc(xhr, textStatus, errorThrown);
            }
        },
        beforeSend : function(xhr) {
        	$.showLoading();
            if (beforefunc && typeof beforefunc == 'function') {
                beforefunc(xhr);
            }
        },
        complete : function(xhr, textStatus) {
            if (completefunc && typeof completefunc == 'function') {
                completefunc(xhr, textStatus);
            }
        }
    });
}