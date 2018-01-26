var vm = new Vue({
	el: '#courseadvisory',
	data: {
		courseId: 0,
		courseType: 0,
		advisorys: [ 
		]
	},
	mounted: function() {
		this.courseId = sessionStorage.advisoryCourseId;
		this.courseType = sessionStorage.advisoryCourseType;
		
		var data = { pageSize: 100, course: { id: this.courseId }, courseType: { code: this.courseType } };
		var params = $.customParam(data);
		getList('/usercourseadvisory/', params, function(d) {
			console.log(d);
			var datas = d.data.datas;
			vm.advisorys = [];
			for (var i = 0; i < datas.length; i++) {
				var c = datas[i];
				vm.advisorys.push({ id: c.id, content: c.content, createTime: c.createTime, reply: c.reply, replyTime: c.replyTime, user: c.user });
			}
		});
	},
	methods: {
		reply: function(id) {
			$.prompt({
				title: '请输入回复内容',
				text: '',
				input: '',
				empty: false, // 是否允许为空
				onOK: function (input) {
					console.log(input);
					
					var data = { id: id, reply: input };
					var params = $.customParam(data);
					put('/usercourseadvisory/reply/', params, function(d) {
						console.log(d);
						showOkMsg('操作成功', function() {
							window.location.reload();
						});
					});
				},
				onCancel: function () {
				}
			});
			
			$("#weui-prompt-input").remove(); //去除原始输入框 
			$(".weui-dialog__bd").append('<textarea rows="8" style="height:70px;width:100%;" class="weui_input weui-prompt-input" id="weui-prompt-input" value=""></textarea>'); 
		}
	}
});