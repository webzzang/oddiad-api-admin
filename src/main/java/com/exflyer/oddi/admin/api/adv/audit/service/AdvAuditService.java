package com.exflyer.oddi.admin.api.adv.audit.service;

import com.exflyer.oddi.admin.api.adv.audit.code.AdvRequestCodes;
import com.exflyer.oddi.admin.api.adv.audit.dao.AdvAuditMapper;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditDetailResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditFileResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchReq;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditModReq;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditPartnerResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditSearchCodes;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvRejectListResult;
import com.exflyer.oddi.admin.api.adv.make.code.AdvAuditCodes;
import com.exflyer.oddi.admin.api.external.payment.PaymentService;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelRes;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Adv;
import com.exflyer.oddi.admin.models.AdvFile;
import com.exflyer.oddi.admin.models.AdvRejectLog;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.AdvFileRepository;
import com.exflyer.oddi.admin.repository.AdvRejectLogRepository;
import com.exflyer.oddi.admin.repository.AdvRepository;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class AdvAuditService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private AdvAuditMapper advAuditMapper;
    @Autowired
    private AdvRepository advRepository;
    @Autowired
    private AdvRejectLogRepository advRejectLogRepository;
    @Autowired
    private AdvFileRepository advFileRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private PaymentService paymentService;

    public AdvAuditSearchCodes findCodeForSearching() {
        List<Code> advChannelCode = codeRepository.findByGroupCodeAndUsable("PTT", true);
        List<Code> auditCode = codeRepository.findByGroupCodeAndUsable("ADT", true);
        List<Code> rejectionCode = codeRepository.findByGroupCodeAndUsable("RJT", true);
        List<Code> designCode = codeRepository.findByGroupCodeAndUsable("AFT", true);
        List<Code> progressCode = codeRepository.findByGroupCodeAndUsableAndVal("PGT", true, "1");
        return new AdvAuditSearchCodes(advChannelCode, auditCode, rejectionCode, designCode,
            progressCode);
    }

    public PagingResult<AdvAuditListSearchResult> findBySearchReq(AdvAuditListSearchReq searchReq) {

        searchReq.setAuditWaitCode(AdvAuditCodes.ADV_WAITING.getCode());
        searchReq.setPaymentCompleteCode(AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        searchReq.setPaymentCancelCode(AdvAuditCodes.PROGRESS_PAYMENT_CANCEL.getCode());

        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "a.seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<AdvAuditListSearchResult> result = advAuditMapper.findBySearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }

    public AdvAuditDetailResult findDetail(Long seq) throws ApiException {
        AdvAuditResult findDetailAdvBasicInfo = advAuditMapper.findDetailAdvBasicInfo(seq);

        findDetailAdvBasicInfo.setNowTime(LocalDateUtils.krNowByFormatter("yyyy-MM-dd HH:mm:ss"));

        if (findDetailAdvBasicInfo == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }

        List<AdvAuditPartnerResult> findDetailAdvPartnerList = advAuditMapper.findDetailAdvPartnerList(seq, ChannelCodes.ODDI_ZONE.getCode());
        List<AdvAuditFileResult> findDetailAdvFileList = advAuditMapper.findDetailAdvFileList(seq);
        List<AdvRejectListResult> rejectionList = advAuditMapper.findDetailRejectionList(seq);
        return new AdvAuditDetailResult(findDetailAdvBasicInfo, findDetailAdvPartnerList,findDetailAdvFileList, rejectionList);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(AdvAuditModReq advAuditModReq) throws ApiException {
        saveAdv(advAuditModReq);
    }

    private void saveAdv(AdvAuditModReq advAuditModReq) throws ApiException {
        // 광고심사정보(메모) 수정
        Optional<Adv> advAuditOptional = advRepository.findById(advAuditModReq.getSeq());
        Adv adv = advAuditOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        adv.setModifyReq(advAuditModReq);
        advRepository.save(adv);

        // 광고심사 파일 수정
        // 기존 파일 정보 조회
        List<AdvFile> beforeFileItems = advFileRepository.findByAdvSeq(advAuditModReq.getSeq());
    /*List<Long> l_beforeFileItems = advFileRepository.findAllByFileSeq(advAuditModReq.getSeq());

    List<Long> l_beforeFileItems = new ArrayList<>();
    beforeFileItems.forEach(datas -> {
      l_beforeFileItems.add(datas.getFileSeq());
    });*/

        // 등록하려는 파일정보
        List<AdvFile> newFileSeqItems = advAuditModReq.getAdvFileList();

        List<Long> l_newFileSeqItems = new ArrayList<>();
        newFileSeqItems.forEach(datas -> {
            l_newFileSeqItems.add(datas.getFileSeq());
        });

        // 변경전 fileSeq추출
        List<Long> isNotInFileSeq = advFileRepository
            .isNotInFileSeq(advAuditModReq.getSeq(), l_newFileSeqItems);

        // adv_file 삭제
        advFileRepository.deleteAll(beforeFileItems);

        // 파일 등록
        //saveAdvFile(newFileSeqItems);
        advFileRepository.saveAll(newFileSeqItems);  // 새로 등록

        // 매핑여부 변경
        fileService.updateMappingDone(l_newFileSeqItems);

        // S3, files 삭제
        if (isNotInFileSeq != null) {
            fileService.delete(isNotInFileSeq);
        }
    }

    public void saveAdvFile(List<AdvFile> newFileSeqItems) {
        advFileRepository.saveAll(newFileSeqItems);  // 새로 등록
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void audit(AdvAuditModReq advAuditModReq) throws ApiException, IOException {

        saveAdv(advAuditModReq);

        Optional<Adv> advAuditOptional = advRepository.findById(advAuditModReq.getSeq());
        Adv adv = advAuditOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        // 승인
        if (advAuditModReq.getAuditEnum() == AdvRequestCodes.ADV_APPROVAL.getReqCode()) {
            advAuditModReq.setAuditCode(AdvRequestCodes.ADV_APPROVAL.getCode());
            adv.setAuditReq(advAuditModReq);
            adv.setUserCheck(false);
            // 보류
        } else if (advAuditModReq.getAuditEnum() == AdvRequestCodes.ADV_REJECTION.getReqCode()) {
            advAuditModReq.setAuditCode(AdvRequestCodes.ADV_REJECTION.getCode());
            adv.setRejectionReq(advAuditModReq);
            adv.setUserCheck(false);
        } else {
            throw new ApiException(ApiResponseCodes.MISS_MATCH);
        }

        advRepository.save(adv);

        //보류 로그
        if (advAuditModReq.getAuditEnum() == AdvRequestCodes.ADV_REJECTION.getReqCode()) {
            AdvRejectLog advRejectLog = new AdvRejectLog();
            advRejectLog.setAdvSeq(advAuditModReq.getSeq());
            advRejectLog.setAdvRegDate(adv.getRegDate());
            advRejectLog.setRejectionCode(advAuditModReq.getRejectionCode());
            advRejectLog.setRejectionReason(advAuditModReq.getRejectionReason());
            advRejectLog.setRegDate(LocalDateUtils.krNow());
            advRejectLog.setRegId(advAuditModReq.getAuditId());
            advRejectLogRepository.save(advRejectLog);
        }


    }

    public InicisCancelRes paymentCancel(Long advSeq, String memberId, String regId) throws Exception {
        return paymentService.paymentCancel(null, advSeq, memberId, regId);
    }
}
