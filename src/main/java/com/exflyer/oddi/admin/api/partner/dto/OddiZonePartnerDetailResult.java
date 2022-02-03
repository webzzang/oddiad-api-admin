package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OddiZonePartnerDetailResult {

  @DecryptField
  private OddiZonePartnerResult oddiZoneBasicInfo;
  private List<OddiZonePartnerTagResult> oddiZoneTagList;
  private List<OddiZonePartnerFileResult> oddiZoneFileList;
  private List<OddiZonePartnerDeviceResult> oddiZoneDeviceList;
  private List<OddiZonePartnerSubwayResult> oddiZoneSubwayList;

  public OddiZonePartnerDetailResult(
      OddiZonePartnerResult oddiZoneBasicInfo
      , List<OddiZonePartnerTagResult> oddiZoneTagList
      , List<OddiZonePartnerFileResult> oddiZoneFileList
      , List<OddiZonePartnerDeviceResult> oddiZoneDeviceList
      , List<OddiZonePartnerSubwayResult> oddiZoneSubwayList){
    this.oddiZoneBasicInfo = oddiZoneBasicInfo;
    this.oddiZoneTagList = oddiZoneTagList;
    this.oddiZoneFileList = oddiZoneFileList;
    this.oddiZoneDeviceList = oddiZoneDeviceList;
    this.oddiZoneSubwayList = oddiZoneSubwayList;
  }

}
