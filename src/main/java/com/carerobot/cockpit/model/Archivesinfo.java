package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
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
@ApiModel(value="Archivesinfo对象", description="")
@TableName("Archives_info")
public class Archivesinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Id")
    private Integer Id;

    @ApiModelProperty(value = "登记状态")
    @TableField("RegisterStatus")
    private String RegisterStatus;

    @TableField("RegisterStatusId")
    private Integer RegisterStatusId;

    @ApiModelProperty(value = "区域名称")
    @TableField("Area")
    private String Area;

    @TableField("BedInfoBedNo")
    private String BedInfoBedNo;

    @TableField("Name")
    private String Name;

    @TableField("Gender")
    private Integer Gender;

    @TableField("Birthday")
    private Date Birthday;

    @ApiModelProperty(value = "助记码")
    @TableField("Mnemonics")
    private String Mnemonics;

    @TableField("HospitalizedNo")
    private String HospitalizedNo;

    @TableField("RHBloodType")
    private String RHBloodType;

    @ApiModelProperty(value = "邮编")
    @TableField("PostalCode")
    private String PostalCode;

    @TableField("CellPhoneNumber")
    private String CellPhoneNumber;

    @TableField("TelephoneNumber")
    private String TelephoneNumber;

    @ApiModelProperty(value = "监护人")
    @TableField("Guardian")
    private String Guardian;

    @ApiModelProperty(value = "监护人关系")
    @TableField("GuardianRelation")
    private String GuardianRelation;

    @ApiModelProperty(value = "爱好")
    @TableField("Hobby")
    private String Hobby;

    @ApiModelProperty(value = "饮食习惯")
    @TableField("EatingHabits")
    private String EatingHabits;

    @ApiModelProperty(value = "疾病注意事项")
    @TableField("Attention")
    private String Attention;

    @TableField("NationStr")
    private String NationStr;

    @ApiModelProperty(value = "婚姻状态")
    @TableField("MaritalStatusStr")
    private String MaritalStatusStr;

    @ApiModelProperty(value = "银行账号")
    @TableField("BankAccount")
    private String BankAccount;

    @ApiModelProperty(value = "银行名称")
    @TableField("BankName")
    private String BankName;

    @TableField("IsTransRegion")
    private String IsTransRegion;

    @TableField("Family")
    private String Family;

    @ApiModelProperty(value = "紧急联系人")
    @TableField("EmergencyPerson")
    private String EmergencyPerson;

    @ApiModelProperty(value = "紧急联系电话")
    @TableField("EmergencyContact")
    private String EmergencyContact;

    @ApiModelProperty(value = "账单寄送方式")
    @TableField("BillDeliveryWay")
    private String BillDeliveryWay;

    @TableField("BillDeliveryAddress")
    private String BillDeliveryAddress;

    @ApiModelProperty(value = "门禁卡号")
    @TableField("AccessCardNo")
    private String AccessCardNo;

    @TableField("RoomEquipment")
    private String RoomEquipment;

    @TableField("PhysicalCondition")
    private String PhysicalCondition;

    @ApiModelProperty(value = "死亡备注")
    @TableField("Residence")
    private String Residence;

    @TableField("OccupationStr")
    private String OccupationStr;

    @ApiModelProperty(value = "教育背景")
    @TableField("EducationalBackground")
    private String EducationalBackground;

    @ApiModelProperty(value = "死亡类别")
    @TableField("DeathType")
    private Integer DeathType;

    @ApiModelProperty(value = "死亡日期")
    @TableField("DeathDate")
    private Date DeathDate;

    @TableField("DeathRemarks")
    private String DeathRemarks;

    @TableField("CardNo")
    private String CardNo;

    @TableField("Age")
    private Integer Age;

    @TableField("PhotoImgUrl")
    private String PhotoImgUrl;

    @TableField("NationId")
    private Integer NationId;

    @TableField("MaritalStatusId")
    private Integer MaritalStatusId;

    @TableField("OccupationId")
    private Integer OccupationId;

    @TableField("CredentialsId")
    private Integer CredentialsId;

    @TableField("CredentialsNo")
    private String CredentialsNo;

    @TableField("NativePlaceProvinceId")
    private Integer NativePlaceProvinceId;

    @TableField("NativePlaceCityId")
    private Integer NativePlaceCityId;

    @TableField("NativePlaceAddress")
    private String NativePlaceAddress;

    @TableField("LiveProvinceId")
    private Integer LiveProvinceId;

    @TableField("LiveCityId")
    private Integer LiveCityId;

    @TableField("LiveAddress")
    private String LiveAddress;

    @TableField("CulturalDegreeId")
    private Integer CulturalDegreeId;

    @TableField("BloodTypeId")
    private Integer BloodTypeId;

    @TableField("CareTypeId")
    private Integer CareTypeId;

    @TableField("HealthStatus")
    private String HealthStatus;

    @TableField("MedicaHistory")
    private String MedicaHistory;

    @TableField("Reason")
    private String Reason;

    @TableField("Pension")
    private BigDecimal Pension;

    @TableField("Estate")
    private String Estate;

    @TableField("OtherProperty")
    private String OtherProperty;

    @TableField("IsLiveAlone")
    private Integer IsLiveAlone;

    @TableField("ChildCount")
    private Integer ChildCount;

    @TableField("ObtainWayId")
    private Integer ObtainWayId;

    @TableField("SalesUserId")
    private Integer SalesUserId;

    @TableField("CustomerSourceId")
    private Integer CustomerSourceId;

    @TableField("CustomerClassId")
    private Integer CustomerClassId;

    @TableField("Remarks")
    private String Remarks;

    @TableField("IsDisable")
    private Integer IsDisable;

    @TableField("LiveStatus")
    private Integer LiveStatus;

    @TableField("BookTime")
    private Date BookTime;

    @TableField("ChangeBedApplyState")
    private Integer ChangeBedApplyState;

    @TableField("ChangeNurseApplyState")
    private Integer ChangeNurseApplyState;

    @TableField("EnterTime")
    private Date EnterTime;

    @TableField("LeftTime")
    private Date LeftTime;

    @TableField("NurseLevelId")
    private Integer NurseLevelId;

    @TableField("NurseLevel")
    private String NurseLevel;

    @TableField("NurseUnit")
    private String NurseUnit;

    @TableField("BedInfoId")
    private Integer BedInfoId;

    @TableField("Focus")
    private String Focus;

    @TableField("Resistant")
    private String Resistant;

    @TableField("ChangeCustomerClassTime")
    private Date ChangeCustomerClassTime;

    @TableField("LastInterflowDate")
    private Date LastInterflowDate;

    @TableField("NextInterflowDate")
    private Date NextInterflowDate;

    @TableField("IsReserve")
    private Integer IsReserve;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

    @TableField("NextEmphasis")
    private String NextEmphasis;

    @TableField("Summary")
    private String Summary;

    @TableField("FirstInterflowDate")
    private Date FirstInterflowDate;

    @TableField("CusSourceDetailId")
    private Integer CusSourceDetailId;

    @TableField("SalesUserName")
    private String SalesUserName;

    @TableField("CreatedUserId")
    private Integer CreatedUserId;

    @TableField("CreatedUserName")
    private String CreatedUserName;

    @TableField("CusProcessStatus")
    private Integer CusProcessStatus;

    @TableField("ServiceChangeStatus")
    private Integer ServiceChangeStatus;

    @TableField("CustomerQuelle")
    private Integer CustomerQuelle;

    @TableField("CustomerDesire")
    private String CustomerDesire;

    @TableField("CustomerUrgent")
    private Integer CustomerUrgent;

    @TableField("CustomerPayment")
    private String CustomerPayment;

    @TableField("CustomerNeed")
    private String CustomerNeed;

    @TableField("RelationshipId")
    private Integer RelationshipId;

    @TableField("LeftReason")
    private String LeftReason;

    @TableField("NoDeal")
    private String NoDeal;

    @TableField("CityName")
    private String CityName;

    @TableField("BedDeposit")
    private BigDecimal BedDeposit;

    @TableField("IsBedDepositUsed")
    private Integer IsBedDepositUsed;

    @TableField("LastLeftOrgId")
    private Integer LastLeftOrgId;

    @TableField("LastLeftOrgName")
    private String LastLeftOrgName;

    @TableField("LastLeftTime")
    private Date LastLeftTime;

    @TableField("CreatorOrgId")
    private Integer CreatorOrgId;

    @TableField("OtherSourceDetail")
    private String OtherSourceDetail;

    @TableField("CreatorOrgName")
    private String CreatorOrgName;

    @TableField("userId")
    private String userId;

    @TableField("labelIds")
    private String labelIds;

    @TableField("stateCode")
    private String stateCode;

    private String title;

    @TableField("companyName")
    private String companyName;

    @TableField("IsMember")
    private Integer IsMember;

    @TableField("Memberify")
    private Integer Memberify;

    @TableField("shareUserIds")
    private String shareUserIds;

    @TableField("shareDeptIds")
    private String shareDeptIds;

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

    @TableField("MedicalCard")
    private String MedicalCard;

    @TableField("NewCreatorUserId")
    private Long NewCreatorUserId;

    @TableField("NewCreatorUserName")
    private String NewCreatorUserName;

    private String test;

    @TableField("TestData")
    private String TestData;

    @TableField("RoomId")
    private Integer RoomId;

    @TableField("BedId")
    private Integer BedId;

    @TableField("NurseTypeName")
    private String NurseTypeName;

    @TableField("CustomerUrgentStr")
    private String CustomerUrgentStr;

    @TableField("MembershipType")
    private Integer MembershipType;

    @TableField("IsSign")
    private Integer IsSign;

    @TableField("IsNeedWheelchair")
    private Integer IsNeedWheelchair;

    @ApiModelProperty(value = "担保人")
    @TableField("Guarantor")
    private String Guarantor;

    @ApiModelProperty(value = "担保人证件号")
    @TableField("GuarantorCredentialsNo")
    private String GuarantorCredentialsNo;

    @ApiModelProperty(value = "担保人联系地址")
    @TableField("GuarantorAddress")
    private String GuarantorAddress;

    @ApiModelProperty(value = "担保人电话")
    @TableField("GuarantorPhone")
    private String GuarantorPhone;

    @ApiModelProperty(value = "传真")
    @TableField("GuarantorFax")
    private String GuarantorFax;

    @ApiModelProperty(value = "电子邮件")
    @TableField("GuarantorEmail")
    private String GuarantorEmail;

    @ApiModelProperty(value = "收件人")
    @TableField("GuarantorAddressee")
    private String GuarantorAddressee;

    @TableField("IsLongOrShort")
    private Integer IsLongOrShort;

    @TableField("AreaId")
    private Integer AreaId;

    @TableField("FinancialNumber")
    private String FinancialNumber;

    @TableField(exist = false)
    private List<LeaveApplication> leaveApplications;

    @TableField(exist = false)
    private List<AccidentReport> accidentReports;

    @TableField("OffsetType")
    private int OffsetType;
}
