package com.stylefeng.guns.modular.club.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 附加消费项目
 */
@Data
@Table(name = "additional_expense_item")
public class AdditionalExpenseItem {
    /**
     *
     */
    @Id
    private String id;

    /**
     * 附加消费类型标识
     */
    @Column(name = "type_id")
    private Integer typeId;


    /**
     * 数量
     */
    @Column(name = "count")
    private Integer count;

    /**
     * 金额
     */
    @Column(name = "money")
    private BigDecimal money;


    /**
     * 关联消费记录ID
     */
    @Column(name = "expense_record_id")
    private String expenseRecordId;
}
