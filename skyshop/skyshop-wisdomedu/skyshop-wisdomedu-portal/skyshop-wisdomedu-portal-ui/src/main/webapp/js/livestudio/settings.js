var vue = new Vue({
	el: '#settings',
	data: {
		id: 0,
		openid: '',
		name: '我的直播间',
		logo: config.ui_server_url + '/images/logo.png',
		follow: 889,
		bg: { color: 'white', 'background-image': 'url(' + config.ui_server_url + '/images/bg-2.jpeg)' },
		bgs: [ { url: config.ui_server_url + '/images/bg-1.jpeg' }, 
			{ url: config.ui_server_url + '/images/bg-2.jpeg' }, 
			{ url: config.ui_server_url + '/images/bg-3.jpeg' },
			{ url: config.ui_server_url + '/images/bg-4.jpeg' },
			{ url: config.ui_server_url + '/images/bg-5.jpeg' }, 
			{ url: config.ui_server_url + '/images/bg-6.jpeg' }, 
			{ url: config.ui_server_url + '/images/bg-7.jpeg' },
			{ url: config.ui_server_url + '/images/bg-8.jpeg' }, 
			{ url: config.ui_server_url + '/images/bg-9.jpeg' },
			{ url: config.ui_server_url + '/images/bg-10.jpeg' },
		],
		selectedBg: ''
	},
	mounted: function() {
		console.log('mounted<<<');
		
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
				vue.bg['background-image'] = 'url(' + data.backgroundimg + ')';
			}
		});
	},
	methods: {
		selectBg: function(index){
			console.log(index);
			vue.bg['background-image'] = 'url(' + vue.bgs[index].url + ')';
			vue.selectedBg = vue.bgs[index].url;
		},
		uploadBg: function() {
			showMsg("正在上传背景图");
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			$.ajax({
			    url: config.api_server_url + '/upload/livestudio/bg',
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
				console.log('res=' + JSON.stringify(res));
				if(res && res.code == 1 && res.url) {
					showOkMsg("背景图上传成功");
					var bg = res.url;
					console.log(bg);
					bg = bg.replace(/\\/gi, '/');
					vue.bg['background-image'] = 'url(' + bg + ')';
					vue.selectedBg = bg;
				} else {
					showError('背景图上传失败，请稍后再试');
				}
			}).fail(function(res) {
				showError('背景图上传失败，请稍后再试');
				console.log('fail:' + JSON.stringify(res));
			});
		},
		confirm: function() {
			if(!vue.selectedBg) {
				return;
			}
			var data = { id: vue.id, backgroundimg: vue.selectedBg };
			var params = $.customParam(data);
			put('/livestudio/updatebackgroundimg', params, function(d){
				showOkMsg('操作成功');
			});
		}
	},
});