package com.exflyer.oddi.admin.api.product.promotion.service;

import com.exflyer.oddi.admin.api.product.promotion.code.PromotionCodes;
import com.exflyer.oddi.admin.api.product.promotion.dao.PromotionMapper;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionBasicResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponList;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionListSearchResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionMemberCouponResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionSearchCodes;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionSearchReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Promotion;
import com.exflyer.oddi.admin.models.PromotionCoupon;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.PromotionCouponRepository;
import com.exflyer.oddi.admin.repository.PromotionRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.RandomNumber;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionCouponRepository promotionCouponRepository;
    @Autowired
    private PromotionMapper promotionMapper;
    @Autowired
    private CodeRepository codeRepository;

    @PersistenceContext
    EntityManager em;

    public PromotionSearchCodes findCodeForSearching() {
        List<Code> promotionTypeCode = codeRepository.findByGroupCodeAndUsable("PTC", true);
        List<Code> discountTypeCode = codeRepository.findByGroupCodeAndUsable("PDT", true);
        List<Code> memberCouponCode = codeRepository.findByGroupCodeAndUsable("MCT", true);
        List<Code> couponUsableCode = codeRepository.findByGroupCodeAndUsable("CUT", true);
        return new PromotionSearchCodes(promotionTypeCode, discountTypeCode, memberCouponCode, couponUsableCode);
    }

    public PagingResult<PromotionListSearchResult> findBySearchReq(PromotionSearchReq searchReq) {
        searchReq.setType(PromotionCodes.NORMAL_PROMOTION.getCode());
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<PromotionListSearchResult> result = promotionMapper.findBySearchReq(searchReq);

        log.info("datas : "+result);

        return PagingResult.createResultDto(result);
    }


    public PromotionBasicResult findDetail(Long seq) throws ApiException {
        PromotionBasicResult promotionBasicInfo = promotionMapper.findPromotionBasicInfo(seq);
        if (promotionBasicInfo == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }

        return promotionBasicInfo;
    }


    public PagingResult<PromotionCouponResult> findCouponDetail(PromotionCouponReq searchReq) {
        searchReq.setCouponUsableGroupCode(PromotionCodes.COUPON_USABLE.getCode());

        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "rownum desc"));
        int resultCnt = promotionMapper.findPromotionCouponCount(searchReq);

        int end = searchReq.getPageNo() * searchReq.getPageSize();
        int start = end - searchReq.getPageSize();
        int pages = ((int) Math.ceil((double)resultCnt / (double)searchReq.getPageSize()));

        searchReq.setStart(start);
        searchReq.setEnd(searchReq.getPageSize());

        List<PromotionCouponList> result = promotionMapper.findPromotionCouponList(searchReq);

        return PagingResult.createResultHashMap(resultCnt, pages, searchReq.getPageNo(), result);
    }

    public Integer findPromotionCouponCount(PromotionCouponReq searchReq) {
        return promotionMapper.findPromotionCouponCount(searchReq);
    }

    @Transactional
    public Long add(PromotionReq addReq) {
        Promotion promotion = new Promotion(addReq);
        promotion.setType(PromotionCodes.NORMAL_PROMOTION.getCode());
        //promotion.setDiscountType(PromotionCodes.MONEY_DISCOUNT.getCode());
        promotion.setUsable(true);
        promotionRepository.save(promotion);
        em.persist(promotion);

        return promotion.getSeq();
    }

    public void addMemberCoupon(PromotionReq addReq) {

        if(promotionRepository.isMemberPromotion(PromotionCodes.MEMBER_PROMOTION.getCode()) > 0){
            promotionRepository.saveMemberPromotion(
                addReq.getName()
                , PromotionCodes.MONEY_DISCOUNT.getCode()
                , addReq.getDiscountPrice()
                , addReq.getModId()
                , addReq.getModDate()
                , addReq.getMemberCouponExpiredDay()
                , addReq.getMemberCouponCode()
                , addReq.getUsable()
                , PromotionCodes.MEMBER_PROMOTION.getCode()
            );
        }else{

            addReq.setModId(null);
            addReq.setModDate(null);
            addReq.setType(PromotionCodes.MEMBER_PROMOTION.getCode());
            //addReq.setDiscountType(PromotionCodes.MONEY_DISCOUNT.getCode());

            Promotion promotion = new Promotion(addReq);
            promotionRepository.save(promotion);
        }
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void addCoupon(PromotionReq addReq){
        addReq.setType(PromotionCodes.NORMAL_PROMOTION.getCode());
        //addReq.setDiscountType(PromotionCodes.MONEY_DISCOUNT.getCode());

        Promotion promotion = new Promotion(addReq);
        promotion.setUsable(true);
        promotionRepository.save(promotion);
        em.persist(promotion);

        //PromotionCoupon promotionCoupon = new PromotionCoupon(addReq);
        //promotionCouponRepository.save(promotionCoupon);
        addReq.setSeq(promotion.getSeq());
        savePromotionCoupon(addReq);
    }

    public void modifyCoupon(PromotionReq addReq){
//        addReq.setCouponCode(UUID.randomUUID().toString().replaceAll("-", ""));
//
//        PromotionCoupon promotionCoupon = new PromotionCoupon(addReq);
//        promotionCouponRepository.save(promotionCoupon);

        savePromotionCoupon(addReq);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(PromotionReq modReq) throws ApiException {
        Optional<Promotion> promotionOptional = promotionRepository.findById(modReq.getSeq());
        Promotion promotion = promotionOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        promotion.setPromotion(modReq);
        promotionRepository.save(promotion);

        promotionCouponRepository.saveCouponExpiredDate(modReq.getExpiredDate(), modReq.getSeq());
    }

    public Boolean isValidMemberCouponCode(String memberId, String couponCode) throws ApiException {

        Boolean b_returnCode = false;

        int isValidCoupon = promotionCouponRepository.findByIsValidCoupon(couponCode);
        int isMemberValidCoupon = promotionCouponRepository.findByIsMemberValidCoupon(memberId, couponCode);
        if (isValidCoupon > 0 && isMemberValidCoupon > 0) {
            b_returnCode = true;

            promotionCouponRepository.saveByMemberCouponUsing(LocalDateUtils.krNow(), memberId, couponCode);
        }

        return b_returnCode;

    }


    public PromotionMemberCouponResult findMemberDetail(String couponUsableGroupCode) throws ApiException {
        Optional<Promotion> promotionOptional = promotionRepository.findMemberDetail(couponUsableGroupCode);
        Promotion promotion = promotionOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        PromotionMemberCouponResult promotionMemberCouponResult = new PromotionMemberCouponResult(promotion);

        return promotionMemberCouponResult;
    }

    @Transactional(rollbackFor = {Error.class, Exception.class})
    public void savePromotionCoupon(PromotionReq addReq){

//        List<Map> lst = new ArrayList<>();

        int totCouponCnt = addReq.getCouponCnt();

        for (int i=0; i < totCouponCnt; i++) {

            // 숫자만 6자리
            String couponCode = RandomNumber.numberGen(6, 1);
            int isValidCoupon = promotionCouponRepository.findByIsValidCouponCode(couponCode);

            if (isValidCoupon == 0) {
                /*Map<String, Object> map = new HashMap();
                map.put("seq", addReq.getSeq());
//            map.put("couponCode", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8));
                map.put("couponCode", couponCode);
                map.put("expiredDate", addReq.getExpiredDate());
                map.put("regDate", LocalDateUtils.krNow());
                lst.add(map);*/

                PromotionCoupon promotionCoupon = new PromotionCoupon();
                promotionCoupon.setSeq(addReq.getSeq());
                promotionCoupon.setCouponCode(couponCode);
                promotionCoupon.setExpiredDate(addReq.getExpiredDate());
                promotionCoupon.setRegDate(LocalDateUtils.krNow());

                promotionMapper.saveCoupon(promotionCoupon);

            }else{
                totCouponCnt = totCouponCnt+1;
            }
        }

        //log.info(new Gson().toJson(lst));
        //promotionMapper.savePromotionCoupon(lst);
    }
}

