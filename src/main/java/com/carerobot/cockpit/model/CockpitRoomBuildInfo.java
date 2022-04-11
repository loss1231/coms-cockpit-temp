package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wd
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoomBuildinfo对象", description="")
@TableName("room_buildinfo")
public class CockpitRoomBuildInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Name")
    private String Name;

    @TableField("ParentId")
    private Integer ParentId;

    @TableField("OrderNumber")
    private Integer OrderNumber;

    @TableField("Type")
    private Integer Type;

    @TableField("IsFloorNo")
    private Integer IsFloorNo;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

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

    @TableId(value = "Id")
    private Integer Id;

    @TableField("MembershipType")
    private Integer MembershipType;

    @TableField("CareBoxCode")
    private String CareBoxCode;


}
