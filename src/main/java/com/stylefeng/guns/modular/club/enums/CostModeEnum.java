package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

/**
 * Created by lxl on 2018-9-20.
 */
@Getter
public enum CostModeEnum implements CodeEnum{

    BOOKED(0,"预约消费"),SPOT(1,"现场消费");

    private Integer code;
    private String name;

    CostModeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

}
