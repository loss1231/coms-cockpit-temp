package com.carerobot.cockpit.common.enums;

public class AccidentNature {

    public final static int Normal = 0;

    public final static int Major = 1;

    public enum AccidentNatureEnum {

        Especially("特别重大", 0),
        Major("重大", 1),
        Larger("较大", 2),
        Normal1("一般一级", 3),
        Normal2("一般二级", 4),
        Normal3("一般三级", 5),
        Normal4("一般四级", 6);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private AccidentNatureEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (AccidentNature.AccidentNatureEnum c : AccidentNature.AccidentNatureEnum.values()) {
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
}
