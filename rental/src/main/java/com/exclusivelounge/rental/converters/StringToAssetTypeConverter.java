package com.exclusivelounge.rental.converters;

import com.exclusivelounge.rental.domains.assets.enums.AssetType;
import org.springframework.core.convert.converter.Converter;

public class StringToAssetTypeConverter implements Converter<String, AssetType>{
    @Override
    public AssetType convert(String s) {
        return AssetType.valueOf(s.toUpperCase());
    }
}
