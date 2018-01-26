$(function(){
	if(!config.initSessionInfo()) {
		return;
	}
	
	initTable();
	initUku();
});

function initTable() {
	$('.table-sort').dataTable({
		searching: false,
		paging: false,
		info: false,
		ordering: false,
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
			{"bSortable":false, "aTargets":[0, 8]}// 不参与排序的列
		]
	});
}

function initUku() {
	var shopManage = new ShopManageInfo();
	
	shopManage.search = function(date) {
		shopManage.page.current = 0;
        searchList();
	}
	
	shopManage.openCheckLayer = function(shop) {
		openCheckLayer(shop);
	};
	shopManage.checkedAll = function() {
		checkedAll();
	};
	shopManage.allCheckSuccess = function() {
		allCheckSuccess();
    };
    shopManage.allCheckFail = function() {
    	allCheckFail();
    };
	
    shopManage.checkStatusList = [ CheckStatus.all, CheckStatus.unchecked, CheckStatus.successed, CheckStatus.failed ];
	
    var uku = new Ukulele();
    uku.registerController("shopManage", shopManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
    });
    
    uku.init();
}

function allCheckFail() {
	if(shopManage.shops) {
		var ids = [];
		for (var i = 0; i < shopManage.shops.length; i++) {
			var p = shopManage.shops[i];
			if(p.isChecked) {
				ids.push(p.id);
			}
		}
		if(ids.length == 0) {
			showCancelMsg('请先勾选店铺');
			return;
		}
		var idx = layer.prompt({
			formType: 2,
			value: '',
			title: '审核不通过，请输入原因：',
		}, function(value, index, elem){
			put('/shop/batchcheck', {code: CheckStatus.FAILED_CODE, ids: ids, remark: value}, function(){
				layer.close(idx);
				showOkMsg('操作成功', function(){
					window.location.reload();
				});
			});
		});
	}
}

function allCheckSuccess() {
	if(shopManage.shops) {
		var ids = [];
		for (var i = 0; i < shopManage.shops.length; i++) {
			var p = shopManage.shops[i];
			if(p.isChecked) {
				ids.push(p.id);
			}
		}
		if(ids.length == 0) {
			showCancelMsg('请先勾选店铺');
			return;
		}
		put('/shop/batchcheck', {code: CheckStatus.SUCCESSED_CODE, ids: ids}, function(){
			showOkMsg('操作成功', function(){
				window.location.reload();
			});
		});
	}
}

function checkedAll() {
	shopManage.isCheckedAll = !shopManage.isCheckedAll;
	if(shopManage.shops) {
		for (var i = 0; i < shopManage.shops.length; i++) {
			shopManage.shops[i].isChecked = shopManage.isCheckedAll;
		}
		config.uku.refresh('shopManage');
	}
}

function searchList() {
	var data = {
        name : shopManage.name,
        startDate: shopManage.startDate,
        endDate: shopManage.endDate,
        page : shopManage.page.current + 1,
        pageSize : shopManage.page.pageSize
    }

	data.checkStatus = {};
	if((shopManage.checkStatus.code + '') != '') {
		data.checkStatus = {code : shopManage.checkStatus.code, name: ''};
	}
	
    var params = $.customParam(data);
    
    getList('/shop/', params, function(d) {
    	shopManage.shops = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
        	var c = datas[i];
        	
            var shop = new ShopInfo();
            shop.id = c.id;
            shop.name = c.name;
            shop.createTime = c.createTime;
            shop.phone = c.phone;
            shop.address = (c.province ? c.province : '') + (c.city ? c.city : '') + (c.district ? c.district : '') + (c.userAddress ? c.userAddress : '');
            shop.industry = c.industry;
            
            shop.status = ShopStatus.off;
            if(c.status == ShopStatus.ON_CODE) {
            	shop.status = ShopStatus.on;
            }
            shop.checkStatus = CheckStatus.unchecked;
            if(c.ischecked == CheckStatus.SUCCESSED_CODE) {
            	shop.checkStatus = CheckStatus.successed;
            } else if(c.ischecked == CheckStatus.FAILED_CODE) {
            	shop.checkStatus = CheckStatus.failed;
            }
            
            shopManage.shops.push(shop);
        }
        
        shopManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        
        config.uku.refresh('shopManage');
        
        if(d.data.current == 1) {
        	initPage(d.data);
        }
    });
}

function initPage(d) {
	laypage({
	    cont: 'div-page'
	    ,skip: true
	    ,curr: d.current
	    ,pages: d.pages
	    ,groups: 5
	    ,first: '首页'
	    ,last: '尾页'
	    ,prev: '上一页'
	    ,next: '下一页'
	    ,jump: function(obj, first){
    	    var curr = obj.curr;
    	    if(!first) {
	    	  	searchList(curr);
    	    }
    	}
	});
}

function openCheckLayer(shop) {
	var index = layer.open({
		type: shopManage.checkPageInfo.type,
		title: shopManage.checkPageInfo.title + ' - ' + shop.name,
		content: shopManage.checkPageInfo.url + shop.id
	});
	layer.full(index);
	
	sessionStorage.shopLayerIndex = index;
}