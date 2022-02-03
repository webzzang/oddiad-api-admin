package com.exflyer.oddi.admin.api.product.bundle;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.contents.livestream.code.LiveStreamCodes;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartner;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.api.contents.livestream.service.LiveStreamService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleDetailResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleListSearchResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleReq;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleSearchCodes;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleSearchReq;
import com.exflyer.oddi.admin.api.product.bundle.service.ProductBundleService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
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
public class ProductBundleApi {

    @Autowired
    private ProductBundleService productBundleService;
    @Autowired
    private LiveStreamService liveStreamService;

    @ApiOperation(value = "묶음상품관리 사용 코드", notes = "묶음상품관리에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/product/bundle/search-codes")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.READ)
    public ApiResponseDto<ProductBundleSearchCodes> findCodeList() {
        ProductBundleSearchCodes searchCode = productBundleService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "묶음상품관리 파트너 조회", notes = "묶음상품관리 파트너 조회 API 입니다. ")
    @GetMapping(path = "/product/bundle/search-partner")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchPartnerResult>> findPartnerList(@Validated SearchPartner searchReq) {
        searchReq.setOperationGroupCode(LiveStreamCodes.OPERATION_GROUP_CODE.getCode());
        PagingResult<SearchPartnerResult> searchPartnerList = liveStreamService.findLiveStreamSearchPartner(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchPartnerList);
    }

    /*@ApiOperation(value = "묶음상품관리 파트너 검색", notes = "묶음상품관리 파트너 조회 API 입니다. ")
    @GetMapping(path = "/product/bundle/search-partner")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.READ)
    public ApiResponseDto<List<Partner>> findPartnerList(@Validated Long[] seq) {
        List<Partner> searchPartnerList = productBundleService.findPartnerSearchList(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchPartnerList);
    }*/

    @ApiOperation(value = "묶음상품관리 목록조회", notes = "묶음상품관리 조회 API 입니다.")
    @GetMapping(path = "/product/bundle")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<ProductBundleListSearchResult>> find(@Validated ProductBundleSearchReq searchReq) {
        PagingResult<ProductBundleListSearchResult> results = productBundleService.findBySearchReq(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, results);
    }

    @ApiOperation(value = "묶음상품관리 상품 상세 조회", notes = "묶음상품관리 상품 상세 조회 API 입니다. ")
    @GetMapping(path = "/product/bundle/{seq}")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.READ)
    public ApiResponseDto<ProductBundleDetailResult> findDetail(@PathVariable String seq) throws ApiException {
        ProductBundleDetailResult detail = productBundleService.findDetail(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "묶음상품관리 상품 등록 API", notes = "묶음상품관리 상품 등록 API 입니다. ")
    @PostMapping(path = "/product/bundle")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.CREATE)
    public ApiResponseDto addProduct(@Validated @RequestBody ProductBundleReq addReq, AdminAuth adminAuth) {
        addReq.setRegId(adminAuth.getId());
        productBundleService.add(addReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "묶음상품관리 상품 정보 변경", notes = "묶음상품관리 상품 정보 변경 API 입니다. ")
    @PutMapping(path = "/product/bundle/{seq}")
    @MenuValidApi(menuCode = "PRD001", operation = CrudOperation.UPDATE)
    public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody ProductBundleReq modReq, AdminAuth adminAuth) throws ApiException {
        modReq.setSeq(seq);
        modReq.setModId(adminAuth.getId());
        productBundleService.modify(modReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
