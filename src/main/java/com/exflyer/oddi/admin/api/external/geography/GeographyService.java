package com.exflyer.oddi.admin.api.external.geography;

import com.exflyer.oddi.admin.api.external.geography.dto.GridConvertResult;
import com.exflyer.oddi.admin.api.external.geography.dto.KakaoCoordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GeographyService {

  @Autowired
  private GridConverter gridConverter;

  @Autowired
  private KakaoLocalService kakaoLocalService;

  public GridConvertResult findXyByAddr(String addr){
    KakaoCoordinates coordinatesByAddress = kakaoLocalService.findCoordinatesByAddress(addr);
    GridConvertResult convertResult = gridConverter
      .convert(coordinatesByAddress.getLatitude(), coordinatesByAddress.getLongitude());
    return convertResult;
  }

}
