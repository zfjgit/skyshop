var vm = new Vue({
	el: '#users',
	data: { users: [] },
	mounted: function() {
		var data = { liveStudio: { id: sessionStorage.liveStudioId } };
		var params = $.customParam(data);
		getList('/userlivestudio/', params, function(d){
			var datas = d.data.datas;
			vm.users = [];
			
			vm.users = datas;
		});
	},
	methods: {
		remove: function(id) {
			showConfirmMsg('确定要移除这个关注用户吗？', function() {
				del('/userlivestudio/' + id, '', function() {
					showOkMsg('操作成功', function(){
						window.location.reload();
					});
				});
			});
		}
	}
});