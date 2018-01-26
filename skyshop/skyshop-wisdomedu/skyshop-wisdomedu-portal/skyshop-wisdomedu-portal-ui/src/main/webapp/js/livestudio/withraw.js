var vm = new Vue({
	el: '#withraw',
	data: { available: 0, withraw: {}, liveStudioId: sessionStorage.liveStudioId },
	mounted: function() {
		get('/livestudiobalance/livestudio/' + this.liveStudioId, '', function(d){
			var b = d.data;
			vm.available = b.availableAmount;
		});
	},
	methods: {
		confirm: function() {
			if(!withraw.amount || !/\d+\.?\d*/gi.test(withraw.amount)) {
				showMsg("请输入正确的提现金额");
				return;
			}
			if(parseFloat(withraw.amount) > parseFloat(vm.available)) {
				showMsg("提现金额必须小于可提现余额");
				return;
			}
			if(!withraw.bankNum) {
				showMsg("请输入银行卡号");
				return;
			}
			if(!withraw.bankUser) {
				showMsg("请输入银行卡户名");
				return;
			}
			if(!withraw.bankName) {
				showMsg("请输入银行名称");
				return;
			}
			
			var params = $.customParam(vm.withraw);
			
			post('/livestudiowithrawrecord/', params, function(d){
				showOkMsg('操作成功', function(){
					window.history.go(-1);
				});
			});
		}
	}
});