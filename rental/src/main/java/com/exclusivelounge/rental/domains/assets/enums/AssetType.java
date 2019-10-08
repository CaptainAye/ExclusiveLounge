package com.exclusivelounge.rental.domains.assets.enums;

public enum AssetType {
    PLANE(AssetTypeValues.PLANE), CAR(AssetTypeValues.CAR), YACHT(AssetTypeValues.YACHT);

    private String value;

    AssetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class AssetTypeValues {
        public static final String CAR = "CAR";
        public static final String PLANE = "YACHT";
        public static final String YACHT = "YACHT";
    }
}
