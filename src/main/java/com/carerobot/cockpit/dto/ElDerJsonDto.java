package com.carerobot.cockpit.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Data
public class ElDerJsonDto {

    private Integer code;

    private String message;

    private List<ArchivesJsonDto> data;

}
