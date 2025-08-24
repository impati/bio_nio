package com.example.impati.model;

import java.util.List;

public record Coupon(
        String couponId,

        String memberNumber,

        // 가게
        List<String> usableFranchiseNumber,
        List<String> categories,

        // 주문
        String deliveryType,
        boolean onlyFirstOrder,

        // 회원
        boolean onlyMembership
) {

    public boolean containFranchise(String franchiseNumber) {
        return usableFranchiseNumber.contains(franchiseNumber);
    }

    public boolean containCategory(List<String> shopCategories) {
        boolean isContain = false;
        for (String category : categories) {
            if (shopCategories.contains(category)) {
                isContain = true;
                break;
            }
        }

        return isContain;
    }

    public boolean isFranchiseCoupon() {
        return !usableFranchiseNumber.isEmpty();
    }

    public boolean isCategoryCoupon() {
        return !categories.isEmpty();
    }
}

