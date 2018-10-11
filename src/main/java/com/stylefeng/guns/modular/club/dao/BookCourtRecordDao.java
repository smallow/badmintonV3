package com.stylefeng.guns.modular.club.dao;

import com.stylefeng.guns.modular.club.model.BookCourtRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 消费记录Dao
 *
 * @author fengshuonan
 * @Date 2018-09-18 09:40:50
 */
public interface BookCourtRecordDao extends Mapper<BookCourtRecord> {

    List<Map<String, Object>> list(Map<String,Object> param);

    List<Map<String, Object>> bookList(Map<String,Object> param);

    void addBookCourtRecord(BookCourtRecord bookRecord);

    void addBookCourtRecordDetails(Map<String,Object> param);
}
