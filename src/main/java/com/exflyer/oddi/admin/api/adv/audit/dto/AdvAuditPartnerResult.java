package com.exflyer.oddi.admin.api.adv.audit.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvAuditPartnerResult {

    @ApiModelProperty(value = "광고순번", position = 1)
    private String advSeq;

    @ApiModelProperty(value = "광고처명", position = 2)
    private String mallName;

    @ApiModelProperty(value = "파트너순번", position = 3)
    private String partnerSeq;

    @ApiModelProperty(value = "슬롯수", position = 4)
    private String requestSlot;

    @ApiModelProperty(value = "잔여 슬롯수", position = 5)
    private String remain_slot;
}
