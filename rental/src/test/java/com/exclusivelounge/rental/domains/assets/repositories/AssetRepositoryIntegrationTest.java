package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.*;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("AssetRepository integration tests")
public class AssetRepositoryIntegrationTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssetRepository assetRepository;

    @Nested
    @DisplayName("When database is filled with many records")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Transactional
    class ManyDbRecordsTests {

        private Car mockAudiA4;
        private Car mockMercedesCLA220;
        private Car mockAudiA5;
        private Car mockAudiA6;
        private Car mockMercedesGLS;

        @BeforeAll
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
        @Nested
        @DisplayName("when findBy different RentalState")
        @Transactional
        class FindByRentalStateTest {
            @Test
            @DisplayName("when RentalState is AVAILABLE return only AVAILABLE")
            void test_WhenRentalTypeAvailable_thenReturnOnlyAvailable() {
                setupEntityManager();
                List<Asset> expectedAssets = Arrays.asList(mockAudiA4, mockMercedesCLA220);
                List<Asset> actualAssets = new ArrayList<>();
                assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.AVAILABLE).forEach(actualAssets::add);
                assertEquals(expectedAssets, actualAssets);
            }

            @Test
            @DisplayName("when RentalState is RENT return only RENT")
            void test_WhenRentalTypeRent_thenReturnOnlyRent() {
                setupEntityManager();
                List<Asset> expectedAssets = Collections.singletonList(mockAudiA5);
                List<Asset> actualAssets = new ArrayList<>();
                assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.RENT).forEach(actualAssets::add);
                assertEquals(expectedAssets, actualAssets);
            }

            @Test
            @DisplayName("when RentalState is RESERVED return only RESERVED")
            void test_WhenRentalTypeReserved_thenReturnOnlyReserved() {
                setupEntityManager();
                List<Asset> expectedAssets = Arrays.asList(mockAudiA6, mockMercedesGLS);
                List<Asset> actualAssets = new ArrayList<>();
                assetRepository.findAllByAssetTypeAndRentalState(AssetType.CAR, RentalState.RESERVED).forEach(actualAssets::add);
                assertEquals(expectedAssets, actualAssets);
            }

        }

    }
}