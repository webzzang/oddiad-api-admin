package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member> {

}
