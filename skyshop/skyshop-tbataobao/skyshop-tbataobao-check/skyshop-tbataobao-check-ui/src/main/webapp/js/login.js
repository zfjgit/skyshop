$(function() {
    initKaptcha();
    
    initForm();
    
    initFormData();
});

function initFormData() {
    var userInfo = new UserInfo();
    
    var uku = new Ukulele();
    uku.registerController("userInfo", userInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
    });
    uku.init();
}

function initForm() {
    $('.skin-minimal input').iCheck({
        checkboxClass : 'icheckbox-blue',
        radioClass : 'iradio-blue',
        increaseArea : '20%'
    });
    
    $("#form-login").validate({
        rules : {
            account : {
                required : true,
                minlength : 4,
                maxlength : 16
            },
            pwd : {
                required : true,
            },
            verifycode : {
                required : true,
                maxlength : 4
            },
        },
        messages : {
            account : {
                required : '<div><span class="red">*</span><span>请输入账号</span></div>',
            },
            pwd : {
                required : '<div><span class="red">*</span><span>请输入密码</span></div>',
            },
            verifycode : {
                required : '<div><span class="red">*</span><span>请输入验证码</span></div>',
            }
        },
        onkeyup : false,
        focusCleanup : true,
        success : "valid",
        submitHandler : function(form) {
            doLogin();
        }
    });
}

function doLogin() {
    var params = $.customParam(userInfo);
    
    post('/syschecker/login', params, function(d) {
    	if(d.data.userInfo.account == 'admin001') {
    		$("#form-login")[0].action = 'pages/merchant/index.shtml';
    	}
    	sessionStorage.loginUserInfo = JSON.stringify(d.data);
        $("#form-login")[0].submit();
    }, function(jsondata){
    	if(jsondata['code'] == config.username_password_error_code) {
    		showError('账号或密码错误，请重新输入');
    	} else if(jsondata['code'] == config.verifycode_error_code) {
    		showError('验证码错误，请重新输入');
    	} else {
    		showError('登录失败，请稍后再试');
    	}
    	getVerifyCode();
    });
}

function initKaptcha() {
    $('#a-refresh-code').click(function() {
        getVerifyCode();
    });

    getVerifyCode();
}

function getVerifyCode() {
    $('#img-verifycode').attr('src', config.api_server_url + '/kaptcha/create');
}
