package com.carerobot.cockpit.common.enums;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public enum AccidentResonType {

    //气象灾害、地震灾害、火灾爆炸、道路交通、环境污染、质量损失/投诉或召回、财产损失
    // 、有毒有害物质意外排放泄漏、机械伤害、电气事故、高处坠落、坠物伤人、食品中毒
    // 、流行病、急性传染病、集体上访、游行示威、自残自焚、静坐抗议、其他
    Reson1("气象灾害", 1),
    Reson2("地震灾害", 2),
    Reson3("火灾爆炸", 3),
    Reson4("道路交通", 4),
    Reson5("环境污染", 5),
    Reson6("质量损失/投诉或召回", 6),
    Reson7("财产损失", 7),
    Reson8("有毒有害物质意外排放泄漏", 8),
    Reson9("机械伤害", 9),
    Reson10("电气事故", 10),
    Reson11("高处坠落", 11),
    Reson12("坠物伤人", 12),
    Reson13("食品中毒", 13),
    Reson14("流行病", 14),
    Reson15("急性传染病", 15),
    Reson16("集体上访", 16),
    Reson17("游行示威", 17),
    Reson18("自残自焚", 18),
    Reson19("静坐抗议", 19),
    Reson20("其他", 20);


    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private AccidentResonType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (AccidentResonType c : AccidentResonType.values()) {
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
