package com.carerobot.cockpit.common.enums;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public enum AccidentInfluenceEnum {
    //仅需报备、未遂、仅需记录、急救、医疗处理、工作受限、损失工作日、死亡
    Influence1("仅需报备", 1),
    Influence2("未遂", 2),
    Influence3("仅需记录", 3),
    Influence4("急救", 4),
    Influence5("医疗处理", 5),
    Influence6("工作受限", 6),
    Influence7("财产损失", 7),
    Influence8("损失工作日", 8),
    Influence9("死亡", 9);


    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private AccidentInfluenceEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (AccidentInfluenceEnum c : AccidentInfluenceEnum.values()) {
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
