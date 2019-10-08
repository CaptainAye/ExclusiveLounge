package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.entities.Asset;
import com.exclusivelounge.rental.domains.assets.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {

    Iterable<Asset> findAllByAssetTypeAnAndRentalState(AssetType assetType, RentalState rentalState);
}
