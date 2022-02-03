package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AdvAuditResult {

    @ApiModelProperty(value = "순번", position = 1)
    private Long seq;

    @DecryptField
    @ApiModelProperty(value = "고객 ID", position = 2)
    private String id;

    @ApiModelProperty(value = "고객 이름", position = 3)
    private String name;

    @DecryptField
    @ApiModelProperty(value = "전화번호", position = 4)
    private String phoneNumber;

    @ApiModelProperty(value = "신청일시", position = 5)
    private String regDate;

    @ApiModelProperty(value = "광고 시작일", position = 6)
    private String startDate;

    @ApiModelProperty(value = "광고 종료일", position = 7)
    private String endDate;

    @ApiModelProperty(value = "채널 종류 코드", position = 8)
    private String channelType;

    @ApiModelProperty(value = "채널 종류 코드명", position = 9)
    private String channelTypeName;

    @ApiModelProperty(value = "총신청슬롯수", position = 10)
    private String totRequestSlot;

    @ApiModelProperty(value = "심사 상태 코드", position = 11)
    private String auditCode;

    @ApiModelProperty(value = "심사 상태 코드명", position = 12)
    private String auditCodeName;

    @ApiModelProperty(value = "승인 일시", position = 13)
    private String approvalDate;

    @ApiModelProperty(value = "결제 일시", position = 14)
    private String paymentDate;

    @ApiModelProperty(value = "메모", position = 15)
    private String memo;

    @ApiModelProperty(value = "결제예정금액", position = 16)
    private String price;

    @ApiModelProperty(value = "광고이름", position = 17)
    private String title;

    @ApiModelProperty(value = "사업 종류 코드", position = 18)
    private String businessTypeCode;

    @ApiModelProperty(value = "사업 종류 코드명", position = 19)
    private String businessTypeCodeName;

    @ApiModelProperty(value = "디자인 요청 여부", position = 20)
    private Boolean designRequest;

    @ApiModelProperty(value = "디자인 요청 여부", position = 21)
    private String designRequestName;

    @ApiModelProperty(value = "사업자구분", position = 22)
    private String businessGbn;

    @ApiModelProperty(value = "대표자 이름", position = 23)
    private String ceo;

    @ApiModelProperty(value = "사업자등록증 파일순번", position = 24)
    private String businessLicenseFile;

    @ApiModelProperty(value = "사업자등록증 파일명", position = 25)
    private String businessLicenseFileName;

    @ApiModelProperty(value = "회사이름", position = 26)
    private String companyName;

    @ApiModelProperty(value = "사업자등록번호", position = 27)
    private String businessLicenseNumber;

    @ApiModelProperty(value = "진행 코드(심사중, 결제전, 결제후 등)", position = 28)
    private String progressCode;

    @ApiModelProperty(value = "거절 이유", position = 29)
    private String rejectionReason;

    @ApiModelProperty(value = "거절 코드", position = 30)
    private String rejectionCode;

    @ApiModelProperty(value = "거절 코드명", position = 31)
    private String rejectionCodeName;

    @ApiModelProperty(value = "현재 시간", position = 32)
    private String nowTime;

    @ApiModelProperty(value = "진행 상태 코드명", position = 34)
    private String progressCodeName;

    @ApiModelProperty(value = "이메일", position = 35)
    @DecryptField
    private String email;

    @ApiModelProperty(value = "취소 일시", position = 36)
    private String cancelDate;

    @ApiModelProperty(value = "법인여부", position = 37)
    private Integer corporation;

    @ApiModelProperty(value = "회원여부(개인:1, 개인법인, 법인 : 0)", position = 37)
    private Integer memberGbn;
}
