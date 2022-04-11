package com.carerobot.cockpit.dto;

import lombok.Data;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Data
public class ElderLeaveDto {

    private String reason;

    private String roomNo;

    private String name;

    private Integer residentType;

    private Integer ILDays;

    private Integer ALDays;

    private Double total;

    private Double AvgPerResident;

    private Double AvgResident;

}
