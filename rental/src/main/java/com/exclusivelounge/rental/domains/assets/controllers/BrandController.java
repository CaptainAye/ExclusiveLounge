package com.exclusivelounge.rental.domains.assets.controllers;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(@Autowired BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/{assetType}")
    public List<Brand> getBrandsByType(@PathVariable AssetType assetType) {
        return brandService.findBrandsByType(assetType);
    }

    @PostMapping
    public ResponseEntity<Object> createBrand(@RequestBody Brand brandToCreate) {
        Brand savedBrand = brandService.save(brandToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);

    }

    @PutMapping("/{brandId}")
    public ResponseEntity<Object> updateBrand(@PathVariable long brandId, @RequestBody Brand brandToUpdate) {
        brandToUpdate.setId(brandId);
        brandService.update(brandToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Object> deleteBrand(@PathVariable long brandId) {
        brandService.deleteById(brandId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{brandId}/models")
    public List<BrandModel> findModelsByBrand(@PathVariable long brandId) {
            Optional<Brand> foundBrand = brandService.findBrandById(brandId);
            return foundBrand.isPresent() ? foundBrand.get().getModels() : new ArrayList<>();
    }

}
