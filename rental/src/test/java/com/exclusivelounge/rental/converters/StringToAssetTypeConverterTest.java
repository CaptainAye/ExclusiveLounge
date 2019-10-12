package com.exclusivelounge.rental.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;
import static org.junit.jupiter.api.Assertions.*;

class StringToAssetTypeConverterTest {

    private StringToAssetTypeConverter converter = new StringToAssetTypeConverter();

    @Test
    void test_whenStringValueCorrect_thenShouldReturnAssetType() {
        assertEquals(CAR, converter.convert("CaR"));
        assertEquals(PLANE, converter.convert("PlanE"));
        assertEquals(YACHT, converter.convert("YACHT"));
    }

    @Test
    @DisplayName("when input String is empty or null, then return null")
    void test_whenStringIsEmptyOrNull_thenShouldReturnNull() {
        assertNull(converter.convert(""));
        assertNull(converter.convert(null));
    }

    @Test
    void test_whenStringValueIncorrect_thenShouldThrowException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> converter.convert("plain")),
                () -> assertThrows(IllegalArgumentException.class, () -> converter.convert("Yahct"))
        );
    }
}