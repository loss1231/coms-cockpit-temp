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
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ContractLive对象", description="")
public class ContractLive implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id")
    private Integer Id;

    @TableField("IsGetLive")
    private Integer IsGetLive;

    @TableField("LiveName")
    private String LiveName;

    @TableField("HelpRoom")
    private Integer HelpRoom;

    @TableField("HouseRoomNo")
    private String HouseRoomNo;

    @TableField("HouseRoom")
    private String HouseRoom;

    @TableField("LiveDate")
    private Date LiveDate;

    @TableField("EndDate")
    private Date EndDate;

    @TableField("ResveEndDate")
    private Date ResveEndDate;

    @TableField("StartNurseId")
    private Integer StartNurseId;

    @TableField("Remark")
    private String Remark;

    @TableField("AddDate")
    private Date AddDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("OrgId")
    private Integer OrgId;

    @TableField("ArchivesId")
    private Integer ArchivesId;

    @TableField("ArchiveInfoId")
    private Integer ArchiveInfoId;

    @TableField("DeliveryDate")
    private Date DeliveryDate;

    @TableField("DeadlineDate")
    private Date DeadlineDate;


}
