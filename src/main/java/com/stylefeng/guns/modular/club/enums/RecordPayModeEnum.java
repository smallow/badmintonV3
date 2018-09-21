package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

/**
 * Created by lxl on 2018-9-20.
 */
@Getter
public enum RecordPayModeEnum implements CodeEnum {

    WEIXIN(0,"微信"),ALIPAY(1,"支付宝"),BANKCARD(2,"银行卡"),CASH(3,"现金");

    private Integer code;
    private String name;

    RecordPayModeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }
}
