$(function(){
	var uku = new Ukulele();
	var overviewInfo = new UserOverviewInfo();
	uku.registerController("overviewInfo", overviewInfo);
	uku.init();
	
	config.loginUserInfo = JSON.parse(sessionStorage.loginUserInfo);
	if(!config.loginUserInfo) {
		$.toast("请先登录", "forbidden", function() {
			window.location.href = config.ui_server_url + '/index.html';
		});
		return;
	}
	
	$('#income_by_addr').on('click', function(){
        window.location.href = 'income/income_main.html';
    });
	
	var agencyId = 0;
	if(config.loginUserInfo.userInfo['agency']) {
		agencyId = config.loginUserInfo.userInfo['agency'].id;
	}
	get('/agency/overview/' + agencyId, '', function(d){
		if(d.code == config.success_code && d.data) {
			console.log(d);
		} else {
			$.toast('操作遇到问题，请稍后再试', 'forbidden');
		}
	})
});