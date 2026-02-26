package com.vmlens.api.automatic.internal.wrapper;

public class CreateLabel {

    public static String createLabel(String builderMethod, int postion) {
        return builderMethod + "(" + postion +")";
    }

}
