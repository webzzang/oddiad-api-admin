package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.MemberCompany;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberCompanyRepository extends JpaRepository<MemberCompany, String>, JpaSpecificationExecutor<MemberCompany> {

  List<MemberCompany> findByMemberId(String memberId);
}
