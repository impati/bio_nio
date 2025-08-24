package com.example.impati.model;

import java.util.List;

public interface CouponRepository {

    List<Coupon> findBy(String memberNumber);
}
