package com.exflyer.oddi.admin.api.partner.service;

import com.exflyer.oddi.admin.api.adv.make.service.OddiAdvMakeService;
import com.exflyer.oddi.admin.api.external.geography.GeographyService;
import com.exflyer.oddi.admin.api.external.geography.dto.GridConvertResult;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.partner.dao.OddiZonePartnerMapper;
import com.exflyer.oddi.admin.api.partner.dto.OddiZoneAddReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerDetailResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerDeviceResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerFileResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerSearchCodes;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerSubwayResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerTagResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZoneSubwayDetail;
import com.exflyer.oddi.admin.api.partner.dto.PartnerDeviceAddReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.DeviceInfo;
import com.exflyer.oddi.admin.models.Partner;
import com.exflyer.oddi.admin.models.PartnerConfig;
import com.exflyer.oddi.admin.models.PartnerDefaultAdv;
import com.exflyer.oddi.admin.models.PartnerDevice;
import com.exflyer.oddi.admin.models.PartnerImage;
import com.exflyer.oddi.admin.models.PartnerSubway;
import com.exflyer.oddi.admin.models.PartnerTags;
import com.exflyer.oddi.admin.models.Tags;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.DeviceInfoRepository;
import com.exflyer.oddi.admin.repository.PartnerConfigRepository;
import com.exflyer.oddi.admin.repository.PartnerDefailtAdvRepository;
import com.exflyer.oddi.admin.repository.PartnerDeviceRepository;
import com.exflyer.oddi.admin.repository.PartnerImageRepository;
import com.exflyer.oddi.admin.repository.PartnerRepository;
import com.exflyer.oddi.admin.repository.PartnerSubwayRepository;
import com.exflyer.oddi.admin.repository.PartnerTagsRepository;
import com.exflyer.oddi.admin.repository.TagsRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class OddiZonePartnerService {

  @PersistenceContext
  EntityManager em;

  @Autowired
  private CodeRepository codeRepository;
  @Autowired
  private OddiZonePartnerMapper oddiZonePartnerMapper;
  @Autowired
  private PartnerRepository partnerRepository;
  @Autowired
  private PartnerDefailtAdvRepository partnerDefailtAdvRepository;
  @Autowired
  private FileService fileService;
  @Autowired
  private GeographyService geographyService;
  @Autowired
  private PartnerImageRepository partnerImageRepository;
  @Autowired
  private PartnerDeviceRepository partnerDeviceRepository;
  @Autowired
  private PartnerSubwayRepository partnerSubwayRepository;
  @Autowired
  private PartnerTagsRepository partnerTagsRepository;
  @Autowired
  private TagsRepository tagsRepository;
  @Autowired
  private DeviceInfoRepository deviceInfoRepository;
  @Autowired
  private OddiAdvMakeService oddiAdvMakeService;
  @Autowired
  private PartnerConfigRepository partnerConfigRepository;

  public OddiZonePartnerSearchCodes findCodeForSearching() {
    List<Code> operationCode = codeRepository.findByGroupCodeAndUsable("OPT", true);
    List<Code> badgeCode = codeRepository.findByGroupCodeAndUsable("BDG", true);
    List<Code> showCode = codeRepository.findByGroupCodeAndUsable("UST", true);
    List<Code> subwayLineCode = codeRepository.findByGroupCodeAndUsable("SLT", true);
    return new OddiZonePartnerSearchCodes(operationCode, badgeCode, showCode, subwayLineCode);
  }

  public PagingResult<OddiZonePartnerListSearchResult> findBySearchReq(OddiZonePartnerListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "p.seq desc"));
    PageHelper.orderBy(searchReq.getOrderBy());

    Page<OddiZonePartnerListSearchResult> result = oddiZonePartnerMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public OddiZonePartnerDetailResult findDetail(Long seq) throws ApiException {
    OddiZonePartnerResult oddiZonePartnerDetailResult = oddiZonePartnerMapper.findDetailOddiZoneBasicInfo(seq);

    if (oddiZonePartnerDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }

    List<OddiZonePartnerTagResult> oddiZonePartnerTagResults = oddiZonePartnerMapper.findDetailOddiZoneTagList(seq);
    List<OddiZonePartnerFileResult> oddiZonePartnerFileResults = oddiZonePartnerMapper.findDetailOddiZoneFileList(seq);

    OddiZonePartnerDetailResult returnOddiZonePartnerDetailResult;

    if(oddiZonePartnerDetailResult.getChannelType().equals(ChannelCodes.ODDI_ZONE.getCode())){
      List<OddiZonePartnerDeviceResult> oddiZonePartnerDeviceResults = oddiZonePartnerMapper.findDetailOddiZoneDeviceList(seq);
      returnOddiZonePartnerDetailResult = new OddiZonePartnerDetailResult(oddiZonePartnerDetailResult, oddiZonePartnerTagResults, oddiZonePartnerFileResults, oddiZonePartnerDeviceResults, null);
    }else{
      List<OddiZonePartnerSubwayResult> oddiZonePartnerSubwayResults = oddiZonePartnerMapper.findDetailOddiZoneSubwayList(seq);
      returnOddiZonePartnerDetailResult = new OddiZonePartnerDetailResult(oddiZonePartnerDetailResult, oddiZonePartnerTagResults, oddiZonePartnerFileResults, null, oddiZonePartnerSubwayResults);
    }

    return returnOddiZonePartnerDetailResult;
  }

  public Map<String, Integer> isAddDeviceInfo(OddiZoneAddReq oddiZoneAddReq){

    Map<String, Integer> returnMap = new HashMap<>();
    /*List<String> deviceId = new ArrayList<>();

    oddiZoneAddReq.getDeviceId().forEach(datas -> {
      deviceId.add(datas.getDeviceId());
    });*/

    if(oddiZoneAddReq.getDeviceId() != null && oddiZoneAddReq.getDeviceId().size() > 0){
        returnMap = oddiZonePartnerMapper.countDeviceInfo(oddiZoneAddReq.getDeviceId());
    }else{
        returnMap = null;
    }

    return returnMap;
  }

  public List<String> isAddPartnerDevice(OddiZoneAddReq oddiZoneAddReq){
    List<String> partnerDeviceList = new ArrayList<>();

    if(oddiZoneAddReq.getDeviceId() != null && oddiZoneAddReq.getDeviceId().size() > 0) {
      oddiZoneAddReq.getDeviceId().forEach(datas -> {
        String partnerDevice = partnerDeviceRepository.findAllByPartnerDevice(datas.getDeviceId().toUpperCase());
        if(StringUtil.isNotBlank(partnerDevice))partnerDeviceList.add(partnerDevice);
      });
    }

    return partnerDeviceList;
  }

  public List<String> isAddPartnerDeviceInfo(OddiZoneAddReq oddiZoneAddReq){
    List<String> deviceList = new ArrayList<>();

    if(oddiZoneAddReq.getDeviceId() != null && oddiZoneAddReq.getDeviceId().size() > 0) {
      oddiZoneAddReq.getDeviceId().forEach(datas -> {
        DeviceInfo deviceInfo = deviceInfoRepository.findAllByPartnerDevice(datas.getDeviceId().toUpperCase());

        if(deviceInfo == null){
          deviceList.add(datas.getDeviceId().toUpperCase());
        }
      });
    }

    return deviceList;
  }


  public Map<String, Integer> isModifyDeviceInfo(OddiZoneAddReq oddiZoneAddReq){

    Map<String, Integer> returnMap;
    /*List<String> deviceAddReqs = new ArrayList();

    oddiZoneAddReq.getDeviceId().forEach(datas -> {
      deviceAddReqs.add(datas.getDeviceId());
    });*/

    // 기존 partner_device 정보조회
    //List<PartnerDevice> partnerDevicesItems = partnerDeviceRepository.isNotInPartnerDeviceList(oddiZoneAddReq.getSeq(), deviceAddReqs);
    List<PartnerDeviceAddReq> partnerDeviceAddReqs = new ArrayList<>();

    if(oddiZoneAddReq.getDeviceId() != null && oddiZoneAddReq.getDeviceId().size() > 0) {
      oddiZoneAddReq.getDeviceId().forEach(items -> {

        int isNotInPartnerSeq = partnerDeviceRepository.isNotInPartnerSeq(oddiZoneAddReq.getSeq(), items.getDeviceId().toUpperCase());

        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setName(items.getName());
        partnerDeviceAddReq.setDeviceId(items.getDeviceId().toUpperCase());
        if (isNotInPartnerSeq == 0)
          partnerDeviceAddReqs.add(partnerDeviceAddReq);

      });
    }


    if(partnerDeviceAddReqs.size() > 0){
      returnMap = oddiZonePartnerMapper.countDeviceInfo(partnerDeviceAddReqs);
    }else{
      returnMap = null;
    }


    return returnMap;
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void save(OddiZoneAddReq oddiZoneAddReq) {

    Partner partner = new Partner(oddiZoneAddReq);

    //저장시 주소로 위도, 경도, 좌표 변환해서 dto에 set 하는로직 추가
    GridConvertResult gridConvertResult = geographyService.findXyByAddr(oddiZoneAddReq.getAddr());

    partner.setLatitude(gridConvertResult.getLat());
    partner.setLongitude(gridConvertResult.getLng());
    partner.setGridX(gridConvertResult.getX());
    partner.setGridY(gridConvertResult.getY());
//    partner.setAddrSi(oddiZoneAddReq.getAddrSi());
//    partner.setAddrGu(oddiZoneAddReq.getAddrGu());
//    partner.setAddrDong(oddiZoneAddReq.getAddrDong());

    // partner기본 등록
    partnerRepository.save(partner);
    em.persist(partner);

    //오디존만 기본파일노출여부 파일 테이블 등록
    if(oddiZoneAddReq.getChannelType().equals(ChannelCodes.ODDI_ZONE.getCode())) {
      PartnerDefaultAdv partnerDefaultAdv = new PartnerDefaultAdv();
      partnerDefaultAdv.setChannelType(partner.getChannelType());
      partnerDefaultAdv.setPartnerSeq(partner.getSeq());
      partnerDefaultAdv.setExpo(true);
      partnerDefailtAdvRepository.save(partnerDefaultAdv);
    }

    // partner_image 등록
    addPartnerImage(oddiZoneAddReq, partner);
    // 매핑여부 변경
    fileService.updateMappingDone(oddiZoneAddReq.getFileSeq());

    // tags등록
    addPartnerTags(oddiZoneAddReq, partner);

    // partner_device 등록
    if(oddiZoneAddReq.getChannelType().equals(ChannelCodes.ODDI_ZONE.getCode())){
      addPartnerDevice(oddiZoneAddReq, partner);
    }else{
      // partner_subway 등록
      addPartnerSubway(oddiZoneAddReq, partner);
    }

  }



  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void modify(OddiZoneAddReq oddiZoneAddReq) throws ApiException {

    //저장시 주소로 위도, 경도, 좌표 변환해서 dto에 set 하는로직 추가
    GridConvertResult gridConvertResult = geographyService.findXyByAddr(oddiZoneAddReq.getAddr());

    // partner기본 저장
    Optional<Partner> partnerOptional = partnerRepository.findById(oddiZoneAddReq.getSeq());
    Partner partner = partnerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    partner.setLatitude(gridConvertResult.getLat());
    partner.setLongitude(gridConvertResult.getLng());
    partner.setGridX(gridConvertResult.getX());
    partner.setGridY(gridConvertResult.getY());
//    partner.setAddrSi(oddiZoneAddReq.getAddrSi());
//    partner.setAddrGu(oddiZoneAddReq.getAddrGu());
//    partner.setAddrDong(oddiZoneAddReq.getAddrDong());
    partner.setModifyPartner(oddiZoneAddReq);
    partnerRepository.save(partner);

    //기존 파일 정보 조회
    List<PartnerImage> beforePartnerImgItems = partnerImageRepository.findByPartnerSeq(oddiZoneAddReq.getSeq());

    //새로운 파일 정보
    List<Long> newPartnerImgItems = oddiZoneAddReq.getFileSeq();

    // 변경전 fileSeq추출
    List<Long> isNotInFileSeq = partnerImageRepository.isNotInFileSeq(oddiZoneAddReq.getSeq(), newPartnerImgItems);

    // partner_image 삭제
    if(beforePartnerImgItems != null)partnerImageRepository.deleteAll(beforePartnerImgItems);

    // partner_image 저장
    addPartnerImage(oddiZoneAddReq, partner);

    // 매핑여부 변경
    if(newPartnerImgItems != null)fileService.updateMappingDone(newPartnerImgItems);

    // S3, files 삭제
    if(isNotInFileSeq != null){
      fileService.delete(isNotInFileSeq);
    }

    // partner_tags 정보조회
    List<PartnerTags> partnerTagsSeqItems = partnerTagsRepository.findByPartnerSeq(partner.getSeq());
    List<Tags> tagsSeqItems = tagsRepository.findBySeq(partner.getSeq());

    // partner_device 정보조회
    List<PartnerDevice> partnerDevicesItems = partnerDeviceRepository.findByPartnerSeq(partner.getSeq());

    // partner_tags 삭제
    if(partnerTagsSeqItems != null)partnerTagsRepository.deleteAll(partnerTagsSeqItems);

    // tags 삭제
    if(tagsSeqItems != null)tagsRepository.deleteAll(tagsSeqItems);

    // partner_tags, tags 저장
    addPartnerTags(oddiZoneAddReq, partner);

    if(oddiZoneAddReq.getChannelType().equals(ChannelCodes.ODDI_ZONE.getCode())) {
      // partner_device 삭제
      if (partnerDevicesItems != null)
        partnerDeviceRepository.deleteAll(partnerDevicesItems);

      // partner_device 저장
      addPartnerDevice(oddiZoneAddReq, partner);
    }else{

      // partner_subway 정보조회
      //List<PartnerSubway> partnerSubwayItems = partnerSubwayRepository.findByPartnerSeq(partner.getSeq());

      // partner_subway 삭제
      partnerSubwayRepository.deleteSubway(partner.getSeq());

      // partner_subway 등록
      addPartnerSubway(oddiZoneAddReq, partner);
    }

  }

  private void addPartnerImage(OddiZoneAddReq oddiZoneAddReq, Partner partner){
    int cnt = 1;
    if(oddiZoneAddReq.getFileSeq() != null) {
      for (Long fileSeq : oddiZoneAddReq.getFileSeq()) {

        PartnerImage partnerImage = new PartnerImage();

        partnerImage.setPartnerSeq(partner.getSeq());
        partnerImage.setFileSeq(fileSeq);
        partnerImage.setRegDate(LocalDateUtils.krNow());
        partnerImage.setViewOrder(cnt);

        partnerImageRepository.save(partnerImage);

        cnt++;

      }
    }
  }

  private void addPartnerTags(OddiZoneAddReq oddiZoneAddReq, Partner partner){
    if(oddiZoneAddReq.getTags() != null) {
      int cnt = 1;
      for (String tagName : oddiZoneAddReq.getTags()) {

        Tags tags = new Tags();
        tags.setTag(tagName);
        tags.setRegDate(LocalDateUtils.krNow());

        em.persist(tags);
        tagsRepository.save(tags);

        // partner_tags 등록
        PartnerTags partnerTags = new PartnerTags();
        partnerTags.setPartnerSeq(partner.getSeq());
        partnerTags.setTagSeq(tags.getSeq());
        partnerTags.setViewOrder(cnt);
        partnerTags.setRegDate(LocalDateUtils.krNow());

        partnerTagsRepository.save(partnerTags);
        cnt++;
      }
    }
  }

  private void addPartnerDevice(OddiZoneAddReq oddiZoneAddReq, Partner partner){

    if(oddiZoneAddReq.getDeviceId() != null) {
      oddiZoneAddReq.getDeviceId().forEach(datas -> {

        PartnerDevice partnerDevice = new PartnerDevice();
        partnerDevice.setDeviceId(datas.getDeviceId().toUpperCase());
        partnerDevice.setPartnerSeq(partner.getSeq());
        partnerDevice.setName(datas.getName());
        partnerDevice.setDisplayDiv(datas.getDisplayDiv());
        partnerDevice.setSideContentsType(datas.getSideContentsType());
        partnerDevice.setBottomContentsType(datas.getBottomContentsType());
        partnerDevice.setRegDate(LocalDateUtils.krNow());
        partnerDevice.setRegId(oddiZoneAddReq.getRegId());

        partnerDeviceRepository.save(partnerDevice);

        /*try {
          oddiAdvMakeService.devicePush(partner.getSeq(), datas.getDeviceId(), "device_create");
        } catch (IOException | ApiException e) {
          e.printStackTrace();
        }*/

      });
    }
  }

  private void addPartnerSubway(OddiZoneAddReq oddiZoneAddReq, Partner partner){

    if(oddiZoneAddReq.getSubwayList() != null) {
      oddiZoneAddReq.getSubwayList().forEach(datas -> {

        PartnerSubway partnerSubway = new PartnerSubway();
        partnerSubway.setPartnerSeq(partner.getSeq());
        partnerSubway.setSubwayCode(datas.getSubwayCode());
        partnerSubway.setRegDate(LocalDateUtils.krNow());
        partnerSubway.setRegId(oddiZoneAddReq.getRegId());

        partnerSubwayRepository.save(partnerSubway);
      });
    }
  }

  public OddiZoneSubwayDetail findSubwayOperation() throws ApiException {
    OddiZoneSubwayDetail advMakeSubwayDetail = new OddiZoneSubwayDetail();
    Optional<PartnerConfig> partnerConfigOptional = partnerConfigRepository.findById(ChannelCodes.SUBWAY.getCode());
    PartnerConfig partnerConfig = partnerConfigOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    advMakeSubwayDetail.setOperationStartTime(partnerConfig.getOperationStartTime());
    advMakeSubwayDetail.setOperationEndTime(partnerConfig.getOperationEndTime());
    return advMakeSubwayDetail;
  }

}
