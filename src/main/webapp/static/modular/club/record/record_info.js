/**
 * 初始化订场记录详情对话框
 */
var RecordInfoDlg = {
    recordInfoData : {},
    validateFields: {
        bookPersonName: {
            validators: {
                notEmpty: {
                    message: '预订者名称不能为空'
                }
            }
        },
        bookPersonPhone: {
            validators: {
                notEmpty: {
                    message: '预订者手机号码不能为空'
                }
            }
        },
        cost: {
            validators: {
                notEmpty: {
                    message: '消费金额不能为空'
                }
            }
        },
        bookDate: {
            validators: {
                notEmpty: {
                    message: '预订日期不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RecordInfoDlg.clearData = function() {
    this.recordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecordInfoDlg.set = function(key, val) {
    this.recordInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RecordInfoDlg.close = function() {
    parent.layer.close(window.parent.Record.layerIndex);
}

/**
 * 收集数据
 */
RecordInfoDlg.collectData = function() {
    this.set('id')
        .set('bookPersonName').set('bookPersonPhone').set('bookDate').set('bookMode')
        .set('state').set('cost').set('payMode');
}

/**
 * 验证数据是否为空
 */
RecordInfoDlg.validate = function () {
    $('#recordInfoForm').data("bootstrapValidator").resetForm();
    $('#recordInfoForm').bootstrapValidator('validate');
    return $("#recordInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
RecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/record/add", function(data){
        Feng.success("添加成功!");
        window.parent.Record.table.refresh();
        RecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.recordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/record/update", function(data){
        Feng.success("修改成功!");
        window.parent.Record.table.refresh();
        RecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.recordInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("recordInfoForm", RecordInfoDlg.validateFields);

    $("#bookMode").val($("#bookModeValue").val());
    $("#state").val($("#stateValue").val());
    $("#payMode").val($("#payModeValue").val());
});
