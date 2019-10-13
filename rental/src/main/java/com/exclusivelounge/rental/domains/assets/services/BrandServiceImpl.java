package com.exclusivelounge.rental.domains.assets.services;
import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandNotFoundException;
import com.exclusivelounge.rental.domains.assets.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    public BrandServiceImpl(@Autowired BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findBrandsByType(AssetType assetType) {
        return brandRepository.findAllByAssetType(assetType);
    }

    @Override
    public Brand save(Brand brandToSave) {
        brandRepository.save(brandToSave);
        return brandToSave;
    }

    @Override
    public Brand update(Brand brandToUpdate) throws BrandNotFoundException{
        Optional<Brand> foundBrand = brandRepository.findById(brandToUpdate.getId());
        if (!foundBrand.isPresent()) {
            throw new BrandNotFoundException();
        }
        brandRepository.save(brandToUpdate);
        return brandToUpdate;
    }

    @Override
    public void deleteById(long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public Optional<Brand> findBrandById(long brandId) {
        return brandRepository.findById(brandId);
    }
}
