package com.ieening.learnSerializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TwoDate {
    Date first;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date second;

    public Date getFirst() {
        return first;
    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public Date getSecond() {
        return second;
    }

    public void setSecond(Date second) {
        this.second = second;
    }

}
