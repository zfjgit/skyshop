/**
 * http://usejsdoc.org/
 */

$(function() {
    $("<link/>", {
        rel : "shortcut icon",
        type : "image/png",
        href : config.ui_server_url + '/images/logo.png'
    }).appendTo("head");
    
    $('.h_back:visible').click(function() {
        history.back();
    });
    
    $.toast.prototype.defaults.duration = 1500;
    
    hideloading();
    
    $('body')
        .append(
            '<div class="cover" style="position: absolute;top:0;left:0;width:100%;height:100%;z-index:100;background-color:#f0f0f0;opacity:0.30;display:none;"></div>');
    
    $('body')
        .append(
            '<div id="loading-gif" style="position: absolute;left: 0;top:0;display: flex;z-index:101;background: transparent;width: 100%;height: 100%;text-align: center;margin: auto;opcity:0.9;">'
                + '<div id="loading-msg" style="top:50%;margin: auto;width: 200px;height: 200px;background: url(\'images/qsh1.png\');background-size: 100%;">'
                + '<img src="images/wait.gif" style="width: 100%;"/></div></div>');
    hideloading();
    
    $('body')
        .append(
            '<div class="msg-dialog"  style="position: absolute;top:0;left:0;display: flex;z-index:102;text-align: center;width: 100%;height: 100%;font-size: 20px;">'
                + '<div style="top:50%;margin: auto;width: 300px;height: 150px;background-size: 100%;background: url(\'images/bg181.png\');background-repeat:no-repeat;">'
                + '<div class="msg" style="margin-top:25px;"></div><div class="msg-btn" style="position:relative;bottom:-20px;font-size: 20px;background: url(\'images/bt.png\');background-repeat: no-repeat;padding:15px;background-position-x:center;">确定</div></div></div>');
    
    $('.msg-btn').on('click', function() {
        hideMsg();
    });
    
    hideMsg();
});

function showMsg(msg) {
    $('.cover').show();
    $('.msg').text(msg);
    $('.msg-dialog').show();
}

function hideMsg() {
    $('.cover').hide();
    $('.msg-dialog').hide();
}

function showloading(img) {
    $('.cover').show();
    if (img) {
        $('#loading-msg').css('background-image', 'url(\'' + img + '\')');
    }
    $('#loading-gif').show();
}

function hideloading() {
    $('.cover').hide();
    $('#loading-gif').hide();
}