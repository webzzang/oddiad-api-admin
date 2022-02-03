package com.exflyer.oddi.admin.api.product.partnerConfig.service;

import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.product.partnerConfig.dao.PartnerConfigMapper;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigDetailResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneDefaultFileReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneDefaultReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneSearchCodes;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.PartnerConfigDetailResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.PartnerDefaultAdvFileResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.SubwayConfigDetailResult;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.PartnerConfig;
import com.exflyer.oddi.admin.models.PartnerDefaultAdvFiles;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.PartnerConfigRepository;
import com.exflyer.oddi.admin.repository.PartnerDefailtAdvFilesRepository;
import com.exflyer.oddi.admin.repository.PartnerDefailtAdvRepository;
import com.exflyer.oddi.admin.repository.PartnerDeviceRepository;
import com.exflyer.oddi.admin.repository.PartnerRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
public class PartnerConfigService {

    @Autowired
    private PartnerConfigRepository partnerConfigRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerDeviceRepository partnerDeviceRepository;
    @Autowired
    private PartnerDefailtAdvFilesRepository partnerDefailtAdvFilesRepository;
    @Autowired
    private PartnerConfigMapper partnerConfigMapper;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private FileService fileService;

    public OddiZoneSearchCodes findCodeForSearching() {
        List<Code> displayDivCode = codeRepository.findByGroupCodeAndUsable("DPD", true);
        List<Code> sideDisplayServiceCode = codeRepository.findByGroupCodeAndUsable("DSC", true);
        List<Code> bottomDisplayServiceCode = codeRepository.findByGroupCodeAndUsable("BDT", true);
        return new OddiZoneSearchCodes(displayDivCode, sideDisplayServiceCode, bottomDisplayServiceCode);
    }

    public OddiZoneConfigDetailResult detail(String type) throws ApiException {
        PartnerConfigDetailResult detail = partnerConfigMapper.detail(type);
        if (detail == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }
        List<PartnerDefaultAdvFileResult> fileList = partnerConfigMapper.findDefaultAdvFiles(type);
        return new OddiZoneConfigDetailResult(detail, fileList);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(OddiZoneConfigReq modReq) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(modReq.getType());
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        if(modReq.getSlotCount() != null)partnerConfig.setSlotCount(modReq.getSlotCount());
        if(modReq.getSlotVideoTime() != null)partnerConfig.setSlotVideoTime(modReq.getSlotVideoTime());
        if(modReq.getOddiAdvFromStartDate() != null && modReq.getOddiAdvToStartDate() != null){
            partnerConfig.setOddiAdvFromStartDate(modReq.getOddiAdvFromStartDate());
            partnerConfig.setOddiAdvToStartDate(modReq.getOddiAdvToStartDate());
        }
        if(modReq.getOddiAdvMaxDate() != null)partnerConfig.setOddiAdvMaxDate(modReq.getOddiAdvMaxDate());
        if(modReq.getOddiAdvCancelDate() != null)partnerConfig.setOddiAdvCancelDate(modReq.getOddiAdvCancelDate());
        if(modReq.getSubwayAdvLastDate() != null)partnerConfig.setSubwayAdvLastDate(modReq.getSubwayAdvLastDate());
        if(modReq.getSubwayAdvMaxStartDate() != null)partnerConfig.setSubwayAdvMaxStartDate(modReq.getSubwayAdvMaxStartDate());
        if(modReq.getSubwayAdvCancelDate() != null)partnerConfig.setSubwayAdvCancelDate(modReq.getSubwayAdvCancelDate());
        if(modReq.getSubwayAdvMaxDate() != null)partnerConfig.setSubwayAdvMaxDate(modReq.getSubwayAdvMaxDate());
        if(modReq.getDesignRequest() != null)partnerConfig.setDesignRequest(modReq.getDesignRequest());
        if(StringUtil.isNotBlank(modReq.getAdvName())){
            partnerConfig.setAdvName(modReq.getAdvName());
        }
        if(StringUtil.isNotBlank(modReq.getDisplayDiv()) && StringUtil.isNotBlank(modReq.getSideDisplayServiceCode())){
            partnerConfig.setDisplayDiv(modReq.getDisplayDiv());
            partnerConfig.setSideDisplayServiceCode(modReq.getSideDisplayServiceCode());
        }
        if(StringUtil.isNotBlank(modReq.getOperationStartTime()) && StringUtil.isNotBlank(modReq.getOperationEndTime())){
            partnerConfig.setOperationStartTime(modReq.getOperationStartTime());
            partnerConfig.setOperationEndTime(modReq.getOperationEndTime());
        }
        partnerConfigRepository.save(partnerConfig);

        if(modReq.getSlotCount() != null)partnerRepository.saveSlotCount(modReq.getSlotCount(), modReq.getType());
        if(modReq.getSlotVideoTime() != null)partnerRepository.saveSlotVideoTime(modReq.getSlotVideoTime(), modReq.getType());
        if(StringUtil.isNotBlank(modReq.getOperationStartTime()) && StringUtil.isNotBlank(modReq.getOperationEndTime()))
            partnerRepository.saveOperationTime(modReq.getOperationStartTime(), modReq.getOperationEndTime(), modReq.getType());

        if(StringUtil.isNotBlank(modReq.getDisplayDiv()) && StringUtil.isNotBlank(modReq.getSideDisplayServiceCode()))
            partnerDeviceRepository.saveConfig(modReq.getDisplayDiv(), modReq.getSideDisplayServiceCode(), "image");


        if(ChannelCodes.ODDI_ZONE.getCode().equals(modReq.getType()) && modReq.getDefaultAdvFileList() != null) {
            partnerDefailtAdvFilesRepository.deleteAll();

            int cnt = 1;
            for(OddiZoneDefaultFileReq oddiZoneDefaultFileReq : modReq.getDefaultAdvFileList()){
                PartnerDefaultAdvFiles partnerDefaultAdvFiles = new PartnerDefaultAdvFiles();

                partnerDefaultAdvFiles.setChannelType(ChannelCodes.ODDI_ZONE.getCode());
                partnerDefaultAdvFiles.setDefaultAdvFileSeq(oddiZoneDefaultFileReq.getDefaultAdvFileSeq());
                partnerDefaultAdvFiles.setDefaultAdvType(oddiZoneDefaultFileReq.getDefaultAdvType());
                partnerDefaultAdvFiles.setViewOrder(cnt);

                partnerDefailtAdvFilesRepository.save(partnerDefaultAdvFiles);
                cnt++;

                fileService.updateMappingDone(Arrays.asList(oddiZoneDefaultFileReq.getDefaultAdvFileSeq()));
            }

            List<Long> l_newFileSeqItems = new ArrayList<>();
            modReq.getDefaultAdvFileList().forEach(datas -> {
                l_newFileSeqItems.add(datas.getDefaultAdvFileSeq());
            });

            List<Long> isNotInFileSeq = partnerDefailtAdvFilesRepository.isNotInFileSeq(l_newFileSeqItems);

            if(isNotInFileSeq != null){
                fileService.delete(isNotInFileSeq);
            }
        }
    }

