package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 充值记录
 */
@Data
@Table(name = "club_pay_record")
public class PayRecord {

    @Id
    private Integer id;

    @Column(name = "club_id")
    private Integer clubId;

    @Column(name = "pay_time")
    private Timestamp payTime;

    @Column(name = "pay_person_name")
    private String payPersonName;

    @Column(name = "pay_person_phone")
    private String payPersonPhone;

    @Column(name = "pay_way")
    private Integer payWay;


    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "user_id")
    private Integer userId;

}
