package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2021-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DischargeApplication对象", description="")
public class DischargeApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("archiveId")
    private Integer archiveId;

    @ApiModelProperty(value = "预计出院时间")
    @TableField("leaveTime")
    private Date leaveTime;

    private String note;

    private String reason;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("area")
    private String area;

    @ApiModelProperty(value = "床位号")
    @TableField("bedInfoBedNo")
    private String bedInfoBedNo;

    @ApiModelProperty(value = "身份证号")
    @TableField("credentialsNo")
    private String credentialsNo;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "入住类型")
    @TableField("isLongOrShortDsc")
    private String isLongOrShortDsc;

    @ApiModelProperty(value = "申请时间")
    @TableField("applicationTime")
    private Date applicationTime;


    @TableField("isDeleted")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("lastUpdateTime")
    private Date lastUpdateTime;
}
