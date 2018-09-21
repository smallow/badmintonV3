package com.stylefeng.guns.modular.club.dao;

import com.stylefeng.guns.modular.club.model.Price;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lxl on 2018-9-21.
 */
public interface PriceDao extends Mapper<Price>{
    List<Map<String, Object>> list();
}
