package com.exflyer.oddi.admin.api.partner.dao;

import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerDeviceResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerFileResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerSubwayResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerTagResult;
import com.exflyer.oddi.admin.api.partner.dto.PartnerDeviceAddReq;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OddiZonePartnerMapper {

    Page<OddiZonePartnerListSearchResult> findBySearchReq(OddiZonePartnerListSearchReq searchReq);
    OddiZonePartnerResult findDetailOddiZoneBasicInfo(Long seq);
    List<OddiZonePartnerTagResult> findDetailOddiZoneTagList(Long seq);
    List<OddiZonePartnerFileResult> findDetailOddiZoneFileList(Long seq);
    List<OddiZonePartnerDeviceResult> findDetailOddiZoneDeviceList(Long seq);
    List<OddiZonePartnerSubwayResult> findDetailOddiZoneSubwayList(Long seq);
    Map<String, Integer> countDeviceInfo(List<PartnerDeviceAddReq> deviceId);
    Map<String, String> countDeviceInfo2(String deviceId);
}
