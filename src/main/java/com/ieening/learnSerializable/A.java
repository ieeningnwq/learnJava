package com.ieening.learnSerializable;

public class A {
    Common firstCommon;
    Common secondCommon;

    JsonIdentityCommon firstJsonIdentityCommon;
    JsonIdentityCommon secondJsonIdentityCommon;

    public Common getFirstCommon() {
        return firstCommon;
    }

    public void setFirstCommon(Common firstCommon) {
        this.firstCommon = firstCommon;
    }

    public Common getSecondCommon() {
        return secondCommon;
    }

    public void setSecondCommon(Common secondCommon) {
        this.secondCommon = secondCommon;
    }

    public JsonIdentityCommon getFirstJsonIdentityCommon() {
        return firstJsonIdentityCommon;
    }

    public void setFirstJsonIdentityCommon(JsonIdentityCommon firstJsonIdentityCommon) {
        this.firstJsonIdentityCommon = firstJsonIdentityCommon;
    }

    public JsonIdentityCommon getSecondJsonIdentityCommon() {
        return secondJsonIdentityCommon;
    }

    public void setSecondJsonIdentityCommon(JsonIdentityCommon secondJsonIdentityCommon) {
        this.secondJsonIdentityCommon = secondJsonIdentityCommon;
    }

}
