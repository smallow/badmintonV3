package com.stylefeng.guns.modular.club.enums;

import com.stylefeng.guns.common.constant.CodeEnum;
import lombok.Getter;

/**
 * Created by lxl on 2018-9-20.
 */
@Getter
public enum RecordStatusEnum implements CodeEnum{

    STATUS_BOOKED(1,"已预定"),STATUS_REACHED(2,"已到达"),STATUS_MISSED(3,"未到达");

    private Integer code;
    private String name;

    RecordStatusEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

}
