package com.exclusivelounge.rental.validation.validators;

import com.exclusivelounge.rental.validation.annotations.IsValidYear;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IntYearValidator implements ConstraintValidator<IsValidYear, Integer> {
   private static final int MIN_VEHICLE_YEAR = 1900;

   int getMinVehicleYear() {
      return MIN_VEHICLE_YEAR;
   }

   @Override
   public void initialize(IsValidYear constraint) {
   }

   @Override
   public boolean isValid(Integer year, ConstraintValidatorContext context) {
      Integer maxYear = LocalDate.now().getYear();
      Integer minYear = MIN_VEHICLE_YEAR;
      return year == null || (year >= minYear && year <= maxYear);
   }
}
