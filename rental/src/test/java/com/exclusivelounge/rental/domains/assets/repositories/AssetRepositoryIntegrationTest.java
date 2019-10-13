package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.*;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.exclusivelounge.rental.domains.assets.model.enums.AssetType.*;
import static com.exclusivelounge.rental.model.enumerations.RentalState.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AssetRepository integration tests")
@DataJpaTest
class AssetRepositoryIntegrationTest {

    private Car mockAudiA4;
    private Car mockMercedesCLA220;
    private Car mockAudiA5;
    private Car mockAudiA6;
    private Car mockMercedesGLS;
    private Plane mockBoeing767;
    private Yacht mockFeadshipNajiba;

    @BeforeEach
    void setupEntityManager() {
        entityManager.clear();
        setupVehicles();
    }

    private void setupCars() {
        setupAudi();
        setupMercedes();
    }

    private void setupAudi() {
        Brand audi = new Brand("Audi", CAR);
        BrandModel audiA4 = new BrandModel( audi, "A4 B8", 2012, 2017);
        BrandModel audiA5 = new BrandModel(audi, "A5 B8", 2018);
        BrandModel audiA6 = new BrandModel(audi, "A5 B8", 2018);
        mockAudiA4 = new Car(audiA4,  new CarCharacteristics(), AVAILABLE);
        mockAudiA5 = new Car(audiA5, new CarCharacteristics(), RENT);
        mockAudiA6 = new Car(audiA6, new CarCharacteristics(), RESERVED);
        entityManager.persist(audi);
        entityManager.persist(audiA4);
        entityManager.persist(audiA5);
        entityManager.persist(audiA6);
        entityManager.persist(mockAudiA4);
        entityManager.persist(mockAudiA5);
        entityManager.persist(mockAudiA6);
    }

    private void setupMercedes() {
        Brand mercedes = new Brand("Mercedes", CAR);
        BrandModel mercedesCLA220 = new BrandModel(mercedes, "CLA 220d", 2017);
        BrandModel mercedesGLS = new BrandModel(mercedes, "GLS", 2019);
        mockMercedesCLA220 = new Car(mercedesCLA220, new CarCharacteristics(), AVAILABLE);
        mockMercedesGLS = new Car(mercedesGLS, new CarCharacteristics(), RESERVED);
        entityManager.persist(mercedes);
        entityManager.persist(mercedesCLA220);
        entityManager.persist(mercedesGLS);
        entityManager.persist(mockMercedesCLA220);
        entityManager.persist(mockMercedesGLS);
    }

    private void setupPlanes() {
        Brand boeing = new Brand("Boeing", PLANE);
        BrandModel boeing767 = new BrandModel(boeing, "767", 1964);
        mockBoeing767 = new Plane(boeing767, AVAILABLE);
        entityManager.persist(boeing);
        entityManager.persist(boeing767);
        entityManager.persist(mockBoeing767);
    }

    private void setupYacht() {
        Brand feadship = new Brand("Feadship", YACHT);
        BrandModel feadshipNajiba = new BrandModel(feadship, "Najiba", 2019);
        mockFeadshipNajiba = new Yacht(feadshipNajiba, AVAILABLE);
        entityManager.persist(feadship);
        entityManager.persist(feadshipNajiba);
        entityManager.persist(mockFeadshipNajiba);
    }

    private void setupVehicles() {
        setupCars();
        setupPlanes();
        setupYacht();
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssetRepository assetRepository;

    @Test
    @DisplayName("When AssetType=CAR, RentalState=AVAILABLE then return only {CAR, AVAILABLE}")
    void test_WhenRentalTypeAvailable_thenReturnOnlyAvailable() {

        List<Asset> expectedAssets = Arrays.asList(mockAudiA4, mockMercedesCLA220);
        List<Asset> actualAssets = assetRepository.findAllByAssetTypeAndRentalState(CAR, AVAILABLE);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=RENT then return only {CAR, RENT}")
    void test_whenRentalTypeRent_thenReturnOnlyRent() {
        List<Asset> expectedAssets = Collections.singletonList(mockAudiA5);
        List<Asset> actualAssets = assetRepository.findAllByAssetTypeAndRentalState(CAR, RENT);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=RESERVED then return only {CAR, RESERVED}")
    void test_whenRentalTypeReserved_thenReturnOnlyReserved() {
        List<Asset> expectedAssets = Arrays.asList(mockAudiA6, mockMercedesGLS);
        List<Asset> actualAssets = assetRepository.findAllByAssetTypeAndRentalState(CAR, RESERVED);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When AssetType=CAR, RentalState=IN_SERVICE then return only {CAR, IN_SERVICE}")
    void test_whenRentalStateInService_thenReturnOnlyInService() {
        List<Asset> expectedAssets = Collections.emptyList();

        List<Asset> actualAssets = assetRepository.findAllByAssetTypeAndRentalState(CAR, IN_SERVICE);
        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    @DisplayName("When no db records then return empty list")
    void test_whenNoDbRecords_returnEmptyList() {
        entityManager.clear();
        List<Asset> expectedAssets = Collections.emptyList();
        List<Asset> actualAssets = assetRepository.findAllByAssetTypeAndRentalState(CAR, AVAILABLE);
        assertEquals(expectedAssets, actualAssets);
    }
}