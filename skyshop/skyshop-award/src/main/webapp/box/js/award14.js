var turnplate={
    prize_type : '',
    playid : '',                                    // 抽奖id
    playtimes : 0,                                  // 次数
    player: {account : '', pwd : '', point : 0, addr: '', user_name: '', user_tel: ''},    // 玩家
	colors: [],					                    // 大转盘奖品区块对应背景颜色
	prizes: [],
	cost: 10,                   // 每把消耗点数
	outsideRadius: 192,			// 大转盘外圆的半径
	textRadius: 165,			// 大转盘奖品位置距离圆心的距离
	insideRadius: 68,			// 大转盘内圆的半径
	startAngle: 0,				// 开始角度
	bRotate: false				// false:停止;ture:旋转
};

function rnd(n, m){
	// 0 <= r < 1
	var r = Math.random();
	
	// 0 <= random <= m - n
	// var random = Math.floor(r * (m - n + 1));
	
	// n <= random <= m
	var random = Math.floor(r * (m - n + 1) + n);
	return random;
}

function updateRandom() {
	// return 0;
    var prizes = turnplate.prizes;
    
	var p = Math.random();

	var sum = 0; 
	for(var i = 0; i < prizes.length; i ++) {
	    var prize = prizes[i];
	    
	    var needtimes = parseInt(prize.prizes_original) / turnplate.cost;
	    if(turnplate.playtimes < needtimes) {
	        continue;
	    }
	    
		sum += prize.prizes_probability;
	}
	p *= sum;
	
	var beforeProbilitys = 0;
	for(var i = 0; i < prizes.length; i ++) {
		var prize = prizes[i];
        
		var prob = prize.prizes_probability;
        if(prob <= 0) {
            continue;
        }
        
        var needtimes = parseInt(prize.prizes_original) / turnplate.cost;
        if(turnplate.playtimes < needtimes) {
            continue;
        }
        
		beforeProbilitys += prob;
		if( p > beforeProbilitys - prob && p < beforeProbilitys) {
			return i;
		}
	}
	return updateRandom();
}

