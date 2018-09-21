package com.stylefeng.guns.modular.club.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PayRecordVO {

    private Integer id;
    private Integer clubId;
    private Integer clubName;
    private Timestamp payTime;
    private String payPersonName;
    private String payPersonPhone;
    private String payWayName;
    private Integer payWay;
    private BigDecimal money;
    private BigDecimal payBeforeMoney;
    private BigDecimal payAfterMoney;
    private Integer userName;
    private Integer userId;
}
