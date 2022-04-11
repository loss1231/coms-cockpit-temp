package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.carerobot.cockpit.common.enums.AccidentInfluenceEnum;
import com.carerobot.cockpit.common.enums.AccidentResonType;
import com.carerobot.cockpit.common.enums.HurtTypeEnum;
import com.carerobot.cockpit.dto.AccidentAnalysisDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author wd
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccidentReport对象", description="")
public class AccidentReport implements Serializable {

    @TableId(value = "Id")
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

    @ApiModelProperty(value = "员工id")
    @TableField("EmployeeId")
    private Long EmployeeId;

    @ApiModelProperty(value = "发生日期")
    @TableField("HappenTime")
    private Date HappenTime;

    @ApiModelProperty(value = "发生时间")
    @TableField("HappenData")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date HappenData;

    @ApiModelProperty(value = "报告者记录者")
    @TableField("Reporter")
    private String Reporter;

    @ApiModelProperty(value = "记录时间")
    @TableField("CreationTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
    private Boolean IsDeleted;

    @ApiModelProperty(value = "创建人")
    @TableField("CreatorUserId")
    private Long CreatorUserId;

    @ApiModelProperty(value = "更新时间")
    @TableField("UpdateDate")
    private Date UpdateDate;

    @ApiModelProperty(value = "事故状态（未使用）")
    @TableField("AccidentStatus")
    private Integer AccidentStatus;

    @TableField("OrgId")
    private Integer OrgId;

    @ApiModelProperty(value = "事故级别")
    @TableField("AccidentNature")
    private Integer AccidentNature;

    @ApiModelProperty(value = "事故影响")
    @TableField("AccidentInfluence")
    private Integer AccidentInfluence;

    @ApiModelProperty(value = "事故影响 具体描述")
    @TableField("AccidentInfluenceName")
    private String AccidentInfluenceName;

    @ApiModelProperty(value = "事故类别（原因）1")
    @TableField("AccidentType")
    private Integer AccidentType;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CreatorUserName")
    private String CreatorUserName;

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

    @TableField(exist = false)
    private AccidentAnalysisDto analysis;

    @TableField(exist = false)
    private List<AccidentAnalysisDto> analysiss;

    @TableField(exist = false)
    private List<AccidentRectify> rectifies;

    @TableField(exist = false)
    private String hurtTypeName;

    @TableField(exist = false)
    private String AccidentNatureName;

    @TableField(exist = false)
    private String support;

    @TableField(exist = false)
    private String AccidentTypeName;

    @TableField(exist = false)
    private String AccidentStatusName;

    @TableField(exist = false)
    private List<String> images;

    public String getAccidentStatusName() {
        if (this.getAccidentStatus()!=null)return com.carerobot.cockpit.common.enums.AccidentStatus.AccidentStatusEnum.getName(AccidentStatus);
        return "";
    }

    public String getAccidentInfluenceName() {
        if (this.getUseType()!=null && this.getUseType()==2){
            if (this.getAccidentType()!=null&&this.getAccidentType()==com.carerobot.cockpit.common.enums.AccidentType.AccidentTypeEnum.TYPE1001.getIndex()){
                if (this.getAccidentInfluence()!=null) return com.carerobot.cockpit.common.enums.AccidentType.AccidentTypeEnum.SecurityEnum.getName(this.getAccidentInfluence());
            }else if (this.getAccidentType()!=null&&this.getAccidentType()==com.carerobot.cockpit.common.enums.AccidentType.AccidentTypeEnum.TYPE1002.getIndex()){
                if (this.getAccidentInfluence()!=null) return com.carerobot.cockpit.common.enums.AccidentType.AccidentTypeEnum.HealthEnum.getName(this.getAccidentInfluence());
            }
        }else{
            if (this.getAccidentInfluence()!=null) return AccidentInfluenceEnum.getName(this.getAccidentInfluence());
        }
        return AccidentInfluenceName;
    }

    public String getAccidentTypeName() {
        if (this.getUseType()!=null && this.getUseType()==2){
            if (this.getAccidentType()!=null) return com.carerobot.cockpit.common.enums.AccidentType.AccidentTypeEnum.getName(this.getAccidentType());
        }else{
            if (this.getAccidentType()!=null) return AccidentResonType.getName(this.getAccidentType());
        }
        return "";
    }

    public String getSupport() {
        if (this.getIsAmbulance()!=null&&this.getIsAmbulance()==1){
            return "需要救护车";
        }else if(this.getIsCallThePolice()!=null&&this.getIsCallThePolice()==1){
            return "需要报警";
        }else if(this.getIsTreatment()!=null&&this.getIsTreatment()==1){
            return "需要救护治疗";
        }
        return "";
    }

    public String getAccidentNatureName() {
        if (this.getAccidentNature()!=null){
            return com.carerobot.cockpit.common.enums.AccidentNature.AccidentNatureEnum.getName(this.getAccidentNature());
        }
        return "";
    }

    public String getHurtTypeName() {
        if (this.getHurtType()!=null){
            return HurtTypeEnum.getName(this.getHurtType());
        }
        return "";
    }
}