function drawRouletteWheel() {
    // 动态添加大转盘的奖品与奖品区域背景颜色
  	var canvas = document.getElementById("wheelcanvas");    
  	if (canvas.getContext) {
	  	// 根据奖品个数计算圆周角度
	  	// 弧度=角度*Math.PI/180， 每个奖品的弧度 = (2 * Math.PI) / turnplate.prizes.length
		// 角度=弧度*180/Math.PI
		var arc = 2 * Math.PI / turnplate.prizes.length;
		var ctx = canvas.getContext("2d");
		
		// 在给定矩形内清空一个矩形
		ctx.clearRect(0, 0, 422, 422);
		
		// strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式
		ctx.strokeStyle = "#FFBE04";
		
		// font 属性设置或返回画布上文本内容的当前字体属性
		ctx.font = '16px Microsoft YaHei';
	  
	  	for(var i = 0; i < turnplate.prizes.length; i++) {       
			var angle = turnplate.startAngle + i * arc;
			ctx.fillStyle = turnplate.colors[i];
			ctx.beginPath();
			
			// arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）
			ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);    
			ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
			ctx.stroke();  
			ctx.fill();
			
			// 锁画布(为了保存之前的画布状态)
			ctx.save();   
			
			// ----绘制奖品开始----
			ctx.fillStyle = "#E5302F";
			
			var prize = turnplate.prizes[i];
			
			var text = prize.prizes_name;
			
			var line_height = 20;
			
			// translate方法重新映射画布上的 (0,0) 位置
			ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);
			
			// rotate方法旋转当前的绘图
			ctx.rotate(angle + arc / 2 + Math.PI / 2);
			
		  	/** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) * */
		  	if(text.indexOf(" ") > 0) {// 流量包
			  	var texts = text.split(" ");
			  	for(var j = 0; j < texts.length; j++){
				  	ctx.font = j == 0 ? 'bold 18px Microsoft YaHei' : '16px Microsoft YaHei';
				  	if(j == 0) {
					  	ctx.fillText(texts[j], - ctx.measureText(texts[j]).width / 2, j * line_height);
				  	} else {
				  		if(texts[j].length > 5) {
				  			var line2 = texts[j].substring(0, 5) + "||" + texts[j].substring(5);
							var line2s = line2.split("||");
							for(var k = 0; k < line2s.length; k ++){
							 	ctx.fillText(line2s[k], - ctx.measureText(line2s[k]).width / 2, (j + k) * line_height);
							}
				  		} else {
						  	ctx.fillText(texts[j], - ctx.measureText(texts[j]).width / 2, j * line_height);
				  		}
				  	}
			  	}
		  	} else if(text.indexOf(" ") == -1 && text.length > 5) {// 奖品名称长度超过一定范围
				text = text.substring(0, 5) + "||" + text.substring(5);
				var texts = text.split("||");
				for(var j = 0; j < texts.length; j++){
				 	ctx.fillText(texts[j], - ctx.measureText(texts[j]).width / 2, j * line_height);
				}
		  	} else {
			  	// 在画布上绘制填色的文本。文本的默认颜色是黑色
			  	// measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
			  	ctx.fillText(text, - ctx.measureText(text).width / 2, 0);
		  	}
		  	
		  	if(prize.prizes_type == 'A') {
		  	    var h = 2 * line_height;
		  	    var texts = text.split(" ");
		  	    if(texts.length > 1) {
		  	        if(texts[1].length > 5) {
		  	            h = 3 * line_height;
		  	        }
		  	    }
		  	    var origiText = '价值:' + prize.prizes_original;
		  	    ctx.font = '14px Microsoft YaHei';
		  	    ctx.fillText(origiText, -ctx.measureText(origiText).width / 2, h);
            }
		  	
			// 添加对应图标
  			if(text.indexOf("商贸通") >= 0){
	  			var img = document.getElementById("shan-img");
	  			img.onload = function(){  
		  			ctx.drawImage(img, -15, 30);      
	  			}; 
	  			ctx.drawImage(img, -15, 30);  
  			} else if (text.indexOf("谢谢参与") >= 0 || (prize.prizes_type == 'B' && prize.prizes_original == 0)){
	  			var img = document.getElementById("sorry-img");
	  			img.onload = function(){  
		  			ctx.drawImage(img, -15, 25);      
	  			};  
	  			ctx.drawImage(img, -15, 25);  
  			}
			
  			// 把当前画布返回（调整）到上一个save()状态之前
  			ctx.restore();
  			// ----绘制奖品结束----
	  	}     
  	} 
}


