package com.carerobot.cockpit.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FeeReportShowDto {

    @ApiModelProperty(value = "入住类型")
    private String IsLongOrShort;

    @ApiModelProperty(value = "区域")
    private String Area;

    @ApiModelProperty(value = "床位号")
    private String BedInfoBedNo;

    @ApiModelProperty(value = "姓名")
    private String Name;

    @ApiModelProperty(value = "证件号码")
    private String CredentialsNo;

    @ApiModelProperty(value = "财务凭证号")
    private String FinancialNumber;

    @ApiModelProperty(value = "年龄")
    private Double Age;

    @ApiModelProperty(value = "性别")
    private String Gender;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "账单日期")
    private Date generateDate;

    @ApiModelProperty(value = "账单编号")
    private String billNumber;

    @ApiModelProperty(value = "账单明细编号")
    private Integer billNo;

    @ApiModelProperty(value = "项目类别")
    private String itemType;

    @ApiModelProperty(value = "杂费分类")
    private String IncidentalType;

    @ApiModelProperty(value = "账单分类")
    private String billType;

    @ApiModelProperty(value = "单价")
    private String singPrice;

    @ApiModelProperty(value = "数量")
    private String quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "发生日期")
    private Date happenDate;

    @ApiModelProperty(value = "记账时间")
    private Date createTime;

    @ApiModelProperty(value = "费用起始日期")
    private Date startDate;

    @ApiModelProperty(value = "费用结束日期")
    private Date endDate;

    @ApiModelProperty(value = "操作员")
    private String createUserName;

    @ApiModelProperty(value = "备注")
    private String dsc;

    @ApiModelProperty(value = "机构名称")
    private String OrgName;
}
