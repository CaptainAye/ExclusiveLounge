package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.*;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AssetRepository integration tests")
@DataJpaTest
class AssetRepositoryIntegrationTest {

    private Car mockAudiA4;
    private Car mockMercedesCLA220;
    private Car mockAudiA5;
    private Car mockAudiA6;
    private Car mockMercedesGLS;

    @BeforeEach
    void setupEntityManager() {
        entityManager.clear();
        Brand audi = new Brand("Audi", AssetType.CAR);
        Brand mercedes = new Brand("Mercedes", AssetType.CAR);
        BrandModel audiA4 = new BrandModel( audi, "A4 B8", 2012, 2017);
        BrandModel audiA5 = new BrandModel(audi, "A5 B8", 2018);
        BrandModel audiA6 = new BrandModel(audi, "A5 B8", 2018);
        BrandModel mercedesCLA220 = new BrandModel(mercedes, "CLA 220d", 2017);
        BrandModel mercedesGLS = new BrandModel(mercedes, "GLS", 2019);

        mockAudiA4 = new Car(audiA4,  new CarCharacteristics(), RentalState.AVAILABLE);
        mockAudiA5 = new Car(audiA5, new CarCharacteristics(), RentalState.RENT);
        mockAudiA6 = new Car(audiA6, new CarCharacteristics(), RentalState.RESERVED);
        mockMercedesCLA220 = new Car(mercedesCLA220, new CarCharacteristics(), RentalState.AVAILABLE);
        mockMercedesGLS = new Car(mercedesGLS, new CarCharacteristics(), RentalState.RESERVED);
        entityManager.persist(audi);
        entityManager.persist(mercedes);
        entityManager.persist(audiA4);
        entityManager.persist(audiA5);
        entityManager.persist(audiA6);
        entityManager.persist(mercedesCLA220);
        entityManager.persist(mercedesGLS);
        entityManager.persist(mockAudiA4);
        entityManager.persist(mockAudiA5);
        entityManager.persist(mockAudiA6);
        entityManager.persist(mockMercedesCLA220);
        entityManager.persist(mockMercedesGLS);
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssetRepository assetRepository;

    @Test
    @DisplayName("When AssetType=CAR, RentalState=AVAILABLE then return only {CAR, AVAILABLE}")
    void test_WhenRentalTypeAvailable_thenReturnOnlyAvailable() {
        List<Asset> expectedAssets = Arrays.asList(mockAudiA4, mockMercedesCLA220);
        List<Asset> actualAssets = new ArrayList<>();
        assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.AVAILABLE).forEach(actualAssets::add);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=RENT then return only {CAR, RENT}")
    void test_whenRentalTypeRent_thenReturnOnlyRent() {
        List<Asset> expectedAssets = Collections.singletonList(mockAudiA5);
        List<Asset> actualAssets = new ArrayList<>();
        assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.RENT).forEach(actualAssets::add);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=RESERVED then return only {CAR, RESERVED}")
    void test_whenRentalTypeReserved_thenReturnOnlyReserved() {
        List<Asset> expectedAssets = Arrays.asList(mockAudiA6, mockMercedesGLS);
        List<Asset> actualAssets = new ArrayList<>();
        assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.RESERVED).forEach(actualAssets::add);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=IN_SERVICE then return only {CAR, IN_SERVICE}")
    void test_whenRentalStateInService_thenReturnOnlyInService() {
        List<Asset> expectedAssets = Collections.emptyList();
        List<Asset> actualAssets = new ArrayList<>();
        assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.IN_SERVICE).forEach(actualAssets::add);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When no db records then return empty list")
    void test_whenNoDbRecords_returnEmptyList() {
        entityManager.clear();
        List<Asset> expectedAssets = Collections.emptyList();
        List<Asset> actualAssets = new ArrayList<>();
        assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.AVAILABLE).forEach(actualAssets::add);
        assertEquals(expectedAssets, actualAssets);
    }
}