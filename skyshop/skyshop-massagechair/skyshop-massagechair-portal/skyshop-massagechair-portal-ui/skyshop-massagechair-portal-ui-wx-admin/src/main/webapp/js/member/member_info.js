$(function() {
    config.initSessionInfo();
    
    $('.t_agency_field').hide();
    
    var uku = new Ukulele();
    var userInfo = new UserInfo();
    uku.registerController("userInfo", userInfo);
    uku.init();
    
    get('/user/' + config.loginUserInfo.userInfo.id, '', function(d) {
        if (d && d.code == config.success_code && d.data) {
            if (!d.data['agency']) {
                userInfo.id = d.data.id;
                userInfo.name = d.data.name;
                userInfo.code = d.data.code;
                userInfo.type = d.data.type;
                userInfo.status = d.data.status;
            } else {
                $('.t_agency_field').show();
                d.data.agency.percentage = 100 - d.data.agency.orderIncomePercentage;
            }
            uku.refresh("userInfo");
        } else {
            $.toast('操作遇到问题，请稍后再试', 'forbidden');
        }
    });
    
    $('#btn_exit').on('click', function() {
        del('/loginuser/' + userInfo.id, '', function(d) {
            if (d && d.code == config.deleted_success_code) {
                $.confirm('您确定要退出登录吗？', '退出登录', function() {
                    window.location.href = config.ui_server_url + '/index.html';
                });
            } else {
                $.toast('操作遇到问题，请稍后再试', 'forbidden');
            }
        });
    });
});