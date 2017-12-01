/**
 * http://usejsdoc.org/
 */
$(function() {
    $('#login').click(function() {
        if ($('#rememberme').prop('checked')) {
            localStorage.username = $('#account').val();
            localStorage.password = $('#pwd').val();
        } else {
            localStorage.username = '';
            localStorage.password = '';
        }
        var account = $('#account').val();
        var pwd = $('#pwd').val();
        
        var data = {
            code : account,
            pwd : pwd
        };
        
        post('/loginuser/', data, function(d) {
            console.log('d=' + JSON.stringify(d));
        });
    });
});
