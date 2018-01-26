$(function(){
	if(!config.initSessionInfo()) {
		return;
	}
	
	initTable();
	initUku();
});

function initTable() {
	var table = $('.table-sort').dataTable({
		searching: false,
		paging: false,
		info: false,
		ordering: false,
		//"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		//"aoColumnDefs": [
		//	{"bSortable":false, "aTargets":[0, 8]}// 不参与排序的列
		//]
	});
	$('.table-sort').on('order.dt', function() {
		$('.table-sort').find('tbody').find('tr').remove();
		searchList(productManage.page.current);
		
		//table.order([[1, 'asc']]);
	});
}

function initUku() {
	var productManage = new ProductManageInfo();
	
	productManage.search = function(date) {
        searchList(productManage.page.current);
	}
	
	productManage.openCheckLayer = function(product) {
		openCheckLayer(product);
	};
	productManage.checkedAll = function() {
		checkedAll();
	};
	productManage.allCheckSuccess = function() {
		allCheckSuccess();
    };
    productManage.allCheckFail = function() {
    	allCheckFail();
    };
	
	productManage.checkStatusList = [ CheckStatus.all, CheckStatus.unchecked, CheckStatus.successed, CheckStatus.failed ];
	
    var uku = new Ukulele();
    uku.registerController("productManage", productManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList(1);
    });
    
    uku.init();
}

function allCheckFail() {
	if(productManage.products) {
		var ids = [];
		for (var i = 0; i < productManage.products.length; i++) {
			var p = productManage.products[i];
			if(p.isChecked) {
				ids.push(p.id);
			}
		}
		if(ids.length == 0) {
			showCancelMsg('请先勾选商品');
			return;
		}
		var idx = layer.prompt({
			formType: 2,
			value: '',
			title: '审核不通过，请输入原因：',
		}, function(value, index, elem){
			put('/product/batchcheck', {code: CheckStatus.FAILED_CODE, ids: ids, remark: value}, function(){
				layer.close(idx);
				showOkMsg('操作成功', function(){
					window.location.reload();
				});
			});
		});
	}
}

function allCheckSuccess() {
	if(productManage.products) {
		var ids = [];
		for (var i = 0; i < productManage.products.length; i++) {
			var p = productManage.products[i];
			if(p.isChecked) {
				ids.push(p.id);
			}
		}
		if(ids.length == 0) {
			showCancelMsg('请先勾选商品');
			return;
		}
		put('/product/batchcheck', {code: CheckStatus.SUCCESSED_CODE, ids: ids}, function(){
			showOkMsg('操作成功', function(){
				window.location.reload();
			});
		});
	}
}

function checkedAll() {
	productManage.isCheckedAll = !productManage.isCheckedAll;
	if(productManage.products) {
		for (var i = 0; i < productManage.products.length; i++) {
			productManage.products[i].isChecked = productManage.isCheckedAll;
		}
		config.uku.refresh('productManage');
	}
}

function searchList(pageNo) {
	var data = {
        name : productManage.name,
        startDate: productManage.startDate,
        endDate: productManage.endDate,
        page : pageNo,
        pageSize : productManage.page.pageSize
    }

	data.checkStatus = {};
	if((productManage.checkStatus.code + '') != '') {
		data.checkStatus = {code : productManage.checkStatus.code, name: ''};
	}
	
    var params = $.customParam(data);
    
    getList('/product/', params, function(d) {
    	productManage.products = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
        	var c = datas[i];
        	
            var product = new ProductInfo();
            product.id = c.productIdx;
            product.name = c.productName;
            product.catagory = { id: c.categoryIdx1, name: c.categoryName };
            product.shop = {id: c.shopIdx, name: c.shopName};
            product.createTime = c.makeTime;
            
            product.status = ProductStatus.onsale;
            if(c.productStatus == ProductStatus.OFFSALE_CODE) {
            	product.status = ProductStatus.offsale;
            }
            product.checkStatus = CheckStatus.unchecked;
            if(c.ischecked == CheckStatus.SUCCESSED_CODE) {
            	product.checkStatus = CheckStatus.successed;
            } else if(c.ischecked == CheckStatus.FAILED_CODE) {
            	product.checkStatus = CheckStatus.failed;
            }
            
            productManage.products.push(product);
        }
        
        productManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        
        config.uku.refresh('productManage');
        
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

function openCheckLayer(product) {
	var index = layer.open({
		type: productManage.checkPageInfo.type,
		title: productManage.checkPageInfo.title + ' - ' + product.name,
		content: productManage.checkPageInfo.url + product.id
	});
	layer.full(index);
	
	sessionStorage.productLayerIndex = index;
}