package com.stylefeng.guns.modular.club.service.impl;

import com.stylefeng.guns.modular.club.dao.BookCourtRecordDao;
import com.stylefeng.guns.modular.club.model.BookCourtRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.club.service.IBookCourtRecordService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 订场记录Dao
 *
 * @author fengshuonan
 * @Date 2018-09-18 09:41:00
 */
@Service
public class BookCourtRecordServiceImpl implements IBookCourtRecordService {
    @Autowired
    private BookCourtRecordDao bookCourtRecordDao;

    @Transactional
    public void saveBookInfo(BookCourtRecord bcr,Map<String,Object> bookedMap){
        bcr.setCreateTime(new Timestamp(System.currentTimeMillis()));
        bookCourtRecordDao.addBookCourtRecord(bcr);

        Map<String,Object> param=new HashMap<>();
        param.put("bookedInfo",bookedMap);
        param.put("bookCourtRecordId",bcr.getId());
        bookCourtRecordDao.addBookCourtRecordDetails(param);
    }

}
