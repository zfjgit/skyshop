function ChairManageInfo() {
    this.page = new PageInfo();
    this.chairs = [ new MassageChairInfo() ];
    this.chairName = '';
}

function GSMManageInfo() {
    this.page = new PageInfo();
    this.gsms = [ new GSMModuleInfo() ];
    this.imei = '';
}

function SIMManageInfo() {
    this.page = new PageInfo();
    this.sims = [ new SIMCardInfo() ];
    this.sim = '';
    this.needRecharge = false;
}

function PageInfo() {
    this.hasNextPage = false;
    this.current = 0;
    this.pageSize = 10;
    this.total = 0;
    this.pages = 1;
    this.loading = true;
}

function AddrManageInfo() {
    this.page = new PageInfo();
    this.addrs = [ new InstallAddressInfo() ];
    this.addrName = '';
}

function PriceManageInfo() {
    this.page = new PageInfo();
    this.prices = [ new PriceInfo() ];
    this.priceName = '';
}

function AgencyManageInfo() {
    this.page = new PageInfo();
    this.agencys = [ new AgencyInfo() ];
    this.agencyName = '';
}

function OrderManageInfo() {
    this.page = new PageInfo();
    
    this.orders = [ new OrderInfo() ];
    this.total = 0;
    
    this.startDate = today(-30);
    this.endDate = today();
}

function MassageChairInfo() {
    this.id = 0;
    this.name = '';
    this.brand = '';
    this.gsm = new GSMModuleInfo();
    this.status = new EnumInfo();
    this.installationAddress = new InstallAddressInfo();
    this.deleteStatus = new EnumInfo();
    this.agency = new AgencyInfo();
    this.description = '';
    this.prices = [ new PriceInfo() ];
    this.qrcode = '';
    
    this.gsmPairs = {};
    this.addrPairs = {};
    this.pricePairs = {};
    this.statusPairs = {
        '未投放' : 'A',
        '正常未使用' : 'B',
        '正常使用中' : 'C',
        '故障' : 'D'
    };
    
    this.detailUrl = 'chair_detail.html?';
}
function GSMModuleInfo() {
    this.id = 0;
    this.imei = '';
    this.name = '';
    this.module = '';
    this.simCard = new SIMCardInfo();
    this.status = new EnumInfo();
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.simPairs = {};
    this.statusPairs = {
        '未使用' : 'A',
        '故障' : 'B',
        '使用中' : 'C'
    };
    
    this.detailUrl = 'gsm_detail.html?';
}
function SIMCardInfo() {
    this.id = 0;
    this.sim = '';
    this.name = '';
    this.status = new EnumInfo();
    this.dataFlow = 100;
    this.dueDate = '';
    this.operator = new EnumInfo();
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.isNeedRecharge = function(dueDate) {
        if (dueDate && dueDate.indexOf('-') != -1) {
            var dates = dueDate.split('-');
            if (dates.length == 3) {
                var dueDate = new Date();
                dueDate.setFullYear(parseInt(dates[0]));
                dueDate.setMonth(parseInt(dates[1]) - 1);
                dueDate.setDate(parseInt(dates[2]));
                var dateDiff = dueDate.getTime() - new Date().getTime();
                if (0 < dateDiff && dateDiff < 3 * 24 * 60 * 60 * 1000) {
                    return '及时充值';
                } else if (dateDiff <= 0) {
                    return '需充值';
                } else {
                    return '';
                }
            }
        }
    }
    this.operatorPairs = {
        '电信' : 'A',
        '移动' : 'B',
        '联通' : 'C'
    };
    
    this.simPairs = {};
    this.statusPairs = {
        '未使用' : 'A',
        '故障' : 'B',
        '使用中' : 'C'
    };
    
    this.detailUrl = 'sim_detail.html?';
}

function InstallAddressInfo() {
    this.id = 0;
    this.fullAddress = '';
    this.contact = '';
    this.contactNumber = '';
    this.agency = new AgencyInfo();
    this.province = new AddressInfo();
    this.city = new AddressInfo();
    this.district = new AddressInfo();
    this.detailAddress = '';
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.chairs = [];
    this.chairPairs = {};
    
    this.detailUrl = 'addr_detail.html?';
}

function AddressInfo() {
    this.id = 0;
    this.name = '';
    this.level = 1;
    this.code = 0;
    this.parent = {
        code : 0,
        name : ''
    };
}

function ChairOrderPartitionManageInfo() {
    this.page = new PageInfo();
    
    this.orderPartitions = [ new OrderPartitionInfo() ];
    this.chair = new MassageChairInfo();
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
}

