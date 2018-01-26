var vm = new Vue({
	el: '#history',
	data: { withraws: [] },
	mounted: function() {
		var data = { liveStudio: { id: sessionStorage.liveStudioId }};
		var params = $.customParam(data);
		getList('/livestudiowithrawrecord/', params, function(d){
			vm.withraws = d.data.datas;
		});
	},
});