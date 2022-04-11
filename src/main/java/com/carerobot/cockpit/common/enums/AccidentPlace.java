package com.carerobot.cockpit.common.enums;

public class AccidentPlace {

    public final static int PlaceOne = 1;

    public final static int PlaceTwo = 2;

    public final static int PlaceThree = 3;

    public enum AccidentPlaceEnum {

        PlaceOne("房间内", 1),
        PlaceTwo("公共区域", 2),
        PlaceThree("社区外",3);

        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private AccidentPlaceEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (AccidentPlace.AccidentPlaceEnum c : AccidentPlace.AccidentPlaceEnum.values()) {
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