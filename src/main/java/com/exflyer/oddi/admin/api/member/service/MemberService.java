package com.exflyer.oddi.admin.api.member.service;

import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.member.dao.MemberMapper;
import com.exflyer.oddi.admin.api.member.dto.MemberDetailResult;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchReq;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchResult;
import com.exflyer.oddi.admin.api.member.dto.MemberModReq;
import com.exflyer.oddi.admin.api.member.dto.MemberSearchCodes;
import com.exflyer.oddi.admin.api.member.dto.MemberTermsListResult;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Member;
import com.exflyer.oddi.admin.models.MemberCompany;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.MemberCompanyRepository;
import com.exflyer.oddi.admin.repository.MemberRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

  @Autowired
  private CodeRepository codeRepository;

  @Autowired
  private MemberMapper memberMapper;

  @Autowired
  private FileService fileService;

  @Autowired
  private MemberCompanyRepository memberCompanyRepository;

  @Autowired
  private MemberRepository memberRepository;

  public MemberSearchCodes findCodeForSearching() {
    List<Code> memberStatusCode = codeRepository.findByGroupCodeAndUsable("CTS", true);
    return new MemberSearchCodes(memberStatusCode);
  }

  public PagingResult<MemberListSearchResult> findBySearchReq(MemberListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<MemberListSearchResult> result = memberMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public MemberDetailResult findDetail(String id) throws ApiException {
    MemberDetailResult memberDetailResult = memberMapper.findDetail(id);
    if (memberDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }

    List<MemberTermsListResult> memberTermsList = memberMapper.findByMemberTerms(memberDetailResult.getId());
    List<MemberCompany> memberCompanyList = memberCompanyRepository.findByMemberId(memberDetailResult.getId());
    memberCompanyList.forEach(memberCompany -> {
      if (memberCompany.isCorporation()) {
        memberDetailResult.setCBusinessLicenseFileSeq(memberCompany.getBusinessLicenseFile());
        memberDetailResult.setCCeo(memberCompany.getCeo());
        memberDetailResult.setCMallName(memberCompany.getName());
        memberDetailResult.setCBusinessLicenseNumber(memberCompany.getBusinessLicenseNumber());
        memberDetailResult.setCBusinessLicenseFilePath(fileService.getFilePath(memberCompany.getBusinessLicenseFile()));
        memberDetailResult
          .setCRegDate(memberCompany.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      } else {
        memberDetailResult.setPBusinessLicenseFileSeq(memberCompany.getBusinessLicenseFile());
        memberDetailResult.setPCeo(memberCompany.getCeo());
        memberDetailResult.setPMallName(memberCompany.getName());
        memberDetailResult.setPBusinessLicenseNumber(memberCompany.getBusinessLicenseNumber());
        memberDetailResult.setPBusinessLicenseFilePath(fileService.getFilePath(memberCompany.getBusinessLicenseFile()));
        memberDetailResult
          .setPRegDate(memberCompany.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      }
    });

    memberDetailResult.setMemberTermsList(memberTermsList);
    return memberDetailResult;
  }

  public void modify(MemberModReq memberModReq) throws ApiException {
    Optional<Member> memberOptional = memberRepository.findById(memberModReq.getId());
    Member member = memberOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    member.setModifyReq(memberModReq);
    memberRepository.save(member);
  }
}
