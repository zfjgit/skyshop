$(function() {
    config.initSessionInfo();
    
    $('.t_agency_field').hide();
    
    var uku = new Ukulele();
    var userInfo = config.loginUserInfo.userInfo;
    uku.registerController("userInfo", userInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    get('/user/' + config.loginUserInfo.userInfo.id, '', function(d) {
        userInfo.id = d.data.id;
        userInfo.name = d.data.name;
        userInfo.code = d.data.code;
        userInfo.type = d.data.type;
        userInfo.status = d.data.status;
        if (d.data['agency']) {
            $('.t_agency_field').show();
            userInfo.agency = {
                id : d.data.agency.id,
                name : d.data.agency.name,
                balance : d.data.agency.balance,
                orderIncomePercentage : d.data.agency.orderIncomePercentage + '%',
                level : {
                    code : d.data.agency.level.code,
                    name : d.data.agency.level.name
                }
            };
            if (d.data.agency['parent']) {
                userInfo.agency.p_a_r_e_n_t = {
                    name : d.data.agency['parent'].name
                };
            }
        }
        config.loginUserInfo.userInfo = userInfo;
        config.uku.refresh("userInfo");
    });
}

function addListeners() {
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
}