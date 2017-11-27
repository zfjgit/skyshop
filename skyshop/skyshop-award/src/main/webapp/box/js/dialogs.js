function showWin(name, type) {
    $('#award').text(name);
    
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.8 * winHeight;
    if(type == 'A') {
    	h = 0.85 * winHeight;
    }
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.32 * winWidth;
    }
    
    $('#award-show').show();
    
    $('#award-show').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#award-show').width(w);
        
        $('#award-button').css("display", "inline-block");
        $('#award').css("display", "inline-block");
        
        $('#award').css('margin-top', '65%');
        $('#award-button').css('margin-top', '20%');
        $('#award-show').css('background-image', 'url("' + award_bg + '")');
        $('#award-show').css('background-position-y', '-30px');
        
        $('#award-button').focus();
        
        if(type == 'A') {
        	$('#addr').show();
            $('#award-button').css('margin-top', '4%');
        }
    });
    
    try {
        window.VJPlayer.play("http://8089test.s-itv.com/choujian/win1.wav");
    } catch (e) {
        
    }
}

function hideWin() {
    $('#award-show').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#award-button').hide();
        $('#award').hide();
        $('#award-show').hide();
        $('#addr').hide();
        $('#a-pointer').focus();
    });
}

function showLose() {
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.65 * winHeight;
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.30 * winWidth;
    }
    
    $('#award-lose').show();
    
    $('#award-lose').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#award-button-lose').css('display', 'inline-block');
        $('#award-button-lose').focus();
    });
    
    try {
        window.VJPlayer.play("http://8089test.s-itv.com/choujian/lose.wav");
    } catch (e) {
        
    }
}

function hideLose() {
    $('#award-lose').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#award-button-lose').css('display', 'none');
        $('#award-lose').hide();
        
        $('#a-pointer').focus();
    });
}

function showMsg(msg, isAwards) {
    $('#msg-content').html(msg);
    
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    $('#msg').css('height', 'auto');
    
    var h = $('#msg').height();
    if(isAwards) {
        var h = 0.8 * winHeight;
    }
    
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        if(isAwards) {
            w = 0.50 * winWidth;    
        } else {
            w = 0.30 * winWidth;
        }
    }
    
    $('#msg').height(0);
    
    $('#msg').show();
    
    var top = winHeight / 2 - h / 2 + h / 20;

    $('#msg').animate({"left": (winWidth / 2 - w / 2), "top": top, "height": h, "width": w }, 500, function(){
        $('#msg').css('height', 'auto');
        
        $('#button-msg').focus();
    });
}

function hideMsg() {
    $('#msg').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#msg').hide();
        $('#msg').css("height", "auto");
        if($('#award-show').is(':hidden')) {
            $('#a-pointer').focus();
        } else {
            $('#award-button').focus();
        }
    });
}

function showLogin() {
    var winWidth = $('body').width();
    var winHeight = $(window).height();
    
    var h = 0.3 * winHeight;
    var w = 0.9 * winWidth;
    if(winWidth > 720) {
        w = 0.35 * winWidth;
    } else {
        h = 0.3 * winHeight;
    }

    $('#login-form').show();
    
    $('#login-form').animate({"left": (winWidth / 2 - w / 2), "top": (winHeight / 2 - h / 2), "height": h, "width": w }, 500, function(){
        $('#form-content').css("display", "block");
        $('#button-login').css("display", "inline-block");
        $('#login-form').css('height', 'auto');
        
        $('#button-login').focus();
    });
}

function hideLogin() {
    $('#login-form').animate({"left": "50%", "top": "50%", "height": 0, "width": 0 }, 500, function(){
        $('#form-content').css("display", "none");
        $('#button-login').css("display", "none");
        
        $('#a-pointer').focus();
        
        $('#login-form').hide();
    });
}