$(function() {
    var uku = new Ukulele();
    var loginUserInfo = new LoginUserInfo();
    uku.registerController("loginUserInfo", loginUserInfo);
    uku.init();
    
    if (localStorage.account) {
        loginUserInfo.setCode(localStorage.account);
    }
    if (localStorage.password) {
        loginUserInfo.setPassword(localStorage.password);
    }
    
    $('#login').on('click', function() {
        loginUserInfo.code = $.trim(loginUserInfo.code);
        loginUserInfo.password = $.trim(loginUserInfo.password);
        
        if ($.trim(loginUserInfo.code) == '') {
            $.toast("请输入账号", "cancel");
            return;
        }
        if ($.trim(loginUserInfo.password) == '') {
            $.toast("请输入密码", "cancel");
            return;
        }
        
        if ($('#rememberme').prop('checked')) {
            localStorage.account = loginUserInfo.code;
            localStorage.password = loginUserInfo.password;
        } else {
            localStorage.account = '';
            localStorage.password = '';
        }
        
        var data = {
            code : loginUserInfo.code,
            password : loginUserInfo.password
        };
        
        post('/loginuser/', data, function(d) {
            sessionStorage.loginUserInfo = JSON.stringify(d.data);
            window.location.href = 'pages/main.html';
        }, function(d) {
            if (d.code == config.not_found_error_code) {
                showError("账号或密码错误");
            } else if (d.code == config.unnormal_status_code) {
                showError("账号状态不正常，请联系管理员");
            } else {
                showOperateError();
            }
        });
    });
});
