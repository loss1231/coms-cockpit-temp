package com.carerobot.cockpit.common.enums;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public enum AccidentResonDscEnum {

    TypeOne("人的不安全行为", 1),
    TypeTwo("物的不安全状态", 2),
    TypeThree("环境的缺失",3);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private AccidentResonDscEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (AccidentResonDscEnum c : AccidentResonDscEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
