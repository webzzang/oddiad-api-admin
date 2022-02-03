package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.KakaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KakaoTemplateRepository extends JpaRepository<KakaoTemplate, String>, JpaSpecificationExecutor<KakaoTemplate> {

}
