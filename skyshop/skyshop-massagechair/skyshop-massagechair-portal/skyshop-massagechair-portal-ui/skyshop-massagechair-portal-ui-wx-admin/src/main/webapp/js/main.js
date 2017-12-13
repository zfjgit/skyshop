$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var overviewInfo = new UserOverviewInfo();
    uku.registerController("overviewInfo", overviewInfo);
    uku.init();
    
    get('/agency/overview/' + config.agency.id, '', function(d) {
        var info = d.data;
        overviewInfo.addressCount = d.data.addrCount;
        overviewInfo.todayIncome = parseFloat(d.data.total).toFixed(2);
        overviewInfo.deviceCount = d.data.chairCount;
        overviewInfo.orderCount = d.data.orderCount;
        overviewInfo.priceCount = d.data.priceCount;
        overviewInfo.agencyCount = d.data.agencyCount;
        overviewInfo.partitionCount = d.data.orderPartitionCount;
        
        uku.refresh('overviewInfo');
    })
});