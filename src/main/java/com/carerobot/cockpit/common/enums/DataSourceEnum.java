package com.carerobot.cockpit.common.enums;

/**
 * @Author:
 * @Date: 2021/6/17 12:13
 * @Description:
 */
public enum DataSourceEnum {

    DB1("db1"),DB2("db2"),DB3("db3"),DB4("db4"),DB5("db5"),DB6("db6"),DB7("db7"),DB8("db8");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
