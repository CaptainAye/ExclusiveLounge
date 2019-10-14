package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;

@DataJpaTest
@DisplayName("BrandRepository integration tests")
class BrandRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BrandRepository brandRepository;

    private Brand inDatabaseAudi;
    private Brand inDatabaseBmw;
    private Brand inDatabaseBoeing;
    private Brand inDatabaseFeadship;

    @BeforeEach
    private void setup() {
        entityManager.clear();
        inDatabaseAudi = new Brand("Audi", CAR);
        inDatabaseBmw = new Brand("bmw", CAR);
        inDatabaseFeadship = new Brand("Feadship", YACHT);
        inDatabaseBoeing = new Brand("Boeing", PLANE);
        BrandModel bmwM5 = new BrandModel( "m5", 2018);
        BrandModel audiA4 = new BrandModel( "A4 B8", 2012, 2017);
        BrandModel audiA5 = new BrandModel( "A5 B8", 2018);
        BrandModel audiA6 = new BrandModel( "A5 B8", 2018);
        inDatabaseAudi.setModels(Arrays.asList(audiA4, audiA5, audiA6));
        inDatabaseBmw.addModel(bmwM5);
        entityManager.persist(inDatabaseAudi);
        entityManager.persist(inDatabaseBmw);
        entityManager.persist(inDatabaseFeadship);
        entityManager.persist(inDatabaseBoeing);
    }

    @Test
    @DisplayName("Given database filled with many records when AssetType is CAR then should return only CAR brands")
    void givenDbHasRecords_whenAssetTypeCar_thenShouldReturnCarBrands() {
        List<Brand> carBrands = brandRepository.findAllByAssetType(CAR);
        List<Brand> expectedBrands = Arrays.asList(inDatabaseAudi, inDatabaseBmw);
        Assert.assertThat(carBrands, Is.is(expectedBrands));
    }

    @Test
    @DisplayName("Given database filled with many records when AssetType is PLANE then should return only PLANE brands")
    void givenDbHasRecords_whenAssetTypePlane_thenShouldReturnPlaneBrands() {
        List<Brand> carBrands = brandRepository.findAllByAssetType(PLANE);
        List<Brand> expectedBrands = Collections.singletonList(inDatabaseBoeing);
        Assert.assertThat(carBrands, Is.is(expectedBrands));
    }

    @Test
    @DisplayName("Given database filled with many records when AssetType is YACHT then should return only YACHT brands")
    void givenDbHasRecords_whenAssetTypeYacht_thenShouldReturnYachtBrands() {
        List<Brand> carBrands = brandRepository.findAllByAssetType(YACHT);
        List<Brand> expectedBrands = Collections.singletonList(inDatabaseFeadship);
        Assert.assertThat(carBrands, Is.is(expectedBrands));
    }

    @Test
    @DisplayName("Given database filled with many records when AssetType is null then should return empty list")
    void givenDbHasRecords_whenAssetTypeNull_thenShouldReturnEmptyList() {
        List<Brand> carBrands = brandRepository.findAllByAssetType(null);
        List<Brand> expectedBrands = Collections.emptyList();
        Assert.assertThat(carBrands, Is.is(expectedBrands));
    }
}