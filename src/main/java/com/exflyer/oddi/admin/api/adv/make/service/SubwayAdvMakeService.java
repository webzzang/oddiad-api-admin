package com.exflyer.oddi.admin.api.adv.make.service;

import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeModReq;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.AdvFile;
import com.exflyer.oddi.admin.models.AdvPartner;
import com.exflyer.oddi.admin.models.PartnerDefaultAdv;
import com.exflyer.oddi.admin.repository.AdvFileRepository;
import com.exflyer.oddi.admin.repository.AdvPartnerRepository;
import com.exflyer.oddi.admin.repository.PartnerDefailtAdvRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class SubwayAdvMakeService {
    @Autowired
    private PartnerDefailtAdvRepository partnerDefailtAdvRepository;
    @Autowired
    private AdvPartnerRepository advPartnerRepository;
    @Autowired
    private AdvFileRepository advFileRepository;
    @Autowired
    private FileService fileService;

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(AdvMakeModReq advMakeModReq) throws ApiException {
        Optional<PartnerDefaultAdv> partnerDefaultAdvOptional = partnerDefailtAdvRepository.findByChannelTypeByPartnerSeq(advMakeModReq.getSeq());
        PartnerDefaultAdv partnerDefaultAdv = partnerDefaultAdvOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerDefaultAdv.setExpo(advMakeModReq.getDefaultAdvExpo());
        partnerDefailtAdvRepository.save(partnerDefaultAdv);

        advMakeModReq.getAdvExpoList().forEach(datas -> {
            Optional<AdvPartner> advOptional = advPartnerRepository.findByAdvSeqByPartnerSeq(datas.getAdvSeq(), advMakeModReq.getSeq());
            try {
                AdvPartner advPartner = advOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                advPartner.setViewOrder(datas.getViewOrder());
                advPartner.setExpo(datas.getExpo());
                advPartnerRepository.save(advPartner);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        });

        List<AdvFile> newFileSeqItems = advMakeModReq.getAdvFileList();

        List<Long> l_newFileSeqItems = new ArrayList<>();
        newFileSeqItems.forEach(datas -> {
            l_newFileSeqItems.add(datas.getFileSeq());
        });

        // 변경전 fileSeq추출
        List<Long> isNotInFileSeq = advFileRepository
            .isNotInFileSeq(advMakeModReq.getSeq(), l_newFileSeqItems);

        advMakeModReq.getAdvFileList().forEach(datas -> {
            advFileRepository.deleteAdvFile(datas.getAdvSeq());
        });

        advFileRepository.saveAll(newFileSeqItems);

        fileService.updateMappingDone(l_newFileSeqItems);

        if (isNotInFileSeq != null) {
            fileService.delete(isNotInFileSeq);
        }
    }
}
