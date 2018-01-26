var vue = new Vue({
	el: '#manage',
	data: { 
		id: '1',
		openid: '',
		info : { headimg: config.ui_server_url + '/images/logo.png', name: '张三的直播间' },
		data : { follow: 133, income: 1133.00 },
	},
	mounted: function() {
		var s = window.location.search;
		if(s) {
			var ss = s.split('&');
			if(ss.length > 1) {
				if(ss[0] && ss[0].indexOf('=') != -1) {
					var id = ss[0].split('=')[1];
					if(id) {
						this.id = id;
					}
				}
				if(ss[1] && ss[1].indexOf('=') != -1) {
					var openid = ss[1].split('=')[1];
					if(openid) {
						this.openid = openid;
					}
				}
			}
		}
		
		if(!this.id) {
			showCancelMsg('参数错误', function() {
				window.history.go(-1);
			});
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
				vue.info = { headimg: data.headimg, name: data.name }
				vue.data.follow = data.followCount;
				vue.data.income = data.income;
				
				sessionStorage.liveStudioId = data.id;
				sessionStorage.openid = data.openid;
			}
		});
	},
	methods : {
		goInfoPage : function() {
			window.location.href = config.ui_server_url + '/livestudio/info.html';
		},
		goCreateTopic: function() {
			window.location.href = config.ui_server_url + '/course/createsinglecourse.html';
		},
		goCreateSeriesCourse : function() {
			window.location.href = config.ui_server_url + '/course/createseriescourse.html';
		},
		goCreateNews: function() {
			window.location.href = config.ui_server_url + '/livestudio/createnews.html';
		},
		goHomePageSettings: function() {
			window.location.href = config.ui_server_url + '/livestudio/settings.html';
		},
		goUserManage: function() {
			window.location.href = config.ui_server_url + '/user/usermanage.html';
		},
		goAdvisiroyManage: function() {
			window.location.href = config.ui_server_url + '/course/advisory.html';
		},
		goCourseManage: function() {
			window.location.href = config.ui_server_url + '/course/coursemanage.html';
		},
		goDataStatistics: function() {
			window.location.href = config.ui_server_url + '/livestudio/datastatistics.html';
		},
		goSeriesCourseCategory: function() {
			window.location.href = config.ui_server_url + '/course/category.html';
		},
		goNewsManage: function() {
			window.location.href = config.ui_server_url + '/livestudio/newsmanage.html';
		},
		goIncomeManage: function() {
			window.location.href = config.ui_server_url + '/livestudio/incomes.html';
		},
		
		goHomePage: function() {
			window.location.href = config.front_server_url + '/myinfo.aspx?openid=' + sessionStorage.openid;
		},
		goMemberInfo: function() {
			window.location.href = config.front_server_url + '/livestudioinfo.aspx?id=' + sessionStorage.liveStudioId + '&openid=' + sessionStorage.openid;
		},
	}
});