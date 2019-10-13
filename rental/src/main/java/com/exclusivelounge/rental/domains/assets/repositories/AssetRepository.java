package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.Asset;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.model.enumerations.RentalState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetRepository extends CrudRepository<Asset, Long> {

    List<Asset> findAllByAssetTypeAndRentalState(AssetType assetType, RentalState rentalState);
}
