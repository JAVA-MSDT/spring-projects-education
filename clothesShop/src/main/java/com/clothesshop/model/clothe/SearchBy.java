package com.clothesshop.model.clothe;

public enum SearchBy {
    DESCRIPTION("Description"),
    TYPE("Type"),
    FABRIC("Fabric"),
    SIZE("Size"),
    GENDER("Gender");

    public final String value;

    SearchBy(String value) {
        this.value = value;
    }
}
