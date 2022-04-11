package com.carerobot.cockpit.common.enums;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public enum TableNameEnum {

    Item("item"),DB2("db2"),DB3("db3"),DB4("db4"),DB5("db5"),DB6("db6"),DB7("db7"),DB8("db8");

    private String value;

    TableNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
