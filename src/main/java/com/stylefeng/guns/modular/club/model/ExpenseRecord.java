package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "expense_record")
public class ExpenseRecord {

    @Id
    private String id;

    /**
     * 场地标识
     */
    @Column(name = "court_id")
    private Integer courtId;

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
     * 是否预定
     */
    @Column(name = "is_booked")
    private Integer isBooked;


    /**
     * 活动开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 活动时间数
     */
    @Column(name = "expense_time_number")
    private Double expenseTimeNumber;

    /**
     * 场地消费金额
     */
    @Column(name = "court_expense")
    private BigDecimal courtExpense;

    /**
     * 场地消费方式  0:会员卡 1:游客 2:次卡
     */
    @Column(name = "court_expense_mode")
    private Integer courtExpenseMode;

    /**
     * 支付方式 0:现金 1:支付宝 2:微信 3:会员卡
     */
    @Column(name = "pay_mode")
    private Integer payMode;

    /**
     * 是否有附加消费 0:没有 1:有
     */
    @Column(name = "additional_expense")
    private Integer additionalExpense;

    /**
     * 总消费
     */
    @Column(name = "total_expense")
    private BigDecimal totalExpense;

    /**
     * 押金
     */
    @Column(name = "cash_pledge")
    private BigDecimal cashPledge;

    /**
     * 优惠金额
     */
    @Column(name = "special_price")
    private BigDecimal specialPrice;

    /**
     * 备注
     */
    @Column(name = "memo")
    private String memo;

    /**
     * 消费状态  0已开单,1已结算,2已退单
     */
    @Column(name = "state")
    private Integer state;
}
