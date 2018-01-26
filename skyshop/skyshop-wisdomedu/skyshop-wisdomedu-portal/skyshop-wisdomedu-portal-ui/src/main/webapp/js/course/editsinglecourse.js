var vm = new Vue({
	el: '#course',
	data: {
		id: '',
		headimg: '',
		title: '',
		introduction: '',
		tickets: '',
		passwd: '',
		startTime: '',
		liveType: {},
		courseForm: {},
		seriesCourse: {},
		seriesCourses: [],
		materials: [],
		headimgBg: {},
		isShowDetailPage: false,
		isSilenceMode: false,
		material: {},
		materialFile: '',
		materialsImg: '../images/course-bg.jpg',
	},
	mounted: function() {
		var s = window.location.search;
		if(s) {
			this.id = s.substring(1);
			get('/singlecourse/' + this.id, '', function(d){
				var data = d.data;
				vm.headimg = data.headimg;
				vm.title = data.title;
				vm.introduction = data.introduction ? data.introduction : '';
				vm.tickets = data.ticketsMoney ? data.ticketsMoney : 0;
				vm.passwd = data.passwd;
				vm.liveType = data.liveType;
				vm.startTime = data.startTime;
				vm.courseForm = data.courseForm;
				vm.seriesCourse = data.seriesCourse;
				vm.isSilenceMode = data.silenceMode;
				vm.isShowDetailPage = data.showDetailPage;
				vm.headimgBg = { 'background-image': 'url(' + data.headimg + ')'};
			});
			
			var params = $.customParam({ liveStudio: { id: sessionStorage.liveStudioId }});
			getList('/seriescourse/', params, function(d){
				var datas = d.data.datas;
				vm.seriesCourses = datas;
			});
			
			params = $.customParam({ course: { id: this.id }});
			getList('/courseresource/', params, function(d) {
				var data = d.data;
				vm.materials = data.datas;
			});
		}
	},
	methods: {
		selectSeriesCourse: function(item) {
			vm.seriesCourse = item;
		},
		showSelectSeriesCourse: function() {
			$("#seriesCourse").popup();
		},
		confirmSeriesCourse: function() {
			$.closePopup();
		},
		cancel: function() {
			vm.seriesCourse = {};
			$.closePopup();
		},
		setLiveIntroPageOn: function(isChecked) {
			vm.isShowDetailPage = isChecked;
		},
		showMaterialsPop: function() {
			$("#materials").popup();
		},
		editCourseIntroduction: function() {
			$.prompt({
				title: '请输入课程简介',
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
		setSilenceMode: function(mode) {
			vm.isSilenceMode = mode;
		},
		confirm: function(){
			if(!vm.title) {
				showMsg('请输入课程名称');
				return;
			}
			if(vm.liveType.code == 2 && !vm.passwd) {
				showMsg('请输入加密密码');
				return;
			} else if(vm.liveType.code == 3 && !vm.tickets) {
				showMsg('请输入门票金额');
				return;
			}
			
			var course = { id: vm.id, title: vm.title, headimg: vm.headimg, introduction: vm.introduction, startTime: vm.startTime, silenceMode: vm.isSilenceMode, showDetailPage: vm.isShowDetailPage, passwd: vm.passwd, ticketsMoney: vm.tickets ? vm.tickets : '' };
			course.liveType = vm.liveType;
			course.courseForm = vm.courseForm;
			course.seriesCourse = vm.seriesCourse ? { id : vm.seriesCourse.id } : {};
			course.liveStudio = { id: sessionStorage.liveStudioId };
			var params = $.customParam(course);
			put('/singlecourse/', params, function(d){
				showOkMsg('操作成功', function() {
					window.location.href = config.ui_server_url + '/course/coursemanage.html';
				});
			});
		},
		deleteMaterials: function(id, idx) {
			showConfirmMsg('确定要删除这个课程素材吗？', function(){
				del('/courseresource/' + id, '', function(){
					showOkMsg('操作成功', function() {
						vm.materials.splice(idx, 1);
					});
				});
			});
		},
		uploadHeadimg: function() {
			console.log(!$('#file').val());
			if(!$('#file').val()) {
				return;
			}
			
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
					vm.headimg = bg;
					vm.headimgBg = { 'background-image': 'url(' + bg + ')' };
				} else {
					showError('图片上传失败，请稍后再试');
				}
			}).fail(function(res) {
				showError('图片上传失败，请稍后再试');
				console.log('fail:' + res);
			});
		},
		uploadMaterials: function() {
			console.log(vm.materialFile);
			if(!vm.materialFile) {
				return;
			}
			
			showMsg("正在上传教材");
			var formData = new FormData();
			formData.append('file', $('#material')[0].files[0]);
			formData.append('course.id', vm.id);
			$.ajax({
			    url: config.api_server_url + '/courseresource/uploadcreate/',
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
				
				if(res.code == config.success_code) {
					showOkMsg("教材上传成功");
					vm.materials.push(res.data);
				} else {
					showError('教材上传失败，请稍后再试');
				}
			}).fail(function(res) {
				showError('教材上传失败，请稍后再试');
				console.log('fail:' + res);
			});
		},
	}
});