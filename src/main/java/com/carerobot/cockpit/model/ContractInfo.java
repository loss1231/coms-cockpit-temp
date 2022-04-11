package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ContractInfo对象", description="")
public class ContractInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id")
    private Integer Id;

    @TableField("ArchivesId")
    private Integer ArchivesId;

    @TableField("BedInfoId")
    private Integer BedInfoId;

    @TableField("NurseLevelId")
    private Integer NurseLevelId;

    @TableField("NurseLevelName")
    private String NurseLevelName;

    @TableField("Name")
    private String Name;

    @TableField("Number")
    private String Number;

    @TableField("StartDate")
    private Date StartDate;

    @TableField("EndDate")
    private Date EndDate;

    @TableField("Mnemonics")
    private String Mnemonics;

    @TableField("IsTaste")
    private Integer IsTaste;

    @TableField("Days")
    private Integer Days;

    @TableField("IsSettle")
    private Integer IsSettle;

    @TableField("CreatedUserId")
    private Integer CreatedUserId;

    @TableField("CreatedUserName")
    private String CreatedUserName;

    @TableField("SigningDate")
    private Date SigningDate;

    @TableField("SigningName")
    private String SigningName;

    @TableField("SigningPhone")
    private String SigningPhone;

    @TableField("Guarantor")
    private String Guarantor;

    @TableField("GuarantorPhone")
    private String GuarantorPhone;

    @TableField("Remark")
    private String Remark;

    @TableField("ParentContractId")
    private Integer ParentContractId;

    @TableField("ContractStatus")
    private Integer ContractStatus;

    @TableField("IsPreferential")
    private Integer IsPreferential;

    @TableField("PreferentialStr")
    private String PreferentialStr;

    @TableField("MainContractId")
    private Integer MainContractId;

    @TableField("ContractCategory")
    private Integer ContractCategory;

    @TableField("HasRenew")
    private Integer HasRenew;

    @TableField("ExpectLiveTime")
    private Date ExpectLiveTime;

    @TableField("PackageBedType")
    private Integer PackageBedType;

    @TableField("PackageBedPrice")
    private BigDecimal PackageBedPrice;

    @TableField("Discount")
    private Double Discount;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("CreatorUserName")
    private String CreatorUserName;

    @TableField("CreationTime")
    private Date CreationTime;

    @TableField("CreatorUserId")
    private Long CreatorUserId;

    @TableField("LastModifierUserName")
    private String LastModifierUserName;

    @TableField("LastModificationTime")
    private Date LastModificationTime;

    @TableField("LastModifierUserId")
    private Long LastModifierUserId;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

    @TableField("ContractLiveId")
    private Integer ContractLiveId;

    @TableField("TType")
    private Integer TType;

    @TableField("IsLongOrShort")
    private Integer IsLongOrShort;

    @TableField("ArchivesInfoName")
    private String ArchivesInfoName;

    @TableField("NurseArea")
    private Integer NurseArea;

    @TableField("BedInfoBedNo")
    private String BedInfoBedNo;

    @TableField("BedInfoRoomNo")
    private String BedInfoRoomNo;

    @TableField("CredentialsId")
    private Integer CredentialsId;

    @TableField("CredentialsNo")
    private String CredentialsNo;

    @TableField("Gender")
    private Integer Gender;

    @TableField("SalesUserName")
    private String SalesUserName;

    @TableField("SaleHB")
    private String SaleHB;

    @TableField("NursingPrecautions")
    private String NursingPrecautions;

    @TableField("ChangeReason")
    private Integer ChangeReason;

    @ApiModelProperty(value = "担保人联系地址")
    @TableField("GuarantorAddress")
    private String GuarantorAddress;

    @ApiModelProperty(value = "传真")
    @TableField("GuarantorFax")
    private String GuarantorFax;

    @ApiModelProperty(value = "电子邮件")
    @TableField("GuarantorEmail")
    private String GuarantorEmail;

    @ApiModelProperty(value = "担保人的收件人")
    @TableField("GuarantorAddressee")
    private String GuarantorAddressee;

    @ApiModelProperty(value = "代理人身份证号")
    @TableField("AgentCredentialsNo")
    private String AgentCredentialsNo;

    @ApiModelProperty(value = "代理人与户主的关系")
    @TableField("SigningRelation")
    private String SigningRelation;

    @ApiModelProperty(value = "继受人")
    @TableField("Successor")
    private String Successor;

    @ApiModelProperty(value = "继受人身份证号")
    @TableField("SuccessorCredentialsNo")
    private String SuccessorCredentialsNo;

    @ApiModelProperty(value = "继受人电话")
    @TableField("SuccessorPhone")
    private String SuccessorPhone;

    @ApiModelProperty(value = "继受人关系")
    @TableField("SuccessorRelation")
    private String SuccessorRelation;

    @TableField("BedArea")
    private String BedArea;
}
