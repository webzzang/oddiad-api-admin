package com.exflyer.oddi.admin.api.adv.make.dao;

import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeBasicInfoResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeDetailSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeExpoResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeFilesResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchResult;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvMakeMapper {

  Page<AdvMakeListSearchResult> findBySearchReq(AdvMakeListSearchReq searchReq);
  AdvMakeBasicInfoResult findDetailAdvBasicInfo(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeExpoResult> findDetailNowExpoAdvList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeExpoResult> findDetailWaitExpoAdvList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeFilesResult> findDetailNowExpoAdvFileList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeFilesResult> findDetailWaitExpoAdvFileList(AdvMakeDetailSearchReq advMakeDetailSearchReq);

  List<AdvMakeExpoResult> findSubwayDetailNowExpoAdvList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeFilesResult> findSubwayDetailNowExpoAdvFileList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeExpoResult> findSubwayDetailWaitExpoAdvList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
  List<AdvMakeFilesResult> findSubwayDetailWaitExpoAdvFileList(AdvMakeDetailSearchReq advMakeDetailSearchReq);
}
