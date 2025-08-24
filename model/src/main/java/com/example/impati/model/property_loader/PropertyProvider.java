package com.example.impati.model.property_loader;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.property.Property;
import java.util.List;

public interface PropertyProvider<P extends Property> {

    P provide(Input input, List<Coupon> coupons);
}
