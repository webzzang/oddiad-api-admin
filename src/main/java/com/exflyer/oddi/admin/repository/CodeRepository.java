package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Code;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, String> {

  List<Code> findByGroupCodeAndUsable(String groupCode, boolean usable);

  List<Code> findByGroupCode(String groupCode);

  List<Code> findByGroupCodeAndUsableAndVal(String groupCode, boolean usable, String val);

  Code findByCodeAndUsable(String code, boolean usable);
}
