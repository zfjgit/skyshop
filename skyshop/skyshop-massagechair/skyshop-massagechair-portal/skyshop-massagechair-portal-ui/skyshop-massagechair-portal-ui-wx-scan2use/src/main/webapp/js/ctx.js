/**
 * http://usejsdoc.org/
 */

var config = {
    api_server_host : 'localhost',
    api_server_port : 8080,
    api_server_contextPath : '/api-scan2use',
    
    ui_server_host : 'localhost',
    ui_server_port : 8080,
    ui_server_contextPath : '/ui-scan2use'
}

var api_server_url = 'http://' + config.api_server_host + ':' + config.api_server_port + config.api_server_contextPath;
var ui_server_url = 'http://' + config.ui_server_host + ':' + config.ui_server_port + config.ui_server_contextPath;

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
    
    $.ajax({
        url : api_server_url + url,
        type : method ? method : 'post',
        async : async && ((async + '') == 'false') ? false : true,
        data : data,
        dataType : 'json',
        headers : {
            "X-Requested-With" : "XMLHttpRequest"
        },
        success : function(result, textStatus, jqXHR) {
            try {
                if (result) {
                    var jsondata = result;
                    if (typeof result == 'string') {
                        jsondata = $.parseJSON(result);
                    }
                    if (jsondata['code'] && (jsondata['code'] == 'sessiontimeout' || jsondata['code'] == 'noauth')) {
                        return;
                    } else if (jsondata['code'] && jsondata['code'] == 'error') {
                        alert('发生错误：' + jsondata['msg']);
                        return;
                    }
                    if (successfunc && typeof successfunc == 'function') {
                        successfunc(jsondata, textStatus, jqXHR);
                    }
                } else {
                    alert('没有获取到结果');
                }
            } catch (e) {
                alert(e);
            }
        },
        error : function(xhr, textStatus, errorThrown) {
            if (errorfunc && typeof errorfunc == 'function') {
                errorfunc(xhr, textStatus, errorThrown);
            }
        },
        beforeSend : function(xhr) {
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