function initFocus() {
    $('#a-pointer').on('focusin', function(){
        $('#pointer').attr('src', point_active_bg);
    });
    
    $('#a-pointer').on('focusout', function(){
        $('#pointer').attr('src', point_bg);
    });
    
    $('#a-awards').on('focusin', function(){
        $('#a-awards').parent('span').css('border', 'solid 5px #0066ff');
    });
    
    $('#a-awards').on('focusout', function(){
        $('#a-awards').parent('span').css('border', 'solid 5px transparent');
    });
    
    $('#a-pointer').focus();
    
    $('.award-button').on('focusin', function(){
        $(this).css('border', 'solid 6px #0066ff');
    });
    
    $('.award-button').on('focusout', function(){
        $(this).css('border', 'solid 6px transparent');
    });
    
    $('#a-editaddr').on('focusin', function(){
        $('#a-editaddr').css('border', 'solid 6px #0066ff');
    });
    
    $('#a-editaddr').on('focusout', function(){
        $('#a-editaddr').css('border', 'solid 6px transparent');
    });
    
    $('#a-saveaddr').on('focusin', function(){
        $('#a-saveaddr').css('border', 'solid 6px #0066ff');
    });
    
    $('#a-saveaddr').on('focusout', function(){
        $('#a-saveaddr').css('border', 'solid 6px transparent');
    });
    
    $(':input').on('focusin', function(){
        $(this).css('border', 'solid 6px #0066ff');
    });
    
    $(':input').on('focusout', function(){
        $(this).css('border', 'solid 1px gray');
    });
    
    $(document).bind("keydown", function (e) {
        var keycode = e.which;
        var vid = document.activeElement.id;
        try {
            switch (keycode) {
                case 8: 
                    // back
                    if($('#msg').is(':visible')) {
                        hideMsg();
                        // $('#msg').hide();
                        // $('#msg').find('.award-button').click();
                    } else if($('#award-show').is(':visible')) {
                        hideWin();
                        // $('#award-show').hide();
                    } else if($('#award-lose').is(':visible')) {
                        hideLose();
                        // $('#award-lose').hide();
                        // $('#award-lose').find('.award-button').click();
                    } else if($('#login-form').is(':visible')) {
                        hideLogin();
                        // $('#login-form').hide();
                        // $('#login-form').find('.award-button').click();
                    } else {
                        window.history.go(-1);
                    }
                    break;
                case 37:
                    // left
                    if(!vid || vid == 'a-awards') {
                        $('#a-pointer').focus();
                    } else if(vid == 'a-editaddr') {
                        $('#province').focus();
                    } else if(vid == 'district') {
                        $('#city').focus();
                    } else if(vid == 'city') {
                        $('#province').focus();
                    } else if(vid == 'user_tel') {
                        $('#user_name').focus();
                    } else if(vid == 'user_name') {
                        $('#detailaddr').focus();
                    } else if(vid == 'a-saveaddr') {
                        $('#user_tel').focus();
                    }
                    break;
                case 38:
                    // up
                    if(vid == 'award-button') {
                        if(turnplate.prize_type == 'A' || turnplate.prize_type == '') {
                            if($('#province').is(':hidden')) {
                                $('#a-editaddr').focus();
                            } else {
                                $('#user_name').focus();
                            }
                        }
                    } else if(vid == 'province' || vid == 'city' || vid == 'district') {
                        $('#a-editaddr').focus();
                    } else if(vid == 'detailaddr') {
                        $('#province').focus();
                    } else if(vid == 'user_name') {
                        $('#province').focus();
                    } else if(vid == 'user_tel') {
                        $('#city').focus();
                    } else if(vid == 'a-saveaddr') {
                        $('#district').focus();
                    } else if(vid == 'button-msg' && $('#awards-list')[0]) {
                        $('#awards-list').scrollTop($('#awards-list').scrollTop() - 40);
                    }
                    break;
                case 39:
                    // right
                    if(!vid || vid == 'a-pointer') {
                        $('#a-awards').focus();
                    } else if(vid == 'a-editaddr') {
                        $('#district').focus();
                    } else if(vid == 'province') {
                        $('#city').focus();
                    } else if(vid == 'city') {
                        $('#district').focus();
                    } else if(vid == 'detailaddr') {
                        $('#user_name').focus();
                    } else if(vid == 'user_name') {
                        $('#user_tel').focus();
                    } else if(vid == 'user_tel') {
                        $('#a-saveaddr').focus();
                    }
                    break;
                case 40:
                    // down
                    if(vid == 'a-editaddr') {
                        $('#province').focus();
                    } else if(vid == 'province') {
                        $('#detailaddr').focus();
                    } else if(vid == 'detailaddr' || vid == 'user_name' || vid == 'user_tel' || vid == 'a-saveaddr') {
                        $('#award-button').focus();
                    } else if(vid == 'city') {
                        $('#user_name').focus();
                    } else if(vid == 'district') {
                        $('#a-saveaddr').focus();
                    } else if(vid == 'button-msg' && $('#awards-list')[0]) {
                        $('#awards-list').scrollTop($('#awards-list').scrollTop() + 40);
                    }
                    break;
            }
        } catch (e) {

        }
    });
}

function initText() {
    $('#a-awards').text(text_awards);
    $('#text_point').text(text_point);
    $('#text_cost_1').text(text_cost_1);
    $('#text_cost_2').text(text_cost_2);
    $('#award-list-title').text(text_awards_list);
    $('#text_receiver').text(text_receiver);
    $('#text_addr').text(text_addr);
    $('#text_tel').text(text_tel);
    $('#text_awards_a_tips').text(text_awards_a_tips);
    
    $('body').css('background-image', 'url("' + bg + '")');
}

