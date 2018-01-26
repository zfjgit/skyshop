/**
 * http://usejsdoc.org/
 */

var config = {
    // api_server_host : 'chairsag.api.s-itv.com',
    // api_server_port : 80,
    // api_server_contextPath : '/api',
    
    // ui_server_host : 'chairsag.s-itv.com',
    // ui_server_port : 80,
    // ui_server_contextPath : '/',
    
    api_server_host : 'localhost',
    api_server_port : 8081,
    api_server_contextPath : '/wisdomedu-api',
    
    ui_server_host : 'localhost',
    ui_server_port : 8080,
    ui_server_contextPath : '/wisdomedu-ui',
    
    img_server_host: 'localhost',
    img_server_port: 80,
    img_server_contextPath : '',
    
    front_server_host: 'kehu.tba-taobao.com',
    front_server_port: 80,
    front_server_contextPath : '/WisdomEducation',
    
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
    username_password_error_code : 508,    
    verifycode_error_code : 509,
    
    unnormal_status_code : 601,
    disconnected_code : 602,
    no_price_code : 603,
    
    loginUserInfo : {},
    
    initSessionInfo : function() {
        if (!sessionStorage.loginUserInfo) {
            showLoginError();
            return false;
        }
        config.loginUserInfo = JSON.parse(sessionStorage.loginUserInfo);
        if (!config.loginUserInfo['userInfo']) {
            showLoginError();
            return false;
        }
        return true;
    },
    
    init : function() {
        config.api_server_url = 'http://' + config.api_server_host + ':' + config.api_server_port + config.api_server_contextPath;
        config.ui_server_url = 'http://' + config.ui_server_host + ':' + config.ui_server_port + config.ui_server_contextPath;
        config.img_server_url = 'http://' + config.img_server_host + ':' + config.img_server_port + config.img_server_contextPath;
        
        config.front_server_url = 'http://' + config.front_server_host + ':' + config.front_server_port + config.front_server_contextPath;
    }
};

$.toast.prototype.defaults.duration = 1500;

config.init();

function post(url, data, successfunc, runtimeerrorfunc) {
    doAjax(url, data, successfunc, 'POST', runtimeerrorfunc);
}

function get(url, data, successfunc, isNotShowNoDataMsg) {
    var handler = function(jsondata, textStatus, jqXHR) {
        if (!isNotShowNoDataMsg && (!jsondata['data'] || $.isEmptyObject(jsondata['data']))) {
            showCancelMsg('没有查询到数据');
            return;
        }
        if (successfunc && typeof successfunc == 'function') {
            successfunc(jsondata, textStatus, jqXHR);
        }
    }
    doAjax(url, data, handler, 'GET');
}

function getList(url, data, successfunc, isNotShowNoDataMsg) {
    var handler = function(jsondata, textStatus, jqXHR) {
        var resultdata = jsondata['data'];
        if (!resultdata) {
            showOperateError();
            return;
        }
        if (!isNotShowNoDataMsg && !resultdata['datas']) {
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
        console.log('ajax------->data=' + data);
    }
    $.ajax({
        url : config.api_server_url + url,
        type : (method == 'GET') ? method : 'POST',
        async : (async + '') != 'false',
        data : data,
        dataType : 'json',
        headers : {
            "Token-Header" : config.loginUserInfo.token,
            "X-Requested-With" : "XMLHttpRequest",
            "X-HTTP-Method-Override" : method
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
                        && (jsondata['code'] == config.success_code || jsondata['code'] == config.created_success_code
                            || jsondata['code'] == config.updated_success_code || jsondata['code'] == config.deleted_success_code)) {
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

function showLoginError(msg) {
    msg = (!msg ? '登录信息已过期，请重新登录' : msg);
    // toast消失之前显示loading将导致回调函数失效
    $.toast(msg, 'forbidden', function() {
        top.location.href = config.ui_server_url + '/login.shtml';
    });
}

function showOperateError() {
    showError('操作遇到问题，请稍后再试');
}

function showError(msg, func) {
    $.toast(msg, 'forbidden', function() {
    	if (func && typeof func == 'function') {
            func();
        }
    });
}

function showMsg(msg) {
	$.toast(msg, 'text');
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

function showAlert(msg, func) {
    $.alert(msg, function() {
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
    
    if (diff) {
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

function fixUkuRepeatComment(refresh) {
    if (refresh) {
        var beginComment = '';
        $('.t_table_body').contents().each(function(i, e) {
            if (e && e.nodeType == Node.COMMENT_NODE) {
                if (e && e.nodeValue && e.nodeValue.indexOf('begin uku-repeat:') == 0) {
                    $(e).remove();
                } else if (e && e.nodeValue && e.nodeValue.indexOf('end uku-repeat:') == 0) {
                    var beginComment = e.nodeValue.replace('end', 'begin');
                    $('.t_table_body').prepend('<!--' + beginComment + '-->');
                }
            }
        });
    } else {
        $('.t_table_body').contents().filter(function(e) {
            return this.nodeType = Node.COMMENT_NODE;
        }).each(function(i, e) {
            if (e && e.nodeValue && e.nodeValue.indexOf('begin uku-repeat:') == 0) {
                $(e).remove();
            } else if (e && e.nodeValue && e.nodeValue.indexOf('end uku-repeat:') == 0) {
                var beginComment = e.nodeValue.replace('end', 'begin');
                $(e).before('<!--' + beginComment + '-->');
            }
        });
    }
}