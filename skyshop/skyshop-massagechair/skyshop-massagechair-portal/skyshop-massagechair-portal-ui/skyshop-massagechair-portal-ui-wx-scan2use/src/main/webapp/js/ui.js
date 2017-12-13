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
    
    $('body').append(
        '<div id="loading-gif" style="position: absolute;left: 0;top:0;display: flex;background: transparent;width: 100%;height: 100%;text-align: center;margin: auto;opcity:0.9;">'
            + '<div id="loading-msg" style="top:50%;margin: auto;width: 200px;height: 200px;background: url(\'images/qsh1.png\');background-size: 100%;">'
            + '<img src="images/wait.gif" style="width: 100%;"/>' + '</div>' + '</div>');
    hideloading();
});

function showloading(img) {
    if (img) {
        $('#loading-msg').css('background-image', 'url(\'' + img + '\')');
    }
    $('#loading-gif').show();
}

function hideloading() {
    $('#loading-gif').hide();
}