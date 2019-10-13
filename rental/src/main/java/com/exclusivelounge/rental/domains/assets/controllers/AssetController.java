package com.exclusivelounge.rental.domains.assets.controllers;

import com.exclusivelounge.rental.domains.assets.model.entities.*;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandTypeIncorrectException;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import com.exclusivelounge.rental.domains.assets.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/assets")
public class AssetController {

    private AssetRepository assetRepository;

    @Autowired
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping(path = "/{assetType}")
    public List<Asset> getAssets(@PathVariable AssetType assetType) {
        return assetRepository.findAllByAssetTypeAndRentalState(assetType, RentalState.AVAILABLE);
    }

    @PostMapping (path = "/")
    public ResponseEntity<Object> createVehicle(Vehicle vehicle) {
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
