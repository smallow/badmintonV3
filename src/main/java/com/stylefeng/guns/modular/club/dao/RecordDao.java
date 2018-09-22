package com.stylefeng.guns.modular.club.dao;

import com.stylefeng.guns.modular.club.model.Record;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 消费记录Dao
 *
 * @author fengshuonan
 * @Date 2018-09-18 09:40:50
 */
public interface RecordDao extends Mapper<Record> {

    List<Map<String, Object>> list(Map<String,Object> param);

    List<Map<String, Object>> bookList(Map<String,Object> param);
}
