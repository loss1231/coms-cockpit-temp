package com.carerobot.cockpit.common.enums;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public enum HurtTypeEnum {

    //员工、客户、其他人员（第三方）
    emp("员工", 0),
    archive("客户", 1),
    other("其他人员", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private HurtTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (HurtTypeEnum c : HurtTypeEnum.values()) {
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
