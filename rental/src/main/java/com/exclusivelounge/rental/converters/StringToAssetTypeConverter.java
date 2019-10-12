package com.exclusivelounge.rental.converters;

import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import org.springframework.core.convert.converter.Converter;

public class StringToAssetTypeConverter implements Converter<String, AssetType>{
    @Override
    public AssetType convert(String s) {
        return s != null && !s.isEmpty() ? AssetType.valueOf(s.toUpperCase()) : null;
    }
}
