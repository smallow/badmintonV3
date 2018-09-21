package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lxl on 2018-9-18.
 */
@Data
@Table(name = "club_court")
public class Court {
    @Id
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    /**
     * 是否可用 1-是 2-否
     */
    @Column(name = "available")
    private Integer available;
}
