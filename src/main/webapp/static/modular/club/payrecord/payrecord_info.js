/**
 * 初始化充值记录详情对话框
 */
var PayrecordInfoDlg = {
    payrecordInfoData : {}
};

/**
 * 清除数据
 */
PayrecordInfoDlg.clearData = function() {
    this.payrecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayrecordInfoDlg.set = function(key, val) {
    this.payrecordInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayrecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayrecordInfoDlg.close = function() {
    parent.layer.close(window.parent.Payrecord.layerIndex);
}

/**
 * 收集数据
 */
PayrecordInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
PayrecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payrecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.Payrecord.table.refresh();
        PayrecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payrecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayrecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payrecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.Payrecord.table.refresh();
        PayrecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payrecordInfoData);
    ajax.start();
}

$(function() {

});
