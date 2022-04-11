package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 缴费记录表
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccountRecord对象", description="缴费记录表")
public class AccountRecord implements Serializable {

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

    @ApiModelProperty(value = "缴费金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "员工编号")
    private Integer staffId;

    @ApiModelProperty(value = "操作时间")
    private Date operationTime;

    @ApiModelProperty(value = "客户编号")
    private Integer accountId;

    @ApiModelProperty(value = "缴费类型：现金、信用卡等")
    private String recordType;

    @ApiModelProperty(value = "对应的账单时间")
    private Date itemTime;

    @ApiModelProperty(value = "批次号，同一批新增的数据有同一批次号")
    private String batchId;

    @ApiModelProperty(value = "入账日期")
    private Date billRecordedTime;

    @ApiModelProperty(value = "实际付款日期")
    private Date actualPayTime;

    @ApiModelProperty(value = "开票信息")
    private String invoiceInfo;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "账单类型：押金、会籍费、定金、月费、护理费、滞纳金、杂费（还可以继续细分：有线电视等）等")
    private String itemType;

    @ApiModelProperty(value = "趸交类型，趸交抵扣专用")
    private String fullpayType;

    @ApiModelProperty(value = "手续费")
    private Double serviceFee;
}
