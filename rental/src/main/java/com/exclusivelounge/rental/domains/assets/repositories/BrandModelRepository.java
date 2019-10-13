package com.exclusivelounge.rental.domains.assets.repositories;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.BrandModel;
import org.springframework.data.repository.CrudRepository;

public interface BrandModelRepository extends CrudRepository<BrandModel, Long> {
    Iterable<BrandModel> findAllByBrand(Brand brand);
}
