package com.exflyer.oddi.admin.api.sales.adv.service;

import com.exflyer.oddi.admin.api.sales.adv.dao.AdvHistoryMapper;
import com.exflyer.oddi.admin.api.sales.adv.dto.AdvHistorySearchCodes;
import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryReq;
import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryResult;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdvHistoryService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private AdvHistoryMapper advHistoryMapper;

    public AdvHistorySearchCodes findCodeForSearching() {

        List<Code> channelTypeCode = codeRepository.findByGroupCodeAndUsable("PTT", true);
        return new AdvHistorySearchCodes(channelTypeCode);
    }

    public PagingResult<SearchAdvHistoryResult> findAdvHistory(SearchAdvHistoryReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "p.seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<SearchAdvHistoryResult> result = advHistoryMapper.findAdvHistory(searchReq);
        return PagingResult.createResultDto(result);
    }
}
