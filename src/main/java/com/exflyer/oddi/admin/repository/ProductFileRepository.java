package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.ProductFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductFileRepository extends JpaRepository<ProductFile, String>, JpaSpecificationExecutor<ProductFile> {

    @Query(value = "select file_seq from product_file where product_seq  = :productSeq and file_seq not in (:fileSeq)", nativeQuery = true)
    List<Long> isNotInFileSeq(@Param("productSeq") Long productSeq, @Param("fileSeq") List<Long> fileSeq);

    @Modifying
    @Transactional
    @Query(value = "delete from product_file where product_seq = :productSeq", nativeQuery = true)
    void deleteProductFile(@Param("productSeq") Long productSeq);

}
