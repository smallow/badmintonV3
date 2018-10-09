package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

/**
 * 预定方式
 * Created by lxl on 2018-9-20.
 */
@Getter
public enum BookModeEnum implements CodeEnum{

    ONLINE(0,"网上预定"),SPOT(1,"现场预定"),PER(2,"次卡预定");

    private Integer code;
    private String name;

    BookModeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

}
