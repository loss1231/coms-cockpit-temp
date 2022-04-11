package com.carerobot.cockpit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class AllOrgComboboxPostParamDto {

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern="yyyy-MM")
    private Date startDate;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern="yyyy-MM")
    private Date endDate;

    @ApiModelProperty("机构id")
    private List<Integer> OrgId;
}
