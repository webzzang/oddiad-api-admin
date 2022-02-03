package com.exflyer.oddi.admin.api.product.bundle.dao;

import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleFileResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleListSearchResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundlePartnerResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleSearchReq;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBundleMapper {
    Page<ProductBundleListSearchResult> findBySearchReq(ProductBundleSearchReq searchReq);
    ProductBundleResult findDetail(String seq);
    List<ProductBundlePartnerResult> findDetailPartnerList(String seq);
    List<ProductBundleFileResult> findDetailFileList(String seq);
}
