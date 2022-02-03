package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.ProductPartner;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductPartnerRepository extends JpaRepository<ProductPartner, Long>, JpaSpecificationExecutor<ProductPartner> {

    @Query(value = "select * from product_partner where product_seq = :productSeq and partner_seq = :partnerSeq", nativeQuery = true)
    Optional<ProductPartner> findByProductSeqByPartnerSeq(Long productSeq, Long partnerSeq);

    @Modifying
    @Transactional
    @Query(value = "delete from product_partner where product_seq = :productSeq", nativeQuery = true)
    void deleteProductPartner(@Param("productSeq") Long productSeq);
}
