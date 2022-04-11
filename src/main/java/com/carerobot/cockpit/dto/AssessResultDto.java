package com.carerobot.cockpit.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2021-08-27
 */
@Data
@ApiModel(value="AssessResultDto对象", description="")
public class AssessResultDto{

    @ApiModelProperty(value = "创建人")
    private String createdUser;

    @ApiModelProperty(value = "被评估人code")
    private String code;

    @ApiModelProperty(value = "评估时间")
    private Long assessDate;

    @ApiModelProperty(value = "评估分数")
    private String assessScore;

    @ApiModelProperty(value = "评估结果")
    private String assessResult;

    @ApiModelProperty(value = "评估内容")
    private String assessContent;

}
