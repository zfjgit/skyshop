var vm = new Vue({
	el: '#news',
	data: { news: [] },
	mounted: function() {
		var data = { liveStudio: { id: sessionStorage.liveStudioId } };
		var params = $.customParam(data);
		getList('/livestudionews/', params, function(d) {
			var datas = d.data.datas;
			var datenews = {};
			for (var i = 0; i < datas.length; i++) {
				var n = datas[i];
				var date = n.createTime.split(' ')[0];
				if(datenews[date]) {
					datenews[date].push(n);
				} else {
					datenews[date] = [n];
				}
			}
			for(var date in datenews) {
				console.log(date);
				if(typeof date == 'string' && typeof datenews[date] == 'object' && date.indexOf('-') != -1) {
					var dates = date.split('-');
					var year = dates[0];
					var month = dates[1];
					var day = dates[2];
					vm.news.push({ date: date, year: year, month: month, day: day, news: datenews[date] });
				}
			}
		}, true);
	},
	methods: {
		del: function(id) {
			showConfirmMsg('确定要删除这条动态吗？', function() {
				del('/livestudionews/' + id, '', function(d){
					showOkMsg('操作成功', function() {
						window.location.reload();
					});
				});
			});
		},
		goSubject: function(type, id) {
			if(type == 1) {
				window.location.href = config.front_server_url + '/seriescoursechildinfo.aspx?id=' + id + '&openid=' + sessionStorage.openid;
			} else if(type == 2) {
				window.location.href = config.front_server_url + '/seriescourseinfo.aspx?id=' + id + '&openid=' + sessionStorage.openid;
			}
		}
	}
});