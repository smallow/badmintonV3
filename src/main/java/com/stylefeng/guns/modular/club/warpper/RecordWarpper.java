package com.stylefeng.guns.modular.club.warpper;

import com.stylefeng.guns.common.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.EnumUtil;
import com.stylefeng.guns.modular.club.enums.CostModeEnum;
import com.stylefeng.guns.modular.club.enums.RecordPayModeEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by lxl on 2018-9-20.
 */
public class RecordWarpper extends BaseControllerWarpper {
    public RecordWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("pay_mode", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("pay_mode"))), RecordPayModeEnum.class).getName());
        map.put("cost_mode", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("cost_mode"))), CostModeEnum.class).getName());
    }
}
