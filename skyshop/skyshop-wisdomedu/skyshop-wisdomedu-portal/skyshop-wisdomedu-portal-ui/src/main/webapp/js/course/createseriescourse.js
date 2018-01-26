var vm = new Vue({
	el: '#seriescourse',
	data: {
		id: '',
		poster: '',
		name: '',
		count: '',
		tickets: '',
		introduction: '',
		chargeTypeCode: '1',
		categoryId: '',
		categoryName: '未设置',
		categorys: [],
		posterUploadBg: {},
	},
	mounted: function() {
		var s = window.location.search;
		if(s) {
			this.id = s.substring(1);
			get('/seriescourse/' + this.id, '', function(d){
				$('title').text('编辑系列课 - 智慧教育');
				var data = d.data;
				vm.id = data.id;
				vm.poster = data.headimg;
				vm.name = data.title;
				vm.count = data.count;
				vm.tickets = data.ticketsMoney;
				vm.introduction = data.introduction ? data.introduction : '';
				vm.chargeTypeCode = data.chargeType.code;
				vm.categoryId = data.category ? data.category.id : '';
				vm.categoryName = data.category ? data.category.name : '';
				vm.posterUploadBg = { 'background-image': 'url(' + data.headimg + ')'};
			});
		}
		var category = { liveStudio : { id : sessionStorage.liveStudioId }, pageSize: 100 };
		var params = $.customParam(category);
		getList('/seriescoursecategory/', params, function(d) {
			var data = d.data;
			if(data.datas) {
				for (var i = 0; i < data.datas.length; i++) {
					var c = data.datas[i];
					vm.categorys.push({ id: c.id, name: c.name });
				}
			}
		}, true);
	},
	methods: {
		uploadPoster: function() {
			showMsg("正在上传图片");
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			$.ajax({
			    url: config.api_server_url + '/upload/course/bg',
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false,
			    crossDomain : true,
			    headers : {
		            "Token-Header" : config.loginUserInfo.token,
		            "X-Requested-With" : "XMLHttpRequest",
		        },
			}).done(function(res) {
				console.log('res=' + res);
				if(res && res.code == 1 && res.url) {
					showOkMsg("图片上传成功");
					bg = res.url.replace(/\\/gi, '/');
					vm.poster = bg;
					vm.posterUploadBg = { 'background-image': 'url(' + bg + ')' };
				} else {
					showError('图片上传失败，请稍后再试');
				}
			}).fail(function(res) {
				showError('图片上传失败，请稍后再试');
				console.log('fail:' + res);
			});
		},
		editIntro: function() {
			$.prompt({
				title: '请输入系列课简介',
				text: '',
				input: vm.introduction,
				empty: false, // 是否允许为空
				onOK: function (input) {
					vm.introduction = input;
				},
				onCancel: function () {
				}
			});
			
			$("#weui-prompt-input").remove(); //去除原始输入框 
			$(".weui-dialog__bd").append('<textarea rows="8" style="height:70px;width:100%;" class="weui_input weui-prompt-input" id="weui-prompt-input" v-model="' + vm.introduction + '">' + vm.introduction + '</textarea>'); 
		},
		selectCategory: function(item) {
			vm.categoryId = item.id;
			vm.categoryName = item.name;
		},
		showSelectCategory: function() {
			$("#categorys").popup();
		},
		confirmCategory: function() {
			$.closePopup();
		},
		cancel: function() {
			vm.categoryId = '';
			vm.categoryName = '未设置';
			$.closePopup();
		},
		confirm: function() {
			if(!vm.name) {
				showMsg('请输入系列课名称');
				return;
			}
			if(!vm.count) {
				showMsg('请输入开课的节数');
				return;
			}
			
			var seriesCourse = { id: vm.id, title: vm.name, count: vm.count, headimg: vm.poster, introduction: vm.introduction };
			seriesCourse.headimg = vm.poster ? vm.poster : config.ui_server_url + '/images/course-bg-4.jpg';
			seriesCourse.category = { id: vm.categoryId };
			seriesCourse.chargeType = { code: 1 };
			seriesCourse.ticketsMoney = vm.tickets ? vm.tickets : 0;
			seriesCourse.liveStudio = { id: sessionStorage.liveStudioId };
			
			var params = $.customParam(seriesCourse);
			
			if(!vm.id) {
				post('/seriescourse/', params, function(d) {
					showOkMsg('操作成功', function() {
						window.location.href = config.ui_server_url + '/course/coursemanage.html';
					});
				});
			} else {
				put('/seriescourse/', params, function(d) {
					showOkMsg('操作成功', function() {
						window.location.href = config.ui_server_url + '/course/coursemanage.html';
					});
				});
			}
		}
	}
});