/**
 * 初始化俱乐部管理详情对话框
 */
var GroupInfoDlg = {
    groupInfoData: {},
    validateFields: {
        groupName: {
            validators: {
                notEmpty: {
                    message: '俱乐部名称不能为空'
                }
            }
        },
        masterName: {
            validators: {
                notEmpty: {
                    message: '负责人名称不能为空'
                }
            }
        },
        masterPhone: {
            validators: {
                notEmpty: {
                    message: '负责人电话不能为空'
                }
            }
        },
        masterIdentifyNumber: {
            validators: {
                notEmpty: {
                    message: '负责人身份证号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
GroupInfoDlg.clearData = function () {
    this.groupInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GroupInfoDlg.set = function (key, val) {
    this.groupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GroupInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GroupInfoDlg.close = function () {
    parent.layer.close(window.parent.Group.layerIndex);
}

/**
 * 收集数据
 */
GroupInfoDlg.collectData = function () {
    this.set('id').set("name", $("#groupName").val()).set("masterName").set("masterPhone").set("masterIdentifyNumber", $("#masterIdentifyNumber").val());
};

/**
 * 验证数据是否为空
 */
GroupInfoDlg.validate = function () {
    $('#groupInfoForm').data("bootstrapValidator").resetForm();
    $('#groupInfoForm').bootstrapValidator('validate');
    return $("#groupInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
GroupInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/group/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Group.table.refresh();
        GroupInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.groupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GroupInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/group/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Group.table.refresh();
        GroupInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.groupInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("groupInfoForm", GroupInfoDlg.validateFields);
});
