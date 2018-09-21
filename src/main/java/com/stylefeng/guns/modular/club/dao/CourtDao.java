package com.stylefeng.guns.modular.club.dao;

import com.stylefeng.guns.modular.club.model.Court;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lxl on 2018-9-21.
 */
public interface CourtDao extends Mapper<Court>{
    List<Map<String, Object>> list(Map<String,Object> param);
}
