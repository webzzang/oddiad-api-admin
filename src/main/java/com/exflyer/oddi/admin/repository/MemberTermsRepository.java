package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.MemberTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberTermsRepository extends JpaRepository<MemberTerms, Long>, JpaSpecificationExecutor<MemberTerms> {

}
