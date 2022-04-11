package com.carerobot.cockpit.model;

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
@ApiModel(value="ContractCohabitant对象", description="")
public class ContractCohabitant implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "同住人id")
    @TableField("ArchivesId")
    private Integer ArchivesId;

    @ApiModelProperty(value = "同住人姓名")
    @TableField("LiveName")
    private String LiveName;

    @ApiModelProperty(value = "客户姓名")
    @TableField("ArchivesName")
    private String ArchivesName;

    @ApiModelProperty(value = "客户Id")
    @TableField("ArchiveInfoId")
    private Integer ArchiveInfoId;

    @ApiModelProperty(value = "性质")
    @TableField("Nature")
    private Integer Nature;

    @TableField("Gender")
    private Integer Gender;

    @TableField("Age")
    private Integer Age;

    @TableField("CredentialsNo")
    private String CredentialsNo;

    @TableField("RegisterStatusId")
    private Integer RegisterStatusId;

    @TableField("IsDeleted")
    private Integer IsDeleted;

    @TableField("CreatedDate")
    private Date CreatedDate;

    @ApiModelProperty(value = "入院日期")
    @TableField("LiveDate")
    private Date LiveDate;

    @TableField("StartNurseId")
    private Integer StartNurseId;

    @TableField("ContractId")
    private Integer ContractId;

}
