var vue = new Vue({
	el: '#info',
	data: {
		id: 0,
		openid: '',
		name: '我的直播间',
		introduction: '',
		headimg: config.ui_server_url + '/images/logo.png',
		link: 'http://localhost:8080/livestudio/1',
	},
	mounted: function() {
		this.id = sessionStorage.liveStudioId;
		if(!this.id) {
			showError('直播间不存在', function() {});
			return;
		}
		get('/livestudio/' + this.id, '', function(d) {
			console.log(d);
			
			if(d.code != config.success_code) {
				showError('直播间不存在', function() {
					window.history.go(-1);
				});
			} else {
				var data = d.data;
				vue.name = data.name;
				vue.headimg = data.headimg;
				vue.link = data.link;
				vue.introduction = data.introduction;
				vue.id = data.id;
			}
		});
	},
	methods: {
		editName: function() {
			$.prompt({
				title: '请输入直播间名称',
				text: '',
				input: this.name,
				empty: false, // 是否允许为空
				onOK: function (input) {
					vue.name = input;
					var data = { id: vue.id, name: input };
					var params = $.customParam(data);
					put('/livestudio/updatename', params, function(d){
						showOkMsg('操作成功');
					});
				},
				onCancel: function () {
				    //点击取消
				}
			});
		},
		uploadLogo: function() {
			showMsg("正在上传LOGO");
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			$.ajax({
			    url: config.api_server_url + '/upload/livestudio/logo',
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false,
			    crossDomain : true,
			    headers : {
		            "Token-Header" : config.loginUserInfo.token,
		            "X-Requested-With" : "XMLHttpRequest",
		        },
			}).done(function(res) {
				console.log('res=' + res);
				if(res && res.code == 1 && res.url) {
					showOkMsg("LOGO上传成功");
					vue.headimg = res.url;
					
					var data = { id: vue.id, headimg: res.url };
					var params = $.customParam(data);
					put('/livestudio/updateheadimg', params, function(d){
						showOkMsg('操作成功');
					});
				} else {
					showError('LOGO上传失败，请稍后再试');
				}
			}).fail(function(res) {
				showError('LOGO上传失败，请稍后再试');
				console.log('fail:' + res);
			});
		},
		editIntro: function() {
			$.prompt({
				title: '请输入直播间简介',
				text: '',
				input: this.introduction,
				empty: false, // 是否允许为空
				onOK: function (input) {
					vue.introduction = input;
					console.log(input);
					
					var data = { id: vue.id, introduction: input };
					var params = $.customParam(data);
					put('/livestudio/updateintroduction', params, function(d){
						showOkMsg('操作成功');
					});
				},
				onCancel: function () {
				}
			});
			
			$("#weui-prompt-input").remove(); //去除原始输入框 
			$(".weui-dialog__bd").append('<textarea rows="8" style="height:70px;width:100%;" class="weui_input weui-prompt-input" id="weui-prompt-input" value="' + vue.introduction + '">' + vue.introduction + '</textarea>'); 
		},
		editBg: function() {
			window.location.href = config.ui_server_url + '/livestudio/settings.html';
		}
	}
});


$(function() {
	
});