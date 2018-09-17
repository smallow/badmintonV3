package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

@Getter
public enum GroupStateEnum implements CodeEnum {
    NORMAL(0, "已开通"), APPLY(1, "已申请"), DELETED(2, "已注销");

    private Integer code;
    private String message;

    GroupStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
