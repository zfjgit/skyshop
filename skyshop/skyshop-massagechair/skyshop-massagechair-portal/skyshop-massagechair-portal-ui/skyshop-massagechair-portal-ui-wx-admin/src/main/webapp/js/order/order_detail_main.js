$(function() {
    addListeners();
});

function addListeners() {
    
    $('#item-detail').click(function() {
        $.showLoading();
        
        $('#item-content').load('order_detail.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.orders_tab = 'item-detail';
    });
    $('#item-partition').click(function() {
        $.showLoading();
        $('#item-content').load('order_detail_share.html', function() {
            $.hideLoading();
        });
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.orders_tab = 'item-partition';
    });
    
    if (!sessionStorage.orders_tab) {
        $('#item-detail').click();
    } else {
        $('#' + sessionStorage.orders_tab).click();
    }
}