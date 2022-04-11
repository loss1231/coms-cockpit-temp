package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="RoomInfo对象", description="")
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("RoomNo")
    private String RoomNo;

    @TableField("HouseNo")
    private String HouseNo;

    @TableField("OrientationId")
    private Integer OrientationId;

    @TableField("HouseLayoutId")
    private Integer HouseLayoutId;

    @TableField("BedCount")
    private Integer BedCount;

    @TableField("Square")
    private BigDecimal Square;

    @TableField("FloorInfoId")
    private Integer FloorInfoId;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

    @TableField("Money")
    private BigDecimal Money;

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

    @TableField("ArchivesId")
    private Integer ArchivesId;

    @TableField("CustomerId")
    private Integer CustomerId;

    @TableField("CusumerCount")
    private Integer CusumerCount;

    @TableField("NurseArea")
    private Integer NurseArea;

    @TableField("MembershipArchivesId")
    private Integer MembershipArchivesId;

    @TableField("BuildNo")
    private String BuildNo;


}
