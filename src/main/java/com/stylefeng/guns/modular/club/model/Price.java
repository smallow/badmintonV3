package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Time;

/**
 * Created by lxl on 2018-9-18.
 */
@Data
@Table(name = "club_price")
public class Price {
    @Id
    private Integer id;

    @Column(name = "time")
    private Time time;

    @Column(name = "level_one_price")
    private BigDecimal levelOnePrice;

    @Column(name = "level_two_price")
    private BigDecimal levelTwoPrice;

    @Column(name = "level_three_price")
    private BigDecimal levelThreePrice;
}


