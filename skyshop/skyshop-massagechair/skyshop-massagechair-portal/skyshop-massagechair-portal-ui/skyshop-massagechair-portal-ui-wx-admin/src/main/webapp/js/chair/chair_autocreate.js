$(function() {
    config.initSessionInfo();
    
    $('#btn_ok').on('click', function() {
        var sim = $('#sim').val();
        if (!sim) {
            showCancelMsg('请输入SIM卡号');
            return;
        }
        var sims = sim.split('\n');
        for (var i = 0; i < sims.length; i++) {
            var s = sims[i];
            if (!/\d{11}/gi.test(s)) {
                showCancelMsg('您输入的SIM卡号不是11位数字');
                return;
            }
        }
        sims = sim.replace('\n', ',');
        post('/chair/autocreate/' + sims, '', function(d) {
            showAlert('设备新增成功', function() {
                $('#sim').val('');
            });
        });
    });
});