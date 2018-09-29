package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by lxl on 2018-9-27.
 */
@Data
@Table(name = "club_book_court_record")
public class BookCourtRecord {

    @Id
    private Integer id;

    @Column(name = "club_id")
    private Integer clubId;

    @Column(name = "book_person_name")
    private String bookPersonName;

    @Column(name = "book_person_phone")
    private String bookPersonPhone;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "state")
    private Integer state;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "book_date")
    private Date bookDate;//预定日期
}
