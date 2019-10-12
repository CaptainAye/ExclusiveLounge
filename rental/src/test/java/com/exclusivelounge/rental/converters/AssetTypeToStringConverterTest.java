package com.exclusivelounge.rental.converters;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AssetTypeToStringConverter tests")
class AssetTypeToStringConverterTest {
    private AssetTypeToStringConverter assetTypeToStringConverter = new AssetTypeToStringConverter();

    @Test
    @DisplayName("when AssetType is given then should return its name")
    void test_WhenAssetTypeGiven_ReturnAssetTypeName() {
        Arrays.stream(AssetType.values()).forEach( assetType -> assertEquals(assetType.name(),
                assetTypeToStringConverter.convert(assetType)));
    }

    @Test
    @DisplayName("when AssetType is null then should return empty String")
    void test_WhenAssetTypeNull_ReturnEmptyString() {
                assertEquals(assetTypeToStringConverter.convert(null), "");
    }





}