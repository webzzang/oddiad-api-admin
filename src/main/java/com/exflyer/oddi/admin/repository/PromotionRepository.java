package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Promotion;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionRepository extends JpaRepository<Promotion, Long>, JpaSpecificationExecutor<Promotion> {

    @Query(value = "select count(1) from promotion where type = :type", nativeQuery = true)
    int isMemberPromotion(@Param("type") String type);

    @Query(value = "select * from promotion where type = :couponUsableGroupCode", nativeQuery = true)
    Optional<Promotion> findMemberDetail(@Param("couponUsableGroupCode") String couponUsableGroupCode);

    @Transactional
    @Modifying
    @Query(value = "update promotion set "
        + "name = :name"
        + ", discount_type = :discountType"
        + ", discount_price = :discountPrice"
        + ", mod_id = :modId"
        + ", mod_date = :modDate"
        + ", member_coupon_expired_day = :memberCouponExpiredDay"
        + ", member_coupon_code = :memberCouponCode"
        + ", usable = :usable"
        + " where type = :type", nativeQuery = true)
    void saveMemberPromotion(
        @Param("name") String name
        , @Param("discountType") String discountType
        , @Param("discountPrice") Integer discountPrice
        , @Param("modId") String modId
        , @Param("modDate") LocalDateTime modDate
        , @Param("memberCouponExpiredDay") Integer memberCouponExpiredDay
        , @Param("memberCouponCode") String memberCouponCode
        , @Param("usable") Boolean usable
        , @Param("type") String type
    );
}
