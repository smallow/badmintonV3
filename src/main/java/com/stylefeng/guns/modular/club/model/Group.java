package com.stylefeng.guns.modular.club.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "club_group")
public class Group {
    @Id
    private Integer id;

    private String name;

    /**
     * 俱乐部负责人名称
     */
    @Column(name = "master_name")
    private String masterName;

    /**
     * 俱乐部负责人身份证号
     */
    @Column(name = "master_identify_number")
    private String masterIdentifyNumber;

    /**
     * 管理员昵称
     */
    @Column(name = "admin_nick_name")
    private String adminNickName;

    /**
     * 负责人手机号
     */
    @Column(name = "master_phone")
    private String masterPhone;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    /**
     * 群状态0已开通,1已申请,2已注销
     */
    //@JsonSerialize(using = GroupStateSerializer.class)
    private Integer state;
}
