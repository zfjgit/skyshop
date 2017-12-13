$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    uku.registerController("agency", config.agency);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        
        loadOne();
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (config.agency.id == 0) {
        config.uku.refresh('agency');
    } else {
        get('/agency/?id=' + config.agency.id, '', function(d) {
            config.agency = d.data;
            config.uku.refresh('agency');
        });
    }
}

function addListeners() {
    $('#btn_view').on('click', function() {
        window.location.href = 'agents_detail.html?' + config.agency.id;
    });
    
    $('#item-agents').click(function() {
        $.showLoading();
        
        $('#item-content').load('agents_list.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.agents_tab = 'item-agents';
    });
    $('#item-chair').click(function() {
        $.showLoading();
        $('#item-content').load('agents_chair_list.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.agents_tab = 'item-chair';
    });
    $('#item-addr').click(function() {
        $.showLoading();
        $('#item-content').load('agents_addr_list.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.agents_tab = 'item-addr';
    });
    $('#item-income').click(function() {
        $.showLoading();
        $('#item-content').load('agents_income_list.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.agents_tab = 'item-income';
    });
    
    if (!sessionStorage.agents_tab) {
        $('#item-agents').click();
    } else {
        $('#' + sessionStorage.agents_tab).click();
    }
}