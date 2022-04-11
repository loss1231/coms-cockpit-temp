package com.carerobot.cockpit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ElderPostParamDto {
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startDate;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endDate;

    @ApiModelProperty(value = "入住类型")
    private String types;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty("机构id")
    private List<Integer> orgId;

    @ApiModelProperty("入住类别")
    private String type;

    @ApiModelProperty("姓名或房间号")
    private String nameOrRoomNo;

    @ApiModelProperty("当前页数")
    private int currPage;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty(value = "项目名称-接收")
    private String item_Name;
}
