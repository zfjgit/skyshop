function showWin(name, type) {
    $('#win-audio')[0].play();
    $('#award').text(name);
    
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.85 * winHeight;
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.25 * winWidth;
    }
    
    $('#award-show').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#award-button').css("display", "inline-block");
        $('#award').css("display", "inline-block");
        if(type == 'A') {
            $('#award').css('margin-top', '67%');
            $('#award-button').css('margin-top', '5%');
            $('#award-show').css('background-image', 'url("images/award2.jpg")');
            $('#award-show').css('background-position-y', '-20px');
            $('#addr').css("display", "block");
            $('#div-newaddr').css("display", "block");
            
            if(!$('#user_address').val() && $('#user_address').prop('disabled')) {
            	$('#btn-addr').click();
            } else {
            	$('#btn-addr').text('编辑');
            	
            	$('#user_address').prop('disabled', true);
            	$('#user_tel').prop('disabled', true);
            	$('#user_name').prop('disabled', true);
            }
        } else {
            $('#award').css('margin-top', '85%');
            $('#award-button').css('margin-top', '30%');
            $('#award-show').css('background-image', 'url("images/award.jpg")');
            $('#addr').css("display", "none");
            $('#div-newaddr').css("display", "none");
        }
    });
}

function hideWin() {
	if(turnplate.prize_type == 'A') {
    if( !turnplate.player.user_address || !turnplate.player.user_name || !turnplate.player.user_tel) {
        showMsg('请设置收货地址');
        return;
    } else {
        var user_name = turnplate.player.user_name;
        var user_tel = turnplate.player.user_tel;
        var user_address = turnplate.player.user_address;
        
		var addr = turnplate.player.user_address + ' / ' + turnplate.player.user_tel + ' / ' + turnplate.player.user_name;
		var tpl = turnplate.player.user_tel + '|【三网通共享平台】亲爱的' + turnplate.player.account + '，恭喜你获得了奖品：' + turnplate.prize.prizes_name + '，您的收货地址信息是：' + addr + '，如需修改请前往“台宝网共享交易平台”公众号的“个人中心”设置。';
		$.post(sms, 'type=sms&md5=' + encrypt(tpl).ciphertext.toString(), function(d) {
		});
        
        $.post(url, 'type=setAddress&winning_sno=' + turnplate.playid + '&user_name=' + user_name + '&user_tel=' + user_tel + '&user_address=' + user_address, function(d){
            if(d) {
                var data = $.parseJSON(d);
                if(data && data.status == 1) {
                    // showMsg('收货地址修改成功');
                } else {
                    // showMsg('收货地址修改失败');
                }
            }
        });
    }
	}
    
    $('#award-show').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#award-button').css("display", "none");
        $('#award').css("display", "none");
        $('#addr').css("display", "none");
        $('#div-newaddr').css("display", "none");
    });
}

function showLose() {
    $('#lose-audio')[0].play();
    
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.65 * winHeight;
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.25 * winWidth;
    }

    $('#award-lose').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#award-button-lose').css("display", "inline-block");
    });
}

function hideLose() {
    $('#award-lose').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#award-button-lose').css("display", "none");
    });
}

function showMsg(msg) {
    $('#msg-content').html(msg);
    
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    $('#msg').css('height', 'auto');
    
    var h = $('#msg').height();
    
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.25 * winWidth;
    }
    
    $('#msg').height(0);
    
    $('#msg').show();

    $('#msg').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2 + h / 10), "height": h, "width": w }, 500, function(){
        $('#msg').css('height', 'auto');
    });
}

function hideMsg() {
    $('#msg').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#msg').hide();
        $('#msg').css("height", "auto");
    });
}

function showLogin() {
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.3 * winHeight;
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.25 * winWidth;
    } else {
        h = 0.3 * winHeight;
    }

    $('#login-form').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#form-content').css("display", "block");
        $('#button-login').css("display", "inline-block");
        $('#login-form').css('height', 'auto');
    });
}

function hideLogin() {
    $('#login-form').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#form-content').css("display", "none");
        $('#button-login').css("display", "none");
    });
}