$(function() {
    initFocus();
    
    initText();
    
    // showWin('123', 'A');
    
    var player = getCookie('account');
    if(!player) {
        try {
        	var mac = '';
            var search = window.location.search.substring(1);
            var params = search.split('&');
            for (var i = 0; i < params.length; i++) {
				var p = params[i];
				var pname = p.split('=')[0];
				var pvalue = p.split('=')[1];
				if(pname == 'mac') {
					mac = pvalue;
				}
			}
            // D896E0DF01D0
            // 821b582328ef
            if(!mac) {
                showLogin();
            } else {
                doMacLogin(mac);
            }
        } catch(e) {
            showLogin();
        }
    } else {
        var account = getCookie('account');
        var pwd = getCookie('pwd');
        var point = getCookie('point');
        
        turnplate.player.account = account;
        turnplate.player.pwd = pwd;
        turnplate.player.point = point;
        
        $('#player').text(account);
        $('#point').text(point);
        
        doLogin(account, pwd);
    }
    
	$.getJSON(url, 'type=getGamePrizesAndPoint&game_sno=' + gameid, function(d) {
	    if(d && d.status == 1 && d.list && d.list.length > 0) {
	        var point = d.point;
	        $('#cost').text(point);
	        
	        turnplate.cost = point;
	        
	        var prizes = d.list;
	        
	        turnplate.prizes = prizes;
            
            turnplate.colors = [];
            
            for (var i = 0; i < prizes.length; i++) {
                var prize = prizes[i];
                if(i % 2 == 0) {
                    turnplate.colors.push('#FFF4D6');
                } else {
                    turnplate.colors.push('#FFFFFF');
                }
            }
            
            // 默认3点钟位置为起点
            turnplate.startAngle = 270 * Math.PI / 180 - ((360 * Math.PI / 180) / turnplate.prizes.length) / 2; 
            
            drawRouletteWheel();
	    } else {
	        showMsg('奖品信息加载失败');
	    }
	});
	
	// 旋转转盘 item:奖品位置; txt：提示语;
	var rotateFn = function (angles, item){
		$('#pointer').stopRotate();
		
		$('#pointer').rotate({
			angle: 0,
			animateTo: angles + 1080,
			duration: 9000,
			callback: function (){
			    if(!turnplate.playid) {
			        showMsg('数据连接错误，请检查您的网络连接');
			        return;
			    }
			    
				turnplate.bRotate = !turnplate.bRotate;
				
				var prize = turnplate.prizes[item];
				
				turnplate.prize_type = prize.prizes_type;
				
				if(prize.prizes_type == 'B' && prize.prizes_original == 0) {
				    showLose();
					return;
				}
				
				turnplate.playtimes --;
				
				showWin(prize.prizes_name, prize.prizes_type);
				
				var point = 0;
				if(prize.prizes_type == 'B') {
				    point = prize.prizes_original;
				} else {
					if(turnplate.player.user_tel) {
						if(turnplate.player.user_name && turnplate.player.user_address) {
							var addr = turnplate.player.user_address + ' / ' + turnplate.player.user_tel + ' / ' + turnplate.player.user_name;
							var smstext = sms2.replace('#player#', turnplate.player.account).replace('#prize#', prize.prizes_name).replace('#address#', addr);
							var tpl = encodeURI(turnplate.player.user_tel) + '|' + smstext;
							$.post(sms, 'type=sms&md5=' + encrypt(tpl).ciphertext.toString(), function(d) {
							});
						} else {
                            var smstext = sms1.replace('#player#', turnplate.player.account).replace('#prize#', prize.prizes_name);
							var tpl = encodeURI(turnplate.player.user_tel) + '|' + smstext;
							$.post(sms, 'type=sms&md5=' + encrypt(tpl).ciphertext.toString(), function(d) {
							});
						}
					}
				}
				
				var param = 'type=winning&winning_sno=' + turnplate.playid + '&prize_type=' + prize.prizes_type + '&prize_name=' + prize.prizes_name + 
				            '&point=' + point;
				
				$.post(url, param, function(d){
                    if(d) {
                        var data = $.parseJSON(d);
                        if(data.status == 1) {
                            var point = data.point;
                            
                            turnplate.player.point = point;
                            $('#point').text(point);
                            setCookie('point', point);
                            
                            if(prize.prizes_type == 'B') {
                                turnplate.playid = '';
                            }
                        } else {
                            showMsg('数据连接错误，请检查您的网络连接');
                        }
                    }
                });
			}
		});
		
		try {
		    window.VJPlayer.play("http://8089test.s-itv.com/choujian/award.wav");
		} catch (e) {
	        
	    }
	};

	$('#a-pointer').click(function (){
		if(turnplate.bRotate) {
			return;
		}
		
		if(!turnplate.player || !turnplate.player.account || !turnplate.player.pwd) {
		    showMsg('您需要先登录才能参与抽奖');
		    return;
		}
		
		if(turnplate.player.point < turnplate.cost) {
		    showMsg('您的商贸通点数余额不足，请充值');
		    return;
		}
		
		var account = turnplate.player.account;
		var pwd = turnplate.player.pwd;
		$.post(url, 'type=luckDraw&user2_no=' + account + '&user2_pwd=' + pwd + '&game_sno=' + gameid, function(d){
		    if(d) {
		        d = d.replace('\r', '').replace('\n', '');
                
		        var data = $.parseJSON(d);
		        if(data.status == 1) {
		            var point = data.point;
		            var winning_sno = data.winning_sno;
		            
		            var user_name = data.user_name;
		            var user_tel = data.user_tel;
		            var user_address = data.user_address;
		            
		            $('#user_address').val(user_address);
		            $('#user_name').val(user_name);
		            $('#user_tel').val(user_tel);
		            
		            // var user_name = getCookie('user_name');
                    // var user_tel = getCookie('user_tel');
                    // var user_address = getCookie('user_address');
		            
		            turnplate.player.user_name = user_name;
		            turnplate.player.user_tel = user_tel;
		            turnplate.player.user_address = user_address;
		            
		            turnplate.player.point = point;
		            $('#point').text(point);
		            setCookie('point', point);
		            
		            turnplate.playid = winning_sno;
		            turnplate.playtimes ++;
		        } else {
		            turnplate.playid = '';
		            showMsg('数据连接错误，请检查您的网络连接');
		        }
		    }
		});
		
		turnplate.bRotate = !turnplate.bRotate;
		
		// 获取随机数(奖品个数范围内)
		// var item = rnd(0, turnplate.prizes.length - 1);
		var item = updateRandom();
		
		var itemAngles = 360 / turnplate.prizes.length;
		var angles = item * itemAngles;
		
		rotateFn(angles, item);
	});
});

