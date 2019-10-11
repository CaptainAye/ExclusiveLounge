package com.exclusivelounge.rental.domains.assets.services;

import com.exclusivelounge.rental.domains.assets.model.entities.Brand;
import com.exclusivelounge.rental.domains.assets.model.entities.BrandModel;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandNotFoundException;
import com.exclusivelounge.rental.domains.assets.repositories.BrandModelRepository;
import com.exclusivelounge.rental.domains.assets.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {


    private BrandRepository brandRepository;
    private BrandModelRepository brandModelRepository;

    public BrandService(@Autowired BrandRepository brandRepository, @Autowired BrandModelRepository brandModelRepository) {
        this.brandRepository = brandRepository;
        this.brandModelRepository = brandModelRepository;
    }

    public Iterable<Brand> findBrandsByType(AssetType assetType) {
        return brandRepository.findAllByAssetType(assetType);
    }

    public void save(Brand brandToSave) {
        brandRepository.save(brandToSave);
    }


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateBrandWithModel(long brandId, BrandModel modelToAdd) throws BrandNotFoundException{
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);

        if (!optionalBrand.isPresent()) {
            throw new BrandNotFoundException();
        }

        Brand brandToUpdate = optionalBrand.get();
        brandModelRepository.save(modelToAdd);
        List<BrandModel> brandModels = brandToUpdate.getModels();
        brandModels.add(modelToAdd);
        brandRepository.save(brandToUpdate);

    }

    public void update(long brandId, Brand brandToUpdate) throws BrandNotFoundException{
        Optional<Brand> foundBrand = brandRepository.findById(brandId);
        if (!foundBrand.isPresent()) {
            throw new BrandNotFoundException();
        }
        brandToUpdate.setId(brandId);
        brandRepository.save(brandToUpdate);
    }

    public void deleteById(long brandId) {
        brandRepository.deleteById(brandId);
    }

    public Optional<Brand> findBrandById(long brandId) {
        return brandRepository.findById(brandId);
    }
}
