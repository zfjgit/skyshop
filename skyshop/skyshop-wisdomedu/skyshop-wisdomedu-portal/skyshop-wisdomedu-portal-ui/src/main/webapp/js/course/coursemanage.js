var vm = new Vue({
	el: '#coursemanage',
	data: { courses: [], seriesCourses: [], tab: sessionStorage.courseManageTab },
	mounted: function(){
		getList('/singlecourse/', '', function(d) {
			console.log(d);
			var datas = d.data.datas;
			vm.courses = datas;
		});
		getList('/seriescourse/', '', function(d) {
			console.log(d);
			var datas = d.data.datas;
			vm.seriesCourses = datas;
		});
		
		this.tab = this.tab ? this.tab : 'tab1';
	},
	methods: {
		setCurrentTab: function(tab) {
			this.tab = tab;
			sessionStorage.courseManageTab = tab;
		},
		editCourse: function(id) {
			window.location.href = config.ui_server_url + '/course/editsinglecourse.html?' + id;
		},
		editSeriesCourse: function(id) {
			window.location.href = config.ui_server_url + '/course/createseriescourse.html?' + id;
		},
		goCourseDetail: function(id) {
			window.location.href = config.front_server_url + '/seriescoursechildinfo.aspx?id=' + id + '&openid=' + sessionStorage.openid;
		},
		goSeriesCourseDetail: function(id) {
			window.location.href = config.front_server_url + '/seriescourseinfo.aspx?id=' + id + '&openid=' + sessionStorage.openid;
		}
	}
});