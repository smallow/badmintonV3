package com.stylefeng.guns.modular.club.service.impl;

import com.stylefeng.guns.modular.club.dao.RecordDao;
import com.stylefeng.guns.modular.club.model.BookCourtRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.club.service.IRecordService;
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
public class RecordServiceImpl implements IRecordService {
    @Autowired
    private RecordDao recordDao;

    @Transactional
    public void saveBookInfo(BookCourtRecord bcr,Map<String,Object> bookedMap){
        BookCourtRecord bookCourtRecord = new BookCourtRecord();
        bookCourtRecord.setClubId(bcr.getClubId());
        bookCourtRecord.setBookPersonName(bcr.getBookPersonName());
        bookCourtRecord.setBookPersonPhone(bcr.getBookPersonPhone());
        bookCourtRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        bookCourtRecord.setCost(bcr.getCost());
        bookCourtRecord.setState(bcr.getState());
        bookCourtRecord.setBookDate(bcr.getBookDate());
        recordDao.addBookCourtRecord(bookCourtRecord);

        Map<String,Object> param=new HashMap<>();
        param.put("bookedInfo",bookedMap);
        param.put("bookCourtRecordId",bookCourtRecord.getId());
        recordDao.addBookCourtRecordDetails(param);
    }

}
