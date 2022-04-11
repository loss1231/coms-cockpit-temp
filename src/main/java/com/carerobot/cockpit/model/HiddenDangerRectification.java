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
@ApiModel(value="HiddenDangerRectification对象", description="")
public class HiddenDangerRectification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "隐患id")
    private Integer hiddenDangerId;

    @ApiModelProperty(value = "整改对策描述")
    private String improvementCountermeasures;

    @ApiModelProperty(value = "整改说明")
    private String rectificationDescription;

    @ApiModelProperty(value = "责任部门id")
    private Integer responsibleDepartmentId;

    @ApiModelProperty(value = "责任部门")
    private String responsibleDepartment;

    @ApiModelProperty(value = "责任人id")
    private Integer personLiableId;

    @ApiModelProperty(value = "责任人")
    private String personLiable;

    @ApiModelProperty(value = "计划完成日期")
    private Date planFinishDate;

    @ApiModelProperty(value = "实际完成日期")
    private Date actualFinishDate;

    @ApiModelProperty(value = "是否删除状态")
    private Boolean isDeleted;

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
