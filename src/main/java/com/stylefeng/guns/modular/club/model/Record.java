package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by lxl on 2018-9-18.
 */
@Data
@Table(name = "club_record")
public class Record {
    @Id
    private Integer id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "cost")
    private BigDecimal cost;

    /**
     * 支付方式(0-微信 1-支付宝 2-银行卡 3-现金)
     */
    @Column(name = "pay_mode")
    private Integer payMode;

    /**
     * 消费模式(0-预约消费 1-现场消费)
     */
    @Column(name = "cost_mode")
    private Integer costMode;
}
