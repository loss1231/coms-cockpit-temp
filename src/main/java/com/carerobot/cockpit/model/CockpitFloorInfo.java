package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoomBuildinfo对象", description="")
@TableName("room_floorinfo")
public class CockpitFloorInfo {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id")
    private Integer Id;

    @TableField("Name")
    private String Name;

    @TableField("ParentId")
    private Integer ParentId;

    @TableField("OrderNumber")
    private Integer OrderNumber;

    @TableField("Type")
    private Integer Type;

    @TableField("IsRoomNo")
    private Integer IsRoomNo;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

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

    @TableField("HouseNo")
    private String HouseNo;

    @TableField("CareBoxCode")
    private String CareBoxCode;
}
