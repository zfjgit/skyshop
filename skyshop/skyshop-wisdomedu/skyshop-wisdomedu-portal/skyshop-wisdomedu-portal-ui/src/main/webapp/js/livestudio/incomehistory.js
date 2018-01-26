var vm = new Vue({
	el: '#historys',
	data: { incomes: [] },
	mounted: function() {
		var data = { liveStudio: { id: sessionStorage.liveStudioId }};
		var params = $.customParam(data);
		getList('/livestudioincomes/', params, function(d){
			vm.incomes = d.data.datas;
		});
	},
	methods: {
		
	}
});