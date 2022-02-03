package com.exflyer.oddi.admin.api.manager.account.service;

import com.exflyer.oddi.admin.api.external.notification.dto.KakaoNotificationReq;
import com.exflyer.oddi.admin.api.manager.account.dao.ManagerAccountMapper;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountDetail;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountListSearchReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountModReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountRegReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountSearchCodes;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccounts;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.manager.auth.service.PassWordEncrypt;
//import com.exflyer.oddi.admin.config.KakaoNotificationConfig;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.KakaoNoticationCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.KakaoTemplate;
import com.exflyer.oddi.admin.models.Manager;
import com.exflyer.oddi.admin.models.Notification;
import com.exflyer.oddi.admin.models.NotificationGroup;
import com.exflyer.oddi.admin.models.Role;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.KakaoTemplateRepository;
import com.exflyer.oddi.admin.repository.ManagerRepository;
import com.exflyer.oddi.admin.repository.NotificationGroupRepository;
import com.exflyer.oddi.admin.repository.NotificationRepository;
import com.exflyer.oddi.admin.repository.RoleRepository;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class ManagerAccountService {

  @Autowired
  private ManagerAccountMapper managerAccountMapper;
  @Autowired
  private CodeRepository codeRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private ManagerRepository managerRepository;
  @Autowired
  private PassWordEncrypt passWordEncrypt;
  @Autowired
  private KakaoTemplateRepository kakaoTemplateRepository;
  @Autowired
  private NotificationGroupRepository notificationGroupRepository;
  @Autowired
  private NotificationRepository notificationRepository;
//  @Autowired
//  private KakaoNotificationConfig kakaoNotificationConfig;
  @Autowired
  private AesEncryptor aesEncryptor;
  @PersistenceContext
  EntityManager em;


  public PagingResult findBySearchReq(ManagerAccountListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<ManagerAccounts> result = managerAccountMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public ManagerAccountSearchCodes findSearchCode() {

    List<Code> managerStatusCode =  codeRepository.findByGroupCodeAndUsable("MSC", true);
    List<Role> roleList =  roleRepository.findByUsable(true);
    List<Code> pagingCode = codeRepository.findByGroupCodeAndUsable("PGS", true);

    return new ManagerAccountSearchCodes(managerStatusCode, roleList, pagingCode);
  }

  public ManagerAccountDetail findDetailById(String id) throws ApiException {
    ManagerAccountDetail managerAccountDetail = managerAccountMapper.findDetailById(id);
    if (managerAccountDetail == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    return managerAccountDetail;
  }

  public void modify(ManagerAccountModReq managerAccountModReq) throws ApiException {
    Optional<Manager> managerOptional = managerRepository.findById(managerAccountModReq.getId());
    Manager manager = managerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    manager.setModifyInfo(managerAccountModReq);
    managerRepository.save(manager);
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void add(ManagerAccountRegReq managerAccountRegReq) throws ApiException {
    boolean duplication = isDuplication(managerAccountRegReq.getId());
    if (duplication) {
      throw new ApiException(ApiResponseCodes.DUPLICATE);
    }
    Manager manager = new Manager(managerAccountRegReq);
    String initPassword = createInitPassword();
    manager.setPassword(passWordEncrypt.encryptPassword(initPassword));
    managerRepository.save(manager);

    /*log.info("##############################################################");
    log.info(new Gson().toJson(manager));
    log.info("##############################################################");*/

    managerNotification(manager.getId(), manager, initPassword, managerAccountRegReq.getRegManagerId());
  }

  private String createInitPassword() {
    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8) + "!";
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void initPassword(String encryptManagerId,  AdminAuth adminAuth)
      throws ApiException {
    String initPassword = createInitPassword();
    Optional<Manager> managerOptional = managerRepository.findById(encryptManagerId);
    Manager manager = managerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    manager.setInitPassword(true);
    manager.setPassword(passWordEncrypt.encryptPassword(initPassword));
    manager.setModId(adminAuth.getId());
    manager.setModDate(LocalDateUtils.krNow());
    manager.setPasswordErrorCount(0);
    managerRepository.save(manager);

    managerNotification(encryptManagerId, manager, initPassword, adminAuth.getId());

  }

  private void managerNotification(String encryptManagerId, Manager manager, String initPassword, String regId) throws ApiException {

    KakaoNotificationReq kakaoNotificationReq = sendKakaoNotification(encryptManagerId, manager.getPhoneNumber(), initPassword);

    /*NotificationGroup notificationGroup = new NotificationGroup();
    notificationGroup.setContents(kakaoNotificationReq.getTemplateText());
    notificationGroup.setTemplateId(KakaoNoticationCodes.ADMIN_PW_CHANGE.getCode());
    notificationGroup.setTargetGroupSeq(0L);
    notificationGroup.setAlrimTalk(true);
    notificationGroup.setAdvMessage(false);
    notificationGroup.setAuto(false);
    notificationGroup.setSenderPhoneNumber(kakaoNotificationConfig.getReSendFromPhoneNum());
    notificationGroup.setDone(false);
    notificationGroup.setRegId(kakaoNotificationReq.getRegId());
    notificationGroup.setRegDate(LocalDateUtils.krNow());
    notificationGroupRepository.save(notificationGroup);
    em.persist(notificationGroup);*/

    Notification notification = new Notification();
    notification.setGroupSeq(0L);
    notification.setContents(kakaoNotificationReq.getText());
    notification.setSendTime("0");
    //notification.setSenderPhoneNumber(kakaoNotificationConfig.getReSendFromPhoneNum());
    notification.setAlrimTalk(true);
    notification.setRegDate(LocalDateUtils.krNow());
    notification.setKakaoTemplateId(kakaoNotificationReq.getTemplateCode());
    notification.setSenderId(regId);
    notification.setSenderName("오디 관리자");
//    notification.setReceiveId(aesEncryptor.decrypt(encryptManagerId));
    notification.setReceiveId(encryptManagerId);
    notification.setReceiveName(manager.getName());
    notification.setReceivePhoneNumber(aesEncryptor.decrypt(manager.getPhoneNumber())); //수신자번호
    notification.setKakaoTemplateId(kakaoNotificationReq.getTemplateCode());

    notificationRepository.save(notification);
  }


  public KakaoNotificationReq sendKakaoNotification(String encryptManagerId, String phoneNumber, String initPassword) throws ApiException {
    String templateCode = KakaoNoticationCodes.ADMIN_PW_CHANGE.getCode();

    Optional<KakaoTemplate> kakaoTemplateOptional = kakaoTemplateRepository.findById(templateCode);
    KakaoTemplate kakaoTemplate = kakaoTemplateOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

    KakaoNotificationReq kakaoNotificationReq  = new KakaoNotificationReq();
    kakaoNotificationReq.setText(
        kakaoTemplate.getTemplateContents()
            .replace("#{초기화비밀번호}", initPassword)
    );
    kakaoNotificationReq.setReSend("Y");
    kakaoNotificationReq.setTo(phoneNumber);
    kakaoNotificationReq.setTemplateCode(templateCode);
    kakaoNotificationReq.setRegId(encryptManagerId);
    kakaoNotificationReq.setTemplateText(kakaoTemplate.getTemplateContents());

    return kakaoNotificationReq;
  }

  public boolean isDuplication(String encryptId) {
    return managerRepository.existsById(encryptId);
  }
}
