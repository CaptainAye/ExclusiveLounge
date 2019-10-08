package com.exclusivelounge.rental.domains.assets.controllers;

import com.exclusivelounge.rental.domains.assets.entities.*;
import com.exclusivelounge.rental.domains.assets.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.exceptions.BrandTypeIncorrectException;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import com.exclusivelounge.rental.domains.assets.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/assets")
public class AssetController {

    private AssetRepository assetRepository;

    @Autowired
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping(path = "/{rentalState}/{assetType}")
    public Iterable<Asset> getAvailableAssets(@PathVariable AssetType assetType, @PathVariable RentalState rentalState) {
        return assetRepository.findAllByAssetTypeAnAndRentalState(assetType, rentalState);
    }

    @PostMapping (path = "/assets")
    public ResponseEntity<Object> createCar(@RequestBody Car car) {
        return createVehicle(car);
    }

    @PostMapping (path = "/assets")
    public ResponseEntity<Object> createYacht(@RequestBody Yacht yacht) {
        return createVehicle(yacht);
    }

    @PostMapping (path = "/assets")
    public ResponseEntity<Object> createPlane(@RequestBody Plane plane) {
        return createVehicle(plane);
    }

    private ResponseEntity<Object> createVehicle(Vehicle vehicle) {
        validateVehicle(vehicle);
        assetRepository.save(vehicle);
        return ResponseEntity.noContent().build();

    }

    private void validateVehicle(Vehicle vehicle) {
        AssetType brandType  = vehicle.getBrandModel().getBrand().getAssetType();
        if (vehicle.getAssetType().equals(brandType)) {
            throw new BrandTypeIncorrectException();
        }
    }



    @DeleteMapping(path = "/assets/{assetToDelete}")
    public ResponseEntity<Object> deleteRentalAsset(@PathVariable long assetToDelete) {
        assetRepository.deleteById(assetToDelete);
        return ResponseEntity.noContent().build();
    }



}
