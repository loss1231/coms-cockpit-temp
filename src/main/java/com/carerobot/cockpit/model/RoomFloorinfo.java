package com.carerobot.cockpit.model;

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
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoomFloorinfo对象", description="")
public class RoomFloorinfo implements Serializable {

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
}
