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
public class OccupancyRateDto {

    private String itemName;

    private String longLive = "0";

    private String shortLive = "0";

    private String tryLive = "0";

    private String guest = "0";

    private String ILTotal = "0";

    private String careLevel1 = "0";

    private String careLevel2 = "0";

    private String careLevel3 = "0";

    private String ALTotal = "0";

    private String total = "0";
}
