package com.carerobot.cockpit.dto;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class AllOrgComboboxDto {
    @ApiModelProperty(value = "数据对应的月份")
    private String Date;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "自理入住人数")
    private String ILTotal;

    @ApiModelProperty(value = "自理入住单元数")
    private String ILUnits;

    @ApiModelProperty(value = "自理可入单元数")
    private String ILSRoom;

    @ApiModelProperty(value = "自理可入住率")
    private String ILOccupancy;

    @ApiModelProperty(value = "自理平均可入住率")
    private String ILOccupancyAverage;

    @ApiModelProperty(value = "护理入住人数")
    private String ALTotal;

    @ApiModelProperty(value = "护理入住单元数")
    private String ALUnits;

    @ApiModelProperty(value = "护理可入住单元数")
    private String ALSRoom;

    @ApiModelProperty(value = "护理可入住率")
    private String ALOccupancy;

    @ApiModelProperty(value = "护理平均可入住率")
    private String ALOccupancyAverage;

    @ApiModelProperty(value = "入住率")
    private String TotalOccupancy;

    @ApiModelProperty(value = "合计单元数")
    private String TotalUnits;

    @ApiModelProperty(value = "住户人数")
    private String TotalResidents;

    @ApiModelProperty(value = "自理区服务费收入")
    private BigDecimal incomIl;

    @ApiModelProperty(value = "护理区服务费收入")
    private BigDecimal incomAl;

    @ApiModelProperty(value = "餐饮收入")
    private Integer incomRestaurant;

    @ApiModelProperty(value = "其他运营收入")
    private BigDecimal incomOther;

    @ApiModelProperty(value = "合计收入")
    private BigDecimal Totalincom;



}
