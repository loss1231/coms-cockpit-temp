package com.carerobot.cockpit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ElderStatisticsDto {

    @ApiModelProperty(value = "项目名称")
    public String ItemName;

    @ApiModelProperty(value = "项目编码,分组Code")
    public String ItemCode;

    @ApiModelProperty(value = "Values展示描述")
    public String ValueDes;

    @ApiModelProperty(value = "Values值")
    public String Value;

    @ApiModelProperty(value = "IL")
    public Double IL;

    @ApiModelProperty(value = "AL")
    public Double AL;

    @ApiModelProperty(value = "所有")
    public Double Total;

    @ApiModelProperty(value = "比例")
    public String Percentage = "100%";

//    public ElderStatisticsDto(Integer id, String ItemName, String Value, Integer IL, Integer AL, Integer Total, Number Percentage) {
//        this.id = id;
//        this.ItemName = ItemName;
//        this.Value = Value;
//        this.IL = IL;
//        this.AL = AL;
//        this.Total = Total;
//        this.Percentage = Percentage;
//    }

    public void setPercentage(Double total){
        if (this.getTotal() != 0) this.Percentage = String.format("%.2f", (this.getTotal()/total * 100)) + "%";
    }

}
