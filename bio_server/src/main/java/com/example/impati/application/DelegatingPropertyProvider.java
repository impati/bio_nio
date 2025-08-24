package com.example.impati.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegatingPropertyProvider {

    private final List<PropertyProvider<?>> propertyProviders;

    public Properties provide(Input input, List<Coupon> coupons) {
        Properties properties = new Properties();
        for (PropertyProvider<?> propertyProviders : propertyProviders) {
            Property property = propertyProviders.provide(input, coupons);
            properties.add(property);
        }

        return properties;
    }
}
