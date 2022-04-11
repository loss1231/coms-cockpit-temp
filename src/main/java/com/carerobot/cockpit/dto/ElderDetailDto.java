package com.carerobot.cockpit.dto;

import com.carerobot.cockpit.model.VElderRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ElderDetailDto {

    @ApiModelProperty(value = "合计人数")
    public Integer TotalCount;

    @ApiModelProperty(value = "当前年龄合计")
    public Integer TotalCurrAge;

    @ApiModelProperty(value = "入院年龄合计")
    public Integer TotalAge;

    @ApiModelProperty(value = "当前年龄平均")
    public Double AvgCurrAge;

    @ApiModelProperty(value = "入院年龄平均")
    public Double AvgAge;

    public List<VElderRecord> elderDetails;
}
