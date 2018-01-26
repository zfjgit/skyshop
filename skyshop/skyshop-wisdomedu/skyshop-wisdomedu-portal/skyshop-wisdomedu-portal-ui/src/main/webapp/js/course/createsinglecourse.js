Vue.component("live-type-icon", {
	props: [ 'icon' ],
	template: '<i></i>',
});

var freeLiveType = Vue.component('free-livetype', {
	props: [ 'title' ],
	template: 	'<div>' +
					'<div class="live-type-item-settings-hint">任何人都可以观看直播</div>' + 
					'<div class="live-type-item-settings-controls">' +
						'<div class="live-type-item-settings-controls-title">' +
							'<div class="weui-cell weui-cell_switch">' +
							    '<div class="weui-cell__bd">介绍页说明</div>' +
							    '<div class="weui-cell__ft">' +
							      	'<input @click="setLiveIntroPageOn($event.target.checked)" class="weui-switch" type="checkbox"/>' +
							    '</div>' +
							 '</div>' +
						'</div>' +
						'<div class="live-type-item-settings-controls-hint">' +
							'开启介绍页可以帮您统计报名人数' +
						'</div>' +
					'</div>' +
				'<div>',
	methods: {
		setLiveIntroPageOn: function(val) {
			console.log('val=' + val);
			
			vm.setLiveIntroPageOn(val);
		}
	}
});

var encryptedLiveType = Vue.component('encrypted-livetype', {
	props: [ '' ],
	template: 	'<div class="live-type-item-settings-controls">' +
					'<div class="live-type-item-settings-controls-pwd">' +
			      		'<div class="live-type-item-settings-controls-input-title">设置一个固定密码：</div>' +
			      		'<div class="live-type-item-settings-controls-input-div">' + 
			      			'<input @change="setLivePwd($event.target.value)" maxlength="10" class="live-type-item-settings-controls-input" type="password" placeholder="请输入加密密码">' + 
			      		'</div>' +
					'</div>' +
				'</div>',
	methods: {
		setLivePwd: function(value) {
			console.log('pwd=' + value);
			vm.setLivePwd(value);
		}
	}
});

var ticketsRequiredLiveType = Vue.component('ticketsrequired-livetype', {
	props: [ '' ],
	data: function() {
		return { tickets: '' };
	},
	template: 	'<div class="live-type-item-settings-controls">' +
					'<div class="live-type-item-settings-controls-pwd">' +
						'<div class="live-type-item-settings-controls-input-title">设置入场票费用：</div>' +
						'<div class="live-type-item-settings-controls-input-div">' + 
							'<input :value="tickets" @change="setLiveTickets($event.target.value)" class="live-type-item-settings-controls-input" maxlength="10" type="number" placeholder="最小金额1元">' + 
						'</div>' +
					'</div>' +
				'</div>',
	methods: {
		setLiveTickets: function(value) {
			if(!/^\d+\.*\d?$/gi.test(value)) {
				showCancelMsg('请输入正确的金额', function() {
					vm.setLiveTickets('');
				});
			} else {
				vm.setLiveTickets(value);
			}
		}
	}
});

var vm = new Vue({
	el: '#course',
	components: {
		'freeLiveType' : freeLiveType,
		'encryptedLiveType' : encryptedLiveType,
		'ticketsRequiredLiveType' : ticketsRequiredLiveType,
	},
	data: {
		title: '',
		startTime: '',
		formTypeCode: 2,
		liveTypeCode: 1,
		livePwd: '',
		liveTickets: '',
		liveIntroPageOn: false,
		headimg: config.ui_server_url + '/images/course-bg-3.jpg',
		formTypes: [
			{ code: 1, name: '音视频录播形式', hint: '适合已有录好的音视频，音视频播放+图文展示' },
			{ code: 2, name: '讲座', hint: '' },
			{ code: 3, name: '幻灯片', hint: '' },
			{ code: 4, name: '音视频互动', hint: '' },
		],
		examples: [
			{ image: config.ui_server_url + '/images/course-formtype-1.png', caption: '音视频录播形式' },
			{ image: config.ui_server_url + '/images/course-formtype-2.png', caption: '讲座' },
			{ image: config.ui_server_url + '/images/course-formtype-3.png', caption: '幻灯片' },
			{ image: config.ui_server_url + '/images/course-formtype-4.png', caption: '音视频互动' },
		],
		liveTypes: [
			{ code: 1, name: '公开直播', icon: '&#xe6e6;', iconclass: 'icon Hui-iconfont Hui-iconfont-bofang',  component: freeLiveType, },
			{ code: 2, name: '加密直播', icon: '&#xe60e;', iconclass: 'icon Hui-iconfont Hui-iconfont-suoding', component: encryptedLiveType, },
			{ code: 3, name: '收费直播', icon: '&#xe6b7;', iconclass: 'icon Hui-iconfont Hui-iconfont-hongbao', component: ticketsRequiredLiveType, },
		],
		mySwiper: ''
	},
	mounted: function() {
		$("#datetime-picker").datetimePicker({
			min: today(),
			onClose: function(e) {
				vm.startTime = e.input.val();
			} 
		});
		
		this.mySwiper = new Swiper('.swiper-container', {
			autoplay: 0,//可选选项，自动滑动
			nextButton: '',
			prevButton: '.btn_prev',
			onSlideChangeEnd: function() {
				$(window).scrollTop(0);
			}
		});
	},
	methods: {
		setCourseForm: function(formTypeCode) {
			console.log('formTypeCode=' + formTypeCode);
			vm.formTypeCode = formTypeCode;
		},
		
		setLiveType: function(liveTypeCode) {
			console.log('liveTypeCode=' + liveTypeCode);
			vm.liveTypeCode = liveTypeCode;
		},
		
		setLivePwd: function(pwd) {
			console.log('vm.livepwd=' + pwd);
			vm.livePwd = pwd;
		},
		
		setLiveTickets: function(tickets) {
			console.log('vm.tickets=' + tickets);
			vm.liveTickets = tickets;
		},
		
		setLiveIntroPageOn: function(val) {
			console.log('val=' + val);
			vm.liveIntroPageOn = val;
		},
		
		showExample: function(index) {
			console.log('showExample=' + index);
			var pb = $.photoBrowser({
				items: vm.examples
			});
			pb.open(index);
		},
		next: function() {
			if(vm.title == '') {
				showMsg('请输入课程主题');
				return;
			}
			if(vm.startTime == '') {
				showMsg('请选择课程开始时间');
				return;
			}
			vm.mySwiper.slideNext();
		},
		confirm: function() {
			if(vm.liveTypeCode == 2 && vm.livePwd == '') {
				showMsg('请设置一个加密密码');
				return;
			}
			if(vm.liveTypeCode == 3 && vm.liveTickets == '') {
				showMsg('请设置入场门票金额');
				return;
			}
			
			var course = { title: vm.title, headimg: vm.headimg, startTime: vm.startTime, showDetailPage: vm.liveIntroPageOn, passwd: vm.livePwd, ticketsMoney: vm.liveTickets };
			course.liveType = { code: vm.liveTypeCode };
			course.courseForm = { code: vm.formTypeCode };
			course.liveStudio = { id: sessionStorage.liveStudioId };
			var params = $.customParam(course);
			post('/singlecourse/', params, function(d){
				showOkMsg('操作成功', function() {
					window.location.href = config.ui_server_url + '/course/coursemanage.html';
				});
			});
		}
	},
});
