package com.carerobot.cockpit.dto;

import lombok.Data;

/**
 * @Author
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Data
public class AccidentReportDto {

    private String values;

    private Integer IL;

    private Integer AL;

    private Double total;

}
