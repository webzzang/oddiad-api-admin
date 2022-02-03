package com.exflyer.oddi.admin.api.code.service;

import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.CodeGroup;
import com.exflyer.oddi.admin.repository.CodeGroupRepository;
import com.exflyer.oddi.admin.repository.CodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.Cacheable;

@Component
public class CodeService {

  @Autowired
  private CodeGroupRepository codeGroupRepository;

  @Autowired
  private CodeRepository codeRepository;

  @Cacheable(value = "groupCode")
  public List<CodeGroup> findGroupCodeList() {
    return codeGroupRepository.findAllByUsable(true);
  }

  @Cacheable(value = "code")
  public List<Code> findCodeList(String groupCode) {
    return codeRepository.findByGroupCodeAndUsable(groupCode, true);
  }
}
