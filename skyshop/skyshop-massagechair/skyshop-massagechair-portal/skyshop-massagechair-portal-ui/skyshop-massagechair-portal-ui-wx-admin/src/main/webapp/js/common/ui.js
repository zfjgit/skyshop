/**
 * http://usejsdoc.org/
 */

$(function() {
    $("<link/>", {
        rel : "shortcut icon",
        type : "image/png",
        href : config.ui_server_url + '/images/06.jpg'
    }).appendTo("head");
    
    $('.h_back:visible').click(function() {
        history.back();
    });
    
    $.toast.prototype.defaults.duration = 1500;
    
});