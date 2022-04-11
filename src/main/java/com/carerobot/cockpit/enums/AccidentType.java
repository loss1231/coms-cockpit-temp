package com.carerobot.cockpit.enums;

public class AccidentType {

    public final static int TYPE1001 = 1001;

    public final static int TYPE1002 = 1002;

    public final static int TYPE1003 = 1003;

    public final static int TYPE1004 = 1004;

    public final static int TYPE1005 = 1005;

    public enum AccidentTypeEnum {

        TYPE1001("安全类", 1001),
        TYPE1002("健康类", 1002),
        TYPE1003("精神类", 1003),
        TYPE1004("食品类", 1004),
        TYPE1005("其他",1005);

        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private AccidentTypeEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (AccidentType.AccidentTypeEnum c : AccidentType.AccidentTypeEnum.values()) {
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
