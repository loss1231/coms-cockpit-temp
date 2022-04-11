package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccidentRectify对象", description="")
public class AccidentRectify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "事故报告id")
    private Integer accidentId;

    @ApiModelProperty(value = "整改对策描述")
    private String rectifyDsc;

    @ApiModelProperty(value = "整改说明")
    private String rectifyExplain;

    @ApiModelProperty(value = "责任人")
    private String responsible;

    @ApiModelProperty(value = "责任人id")
    private Integer responsibleId;

    @ApiModelProperty(value = "完成日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date completeDate;

    @ApiModelProperty(value = "计划日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date planDate;

    private Boolean isDeleted;


}
