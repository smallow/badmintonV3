package com.stylefeng.guns.modular.club.dao;

import com.stylefeng.guns.modular.club.model.Group;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 俱乐部管理Dao
 *
 * @author fengshuonan
 * @Date 2018-09-16 21:45:55
 */
public interface GroupDao extends Mapper<Group> {

    List<Map<String, Object>> list(Map<String,Object> param);
}
