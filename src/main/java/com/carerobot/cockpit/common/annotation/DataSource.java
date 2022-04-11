package com.carerobot.cockpit.common.annotation;

import com.carerobot.cockpit.common.enums.DataSourceEnum;
import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;
}
