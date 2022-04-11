package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wd
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ViewFee对象", description="VIEW")
public class ViewFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构id")
    @TableField("OrgId")
    private Integer OrgId;

    @ApiModelProperty(value = "机构名称")
    @TableField("OrgName")
    private String OrgName;

    @ApiModelProperty(value = "账单发生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date happenDate;

    @ApiModelProperty(value = "助记码")
    @TableField("Mnemonics")
    private String Mnemonics;

    private BigDecimal receivable;

    @ApiModelProperty(value = "入住类型")
    @TableField("IsLongOrShort")
    private String IsLongOrShort;

    @ApiModelProperty(value = "区域")
    @TableField("Area")
    private String Area;

    @ApiModelProperty(value = "登记状态")
    @TableField("RegisterStatus")
    private String RegisterStatus;

    @TableField("AreaId")
    private Integer AreaId;

    @ApiModelProperty(value = "床位号")
    @TableField("BedInfoBedNo")
    private String BedInfoBedNo;

    @ApiModelProperty(value = "姓名")
    @TableField("Name")
    private String Name;

    @ApiModelProperty(value = "证件号码")
    @TableField("CredentialsNo")
    private String CredentialsNo;

    @ApiModelProperty(value = "年龄")
    @TableField("Age")
    private Double Age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date statementDate;

    @ApiModelProperty(value = "财务凭证号")
    @TableField("FinancialNumber")
    private String FinancialNumber;

    @ApiModelProperty(value = "性别")
    @TableField("Gender")
    private String Gender;

    private String itemDetailCode;

    @TableField("billDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date billDate;

    @ApiModelProperty(value = "账单明细编号")
    @TableField("billNo")
    private Integer billNo;

    @TableField("detailedNo")
    private String detailedNo;

//    private String itemType;

    @ApiModelProperty(value = "项目类别")
    @TableField("itemType")
    private String itemType;

    @ApiModelProperty(value = "账单分类，一个不知道干嘛用的东东.")
    private String itemBillTypeCode;

    @ApiModelProperty(value = "账单分类")
    @TableField("billType")
    private String billType;

    @ApiModelProperty(value = "杂费分类，另一个不知道做什么用的东西。")
    private String itemMiscTypeCode;

    @ApiModelProperty(value = "账单日期")
    @TableField("generateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date generateDate;

    @ApiModelProperty(value = "记账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "费用起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "费用结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "操作员")
    private String createUserName;

    @ApiModelProperty(value = "备注")
    private String dsc;

    private String batchId;

    @ApiModelProperty(value = "项目名称")
    @TableField("itemName")
    private String itemName;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "单价")
    @TableField("singPrice")
    private String singPrice;

    @ApiModelProperty(value = "数量")
    private String quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "杂费分类")
    @TableField("IncidentalType")
    private String IncidentalType;

    @ApiModelProperty(value = "账单编号")
    private String billNumber;


}
