package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Tags;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TagsRepository extends JpaRepository<Tags, Long>, JpaSpecificationExecutor<Tags> {

    @Query(value = "select t.seq, t.tag, t.reg_date from partner_tags pt inner join tags t on pt.tag_seq = t.seq where partner_seq = :partnerSeq ", nativeQuery = true)
    List<Tags> findBySeq(@Param("partnerSeq") Long partnerSeq);

}
