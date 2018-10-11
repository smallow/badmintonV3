var RecordDetail = {
    id: "RecordDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RecordDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '预订场次', field: 'groupName', align: 'center', valign: 'middle', sortable: true},
        {title: '预订时间', field: 'book_person_name', align: 'center', valign: 'middle', sortable: true}
    ];
};


$(function () {
    var defaultColunms = RecordDetail.initColumn();
    var table = new BSTable(RecordDetail.id, "/record/detail/"+$("#recordId").val(), defaultColunms);
    table.setPaginationType("client");
    RecordDetail.table = table.init();
});
