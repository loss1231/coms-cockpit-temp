package com.carerobot.cockpit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AccidentReportForEDto {

    @ApiModelProperty(value = "事故标题")
    private String AccidentTitle;

    @ApiModelProperty(value = "项目名称")
    private String ItemName;

    private String RoomNo;

    private String BedNo;

    @ApiModelProperty(value = "客户姓名")
    private String LitigantName;

    @ApiModelProperty(value = "发生时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date HappenData;

    @ApiModelProperty(value = "报告者记录者")
    private String Reporter;

    @ApiModelProperty(value = "记录者电话")
    private String ReporterPhoneNumber;

    @ApiModelProperty(value = "请描述事故现场的目击者")
    private String WitnessName;

    @ApiModelProperty(value = "事故发生地")
    private String Place;

    @ApiModelProperty(value = "事故描述/可按照时间顺序描述")
    private String Description;

    @ApiModelProperty(value = "描述事故原因")
    private String Reason;

    @ApiModelProperty(value = "财产损失")
    private String Loss;

    @ApiModelProperty(value = "人员伤亡")
    private String Casualties;

    private Integer OrgId;

    @ApiModelProperty(value = "具体发生地描述")
    private String PlaceStr;

    private String OrgName;

    @ApiModelProperty(value = "事故报告填写分类")
    private Integer UseType;

    @ApiModelProperty(value = "所属部门名称")
    private String DepartmentName;
}
