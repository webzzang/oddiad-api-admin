package com.exflyer.oddi.admin.api.product.promotion.dao;

import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionBasicResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponList;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionListSearchResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionSearchReq;
import com.exflyer.oddi.admin.models.PromotionCoupon;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionMapper {
    Page<PromotionListSearchResult> findBySearchReq(PromotionSearchReq searchReq);
    PromotionBasicResult findPromotionBasicInfo(Long seq);
    List<PromotionCouponList> findPromotionCouponList(PromotionCouponReq searchReq);
    Integer findPromotionCouponCount(PromotionCouponReq searchReq);
    void savePromotionCoupon(List<Map> lst);
    void saveCoupon(PromotionCoupon promotionCoupon);
}
