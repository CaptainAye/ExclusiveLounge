package com.exclusivelounge.rental.domains.assets.controllers;

import com.exclusivelounge.rental.domains.assets.model.entities.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.BrandModel;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandManagementController {

    private final BrandService brandService;

    public BrandManagementController(@Autowired BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{assetType}")
    public Iterable<Brand> getBrandsByType(@PathVariable AssetType assetType) {
        return brandService.findBrandsByType(assetType);
    }

    @PostMapping("/brands")
    public ResponseEntity<Object> createBrand(@RequestBody Brand brandToCreate) {
        brandService.save(brandToCreate);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/brands/{brandId}")
    public ResponseEntity<Object> updateBrand(@PathVariable long brandId, @RequestBody Brand brandToUpdate) {
        brandService.update(brandId, brandToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/brands{brandId}")
    public ResponseEntity<Object> deleteBrand(@PathVariable long brandId) {
        brandService.deleteById(brandId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{brandId}/models")
    public List<BrandModel> findModelsByBrand(@PathVariable long brandId) {
            Optional<Brand> foundBrand = brandService.findBrandById(brandId);
            return foundBrand.isPresent() ? foundBrand.get().getModels() : new ArrayList<>();
    }

    @PostMapping("/{brandId}/models")
    public ResponseEntity<Object> addModelToBrand(@PathVariable long brandId, @RequestBody BrandModel modelToAdd) {
        brandService.updateBrandWithModel(brandId, modelToAdd);
        return ResponseEntity.noContent().build();
    }

}
