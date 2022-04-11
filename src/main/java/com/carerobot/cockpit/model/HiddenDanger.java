package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HiddenDanger对象", description="")
public class HiddenDanger implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "项目名称id")
    private Integer projectNameId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "巡检类别")
    private String patrolCategory;

    @ApiModelProperty(value = "风险种类")
    private String riskType;

    @ApiModelProperty(value = "风险等级")
    private String riskLevel;

    @ApiModelProperty(value = "巡检日期")
    private Date patrolDate;

    @ApiModelProperty(value = "隐患发生地点")
    private String occurredSite;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "记录者id")
    private Integer recorderId;

    @ApiModelProperty(value = "记录者")
    private String recorder;

    @ApiModelProperty(value = "隐患描述")
    private String detailedDescription;

    @ApiModelProperty(value = "改善建议")
    private String improvementSuggestion;

    @ApiModelProperty(value = "是否结案")
    private Boolean whetherEnd;

    @ApiModelProperty(value = "是否逾期")
    private Boolean whetherOverdue;

    @ApiModelProperty(value = "待办状态id")
    private Integer stateId;

    @ApiModelProperty(value = "待办状态")
    private String state;

    @ApiModelProperty(value = "是否删除状态")
    private Boolean isDeleted;

    @ApiModelProperty(value = "机构id")
    private Integer orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;


}
