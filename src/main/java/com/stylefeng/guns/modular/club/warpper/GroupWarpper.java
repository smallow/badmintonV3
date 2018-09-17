package com.stylefeng.guns.modular.club.warpper;

import com.stylefeng.guns.common.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.EnumUtil;
import com.stylefeng.guns.modular.club.enums.GroupStateEnum;

import java.util.List;
import java.util.Map;

public class GroupWarpper extends BaseControllerWarpper {

    public GroupWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("state", EnumUtil.getByCode(Integer.parseInt(String.valueOf(map.get("state"))), GroupStateEnum.class).getMessage());
    }
}
