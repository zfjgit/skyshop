function ProductManageInfo() {
	this.products = [];
	this.page = new PageInfo();
	this.name = '';
	this.startDate = today(-365);
	this.endDate = today();
	this.checkStatus = {code:'', name:''};
	this.checkStatusList = [];
	this.isCheckedAll = false;
	
	this.checkPageInfo = new ProductCheckPageInfo();
}

function ProductCheckPageInfo() {
	this.url = 'product.shtml?';
	this.title = '商品审核';
	this.type = 2; // iframe
}

function ProductEditPageInfo() {
	this.url = 'product.shtml?';
	this.title = '修改商品信息';
	this.type = 2; // iframe
}

function UserInfo() {
    this.account = '';
    this.pwd = '';
    this.verifycode = '';
}

function PageInfo() {
    this.hasNextPage = false;
    this.current = 1;
    this.pageSize = 15;
    this.total = 0;
    this.pages = 1;
    this.loading = true;
}

function EnumInfo() {
    this.code = '';
    this.name = '';
}

function ProductInfo() {
	this.isChecked = false;
	this.id = 0;
	this.name = '';
	this.catagory = {};
	this.shop = {};
	this.createTime = '';
	this.status = {};
	this.checkStatus = {};
	this.sysCatagory = {};
	this.price = 0;
	this.marketPrice = 0;
	this.inventoryCount = 0;
	this.imgs = [];
	this.introducation = '';
	
	this.statusList = [ProductStatus.onsale, ProductStatus.offsale];
	
	this.checkSuccess = function() {};
	this.checkFail = function() {};
	this.checkCancel = function() {};
	
	this.showIntroducation = function(){};
	this.showRemarkInfo = function(){};
	this.showImgMask = function(){};
	
	this.detailUrl = '';
}
function ProductStatus(){}
ProductStatus.ONSALE_CODE = 15;
ProductStatus.OFFSALE_CODE = 25;
ProductStatus.onsale = {code : ProductStatus.ONSALE_CODE, name: '已上架'};
ProductStatus.offsale = {code : ProductStatus.OFFSALE_CODE, name: '已下架'};

function CheckStatus() {}
CheckStatus.SUCCESSED_CODE = 1;
CheckStatus.FAILED_CODE = 2;
CheckStatus.UNCHECKED_CODE = 0;
CheckStatus.successed = {code: CheckStatus.SUCCESSED_CODE, name: '审核通过'};
CheckStatus.failed = {code: CheckStatus.FAILED_CODE, name: '审核未通过'};
CheckStatus.unchecked = {code: CheckStatus.UNCHECKED_CODE, name: '待审核'};
CheckStatus.all = {code: '', name: '全部'};

function ShopManageInfo() {
	this.shops = [];
	this.page = new PageInfo();
	this.name = '';
	this.startDate = today(-365);
	this.endDate = today();
	this.checkStatus = {code:'', name:''};
	this.checkStatusList = [];
	this.isCheckedAll = false;
	
	this.checkPageInfo = new ShopCheckPageInfo();
}

function ShopCheckPageInfo() {
	this.url = 'shop.shtml?';
	this.title = '店铺审核';
	this.type = 2; // iframe
}

function ShopEditPageInfo() {
	this.url = 'shop.shtml?';
	this.title = '修改店铺信息';
	this.type = 2; // iframe
}

function ShopInfo() {
	this.isChecked = false;
	this.id = 0;
	this.name = '';
	this.account = '';
	this.province = new SimpleTypeInfo();
	this.city = new SimpleTypeInfo();
	this.district = new SimpleTypeInfo();
	this.address = '';
	this.detailAddress = '';
	this.email = '';
	this.phone = '';
	this.industry = {};
	this.status = {};
	this.checkStatus = {};
	this.intro = '';
	this.banners = [];
	this.logo = '';
	this.createTime = '';
	
	this.statusList = [ShopStatus.on, ShopStatus.off];
	
	this.showLogoMask = function(){};
	
	this.showIntroducation = function(){};
	this.showRemarkInfo = function(){};
}

function SimpleTypeInfo() {
	this.id = '';
	this.name = '';
}

function ShopStatus(){}
ShopStatus.ON_CODE = 1;
ShopStatus.OFF_CODE = 2;
ShopStatus.on = {code : ShopStatus.ON_CODE, name: '正常'};
ShopStatus.off = {code : ShopStatus.OFF_CODE, name: '禁用'};

function CatagoryInfo() {
	this.id = 0;
	this.name = '';
	this.parent = {};
}

