package com.carerobot.cockpit.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResidentsDto {
    @ApiModelProperty(value = "自理当日人数")
    private Integer ILTotal;

    @ApiModelProperty(value = "护理当日人数")
    private Integer ALTotal;
}