function saveNewAddr() {
    var p = $('#province').val();
    var c = $('#city').val();
    var d = $('#district').val();
    var detail = $('#detailaddr').val();
    var user_name = $('#user_name').val();
    var user_tel = $('#user_tel').val();
    
    if(!p || !c) {
        showMsg('请输入正确的收货地址');
        return;
    }
    
    if(!detail) {
        showMsg('请输入详细地址');
        return;
    }
    
    if(!user_name) {
        showMsg('请输入收货人姓名');
        return;
    }
    
    if(!user_tel) {
        showMsg('请输入联系电话');
        return;
    }
    
    var user_address = p + c + d + detail;
    
    $.post(url, 'type=setAddress&winning_sno=' + turnplate.playid + '&user_name=' + user_name + '&user_tel=' + user_tel + '&user_address=' + user_address, function(d){
        if(d) {
            var data = $.parseJSON(d);
            if(data && data.status == 1) {
                showMsg('收货地址修改成功');
                
                turnplate.player.user_name = user_name;
                turnplate.player.user_tel = user_tel;
                turnplate.player.user_address = user_address;
                
                setCookie('user_name', user_name);
                setCookie('user_tel', user_tel);
                setCookie('user_address', user_address);
                
                $('#origin-addr').text(user_address + ' / ' + user_name + ' / ' + user_tel);
            } else {
                showMsg('收货地址修改失败');
            }
        }
    });
}

