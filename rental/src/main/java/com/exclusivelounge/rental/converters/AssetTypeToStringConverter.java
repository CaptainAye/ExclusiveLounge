package com.exclusivelounge.rental.converters;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import org.springframework.core.convert.converter.Converter;

public class AssetTypeToStringConverter implements Converter<AssetType, String> {
    @Override
    public String convert(AssetType assetType) {
        return assetType.name();
    }
}
