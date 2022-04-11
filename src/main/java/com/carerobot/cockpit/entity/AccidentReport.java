package com.carerobot.cockpit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2021-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccidentReport对象", description="")
public class AccidentReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "事故标题")
    @TableField("AccidentTitle")
    private String AccidentTitle;

    @ApiModelProperty(value = "项目名称")
    @TableField("ItemName")
    private String ItemName;

    @ApiModelProperty(value = "伤员类别")
    @TableField("HurtType")
    private Integer HurtType;

    @TableField("Litigant")
    private Integer Litigant;

    @TableField("RoomNo")
    private String RoomNo;

    @TableField("BedNo")
    private String BedNo;

    @ApiModelProperty(value = "客户姓名")
    @TableField("LitigantName")
    private String LitigantName;

    @TableField("Gender")
    private Integer Gender;

    @TableField("Age")
    private Integer Age;

    @ApiModelProperty(value = "客户id")
    @TableField("PatientId")
    private Long PatientId;

    @ApiModelProperty(value = "发生日期")
    @TableField("HappenTime")
    private Date HappenTime;

    @ApiModelProperty(value = "发生时间")
    @TableField("HappenData")
    private Date HappenData;

    @ApiModelProperty(value = "员工id")
    @TableField("EmployeeId")
    private Long EmployeeId;

    @ApiModelProperty(value = "报告者记录者")
    @TableField("Reporter")
    private String Reporter;

    @ApiModelProperty(value = "记录时间")
    @TableField("CreationTime")
    private Date CreationTime;

    @ApiModelProperty(value = "记录者电话")
    @TableField("ReporterPhoneNumber")
    private String ReporterPhoneNumber;

    @ApiModelProperty(value = "是否提醒")
    @TableField("IsNotified")
    private Integer IsNotified;

    @TableField("RelationName")
    private String RelationName;

    @TableField("Relationship")
    private Integer Relationship;

    @ApiModelProperty(value = "请描述事故现场的目击者")
    @TableField("WitnessName")
    private String WitnessName;

    @ApiModelProperty(value = "事故发生地")
    @TableField("Place")
    private String Place;

    @ApiModelProperty(value = "发生地id")
    @TableField("PlaceId")
    private Integer PlaceId;

    @ApiModelProperty(value = "事故描述/可按照时间顺序描述")
    @TableField("Description")
    private String Description;

    @ApiModelProperty(value = "描述事故原因")
    @TableField("Reason")
    private String Reason;

    @ApiModelProperty(value = "安全事务办公室措施")
    @TableField("ActionAndExecuter")
    private String ActionAndExecuter;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @ApiModelProperty(value = "创建人id")
    @TableField("CreatorUserId")
    private Long CreatorUserId;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CreatorUserName")
    private String CreatorUserName;

    @ApiModelProperty(value = "更新时间")
    @TableField("UpdateDate")
    private Date UpdateDate;

    @ApiModelProperty(value = "事故状态（未使用）")
    @TableField("AccidentStatus")
    private Integer AccidentStatus;

    @TableField("OrgId")
    private Integer OrgId;

    @ApiModelProperty(value = "事故类别（影响）2")
    @TableField("AccidentInfluence")
    private Integer AccidentInfluence;

    @ApiModelProperty(value = "事故类别（影响）2  具体描述")
    @TableField("AccidentInfluenceName")
    private String AccidentInfluenceName;

    @ApiModelProperty(value = "事故级别")
    @TableField("AccidentNature")
    private Integer AccidentNature;

    @ApiModelProperty(value = "事故类别（原因）1")
    @TableField("AccidentType")
    private Integer AccidentType;

    @ApiModelProperty(value = "UUID唯一标识")
    @TableField("AccidentId")
    private String AccidentId;

    @TableField("UnitId")
    private Integer UnitId;

    @TableField("IsTransfer")
    private Integer IsTransfer;

    @TableField("Schedule")
    private Integer Schedule;

    @ApiModelProperty(value = "修改人姓名")
    @TableField("LastModifierUserName")
    private String LastModifierUserName;

    @ApiModelProperty(value = "修改时间")
    @TableField("LastModificationTime")
    private Date LastModificationTime;

    @ApiModelProperty(value = "修改人id")
    @TableField("LastModifierUserId")
    private Long LastModifierUserId;

    @TableField("CommunityName")
    private String CommunityName;

    @ApiModelProperty(value = "监护人电话")
    @TableField("CellPhoneNumber")
    private String CellPhoneNumber;

    @TableField("IsTreatment")
    private Integer IsTreatment;

    @TableField("IsAmbulance")
    private Integer IsAmbulance;

    @TableField("IsCallThePolice")
    private Integer IsCallThePolice;

    @ApiModelProperty(value = "具体发生地描述")
    @TableField("PlaceStr")
    private String PlaceStr;

    @ApiModelProperty(value = "财产损失")
    @TableField("Loss")
    private String Loss;

    @ApiModelProperty(value = "人员伤亡")
    @TableField("Casualties")
    private String Casualties;

    @TableField("OrgName")
    private String OrgName;

    @ApiModelProperty(value = "事故报告填写分类")
    @TableField("UseType")
    private Integer UseType;

    @ApiModelProperty(value = "所属部门")
    @TableField("DepartmentId")
    private Integer DepartmentId;

    @TableField("DepartmentName")
    private String DepartmentName;


}
