package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Banner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BannerRepository extends JpaRepository<Banner, Long>, JpaSpecificationExecutor<Banner> {

    @Query(value = "select b.*, (select f.name from files f where f.seq = b.file_seq) as file_name from banner b where seq = :seq", nativeQuery = true)
    Optional<Banner> findByBannerByFile(@Param("seq")Long seq);

}
