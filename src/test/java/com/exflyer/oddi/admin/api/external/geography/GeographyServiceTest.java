package com.exflyer.oddi.admin.api.external.geography;

import com.exflyer.oddi.admin.api.external.geography.dto.GridConvertResult;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class GeographyServiceTest {

  @Autowired
  private GeographyService geographyService;
  
  @DisplayName("지번 주소로 좌표 정보 조회")
  @Test
  public void findGeographyByOdlAddr() throws Exception {
    String oldAddr = "서울특별시 금천구 가산동 517-4";
    GridConvertResult oldAddrResult = geographyService.findXyByAddr(oldAddr);
    Assertions.assertThat(oldAddrResult).isNotNull();
    log.info("{}", oldAddrResult);

    String newAddr = "서울특별시 금천구 가산동 가산디지털1로";
    GridConvertResult newAddrResult = geographyService.findXyByAddr(newAddr);
    Assertions.assertThat(newAddrResult).isNotNull();
    log.info("{}", newAddrResult);


  }

}
