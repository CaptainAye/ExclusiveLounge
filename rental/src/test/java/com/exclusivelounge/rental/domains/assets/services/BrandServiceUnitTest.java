package com.exclusivelounge.rental.domains.assets.services;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.repositories.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BrandServiceUnitTest {

    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.initMocks(this);
        brandService = new BrandServiceImpl(brandRepository);
    }

    @Test
    @DisplayName("given save successful, should save entity and return one with assigned id")
    void givenSaveIsSuccessful_thenReturnSavedEntityWithId () {
        long saveId = 1;
        String brandName = "Audi";
        AssetType brandType = AssetType.CAR;
        Mockito.doAnswer(
                mockMethodInvocation -> {
                    Brand savedBrand = mockMethodInvocation.getArgument(0);
                    savedBrand.setId(saveId);
                    return savedBrand;
                }
        ).when(brandRepository).save(ArgumentMatchers.any(Brand.class));

        Brand brandToSave = new Brand(brandName, brandType);
        Brand expectedBrand = new Brand(saveId, brandName, brandType);
        assertEquals(expectedBrand, brandService.save(brandToSave));
    }




}