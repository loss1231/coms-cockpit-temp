package com.carerobot.cockpit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CareBoxArchiveUpdateDto {

    //户口地址
    private String address;

    //微信头像
    private String avatar;

    //生日
    private Date Birthday;

    //健康管家Code
    private String caremanager_code;

    //健康管家姓名
    private String caremanager_name;

    //国家
    private String country;

    //备注
    private String description;

    //性别（0：女，1：男）
    private Integer gender;

    //身份证号码
    private String id_card;

    //老人姓名
    private String name;

    //身份证类型（ 0：大陆居民身份证 1：护照 2：台湾回乡证 3：其他证件）
    private Integer id_card_type;

    //手机号码
    private String phone;

    //入住状态（ 0, 未入住, 1, 已入住, 2, 退住, 3, 离院请假）
    private Integer resident_status;

    private String contact;

    private String contact_phone;

    private Integer source;

    private Integer focus;
}
