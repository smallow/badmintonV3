package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

/**
 * 预订方式
 * Created by lxl on 2018-9-20.
 */
@Getter
public enum BookModeEnum implements CodeEnum{

    ONLINE(0,"网上预订"),SPOT(1,"现场预订"),PER(2,"次卡预订");

    private Integer code;
    private String name;

    BookModeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

}
