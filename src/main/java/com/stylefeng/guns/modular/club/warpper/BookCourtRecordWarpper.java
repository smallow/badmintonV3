package com.stylefeng.guns.modular.club.warpper;

import com.stylefeng.guns.common.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.EnumUtil;
import com.stylefeng.guns.modular.club.enums.BookModeEnum;
import com.stylefeng.guns.modular.club.enums.BookRecordStateEnum;
import com.stylefeng.guns.modular.club.enums.RecordPayModeEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by lxl on 2018-9-20.
 */
public class BookCourtRecordWarpper extends BaseControllerWarpper {
    public BookCourtRecordWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        if(map.get("book_mode")!=null){
            map.put("book_mode", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("book_mode"))), BookModeEnum.class).getName());
        }
        if(map.get("state")!=null){
            map.put("state", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("state"))), BookRecordStateEnum.class).getName());
        }
        if(map.get("pay_mode")!=null){
            map.put("pay_mode", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("pay_mode"))), RecordPayModeEnum.class).getName());
        }
    }
}