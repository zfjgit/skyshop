var vm = new Vue({
	el: '#advisory',
	data: {
		courseType: 2,
		courses: [ { id: 1, title: '普通话发音', lastTime: '05-01 12:11', count: 1 }, 
				{ id: 2, title: '自拍美颜', lastTime: '05-02 09:01', count: 1 } 
		],
		seriesCourses: [
			{ id: 1, title: 'Photoshop入门教程', lastTime: '05-01 12:11', count: 1 }, 
			{ id: 2, title: 'Office入门教程', lastTime: '05-02 09:01', count: 1 } 
		],
		tab: sessionStorage.advisoryTab,
	},
	mounted: function() {
		var data = { pageSize: 100 };
		var params = $.customParam(data);
		get('/courseadvisory/', params, function(d) {
			var data = d.data;
			vm.courses = data.courseAdvisoryInfos;
			vm.seriesCourses = data.seriesCourseAdvisoryInfos;
		}, true);
		
		this.tab = this.tab ? this.tab : 'tab1';
	},
	methods: {
		setCurrentTab: function(tab) {
			this.tab = tab;
			sessionStorage.advisoryTab = tab;
		},
		goSeriesCourseAdvisory: function(id) {
			sessionStorage.advisoryCourseId = id;
			sessionStorage.advisoryCourseType = 2;
			window.location.href = config.ui_server_url + '/course/courseadvisory.html';
		},
		goCourseAdvisory: function(id) {
			sessionStorage.advisoryCourseId = id;
			sessionStorage.advisoryCourseType = 1;
			window.location.href = config.ui_server_url + '/course/courseadvisory.html';
		}
	}
});