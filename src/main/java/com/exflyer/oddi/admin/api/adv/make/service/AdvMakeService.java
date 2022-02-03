package com.exflyer.oddi.admin.api.adv.make.service;

import com.exflyer.oddi.admin.api.adv.make.code.AdvAuditCodes;
import com.exflyer.oddi.admin.api.adv.make.dao.AdvMakeMapper;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeBasicInfoResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeDetailResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeDetailSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeExpoResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeFilesResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeSearchCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.PartnerConfigRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdvMakeService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private AdvMakeMapper advMakeMapper;
    @Autowired
    private PartnerConfigRepository partnerConfigRepository;

    public AdvMakeSearchCodes findCodeForSearching() {
        List<Code> advChannelCode = codeRepository.findByGroupCodeAndUsable("PTT", true);
        List<Code> displayDivCode = codeRepository.findByGroupCodeAndUsable("DPD", true);
        List<Code> sideDisplayServiceCode = codeRepository.findByGroupCodeAndUsable("DSC", true);
        List<Code> bottomDisplayServiceCode = codeRepository.findByGroupCodeAndUsable("BDT", true);
        return new AdvMakeSearchCodes(advChannelCode, displayDivCode, sideDisplayServiceCode,
            bottomDisplayServiceCode);
    }

    public PagingResult<AdvMakeListSearchResult> findBySearchReq(AdvMakeListSearchReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setAuditApprovalCode(AdvAuditCodes.ADV_APPROVAL.getCode());
        searchReq.setProgressPaymentCompleteCode(AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "p.seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<AdvMakeListSearchResult> result = advMakeMapper.findBySearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }

    public AdvMakeDetailResult findDetail(Long seq, String deviceId) throws ApiException {

        AdvMakeDetailSearchReq advMakeDetailSearchReq = new AdvMakeDetailSearchReq();
        List<AdvMakeExpoResult> findDetailAdvDetailNowExpoList = null;
        List<AdvMakeFilesResult> findDetailAdvDetailNowExpoFileList = null;
        List<AdvMakeExpoResult> findDetailAdvDetailWaitExpoList = null;
        List<AdvMakeFilesResult> findDetailAdvDetailWaitExpoFileList = null;

        if (deviceId.isEmpty() || deviceId == null || deviceId.equals("null")) {
            deviceId = null;
        }

        advMakeDetailSearchReq.setSeq(seq);
        advMakeDetailSearchReq.setDeviceId(StringUtils.stripToEmpty(deviceId));
        advMakeDetailSearchReq.setNowDate(LocalDateUtils.krNowByFormatter("yyyyMMdd"));
        advMakeDetailSearchReq.setAuditApprovalCode(AdvAuditCodes.ADV_APPROVAL.getCode());
        advMakeDetailSearchReq.setProgressPaymentCompleteCode(AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        advMakeDetailSearchReq.setOddiChannelType(ChannelCodes.ODDI_ZONE.getCode());

        AdvMakeBasicInfoResult findDetailAdvBasicInfo = advMakeMapper.findDetailAdvBasicInfo(advMakeDetailSearchReq);
        advMakeDetailSearchReq.setOddiChannelType(findDetailAdvBasicInfo.getChannelType());
        findDetailAdvBasicInfo.setNowDate(LocalDateUtils.krNow());

        if(ChannelCodes.ODDI_ZONE.getCode().equals(findDetailAdvBasicInfo.getChannelType())){
            if(deviceId != null) findDetailAdvDetailNowExpoList = advMakeMapper.findDetailNowExpoAdvList(advMakeDetailSearchReq);
            if(deviceId != null) findDetailAdvDetailNowExpoFileList = advMakeMapper.findDetailNowExpoAdvFileList(advMakeDetailSearchReq);
            findDetailAdvDetailWaitExpoList = advMakeMapper.findDetailWaitExpoAdvList(advMakeDetailSearchReq);
            findDetailAdvDetailWaitExpoFileList = advMakeMapper.findDetailWaitExpoAdvFileList(advMakeDetailSearchReq);
        }else{
            findDetailAdvDetailNowExpoList = advMakeMapper.findSubwayDetailNowExpoAdvList(advMakeDetailSearchReq);
            findDetailAdvDetailNowExpoFileList = advMakeMapper.findSubwayDetailNowExpoAdvFileList(advMakeDetailSearchReq);
            findDetailAdvDetailWaitExpoList = advMakeMapper.findSubwayDetailWaitExpoAdvList(advMakeDetailSearchReq);
            findDetailAdvDetailWaitExpoFileList = advMakeMapper.findSubwayDetailWaitExpoAdvFileList(advMakeDetailSearchReq);
        }

        return new AdvMakeDetailResult(findDetailAdvBasicInfo, findDetailAdvDetailNowExpoList,
            findDetailAdvDetailWaitExpoList, findDetailAdvDetailNowExpoFileList,
            findDetailAdvDetailWaitExpoFileList);
    }

}
