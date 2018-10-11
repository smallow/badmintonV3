package com.stylefeng.guns.modular.club.service;

import com.stylefeng.guns.modular.club.model.BookCourtRecord;

import java.util.Map;

/**
 * 订场记录Service
 *
 * @author fengshuonan
 * @Date 2018-09-18 09:40:58
 */
public interface IBookCourtRecordService {

    void saveBookInfo(BookCourtRecord bookCourtRecord, Map<String,Object> bookedMap);

}
