package com.ieening.learnEnum;

public enum SeasonEnum {

    SPRING(1) {

        @Override
        public String description() {
            return "最是平常百姓家，新竹半掩几春花。门前犬吠鸣鸭远，老汉低眉啜现茶。";
        }

    },

    SUMMER(2) {
        @Override
        public String description() {
            return "树梢挑月伏南窗，梦随清风玉枕凉。了断新愁千缕结，池塘半锁藕花香。";
        }
    },

    AUTOM(3) {

        @Override
        public String description() {
            return "廊桥雁断树栖鸦，柳水秋枫揽碧霞。斜阳晚照无限好，诗茶伴雨写霜花。";
        }

    },

    WINTER(4) {
        @Override
        public String description() {
            return "玉蝶飘飞压翠枝，红梅初笑恨未迟。晨风落雪轻妙舞，月照西窗斌小诗。";
        }
    };

    private int id;

    public int getId() {
        return id;
    }

    private SeasonEnum(int id) {
        this.id = id;
    }

    public static SeasonEnum fromId(int id) {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (id == seasonEnum.id) {
                return seasonEnum;
            }
        }
        return null;
    }

    public abstract String description();
}
