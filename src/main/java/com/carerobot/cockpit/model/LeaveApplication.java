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
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LeaveApplication对象", description="")
public class LeaveApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long applicantId;

    @TableField("startDate")
    private Date startDate;

    @TableField("endDate")
    private Date endDate;

    private String applicant;

    @ApiModelProperty(value = "陪同人")
    private String accompany;

    @ApiModelProperty(value = "审批人")
    private String auditor;

    @TableField("leaveTypeId")
    private Integer leaveTypeId;

    @TableField("creatorUserName")
    private String creatorUserName;

    @TableField("createTime")
    private Date createTime;

    @TableField("lastUpdateTime")
    private Date lastUpdateTime;

    @TableField("deletedOrNot")
    private Integer deletedOrNot;

    private Integer status;

    @TableField("nurseName")
    private String nurseName;

    private String explanation;

    private String notes;

    private transient Archivesinfo archivesinfo;
}
