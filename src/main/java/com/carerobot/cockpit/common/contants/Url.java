package com.carerobot.cockpit.common.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author YY
 * @Description
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class Url {

    public static String url;

    public static String carebox;

    @Value("${system.url}")
    public void setUrl(String url) {
        Url.url = url;
    }

    @Value("${system.carebox}")
    public void setCarebox(String carebox) {
        Url.carebox = carebox;
    }
}
