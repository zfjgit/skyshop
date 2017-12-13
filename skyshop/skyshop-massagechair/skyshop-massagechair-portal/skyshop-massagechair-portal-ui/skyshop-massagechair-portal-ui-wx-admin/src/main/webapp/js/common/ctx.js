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
    
    is_debug : false,
    
    uku : {},
    
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
    no_price_code : 603,
    
    loginUserInfo : {},
    agency : {
        id : 0,
        name : '系统',
        level : {
            code : 'A',
            name : '平台'
        }
    },
    
    initSessionInfo : function() {
        if (!sessionStorage.loginUserInfo) {
            showLoginError();
            return;
        }
        config.loginUserInfo = JSON.parse(sessionStorage.loginUserInfo);
        if (!config.loginUserInfo['userInfo']) {
            showLoginError();
            return;
        }
        if (config.loginUserInfo.userInfo['agency']) {
            config.agency = config.loginUserInfo.userInfo['agency'];
        }
    },
    
    init : function() {
    	config.api_server_url = 'http://' + config.api_server_host + ':' + config.api_server_port + config.api_server_contextPath;
    	config.ui_server_url = 'http://' + config.ui_server_host + ':' + config.ui_server_port + config.ui_server_contextPath;
    }
};

config.init();


function post(url, data, successfunc, runtimeerrorfunc) {
    doAjax(url, data, successfunc, 'POST', runtimeerrorfunc);
}

function get(url, data, successfunc) {
    var handler = function(jsondata, textStatus, jqXHR) {
        if (!jsondata['data'] || $.isEmptyObject(jsondata['data'])) {
            showCancelMsg('没有查询到数据');
            return;
        }
        if (successfunc && typeof successfunc == 'function') {
            successfunc(jsondata, textStatus, jqXHR);
        }
    }
    doAjax(url, data, handler, 'GET');
}

function getList(url, data, successfunc) {
    var handler = function(jsondata, textStatus, jqXHR) {
        var resultdata = jsondata['data'];
        if (!resultdata) {
            showOperateError();
            return;
        }
        if (resultdata && !resultdata['datas']) {
            showCancelMsg('没有查询到数据');
            return;
        }
        if (successfunc && typeof successfunc == 'function') {
            successfunc(jsondata, textStatus, jqXHR);
        }
    }
    doAjax(url, data, handler, 'GET');
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
function doAjax(url, data, successfunc, method, runtimeerrorfunc, async, errorfunc, beforefunc, completefunc) {
    if (config.is_debug) {
        console.log('ajax------->url=' + url);
    }
    $
        .ajax({
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
                $.hideLoading();
                
                if (config.is_debug) {
                    if (typeof result == 'object') {
                        console.log("ajax------->result=" + JSON.stringify(result));
                    } else {
                        console.log("ajax------->result=" + result);
                    }
                }
                try {
                    if (result) {
                        var jsondata = result;
                        if (typeof result == 'string') {
                            jsondata = $.parseJSON(result);
                        }
                        if (jsondata['code'] && (jsondata['code'] == config.unauthorized_error_code)) {
                            showLoginError();
                            return;
                        } else if (jsondata['code'] && jsondata['code'] == config.runtime_error_code) {
                            showOperateError();
                            return;
                        } else if (jsondata['code']
                            && (jsondata['code'] == config.success_code || jsondata['code'] == config.created_success_code || jsondata['code'] == config.updated_success_code || jsondata['code'] == config.deleted_success_code)) {
                            if (successfunc && typeof successfunc == 'function') {
                                successfunc(jsondata, textStatus, jqXHR);
                            } else {
                                console.log('没有事件处理函数');
                            }
                        } else {
                            if (runtimeerrorfunc && typeof runtimeerrorfunc == 'function') {
                                runtimeerrorfunc(jsondata);
                            } else {
                                showOperateError();
                            }
                        }
                    } else {
                        showOperateError();
                    }
                } catch (e) {
                    showOperateError();
                    throw e;
                }
            },
            error : function(xhr, textStatus, errorThrown) {
                $.hideLoading();
                
                if (xhr && xhr.status == config.not_found_error_code) {
                    showError('您所请求的对象不存在，请稍后再试');
                } else if (xhr && xhr.status == config.unauthorized_error_code) {
                    showLoginError();
                    return;
                } else {
                    showError('操作遇到问题，请稍后再试：' + (xhr ? xhr.status : ''));
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

function showLoginError() {
    $.toast('登录信息已过期，请重新登录', 'forbidden', function() {
        window.location.href = config.ui_server_url + '/index.html';
    });
}

function showOperateError() {
    showError('操作遇到问题，请稍后再试');
}

function showError(msg) {
    $.toast(msg, 'forbidden');
}

function showOkMsg(msg, func) {
    $.toast(msg, function() {
        if (func && typeof func == 'function') {
            func();
        }
    });
}

function showCancelMsg(msg, func) {
    $.toast(msg, 'cancel', function() {
        if (func && typeof func == 'function') {
            func();
        }
    });
}

function showConfirmMsg(msg, func, cancelFunc) {
    $.confirm(msg, function() {
        if (func && typeof func == 'function') {
            func();
        }
    }, function() {
        if (cancelFunc && typeof cancelFunc == 'function') {
            cancelFunc();
        }
    });
}

function today(diff) {
    var d = new Date();
    
    if(diff) {
    	d.setDate(d.getDate() + diff);
    }
    
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var date = d.getDate();
    
    if (month < 10) {
        month = '0' + month;
    }
    if (date < 10) {
        date = '0' + date;
    }
    return year + '-' + month + '-' + date;
}

function yesterday() {
    return today(-1);
}

function tomorrow() {
	return today(1);
}