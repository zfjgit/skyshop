var vm = new Vue({
	el: '#livestudioincomes',
	data: { total: 0, available: 0, withdrawn: 0, liveStudioId: sessionStorage.liveStudioId },
	mounted: function() {
		get('/livestudiobalance/livestudio/' + this.liveStudioId, '', function(d){
			var b = d.data;
			vm.total = b.totalAmount;
			vm.available = b.availableAmount;
			vm.withdrawn = b.withdrawnAmount;
		});
	},
	methods: {
		goHistory: function() {
			window.location.href = config.ui_server_url + '/livestudio/incomehistory.html';
		},
		goWithraw: function() {
			window.location.href = config.ui_server_url + '/livestudio/withraw.html';
		},
		goWithrawHistory: function() {
			window.location.href = config.ui_server_url + '/livestudio/withrawhistory.html';
		}
	}
});