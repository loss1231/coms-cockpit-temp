package com.carerobot.cockpit.common.enums;

public class AccidentStatus {
    public final static int UnProcess = 0;

    public final static int Process = 1;

    public final static int Finish = 2;

    public enum AccidentStatusEnum {




        UnProcess("未审核", 0),
        Process("已审核", 1),
        Finish("已完成", 2);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private AccidentStatusEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (AccidentStatus.AccidentStatusEnum c : AccidentStatus.AccidentStatusEnum.values()) {
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
