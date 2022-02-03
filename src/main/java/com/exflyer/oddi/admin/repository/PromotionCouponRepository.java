package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PromotionCoupon;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionCouponRepository extends JpaRepository<PromotionCoupon, Long>,JpaSpecificationExecutor<PromotionCoupon> {

    @Modifying
    @Transactional
    @Query(value = "update promotion_coupon set expired_date = :expiredDate where promotion_seq = :seq", nativeQuery = true)
    void saveCouponExpiredDate(@Param("expiredDate") String expiredDate, @Param("seq") Long seq);

    @Query(value="select count(1) from promotion_coupon where coupon_code = :couponCode", nativeQuery = true)
    int findByIsValidCoupon(@Param("couponCode") String couponCode);

    @Query(value="select count(1) from promotion_coupon where member_id = :memberId and coupon_code = :couponCode", nativeQuery = true)
    int findByIsMemberValidCoupon(@Param("memberId") String memberId, @Param("couponCode") String couponCode);

    @Modifying
    @Transactional
    @Query(value = "update promotion_coupon set usable = true, using_date = :usingDate where member_id = :memberId and coupon_code = :couponCode", nativeQuery = true)
    void saveByMemberCouponUsing(@Param("usingDate") LocalDateTime usingDate, @Param("memberId") String memberId, @Param("couponCode") String couponCode);

    @Query(value="select * from promotion_coupon where member_id = :memberId and coupon_code = :couponNumber", nativeQuery = true)
    PromotionCoupon findByMemberByCouponCode(@Param("memberId")String memberId, @Param("couponNumber")String couponNumber);
//    Optional<PromotionCoupon> findByMemberByCouponCode(@Param("memberId")String memberId, @Param("couponNumber")String couponNumber);

    @Query(value="select count(1) from promotion_coupon where coupon_code = :couponCode and usable = false and del = false", nativeQuery = true)
    int findByIsValidCouponCode(@Param("couponCode") String couponCode);

}
