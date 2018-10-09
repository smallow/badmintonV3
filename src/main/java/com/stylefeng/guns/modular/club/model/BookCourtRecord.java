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

    @Column(name = "group_id")
    private Integer groupId;//俱乐部id

    @Column(name = "book_person_name")
    private String bookPersonName;//预定者名称

    @Column(name = "book_person_phone")
    private String bookPersonPhone;//预定者手机号码

    @Column(name = "book_date")
    private Date bookDate;//预定日期

    @Column(name = "book_mode")
    private Integer bookMode;//预定方式 0-网上 1-现场 2-次卡

    @Column(name = "state")
    private Integer state;//状态 0-预约 1-已到 2-未到

    @Column(name = "cost")
    private BigDecimal cost;//消费金额

    @Column(name = "pay_mode")
    private Integer payMode;//支付方式 0-微信 1-支付宝 2-银行卡 3-现金

    @Column(name = "create_time")
    private Timestamp createTime;//创建时间
}
