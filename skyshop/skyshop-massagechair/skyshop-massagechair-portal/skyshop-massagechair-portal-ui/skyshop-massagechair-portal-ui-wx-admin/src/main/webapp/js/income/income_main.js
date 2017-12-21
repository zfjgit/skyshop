$(function() {
    $('#item-chair').click(function() {
        $('#item-content').load('income_by_chair.html');
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.income_tab = 'item-chair';
    });
    $('#item-addr').click(function() {
        $('#item-content').load('income_by_addr.html');
        $('.weui-navbar__item').removeClass('weui-bar__item--on weui-tab__bd-item--active');
        $(this).addClass('weui-bar__item--on weui-tab__bd-item--active');
        
        sessionStorage.income_tab = 'item-addr';
    });
    
    if (!sessionStorage.income_tab) {
        $('#item-chair').click();
    } else {
        $('#' + sessionStorage.income_tab).click();
    }
});