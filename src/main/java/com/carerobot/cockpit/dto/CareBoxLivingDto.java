package com.carerobot.cockpit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CareBoxLivingDto {
    //项目
    private String project;

    //同居者姓名
    private String cohabitant_name;

    //是否独居(0：否，1：是）
    private Integer living_state;

    //栋
    private String building;

    //层
    private String floor;

    //房间号
    private String number;

    //护理类型（0：护理，1：自理）
    private Integer nursing_type;

    //备注
    private String description;

    //状态
    private Integer occupancy_status;
}