function login() {
    var account = $('#account').val();
    if(!account) {
        showMsg('请输入商贸通账号');
        return;
    }
    
    var pwd = $('#password').val();
    if(!pwd) {
        showMsg('请输入商贸通密码');
        return;
    }
    
    doLogin(account, pwd);
}

function doLogin(account, pwd) {
    if(!account || !pwd) {
        return;
    }
    
    $.post(url, 'type=getUserPoint&user2_no=' + account + '&user2_pwd=' + pwd, function(d){
        if(d) {
            var data = $.parseJSON(d);
            if(data.status == 1) {
                var point = data.point;
                $('#player').text(account);
                $('#point').text(point);
                
                turnplate.player.account = account;
                turnplate.player.pwd = pwd;
                turnplate.player.point = point;
                
                setCookie('account', account);
                setCookie('pwd', pwd);
                setCookie('point', point);
                
                hideLogin();
            } else {
                setCookie('account', '');
                setCookie('pwd', '');
                setCookie('point', 0);
                
                showMsg('商贸通账号或密码错误');
            }
        }
    });
}

function doMacLogin(mac) {
    if(!mac) {
        return;
    }
    
    $.post(url, 'type=getUserPointToMac&mac=' + mac, function(d){
        if(d) {
            var data = $.parseJSON(d);
            if(data.status == 1) {
                var point = data.point;
                $('#player').text(data.user2_no);
                $('#point').text(point);
                
                turnplate.player.account = data.user2_no;
                turnplate.player.pwd = data.user2_pwd;
                turnplate.player.point = point;
                
                setCookie('account', data.user2_no);
                setCookie('pwd', data.user2_pwd);
                setCookie('point', point);
                
                hideLogin();
            } else {
                setCookie('account', '');
                setCookie('pwd', '');
                setCookie('point', 0);
                
                showMsg('商贸通账号或密码错误');
            }
        }
    });
}

function showAwards() {
    $.getJSON(url, 'type=getWinningRecord&user2_no=' + turnplate.player.account, function(d) {
        if(d && d.status == 1) {
            var awards = d.list;
            
            var list = '<div style="text-align:center;padding:10px;font-weight:bold;font-size:30px;">' + text_awards + '<span style="font-size:25px;">(' + text_awards_tips + ')</span></div>';
            list += '<div style="height:45px;margin-bottom:0;display:block;"><div style="width:10%;display:inline-block;border:solid 1px red;float:left;padding:5px 0;font-weight:bold;">' + text_no + '</div><div style="float:left;display:inline-block;width:25%;border:solid 1px red;padding:5px 0;border-left:0;font-weight:bold;">' + text_date + '</div><div style="float:left;display:inline-block;width:25%;border:solid 1px red;padding:5px 0;border-left:0;font-weight:bold;">' + text_time + '</div><div style="float:left;display:inline-block;width:39%;border:solid 1px red;padding:5px 0;border-left:0;font-weight:bold;">' + text_prize + '</div></div>';
            list += '<div style="clear:both;"></div>';
            list += '<div id="awards-list" style="max-height:360px;overflow:auto;font-size:25px;margin-top:0;display:block;">';
            list += '<table cellspacing=1 style="background-color:red;">';
            // list += '<tr style="background-color:red;"><th style="width:50px;">序号</th><th>抽奖时间</th><th>获得奖品</th></tr>';
            
            for (var i = 0; i < awards.length; i++) {
                var award = awards[i];
                list += '<tr><td style="width:10%">' + (i + 1) + '</td><td style="width:24%">' + (award.cdate.split(' ')[0]) + '</td><td style="width:25%">' + (award.cdate.split(' ')[1]) + '</td><td style="width:39%">' + (award.prizes_name.replace(' ', '：')) + '</td></tr>';
            }
            list += '</table>';
            list += '</div>';
            list += '<div style="font-size:25px;color:#0066ff;text-align:center;margin-top:5px;">' + text_awards_tips_1 + '</div>';
            
            showMsg(list, true);
        } else {
            showMsg(text_awards_tips_2);
        }
    });
}
