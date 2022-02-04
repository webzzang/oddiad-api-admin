package com.exflyer.oddi.admin.api.external.geography;

import com.exflyer.oddi.admin.api.external.geography.dto.KakaoCoordinates;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoLocalServiceTest {

  @Autowired
  private KakaoLocalService kakaoLocalService;

  @DisplayName("주소 정보로 위경도 조회")
  @Test
  public void findCoordinatesByAddress() throws Exception {
    String testAddr = "서울 금천구 가산동 517-4";
    KakaoCoordinates kakaoCoordinates = kakaoLocalService.findCoordinatesByAddress(testAddr);
    AssertionsForClassTypes.assertThat(kakaoCoordinates).isNotNull();
    AssertionsForClassTypes.assertThat(kakaoCoordinates.getHCode()).isEqualTo("1154551000");
  }

}
