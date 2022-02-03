package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.api.terms.dto.TermsSearchResult;
import com.exflyer.oddi.admin.models.Terms;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TermsRepository extends JpaRepository<Terms, Long>, JpaSpecificationExecutor<Terms> {

  @Query(value = "select seq"
    + "     , title"
    + "     , contents"
    + "     , reg_date"
    + "     , reg_id"
    + "     , mod_date"
    + "     , mod_id"
    + "     , version"
    + "     , memo"
    + "     , type"
    + "     , status_code"
    + "     , (select name from code where code = status_code) as status_code_name"
    + " from terms"
    + " where type = :typeCode", nativeQuery = true)
  List<TermsSearchResult> findByType(@Param("typeCode") String typeCode);

  @Modifying
  @Query(value = "update terms set status_code = :statusCode where type = :typeCode and adv_terms = :advTerms", nativeQuery = true)
  void updateAllNotUsingByType(@Param("typeCode") String typeCode, @Param("statusCode") String statusCode, @Param("advTerms") Boolean advTerms);

  @Modifying
  @Query(value = "update terms set status_code = :statusCode, mod_id = :modId where seq = :seq", nativeQuery = true)
  void updateGabageTerms(@Param("statusCode") String statusCode, @Param("modId") String modId, @Param("seq") Long seq);

  @Query(value = "select * from terms where seq = :seq and type = :typeCode", nativeQuery = true)
  Terms findBySeqAndType(@Param("seq") Long seq, @Param("typeCode") String typeCode);
}