function ChairIncomeManageInfo() {
    this.page = new PageInfo();
    
    this.chairIncomes = [ new ChairIncomeInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
}

function ChairIncomeInfo() {
    this.chair = new MassageChairInfo();
    this.money = 0;
    this.status = '';
    this.detailUrl = 'income_by_chair_detail.html?';
}

function OrderPartitionManageInfo() {
    this.page = new PageInfo();
    
    this.order = new OrderInfo();
    this.orderPartitions = [ new OrderPartitionInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
}

function OrderPartitionInfo() {
    this.agency = {};
    this.order = new OrderInfo();
    this.percentage = 0;
    this.money = 0;
    this.totalMoney = 0;
}

function AddrOrderManageInfo() {
    this.page = new PageInfo();
    
    this.addr = new InstallAddressInfo();
    this.addrOrders = [ new OrderInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
    
    this.detailUrl = 'income_by_addr_order_share.html?';
}

function AddrOrderPartitionManageInfo() {
    this.page = new PageInfo();
    
    this.addr = new InstallAddressInfo();
    this.orderPartitions = [ new OrderPartitionInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
    
    this.detailUrl = 'income_by_addr_order_share.html?';
}

function AddrIncomeManageInfo() {
    this.page = new PageInfo();
    
    this.addrIncomes = [ new AddrIncomeInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
}

function AddrIncomeInfo() {
    this.addr = new InstallAddressInfo();
    this.chairCount = 0;
    this.orderCount = 0;
    this.money = 0;
    this.chairAvgMoney = 0;
    
    this.detailUrl = 'income_by_addr_detail.html?';
}

function OrderInfo() {
    this.code = '';
    this.createTime = '';
    this.minutes = 0;
    this.money = 0;
    this.agency = {
        name : ''
    };
    this.payType = {
        name : ''
    };
    this.chair = {
        name : ''
    };
    this.payStatus = {
        name : ''
    };
    this.installationAddress = {
        name : ''
    };
    this.deleteStatus = {
        name : ''
    };
    
    this.detailUrl = 'order_detail_main.html?';
}

function MalfunctionInfo() {
    this.id = 0;
    this.chair = new MassageChairInfo();
    this.status = new EnumInfo();
    this.type = new EnumInfo();
    this.description = '';
}

function PriceInfo() {
    this.id = 0;
    this.name = '';
    this.money = 0;
    this.minutes = 0;
    this.img = '';
    this.agency = new AgencyInfo();
    
    this.detailUrl = 'price_detail.html?';
}

function LoginUserInfo() {
    this.code = '';
    this.password = '';
    
    this.setCode = function(code) {
        this.code = code;
    }
    this.setPassword = function(pwd) {
        this.password = pwd;
    }
}

function UserOverviewInfo() {
    this.todayIncome = parseFloat(0).toFixed(2);
    this.deviceCount = 0;
    this.addressCount = 0;
    this.orderCount = 0;
    this.priceCount = 0;
    this.agencyCount = 0;
    this.partitionCount = 0;
}

function UserInfo() {
    this.id = 0;
    this.code = '';
    this.name = '';
    this.type = new EnumInfo();
    this.status = new EnumInfo();
    this.agency = new AgencyInfo();
}

function AgencyInfo() {
    this.id = 0;
    this.name = '';
    this.level = new EnumInfo();
    this.p_a_r_e_n_t = {
        id : 0,
        name : '',
        level : new EnumInfo()
    };
    this.parentPercentage = 0;
    this.percentage = 0;
    this.orderIncomePercentage = 0;
    this.deleteStatus = new EnumInfo();
    this.balance = parseFloat(0).toFixed(2);
    
    this.root = {
        id : 0,
        name : '平台/公司'
    };
    
    this.levelPairs = {
        "平台/公司" : 'A',
        "区域总代" : 'C',
        "代理商-E" : 'E',
        "代理商-M" : 'M'
    };
    
    this.parentPairs = {};
    
    this.detailUrl = 'agents_detail.html?';
}

function WithrawRecordManageInfo() {
    this.page = new PageInfo();
    
    this.records = [ new WithrawInfo() ];
    this.total = 0;
    this.startDate = today(-30);
    this.endDate = today();
    this.status = new EnumInfo();
    
    // NEW("A", "待审核"), PROCESSING("B", "处理中"), DONE("C", "提现成功"), FAILED("D", "提现失败");
    this.statusItems = [ {
        code : '',
        name : '全部'
    }, {
        code : 'A',
        name : '待审核'
    }, {
        code : 'B',
        name : '处理中'
    }, {
        code : 'C',
        name : '提现成功'
    }, {
        code : 'D',
        name : '提现失败'
    }, ];
    
    this.statusPairs = {};
}

function WithrawInfo() {
    this.money = '';
    this.agency = new AgencyInfo();
    this.bank = new BankAccountInfo();
    this.status = new EnumInfo();
    
    this.detailUrl = 'withraw_detail.html?';
}

function BankAccountInfo() {
    this.bank = '';
    this.account = '';
    this.accountName = '';
}

function EnumInfo() {
    this.code = '';
    this.name = '';
}