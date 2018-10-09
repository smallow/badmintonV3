/**
 * 订场记录管理初始化
 */
var Record = {
    id: "RecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Record.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '俱乐部名称', field: 'groupName', align: 'center', valign: 'middle', sortable: true},
        {title: '预定者名称', field: 'book_person_name', align: 'center', valign: 'middle', sortable: true},
        {title: '预定者手机号码', field: 'book_person_phone', align: 'center', valign: 'middle', sortable: true},
        {title: '预定日期', field: 'book_date', align: 'center', valign: 'middle', sortable: true},
        {title: '预定方式', field: 'book_mode', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'state', align: 'center', valign: 'middle', sortable: true},
        {title: '消费金额', field: 'cost', align: 'center', valign: 'middle', sortable: true},
        {title: '支付方式', field: 'pay_mode', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'create_time', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Record.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Record.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订场记录
 */
Record.openAddRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加订场记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/record/record_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订场记录详情
 */
Record.openRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订场记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/record/record_update/' + Record.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订场记录
 */
Record.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/record/delete", function (data) {
            Feng.success("删除成功!");
            Record.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("recordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询订场记录列表
 */
Record.search = function () {
    var queryData = {};
    queryData['groupName'] = $("#groupName").val();
    queryData['startTime'] = $("#startTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['state'] = $("#state").val();
    Record.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Record.initColumn();
    var table = new BSTable(Record.id, "/record/list", defaultColunms);
    table.setPaginationType("client");
    Record.table = table.init();
});
