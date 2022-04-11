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
@ApiModel(value="RoomBedinfo对象", description="")
public class RoomBedinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("BedNo")
    private String BedNo;

    @TableField("RoomNo")
    private String RoomNo;

    @TableField("HouseNo")
    private String HouseNo;

    @TableField("BedTypeId")
    private Integer BedTypeId;

    @TableField("Price")
    private BigDecimal Price;

    @TableField("BedStatusId")
    private Integer BedStatusId;

    @TableField("ArchivesId")
    private Integer ArchivesId;

    @TableField("RoomInfoId")
    private Integer RoomInfoId;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

    @TableField("NursingUnitId")
    private Integer NursingUnitId;

    @TableField("BookTime")
    private Date BookTime;

    @TableField("Remark")
    private String Remark;

    @TableField("PrivateRoomCusId")
    private Integer PrivateRoomCusId;

    @TableField("IsEscort")
    private Integer IsEscort;

    @TableField("EscortBelongId")
    private Integer EscortBelongId;

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

    @TableField("CustomerId")
    private Integer CustomerId;

    @TableField("BedCount")
    private Integer BedCount;

    @TableField("CustomerInfoId")
    private Integer CustomerInfoId;

    @TableField("NurseArea")
    private Integer NurseArea;

    @TableField("LiveName")
    private String LiveName;

    @TableField("BuildNo")
    private String BuildNo;

    @TableField("OrientationId")
    private Integer OrientationId;

    @TableField("MembershipArchivesId")
    private Integer MembershipArchivesId;

    @TableField("Deposit")
    private BigDecimal Deposit;

    @TableField("DatePrice")
    private BigDecimal DatePrice;

    @TableField("MonthPrice")
    private BigDecimal MonthPrice;

    @TableField("BedLevel")
    private Integer BedLevel;

    @TableField("ChangeBedRemark")
    private Integer ChangeBedRemark;

    @ApiModelProperty(value = "关联项目")
    @TableField("RelationItems")
    private String RelationItems;

    @ApiModelProperty(value = "朝向")
    @TableField("OrientationStr")
    private String OrientationStr;

    @ApiModelProperty(value = "房型")
    @TableField("HouseLayoutId")
    private Integer HouseLayoutId;

    @TableField("OrderNo")
    private Integer OrderNo;

    @TableField("IsUploaded")
    private Integer IsUploaded;

    @TableField("HouseLayout")
    private String HouseLayout;

    @TableField("BedStatus")
    private String BedStatus;

    @TableField("IsUploadedResult")
    private String IsUploadedResult;

    @TableField("Square")
    private BigDecimal Square;

    @TableField("LivePersonName")
    private String LivePersonName;


}
