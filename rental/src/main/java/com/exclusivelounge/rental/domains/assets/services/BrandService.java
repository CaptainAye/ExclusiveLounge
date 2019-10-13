package com.exclusivelounge.rental.domains.assets.services;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> findBrandsByType(AssetType assetType);

    Brand save(Brand brandToSave);

    Brand update(Brand brandToUpdate) throws BrandNotFoundException;

    void deleteById(long brandId);

    Optional<Brand> findBrandById(long brandId);
}
