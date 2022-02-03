package com.exflyer.oddi.admin.api.terms.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TermsSearchReq extends PagingSearch {

  private String typeCode;

}
