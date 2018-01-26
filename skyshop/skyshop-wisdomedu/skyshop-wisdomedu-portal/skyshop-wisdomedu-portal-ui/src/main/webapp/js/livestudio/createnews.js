var vm = new Vue({
	el: '#createnews',
	data: {
		seriesCourses: [ ],
		courses: [ ],
		newsTypes: [ { code: 2, name: '系列课' }, { code: 1, name: '课程' } ],
		subjectTypeCode: '2',
		subjectId: '',
		content: '',
		name: '',
		bg: config.ui_server_url + '/images/course-bg-2.jpg',
		mySwiper: '',
	},
	mounted: function() {
		var data = { pageSize: 100  };
		var params = $.customParam(data);
		getList('/seriescourse/', params, function(d) {
			var data = d.data;
			if(data.datas) {
				vm.seriesCourses = [];
				for (var i = 0; i < data.datas.length; i++) {
					var s = data.datas[i];
					vm.seriesCourses.push({ id: s.id, name: s.title, bg: s.headimg, });
				}
			}
		}, true);
		
		getList('/singlecourse/', params, function(d) {
			var data = d.data;
			if(data.datas) {
				vm.courses = [];
				for (var i = 0; i < data.datas.length; i++) {
					var s = data.datas[i];
					vm.courses.push({ id: s.id, name: s.title, bg: s.headimg, });
				}
			}
		}, true);
		
		this.mySwiper = new Swiper('.swiper-container', {
			autoplay: 0,//可选选项，自动滑动
			nextButton: '',
			prevButton: '.btn_prev'
		});
	},
	methods: {
		selectSeriesCourse: function(item) {
			vm.subjectId = item.id;
			vm.name = item.name;
		},
		selectCourse: function(item) {
			vm.subjectId = item.id;
			vm.name = item.name;
		},
		setNewsType: function(code) {
			vm.subjectTypeCode = code;
		},
		next: function() {
			if(!vm.subjectId || !vm.subjectTypeCode) {
				showMsg('请选择一个动态类型');
				return;
			}
			
			vm.mySwiper.slideNext();
		},
		confirm: function() {
			if(!vm.content) {
				showMsg('请输入动态内容');
				return;
			}
			
			var news = { content: vm.content, subjectId: vm.subjectId };
			news.subjectType = { code : vm.subjectTypeCode };
			news.liveStudio = { id: sessionStorage.liveStudioId };
			
			var params = $.customParam(news);
			post('/livestudionews/', params, function(d) {
				showOkMsg('操作成功', function() {
					window.location.href = config.ui_server_url + '/livestudio/manage.html';
				});
			});
		}
	}
});