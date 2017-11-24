var ctxPath = '';
/**
 * 发送Ajax请求
 * @param url 请求地址
 * @param data 发送的数据
 * @param successfunc(jsondata, textstatus, xhr) 成功时回调函数
 * @param async 同步/异步，默认为异步
 * @param errorfunc 错误处理函数
 * @param beforefunc 发送之前的处理函数
 * @param completefunc 完成时处理函数
 */
function doAjax(url, data, successfunc, async, errorfunc, beforefunc, completefunc){
	
	var fnCode = $('#_FN_CODE').val() ? $('#_FN_CODE').val() : '';
	if(typeof data == 'object') {
		data['FN_CODE'] = fnCode;
	} else if(typeof data == 'string') {
		if(data == '') {
			data += 'FN_CODE=' + fnCode;
		} else {
			data += '&FN_CODE=' + fnCode;
		}
	} else {
		data = {};
		data['FN_CODE'] = $('#_FN_CODE').val();
	}
	
	$.ajax({
		url: ctxPath + url,
		type: 'post',
		async: async != null && ((async + '') == 'false') ? false : true,
		data: data,
		dataType: 'json',
		headers: {"X-Requested-With": "XMLHttpRequest"},
		success: function(result, textStatus, jqXHR){
			try {
				if(result) {
					var jsondata = result;
					if(typeof result == 'string'){
						jsondata = $.parseJSON(result);
					}
					if(jsondata['code'] && (jsondata['code'] == 'sessiontimeout' || jsondata['code'] == 'noauth')) {
						window.location.href = ctxPath + 'pages/error.jsp?msg='+jsondata['msg'];
						return;
					} else if(jsondata['code'] && jsondata['code'] == 'error'){
						alert('发生错误：'+jsondata['msg']);
						return;
					}
					if(successfunc && typeof successfunc == 'function'){
						successfunc(jsondata, textStatus, jqXHR);
					}
				} else {
					alert($('#noresult').val());
				}
			} catch(e) {
				alert(e);
			}
		},
		error: function(xhr, textStatus, errorThrown){
			if(errorfunc && typeof errorfunc == 'function') {
				errorfunc(xhr, textStatus, errorThrown);
			}
		},
		beforeSend: function(xhr){
			showCover($('#loadingmsg').val());
			if(beforefunc && typeof beforefunc == 'function') {
				beforefunc(xhr);
			}
		},
		complete: function(xhr, textStatus){
			hideCover();
			if(completefunc && typeof completefunc == 'function') {
				completefunc(xhr, textStatus);
			}
		}
	});
}

function showCover(msg, ifr){
	var coverifr = null;
	if(ifr) {
		coverifr = ifr;
	} else {
		coverifr = window.frames['cover-ifr'];
	}
	if(coverifr) {
		if(msg) {
			msg = msg + '.....';
			if(coverifr.length > 1) {
				if(coverifr[0].contentDocument) {
					$(coverifr[0].contentDocument.body).find('#loading-msg').text(msg);
				} else {
					$(coverifr[0].document.body).find('#loading-msg').text(msg);
				}
			} else {
				if(coverifr.contentDocument) {
					$(coverifr.contentDocument.body).find('#loading-msg').text(msg);
				} else {
					$(coverifr.document.body).find('#loading-msg').text(msg);
				}
			}
		}
		$(coverifr).show();
	}
}

function hideCover(){
	if($('#cover-ifr')[0]) {
		$('#cover-ifr').hide();
	}
}

/**
 * 对象转字符串
 */
function obj2String(obj){
	var str = '';
	if(obj) {
		var what = Object.prototype.toString;

		if(what.call( obj ) === '[object Array]') {
			var elements = [];
			for ( var i = 0; i < obj.length; i ++) {
				elements.push(obj2String(obj[i]));
			}
			str = '[' + elements.join(",") + ']';
		} else if(what.call( obj ) === '[object Object]') {
			var attrs = [];
			for(var attr in obj) {
				if(attr && typeof attr == 'string') {
					var value = obj[attr];
					attrs.push('"'+attr+'":"'+(value ? obj2String(value).replace(/\\s/g, '').replace(/\"/g, '&quot;').replace(/\'/g, '&apos;') : '')+'"');
				}
			}
			str = "{"+attrs.join(",")+"}";
		} else if(what.call( obj ) === '[object Function]') {
			str = '[object Function]';
		} else {
			str = obj.toString();
		}
	}
	return str;
}

/**
 * 移除数组中的空元素
 * @param ary
 * @returns
 */
function removeEmpty(ary){
	if(ary && ary.length) {
		for (var i = 0; i < ary.length; i++) {
			var ele = ary[i];
			if(!ele) {
				ary.splice(i, 1);
			}
		}
	}
	return ary;
}

var JsonFormatter = {
    stringify: function (cipherParams) {
        // create json object with ciphertext
        var jsonObj = {
            ct: cipherParams.ciphertext.toString(CryptoJS.enc.Hex)
        };

        // stringify json object
        return JSON.stringify(jsonObj);
    },
    parse: function (jsonStr) {
        // parse json string
        var jsonObj = JSON.parse(jsonStr);

        // extract ciphertext from json object, and create cipher params object
        var cipherParams = CryptoJS.lib.CipherParams.create({
            ciphertext: CryptoJS.enc.Hex.parse(jsonObj.ct)
        });

        return cipherParams;
    }
};
var key = CryptoJS.enc.Hex.parse('71776572747975697177657274797569');
var iv  = CryptoJS.enc.Hex.parse('01020304050607080102030405060708');

function encrypt(passwd){
    return CryptoJS.AES.encrypt(passwd, key, {iv:iv}, { format: JsonFormatter });
}

function decrypt(passwd){
	var pwd = CryptoJS.enc.Hex.parse(passwd);
	return CryptoJS.AES.decrypt({"ciphertext":pwd}, key, {iv:iv}, { format: JsonFormatter }).toString(CryptoJS.enc.Utf8);
}

/**
 * 获取当前时间，格式为：
 * yyyy-MM-dd HH:mm:ss
 */
function now(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	
	if((month + '').length < 2) {
		month = '0' + month;
	}
	if((date + '').length < 2) {
		date = '0' + date;
	}
	if((hours + '').length < 2) {
		hours = '0' + hours;
	}
	if((minutes + '').length < 2) {
		minutes = '0' + minutes;
	}
	if((seconds + '').length < 2) {
		seconds = '0' + seconds;
	}
	return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
}

/**
 * 退出登录
 */
function logout(){
	doAjax('logout.action', '', function(jsondata){
		top.window.location.replace(ctxPath + '/index.jsp');
	});
}

