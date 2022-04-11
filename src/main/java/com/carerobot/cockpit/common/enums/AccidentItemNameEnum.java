package com.carerobot.cockpit.common.enums;

public enum AccidentItemNameEnum {

        //北京香山、天津温莎堡、天津中医门诊、宁波星健兰亭、宁波护理院、浦江星堡、中环一期、中环二期、苏州康复医院、苏州公寓、苏州蜂邻、上海蜂邻
        bjxs("北京香山", 0),
        tjzymz("天津中医门诊", 1),
        tjwsb("天津温莎堡", 2),
        nbxjlt("宁波星健兰亭", 3),
        nbhly("宁波护理院", 4),
        pjxb("浦江星堡", 5),
        zhyq("中环一期", 6),
        zheq("中环二期", 7),
        szkfyy("苏州康复医院", 8),
        szgy("苏州公寓", 9),
        szfl("苏州蜂邻", 10),
        shfl("苏州公寓", 11);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private AccidentItemNameEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (AccidentItemNameEnum c : AccidentItemNameEnum.values()) {
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