package com.example.impati.infrastructure;

import com.example.impati.model.Coupon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
@NoArgsConstructor
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "member_number")
    private String memberNumber;

    @Column(name = "franchise_numbers")
    private String franchiseNumber;

    @Column(name = "categories")
    private String categories;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "first_order")
    private Boolean firstOrder;

    @Column(name = "membership")
    private Boolean membership;

    public CouponEntity(final Long id,
                        final String memberNumber,
                        final String franchiseNumber,
                        final String categories,
                        final String deliveryType,
                        final Boolean firstOrder,
                        final Boolean membership) {
        this.id = id;
        this.memberNumber = memberNumber;
        this.franchiseNumber = franchiseNumber;
        this.categories = categories;
        this.deliveryType = deliveryType;
        this.firstOrder = firstOrder;
        this.membership = membership;
    }

    public Coupon toDomain() {
        return new Coupon(
                id.toString(),
                memberNumber,
                Arrays.stream(franchiseNumber.split(",")).toList(),
                Arrays.stream(categories.split(",")).toList(),
                deliveryType,
                firstOrder,
                membership
        );
    }

    public static CouponEntity from(Coupon coupon) {
        return new CouponEntity(
                coupon.couponId() == null ? null : Long.valueOf(coupon.couponId()),
                coupon.memberNumber(),
                Strings.join(coupon.usableFranchiseNumber(), ','),
                Strings.join(coupon.categories(), ','),
                coupon.deliveryType(),
                coupon.onlyFirstOrder(),
                coupon.onlyMembership()
        );
    }
}
