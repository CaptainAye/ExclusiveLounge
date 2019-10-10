package com.exclusivelounge.rental.validation.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/*
MockitoJUnitRunner can also be added programmatically with :
    MockitoAnnotations.initMocks(test);
*/
@RunWith(MockitoJUnitRunner.class)
class IntYearValidatorTest {

    private IntYearValidator validator = new IntYearValidator();

    @Mock
    private ConstraintValidatorContext mockCtx;

    @DisplayName("Should return true when year is in correct format")
    @Nested
    class CorrectFormatTests {
        private boolean actualResult;

        @Test
        @DisplayName("Given year is in between minimum and now")
        void test_isValid_WhenYearIsInBetweenMinAndNow() {
            int minYear = validator.getMinVehicleYear();
            actualResult = validator.isValid(minYear + 10, mockCtx);
            assertThat(actualResult, is(true));
        }

        @Test
        @DisplayName("Given year is equal to minimum")
        void test_isValid_WhenYearIsMin() {
            int minYear = validator.getMinVehicleYear();
            actualResult = validator.isValid(minYear, mockCtx);
            assertThat(actualResult, is(true));
        }

        @Test
        @DisplayName("Given year is equal to now")
        void test_isValid_WhenYearIsNow() {
            int currentYear = LocalDate.now().getYear();
            actualResult = validator.isValid(currentYear, mockCtx);
            assertThat(actualResult, is(true));
        }


        @Test
        @DisplayName("Given year is null")
        void test_isValid_WhenYearIsNull() {
            actualResult = validator.isValid(null, mockCtx);
            assertThat(actualResult, is(true));
        }
    }

    @DisplayName("Should return false when year is in incorrect format")
    @Nested
    class IncorrectFormatTests {
        private boolean actualResult;

        @Test
        @DisplayName("Given year is lesser than minimum")
        void test_isValid_WhenYearIsInBetweenMinAndNow() {
            int minYear = validator.getMinVehicleYear();
            actualResult = validator.isValid(minYear - 10, mockCtx);
            assertThat(actualResult, is(false));
        }

        @Test
        @DisplayName("Given year is greater than current year")
        void test_isValid_WhenYearIsGreaterThanNow() {
            int currentYear = LocalDate.now().getYear();
            actualResult = validator.isValid(currentYear + 10, mockCtx);
            assertThat(actualResult, is(false));
        }

        @Test
        @DisplayName("Given year is negative")
        void test_isValid_WhenYearIsNegative() {
            actualResult = validator.isValid(-1950, mockCtx);
            assertThat(actualResult, is(false));
        }
    }
}