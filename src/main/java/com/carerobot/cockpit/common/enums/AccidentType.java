package com.carerobot.cockpit.common.enums;

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

        public enum SecurityEnum {
            //安全类（磕碰伤、绊倒、摔倒、触电、扭伤、拉伤、割伤、划伤、坠床、走失、骨折、压伤、刺伤、其他）
            TYPE1("磕碰伤", 1),
            TYPE2("绊倒", 2),
            TYPE3("摔倒", 3),
            TYPE4("触电", 4),
            TYPE5("扭伤",5),
            TYPE6("拉伤", 6),
            TYPE7("割伤", 7),
            TYPE8("划伤", 8),
            TYPE9("坠床", 9),
            TYPE10("走失",10),
            TYPE11("骨折", 11),
            TYPE12("压伤", 12),
            TYPE13("刺伤", 13),
            TYPE14("其他",14);

            // 成员变量
            private String name;
            private int index;

            // 构造方法
            private SecurityEnum(String name, int index) {
                this.name = name;
                this.index = index;
            }

            // 普通方法
            public static String getName(int index) {
                for (AccidentType.AccidentTypeEnum.SecurityEnum c : AccidentType.AccidentTypeEnum.SecurityEnum.values()) {
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

        public enum HealthEnum {
            //健康类（一般疾病、精神疾病、食物中毒、摔倒、发热指标、感冒指标、腹泻指标、其他）
            TYPE1("一般疾病", 1),
            TYPE2("精神疾病", 2),
            TYPE3("食物中毒", 3),
            TYPE4("摔倒", 4),
            TYPE5("发热指标",5),
            TYPE6("感冒指标", 6),
            TYPE7("腹泻指标", 7),
            TYPE8("其他", 8);

            // 成员变量
            private String name;
            private int index;

            // 构造方法
            private HealthEnum(String name, int index) {
                this.name = name;
                this.index = index;
            }

            // 普通方法
            public static String getName(int index) {
                for (AccidentType.AccidentTypeEnum.HealthEnum c : AccidentType.AccidentTypeEnum.HealthEnum.values()) {
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
}
