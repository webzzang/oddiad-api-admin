package com.exflyer.oddi.admin.api.contents.banner;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.contents.banner.code.BannerCodes;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerDetailResult;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerRegReq;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerSearchCodes;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBanner;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBannerResult;
import com.exflyer.oddi.admin.api.contents.banner.service.BannerService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Banner;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "컨텐츠", protocols = "http")
@Slf4j
@RestController
public class TopBannerApi {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "광고판 상단 배너관리 사용 코드", notes = "광고판 상단 배너관리 사용 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/top-banner/search-codes")
    @MenuValidApi(menuCode = "CNT004", operation = CrudOperation.READ)
    public ApiResponseDto<BannerSearchCodes> findCodeList() {
        BannerSearchCodes searchCode = bannerService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "광고판 상단 배너관리 리스트 조회", notes = "광고판 상단 배너관리 리스트 조회 API 입니다. ")
    @GetMapping(path = "/top-banner/search")
    @MenuValidApi(menuCode = "CNT004", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchBannerResult>> find(@Validated SearchBanner searchReq) throws ApiException {
        searchReq.setBannerType(BannerCodes.WEB_BANNER_TYPE.getCode());
        searchReq.setBannerLocationType(BannerCodes.BANNER_TOP_LOCATION.getCode());
        PagingResult<SearchBannerResult> searchSideBannerList = bannerService.findSearchSideBanner(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchSideBannerList);
    }

    @ApiOperation(value = "광고판 상단 배너관리 상세 조회", notes = "광고판 상단 배너관리 상세 조회 API 입니다. ")
    @GetMapping(path = "/top-banner/{seq}")
    @MenuValidApi(menuCode = "CNT004", operation = CrudOperation.READ)
    public ApiResponseDto<BannerDetailResult>detail(@PathVariable Long seq) throws ApiException {
        BannerDetailResult bannerDetailResult = bannerService.findDetail(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, bannerDetailResult);
    }

    @ApiOperation(value = "광고판 상단 배너관리 등록", notes = "광고판 상단 배너관리 등록 API 입니다. ")
    @PostMapping(path = "/top-banner")
    @MenuValidApi(menuCode = "CNT004", operation = CrudOperation.READ)
    public ApiResponseDto<Banner>add(@Validated @RequestBody BannerRegReq regReq, AdminAuth adminAuth)
        throws ApiException {
        regReq.setType(BannerCodes.WEB_BANNER_TYPE.getCode());
        regReq.setLocationCode(BannerCodes.BANNER_TOP_LOCATION.getCode());
        regReq.setRegId(adminAuth.getId());
        bannerService.save(regReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "광고판 상단 배너관리 수정", notes = "광고판 상단 배너관리 수정 API 입니다. ")
    @PutMapping(path = "/top-banner/{seq}")
    @MenuValidApi(menuCode = "CNT004", operation = CrudOperation.READ)
    public ApiResponseDto<Banner>modify(@PathVariable Long seq, @Validated @RequestBody BannerRegReq modReq, AdminAuth adminAuth) throws ApiException {
        modReq.setSeq(seq);
        modReq.setType(BannerCodes.WEB_BANNER_TYPE.getCode());
        modReq.setLocationCode(BannerCodes.BANNER_TOP_LOCATION.getCode());
        modReq.setModId(adminAuth.getId());
        bannerService.modify(modReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
