package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Iterable<Brand> findAllByAssetType(AssetType assetType);

}
