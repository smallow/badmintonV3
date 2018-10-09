package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

@Getter
public enum BookRecordStateEnum implements CodeEnum {
    BOOKED(0, "已预定"), USED(1, "已消费"), MISSED(2, "未到达");

    private Integer code;
    private String name;

    BookRecordStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
