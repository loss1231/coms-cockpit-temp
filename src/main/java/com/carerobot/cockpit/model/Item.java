package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Item对象", description="")
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Integer createUserId;

    private String createUserName;

    private Integer updateUserId;

    private String updateUserName;

    private String isDeleted;

    @ApiModelProperty(value = "账单类型：押金、会籍费、定金、月费、护理费、滞纳金、杂费（还可以继续细分：有线电视等）等")
    private String itemType;

    @ApiModelProperty(value = "账单应收，合同金额，可能年度、可能季度、可能月度、可能一次性。要经过实时计算，算出总金额：合同金额/期间（年、季度或者月）天数*（结束日期-开始日期）.")
    private BigDecimal receivable;

    @ApiModelProperty(value = "账单开始日期，以开始日期作为所在日期区间，例如：2021-05-06。即为月度：5月，季度：2季度，年度：2021年。")
    private Date startDate;

    @ApiModelProperty(value = "账单结束日期，当变更时，找到当前期间（年度、季度或者月度）的、当前客户的结束日期，并更新此条的结束日期。")
    private Date endDate;

    @ApiModelProperty(value = "账单生成日期")
    private Date generateDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态：未付：实收为0；未结清：实收小于应收；结清：实收等于应收；废弃，账单作废；")
    private String status;

    @ApiModelProperty(value = "账单周期类型：月度、季度、年度、一次性")
    private String itemCycleType;

    @ApiModelProperty(value = "合同编号,如果有，对于会籍费、签约费、月费、护理费和餐费等有合同的，可以存一下。在签订合同的时候，直接生成第一个月账单了。")
    private Integer contractId;

    @ApiModelProperty(value = "客户编号")
    private Integer accountId;

    @ApiModelProperty(value = "批次号，同一批新增的订单将拥有同一批次号。")
    private String batchId;

    @ApiModelProperty(value = "费用生效日期")
    private Date effectiveDate;

    @ApiModelProperty(value = "费用截至日期")
    private Date expireDate;

    @ApiModelProperty(value = "是否固定费用")
    private Boolean isFixed;

    @ApiModelProperty(value = "账单日期")
    private Date statementDate;

    @ApiModelProperty(value = "发生日期")
    private Date happenDate;
}
