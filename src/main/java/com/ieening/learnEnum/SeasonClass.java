package com.ieening.learnEnum;

public final class SeasonClass {
    private String name; // 名称

    public String getName() {
        return name;
    }

    private String description; // 季节描述

    public String getDescription() {
        return description;
    }

    // 类内部创建对象，并用公共静态常量形式表示
    public static final SeasonClass SPRING = new SeasonClass("春天", "鸟语花香");
    public static final SeasonClass SUMMER = new SeasonClass("夏天", "烈日炎炎");
    public static final SeasonClass AUTUM = new SeasonClass("秋天", "秋高气爽");
    public static final SeasonClass WINTER = new SeasonClass("冬天", "银装素裹");

    private SeasonClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SeasonClass [name=" + name + ", description=" + description + "]";
    }

}
