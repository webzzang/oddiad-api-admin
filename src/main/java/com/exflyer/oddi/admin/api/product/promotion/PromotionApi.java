package com.exflyer.oddi.admin.api.product.promotion;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.product.promotion.code.PromotionCodes;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionBasicResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionListSearchResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionMemberCouponResult;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionSearchCodes;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionSearchReq;
import com.exflyer.oddi.admin.api.product.promotion.service.PromotionService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "묶음상품관리", protocols = "http")
@Slf4j
@RestController
public class PromotionApi {

    @Autowired
    private PromotionService promotionService;

    @ApiOperation(value = "프로모션관리 사용 코드", notes = "오디존설정에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/promotion/search-codes")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<PromotionSearchCodes> findCodeList() {
        PromotionSearchCodes searchCode = promotionService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "프로모션관리 목록조회", notes = "프로모션관리 조회 API 입니다.")
    @GetMapping(path = "/promotion")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<PromotionListSearchResult>> find(@Validated PromotionSearchReq searchReq) {
        PagingResult<PromotionListSearchResult> results = promotionService.findBySearchReq(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, results);
    }

    @ApiOperation(value = "프로모션관리 기본정보 상세 조회", notes = "프로모션관리 기본정보 상세 조회 API 입니다. ")
    @GetMapping(path = "/promotion/{seq}")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<PromotionBasicResult> findDetail(@PathVariable Long seq) throws ApiException {
        PromotionBasicResult detail = promotionService.findDetail(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "프로모션관리 쿠폰발행정보 조회", notes = "프로모션관리 쿠폰발행정보 조회 API 입니다. ")
    @GetMapping(path = "/promotion/coupon")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<PromotionCouponResult>> findCouponDetail(@Validated PromotionCouponReq searchReq) {
        PagingResult<PromotionCouponResult> detail = promotionService.findCouponDetail(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "프로모션관리 쿠폰발행정보 갯수 조회", notes = "프로모션관리 쿠폰발행정보 갯수 조회 API 입니다. ")
    @GetMapping(path = "/promotion/coupon-count")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<Integer> findPromotionCouponCount(@Validated PromotionCouponReq searchReq) {
        Integer detailCnt = promotionService.findPromotionCouponCount(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detailCnt);
    }

    @ApiOperation(value = "프로모션관리 등록 API", notes = "프로모션관리 등록 API 입니다. ")
    @PostMapping(path = "/promotion")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.CREATE)
    public ApiResponseDto<Long> addPromotion(@Validated @RequestBody PromotionReq addReq, AdminAuth adminAuth) {
        addReq.setRegId(adminAuth.getId());
        Long seq = promotionService.add(addReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, seq);
    }

    @ApiOperation(value = "프로모션관리 등록 및 쿠폰발행 API", notes = "프로모션관리 등록 및 쿠폰발행 API 입니다. ")
    @PostMapping(path = "/promotion/coupon")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.CREATE)
    public ApiResponseDto addCoupon(@Validated @RequestBody PromotionReq addReq, AdminAuth adminAuth) throws ApiException {
        addReq.setRegId(adminAuth.getId());
        promotionService.addCoupon(addReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "프로모션관리 수정화면 - 쿠폰발행 API", notes = "프로모션관리 수정 - 쿠폰발행 API 입니다. ")
    @PostMapping(path = "/promotion/coupon/{seq}")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.CREATE)
    public ApiResponseDto addCoupon(@PathVariable Long seq, @Validated @RequestBody PromotionReq addReq, AdminAuth adminAuth) throws ApiException {
        addReq.setSeq(seq);
        promotionService.modifyCoupon(addReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "프로모션관리 정보 변경", notes = "프로모션관리 정보 변경 API 입니다. ")
    @PutMapping(path = "/promotion/{seq}")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.UPDATE)
    public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody PromotionReq modReq, AdminAuth adminAuth) throws ApiException {
        modReq.setSeq(seq);
        modReq.setModId(adminAuth.getId());
        promotionService.modify(modReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "가입자 쿠폰관리 조회", notes = "가입자 쿠폰관리 조회 API 입니다. ")
    @GetMapping(path = "/promotion/member/coupon")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.READ)
    public ApiResponseDto<PromotionMemberCouponResult> findMemberDetail() throws ApiException {
        PromotionMemberCouponResult detail = promotionService.findMemberDetail(PromotionCodes.MEMBER_PROMOTION.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "가입자 쿠폰관리 - 저장 API", notes = "가입자 쿠폰관리 저장 API 입니다. ")
    @PostMapping(path = "/promotion/member/coupon")
    @MenuValidApi(menuCode = "PRD004", operation = CrudOperation.CREATE)
    public ApiResponseDto addMemberCoupon(@Validated @RequestBody PromotionReq addReq, AdminAuth adminAuth) throws ApiException {
        addReq.setRegId(adminAuth.getId());
        addReq.setRegDate(LocalDateUtils.krNow());
        addReq.setModId(adminAuth.getId());
        addReq.setModDate(LocalDateUtils.krNow());
        promotionService.addMemberCoupon(addReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
