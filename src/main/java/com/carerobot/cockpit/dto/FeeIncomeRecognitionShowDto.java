package com.carerobot.cockpit.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeeIncomeRecognitionShowDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "科目编码")
    private String itemCode;

    @ApiModelProperty(value = "科目名称")
    private String itemName;

    @ApiModelProperty(value = "层级")
    private Integer itemLevel;

    @ApiModelProperty(value = "对应查询code")
    private String queryCode;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "是否其他费用")
    private Boolean isOther;
}
