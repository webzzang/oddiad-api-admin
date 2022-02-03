package com.exflyer.oddi.admin.api.product.bundle.service;

import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.product.bundle.dao.ProductBundleMapper;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleDetailResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleFileResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleListSearchResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundlePartnerResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleReq;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleResult;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleSearchCodes;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleSearchReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Partner;
import com.exflyer.oddi.admin.models.Product;
import com.exflyer.oddi.admin.models.ProductFile;
import com.exflyer.oddi.admin.models.ProductPartner;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.PartnerRepository;
import com.exflyer.oddi.admin.repository.ProductFileRepository;
import com.exflyer.oddi.admin.repository.ProductPartnerRepository;
import com.exflyer.oddi.admin.repository.ProductRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProductBundleService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductBundleMapper productBundleMapper;
    @Autowired
    private ProductPartnerRepository productPartnerRepository;
    @Autowired
    private ProductFileRepository productFileRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private CodeRepository codeRepository;

    @PersistenceContext
    EntityManager em;

    public ProductBundleSearchCodes findCodeForSearching() {
        List<Code> usableCode = codeRepository.findByGroupCodeAndUsable("UST", true);
        List<Code> operationCode = codeRepository.findByGroupCodeAndUsable("OPT", true);
        List<Code> badgeCode = codeRepository.findByGroupCodeAndUsable("BDG", true);
        return new ProductBundleSearchCodes(usableCode, operationCode, badgeCode);
    }

    public List<Partner> findPartnerSearchList(Long[] seq) {

        List<Partner> returnQuery;

        if(seq != null){
            returnQuery = partnerRepository.findPartnerByChannelType(ChannelCodes.ODDI_ZONE.getCode(), seq);
        }else{
            returnQuery = partnerRepository.findPartnerByChannelType(ChannelCodes.ODDI_ZONE.getCode());
        }

        return returnQuery;
    }

    public PagingResult<ProductBundleListSearchResult> findBySearchReq(ProductBundleSearchReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "pro.seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<ProductBundleListSearchResult> result = productBundleMapper.findBySearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }


    public ProductBundleDetailResult findDetail(String seq) throws ApiException {
        ProductBundleResult findDetail = productBundleMapper.findDetail(seq);

        if (findDetail == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }

        List<ProductBundlePartnerResult> findDetailPartnerList = productBundleMapper.findDetailPartnerList(seq);
        List<ProductBundleFileResult> findDetailFileList = productBundleMapper.findDetailFileList(seq);
        return new ProductBundleDetailResult(findDetail, findDetailPartnerList, findDetailFileList);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void add(ProductBundleReq addReq) {

        Product product = new Product(addReq);
        productRepository.save(product);
        em.persist(product);

        addProductPartner(addReq, product);
        addProductFile(addReq, product);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(ProductBundleReq modReq) throws ApiException {
        Optional<Product> productBundleOptional = productRepository.findById(modReq.getSeq());
        Product product = productBundleOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        product.setModifyReq(modReq);
        productRepository.save(product);

        productPartnerRepository.deleteProductPartner(modReq.getSeq());
        addProductPartner(modReq, product);
        /*modReq.getPartnerSeq().forEach(datas -> {
            ProductPartner productPartner = new ProductPartner();
            productPartner.setProductSeq(product.getSeq());
            productPartner.setPartnerSeq(datas);
            productPartner.setRegDate(product.getRegDate());
            productPartner.setRegId(product.getRegId());

            productPartnerRepository.save(productPartner);
        });*/

        List<ProductFile> newFileSeqItems = modReq.getProductFileList();

        List<Long> l_newFileSeqItems = new ArrayList<>();
        newFileSeqItems.forEach(datas -> {
            l_newFileSeqItems.add(datas.getFileSeq());
        });

        List<Long> isNotInFileSeq = productFileRepository.isNotInFileSeq(modReq.getSeq(), l_newFileSeqItems);

        productFileRepository.deleteProductFile(modReq.getSeq());

        addProductFile(modReq, product);

        fileService.updateMappingDone(l_newFileSeqItems);

        if(isNotInFileSeq != null){
            fileService.delete(isNotInFileSeq);
        }
    }

    private void addProductPartner(ProductBundleReq addReq, Product product){
        addReq.getPartnerSeq().forEach(datas -> {
            ProductPartner productPartner = new ProductPartner();
            productPartner.setProductSeq(product.getSeq());
            productPartner.setPartnerSeq(datas);
            productPartner.setRegDate(product.getRegDate());
            productPartner.setRegId(product.getRegId());

            productPartnerRepository.save(productPartner);
        });
    }

    private void addProductFile(ProductBundleReq addReq, Product product){
        addReq.getProductFileList().forEach(datas -> {

            ProductFile productFile = new ProductFile();
            productFile.setFileSeq(datas.getFileSeq());
            productFile.setProductSeq(product.getSeq());
            productFile.setRegDate(product.getRegDate());
            productFile.setRegId(product.getRegId());

            productFileRepository.save(productFile);
        });
    }

}
