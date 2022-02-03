package com.exflyer.oddi.admin.api.contents.banner.service;

import com.exflyer.oddi.admin.api.contents.banner.code.BannerCodes;
import com.exflyer.oddi.admin.api.contents.banner.dao.BannerMapper;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerDetailResult;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerRegReq;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerSearchCodes;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBanner;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBannerResult;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Banner;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.BannerRepository;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private FileService fileService;

    public BannerSearchCodes findCodeForSearching() {
        List<Code> bannerUsableCode = codeRepository.findByGroupCodeAndUsable("CUT", true);
        return new BannerSearchCodes(bannerUsableCode);
    }

    public PagingResult<SearchBannerResult> findSearchSideBanner(SearchBanner searchReq){
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "reg_date desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<SearchBannerResult> result = bannerMapper.findSearchSideBanner(searchReq);
        return PagingResult.createResultDto(result);
    }

    public BannerDetailResult findDetail(Long seq) throws ApiException {
        BannerDetailResult bannerDetailResult = bannerMapper.findByBannerByFile(seq);
        return bannerDetailResult;
    }

    public void save(BannerRegReq regReq) throws ApiException {
        Banner banner = new Banner(regReq);

        findBySitePopupExpoDate(regReq);

        bannerRepository.save(banner);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(BannerRegReq modReq) throws ApiException {
        Optional<Banner> bannerOption = bannerRepository.findById(modReq.getSeq());
        Banner banner = bannerOption.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        Long beforeFileSeq = banner.getFileSeq();
        Long afterFileSeq = modReq.getFileSeq();

        banner.setModifyBanner(modReq);

        findBySitePopupExpoDate(modReq);

        bannerRepository.save(banner);

        if(afterFileSeq != null) {
            if (!afterFileSeq.equals(beforeFileSeq)) {
                fileService.updateMappingDone(Arrays.asList(afterFileSeq));
                fileService.delete(Arrays.asList(beforeFileSeq));
            }
        }

    }

    private void findBySitePopupExpoDate(BannerRegReq regReq) throws ApiException {
        if(regReq.getLocationCode().equals(BannerCodes.SITE_POPUP.getCode())){
            if(bannerMapper.findBySitePopupExpoDate(regReq) > 0){
                throw new ApiException(ApiResponseCodes.DUPLICATE_SITE_POPUP_EXPO);
            }
        }
    }

}
