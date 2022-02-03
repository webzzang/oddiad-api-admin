package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerResult {

    @ApiModelProperty(value = "순번", position = 1)
    private Long seq;

    @ApiModelProperty(value = "매장 이름", position = 2)
    private String mallName;

    @ApiModelProperty(value = "소유자 이름", position = 3)
    private String ownerName;

    @ApiModelProperty(value = "소유자 전화 번호", position = 4)
    @DecryptField
    private String ownerPhoneNumber;

    @ApiModelProperty(value = "우편 번호", position = 5)
    private String zipcode;

    @ApiModelProperty(value = "주소", position = 6)
    private String addr;

    @ApiModelProperty(value = "지번주소", position = 7)
    private String oldAddr;

    @ApiModelProperty(value = "상세 주소", position = 8)
    private String detailAddr;

    @ApiModelProperty(value = "요약", position = 9)
    private String summary;

    @ApiModelProperty(value = "설명", position = 10)
    private String description;

    @ApiModelProperty(value = "광고 사례 노출 여부", position = 11)
    private Boolean advCaseExpo;

    @ApiModelProperty(value = "운영 여부", position = 12)
    private Boolean operation;

    @ApiModelProperty(value = "메모", position = 13)
    private String memo;

    @ApiModelProperty(value = "생성 날짜", position = 14)
    private String regDate;

    @ApiModelProperty(value = "변경 날짜", position = 15)
    private String modDate;

    @ApiModelProperty(value = "배지 코드", position = 16)
    private String badgeCode;

    @ApiModelProperty(value = "총 슬롯", position = 17)
    private String totalSlot;

    @ApiModelProperty(value = "슬롯당 노출 시간", position = 18)
    private String slotVideoTime;

    @ApiModelProperty(value = "슬롯당 금액", position = 19)
    private String slotPrice;

    @ApiModelProperty(value = "총 슬롯", position = 17)
    private String totalSlotLabel;

    @ApiModelProperty(value = "슬롯당 노출 시간", position = 18)
    private String slotVideoTimeLabel;

    @ApiModelProperty(value = "슬롯당 금액", position = 19)
    private String slotPriceLabel;

    @ApiModelProperty(value = "운영 요일", position = 20)
    private String operationWeek;

    @ApiModelProperty(value = "운영 시작 시간", position = 21)
    private String operationStartTime;

    @ApiModelProperty(value = "운영 종료 시간", position = 22)
    private String operationEndTime;

    @ApiModelProperty(value = "일 노출 카운트", position = 23)
    private String dayExpoCount;

    @ApiModelProperty(value = "채널타입", position = 24)
    private String channelType;

    @ApiModelProperty(value = "위치(지하철 광고 위치)", position = 25)
    private String location;

    @ApiModelProperty(value = "화면(지하철 광고 화면)", position = 26)
    private String display;

    @ApiModelProperty(value = "운영 종료 익일 구분", position = 27)
    private Boolean operationEndTomorrowGbn;

    @ApiModelProperty(value = "도면 파일순번", position = 28)
    private Long mapFileSeq;

    @ApiModelProperty(value = "도면 파일패스", position = 29)
    private String mapFilePath;

    @ApiModelProperty(value = "도면 파일명", position = 30)
    private String mapFileName;

    @ApiModelProperty(value = "시", position = 30)
    private String addrSi;

    @ApiModelProperty(value = "구", position = 30)
    private String addrGu;

    @ApiModelProperty(value = "동", position = 30)
    private String addrDong;



}