    public SubwayConfigDetailResult subwayDetail(String type) throws ApiException {
        SubwayConfigDetailResult detail = partnerConfigMapper.subwayDetail(type);
        if (detail == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }
        return detail;
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void slotCountSave(Integer slotCount,String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        partnerConfigRepository.updateSlotCountSave(slotCount, type);
        partnerRepository.saveSlotCount(slotCount, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void slotVideoTimeSave(Integer slotVideoTime,String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateSlotVideoTimeSave(slotVideoTime, type);
        partnerRepository.saveSlotVideoTime(slotVideoTime, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void oddiAdvFromStartDateSave(Integer oddiAdvFromStartDate,Integer oddiAdvToStartDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateOddiAdvFromStartDateSave(oddiAdvFromStartDate, oddiAdvToStartDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void oddiAdvMaxDateSave(Integer oddiAdvMaxDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateOddiAdvMaxDateSave(oddiAdvMaxDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void oddiAdvCancelDateSave(Integer oddiAdvCancelDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateOddiAdvCancelDateSave(oddiAdvCancelDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void subwayAdvLastDateSave(Integer subwayAdvLastDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateSubwayAdvLastDateSave(subwayAdvLastDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void subwayAdvMaxStartDateSave(Integer subwayAdvMaxStartDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateSubwayAdvMaxStartDateSave(subwayAdvMaxStartDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void subwayAdvCancelDateSave(Integer subwayAdvCancelDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateSubwayAdvCancelDateSave(subwayAdvCancelDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void subwayAdvMaxDateSave(Integer subwayAdvMaxDate, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateSubwayAdvMaxDateSave(subwayAdvMaxDate, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void designRequestSave(Boolean designRequest, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateDesignRequestSave(designRequest, type);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void advDefaultSave(OddiZoneDefaultReq modReq) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(modReq.getType());
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        if(ChannelCodes.ODDI_ZONE.getCode().equals(modReq.getType()) && modReq.getDefaultAdvFileList() != null) {
            partnerDefailtAdvFilesRepository.deleteAll();

            int cnt = 1;
            for(OddiZoneDefaultFileReq oddiZoneDefaultFileReq : modReq.getDefaultAdvFileList()){
                PartnerDefaultAdvFiles partnerDefaultAdvFiles = new PartnerDefaultAdvFiles();

                partnerDefaultAdvFiles.setChannelType(ChannelCodes.ODDI_ZONE.getCode());
                partnerDefaultAdvFiles.setDefaultAdvFileSeq(oddiZoneDefaultFileReq.getDefaultAdvFileSeq());
                partnerDefaultAdvFiles.setDefaultAdvType(oddiZoneDefaultFileReq.getDefaultAdvType());
                partnerDefaultAdvFiles.setViewOrder(cnt);

                partnerDefailtAdvFilesRepository.save(partnerDefaultAdvFiles);
                cnt++;

                fileService.updateMappingDone(Arrays.asList(oddiZoneDefaultFileReq.getDefaultAdvFileSeq()));
            }

            List<Long> l_newFileSeqItems = new ArrayList<>();
            modReq.getDefaultAdvFileList().forEach(datas -> {
                l_newFileSeqItems.add(datas.getDefaultAdvFileSeq());
            });

            List<Long> isNotInFileSeq = partnerDefailtAdvFilesRepository.isNotInFileSeq(l_newFileSeqItems);

            if(isNotInFileSeq != null){
                fileService.delete(isNotInFileSeq);
            }
        }
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void displayDivSave(String displayDiv, String sideDisplayServiceCode, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateDisplayDivSave(displayDiv, sideDisplayServiceCode, "image", type);
        partnerDeviceRepository.saveConfig(displayDiv, sideDisplayServiceCode, "image");
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void operationStartTimeSave(String operationStartTime, String operationEndTime, String type) throws ApiException {

        Optional<PartnerConfig> optional = partnerConfigRepository.findById(type);
        PartnerConfig partnerConfig = optional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerConfigRepository.updateOperationStartTimeSave(operationStartTime,operationEndTime, type);
        partnerRepository.saveOperationTime(operationStartTime, operationEndTime, type);
    }
}
