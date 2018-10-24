package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 预定记录
 */
@Data
@Table(name = "book_record")
public class BookRecord {

    @Id
    private String Id;

    /**
     * 俱乐部ID
     */
    @Column(name = "club_id")
    private Integer clubId;

    /**
     * 联系人姓名
     */
    @Column(name = "contact_person_name")
    private String contactPersonName;

    /**
     * 联系人电话
     */
    @Column(name = "contact_person_phone")
    private String contactPersonPhone;

    /**
     * 预定时间
     */
    @Column(name = "book_time")
    private Date bookTime;

    /**
     * 保留时间
     */
    @Column(name = "reserve_time")
    private Date reserveTime;

    /**
     * 场地标识
     */
    @Column(name = "court_id")
    private Integer courtId;

    /**
     * 预定状态 0:已预定 1:已到场 2:已取消
     */
    @Column(name = "book_state")
    private Integer bookState;

    /**
     * 预定类型 0:会员卡 1:游客 2:次卡
     */
    @Column(name = "book_type")
    private Integer bookType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
