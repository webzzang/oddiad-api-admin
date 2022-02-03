package com.exflyer.oddi.admin.models;


import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "member_company")
@ApiModel("회원(광고주) 회사")
public class MemberCompany implements Serializable {

  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(name = "member_id", nullable = false)
  private String memberId;

  @Column(name = "corporation", nullable = false)
  private boolean corporation;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "ceo", nullable = false)
  private String ceo;

  @Column(name = "business_license_number", nullable = false)
  private String businessLicenseNumber;

  @Column(name = "business_license_file")
  private Long businessLicenseFile;

  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  @Column(name = "mod_date")
  private LocalDateTime modDate;



}
