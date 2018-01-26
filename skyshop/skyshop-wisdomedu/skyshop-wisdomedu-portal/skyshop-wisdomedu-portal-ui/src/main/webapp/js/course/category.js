var vm = new Vue({
	el: '#categorys',
	data: {
		id: 0,
		name: '',
		categorys: [  ]
	},
	mounted: function() {
		var data = { pageSize: 100 };
		var params = $.customParam(data);
		getList('/seriescoursecategory/', params, function(d) {
			var data = d.data;
			if(data.datas) {
				vm.categorys = [];
				for (var i = 0; i < data.datas.length; i++) {
					var s = data.datas[i];
					vm.categorys.push({ id: s.id, name: s.name, order: s.order });
				}
			}
		}, true);
		
		var ele = document.getElementById('category-list');
		var sortable = Sortable.create(ele, {
			dataIdAttr: 'data-id',
			onUpdate: function() {
				var ids = sortable.toArray();
				console.log(ids);
				var data = { ids: ids };
				var params = $.customParam(data);
				put('/seriescoursecategory/sort/', params, function(d) {
					showOkMsg('操作成功');
				});
			}
		});
	},
	methods: {
		newCategory: function() {
			$.prompt({
				title: '请输入分类名称',
				text: '',
				input: '',
				empty: false, // 是否允许为空
				onOK: function (input) {
					if(!input || !$.trim(input)) {
						showCancelMsg('请输入分类名称');
						return;
					}
					
					var data = { name: $.trim(input), order: vm.categorys.length + 1, liveStudio: { id: sessionStorage.liveStudioId } };
					var params = $.customParam(data);
					post('/seriescoursecategory/', params, function(d) {
						showOkMsg('操作成功', function() {
							window.location.reload();
						});
					});
				},
				onCancel: function () {
				    //点击取消
				}
			});
		},
		editCategory: function(item) {
			$.prompt({
				title: '请输入分类名称',
				text: '',
				input: item.name,
				empty: false, // 是否允许为空
				onOK: function (input) {
					if(!input || !$.trim(input)) {
						showCancelMsg('请输入分类名称');
						return;
					}
					
					vm.id = item.id;
					
					var data = { id: item.id, name: $.trim(input), order: item.order };
					var params = $.customParam(data);
					put('/seriescoursecategory/', params, function(d) {
						showOkMsg('操作成功');
						
						for (var i = 0; i < vm.categorys.length; i++) {
							var c = vm.categorys[i];
							if(c.id == item.id) {
								c.name = $.trim(input);
							}
						}
					});
				},
				onCancel: function () {
				    //点击取消
				}
			});
		},
		delCategory: function(id) {
			showConfirmMsg('确定要删除这个分类吗？', function() {
				del('/seriescoursecategory/' + id, '', function(d) {
					showOkMsg('操作成功');
					
					var i = 0;
					for (i = 0; i < vm.categorys.length; i++) {
						var c = vm.categorys[i];
						if(id == c.id) {
							break;
						}
					}
					vm.categorys.splice(i, 1);
				});
			}, function() {});
		},
		
		
	}
});