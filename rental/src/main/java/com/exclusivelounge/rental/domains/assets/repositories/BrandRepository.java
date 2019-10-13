package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    List<Brand> findAllByAssetType(AssetType assetType);

}
