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
	var productManage = new ProductManageInfo();
	
	productManage.editPageInfo = new ProductEditPageInfo();
	
	productManage.search = function(date) {
		productManage.page.current = 0;
        searchList();
	}
	
	productManage.openDetailLayer = function(product) {
		openDetailLayer(product);
	};
	productManage.checkedAll = function() {
		checkedAll();
	};
	
	productManage.checkStatusList = [ CheckStatus.all, CheckStatus.unchecked, CheckStatus.successed, CheckStatus.failed ];
	
    var uku = new Ukulele();
    uku.registerController("productManage", productManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
    });
    
    uku.init();
}

function deleteOne(p) {
	// confirm
	showConfirmMsg('确定要删除这个商品吗？', function(){
		del('/product/delete', {code: CheckStatus.SUCCESSED_CODE, id: p.id}, function(){
			showOkMsg('操作成功', function(){
				window.location.reload();
			});
		});
	});
}

function deleteAll() {
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
		// confirm
		showConfirmMsg('确定要删除这些商品吗？', function(){
			del('/product/batchdelete', {code: CheckStatus.SUCCESSED_CODE, ids: ids}, function(){
				showOkMsg('操作成功', function(){
					window.location.reload();
				});
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

function searchList() {
	var data = {
        name : productManage.name,
        startDate: productManage.startDate,
        endDate: productManage.endDate,
        page : productManage.page.current + 1,
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

function openDetailLayer(product) {
	var index = layer.open({
		type: productManage.editPageInfo.type,
		title: productManage.editPageInfo.title + ' - ' + product.name,
		content: productManage.editPageInfo.url + product.id
	});
	layer.full(index);
	
	sessionStorage.productLayerIndex = index;
}