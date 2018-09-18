/**
 * 充值记录管理初始化
 */
var Payrecord = {
    id: "PayrecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Payrecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '俱乐部名称', field: 'clubId', align: 'center', valign: 'middle', sortable: true},
        {title: '充值时间', field: 'payTime', align: 'center', valign: 'middle', sortable: true},
        {title: '充值人姓名', field: 'payPersonName', align: 'center', valign: 'middle', sortable: true},
        {title: '充值人手机号', field: 'payPersonPhone', align: 'center', valign: 'middle', sortable: true},
        {title: '充值金额', field: 'money', align: 'center', valign: 'middle', sortable: true},
        {title: '充值前金额', field: 'payBeforeMoney', align: 'center', valign: 'middle', sortable: true},
        {title: '充值后金额', field: 'payAfterMoney', align: 'center', valign: 'middle', sortable: true},
        {title: '充值方式', field: 'payWayName', align: 'center', valign: 'middle', sortable: true},
        {title: '操作员', field: 'userName', align: 'center', valign: 'middle', sortable: true}

    ];
};

/**
 * 检查是否选中
 */
Payrecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Payrecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加充值记录
 */
Payrecord.openAddPayrecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加充值记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payrecord/payrecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看充值记录详情
 */
Payrecord.openPayrecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '充值记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payrecord/payrecord_update/' + Payrecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除充值记录
 */
Payrecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payrecord/delete", function (data) {
            Feng.success("删除成功!");
            Payrecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payrecordId", this.seItem.id);
        ajax.start();
    }
};

Payrecord.searchGroupInfo = function () {
    var queryData = {};
    queryData['masterPhone'] = $("#masterPhone").val();
    $.post("/group/findGroupByMasterPhone", queryData, function (data) {
        if (data) {
            $("#groupResultList").html("");
            var divGroup = $("<div class='input-group'><div class='input-group-btn'><button data-toggle='dropdown' class='btn btn-white dropdown-toggle' type='button'>俱乐部</button></div></div>");
            $("#groupResultList").append(divGroup);
            var select = $("<select class='form-control'></select>");
            divGroup.append(select);
            select.append($("<option value='0'>全部</option>"));
            $.each(data, function (index, val) {
                var opt = $("<option value='" + val.id + "'>" + val.name + "</option>");
                select.append(opt);
                select.on("change", function () {
                    Payrecord.search($(this).val());
                });
            });
        }
    }, 'json');
};

/**
 * 查询充值记录列表
 */
Payrecord.search = function (clubId) {
    var queryData = {};
    queryData['clubId'] = clubId;
    Payrecord.table.refresh({query: queryData});

};


$(function () {
    var defaultColunms = Payrecord.initColumn();
    var table = new BSTable(Payrecord.id, "/payrecord/list", defaultColunms);
    table.setPaginationType("client");
    Payrecord.table = table.init();
});
