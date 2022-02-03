package com.exflyer.oddi.admin.api.product.partnerConfig.dao;

import com.exflyer.oddi.admin.api.product.partnerConfig.dto.PartnerConfigDetailResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.PartnerDefaultAdvFileResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.SubwayConfigDetailResult;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerConfigMapper {
    PartnerConfigDetailResult detail(String type);
    List<PartnerDefaultAdvFileResult> findDefaultAdvFiles(String type);
    SubwayConfigDetailResult subwayDetail(String type);
}
