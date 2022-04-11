package com.carerobot.cockpit.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FeedbackDto {

    @ApiModelProperty(value = "接收消息用户openid")
    private String touser;

//    @ApiModelProperty(value = "消息类型")
//    private String  msgtype;
//
//    @ApiModelProperty(value = "消息内容")
//    private Map<String, Object> text;

    @ApiModelProperty(value = "消息模板")
    private String  template_id;

//    @ApiModelProperty(value = "点击模板卡片后的跳转页面")
//    private String  page;

    @ApiModelProperty(value = "模板内容")
    private Map<String, Object> data;

    @ApiModelProperty(value = "模板内容")
    private Map<String, Object> miniprogram;
}
