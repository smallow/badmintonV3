/**
 * 俱乐部管理管理初始化
 */
var Group = {
    id: "GroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Group.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '俱乐部名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '负责人名称', field: 'master_name', align: 'center', valign: 'middle', sortable: true},
        {title: '负责人电话', field: 'master_phone', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'state', align: 'center', valign: 'middle', sortable: true},
        {title: '负责人身份证号', field: 'master_identify_number', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'create_date', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Group.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Group.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加俱乐部管理
 */
Group.openAddGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加俱乐部管理',
        area: ['850px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/group/group_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看俱乐部管理详情
 */
Group.openGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '俱乐部管理详情',
            area: ['850px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/group/group_update/' + Group.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除俱乐部管理
 */
Group.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/group/delete", function (data) {
            Feng.success("删除成功!");
            Group.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("groupId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询俱乐部管理列表
 */
Group.search = function () {
    var queryData = {};
    queryData['masterPhone'] = $("#masterPhone").val();
    queryData['groupName'] = $("#groupName").val();
    Group.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Group.initColumn();
    var table = new BSTable(Group.id, "/group/list", defaultColunms);
    table.setPaginationType("client");
    Group.table = table.init();
});
