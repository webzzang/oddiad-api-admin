INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_account_del', '계정 삭제 안내', '오디AD 알림> #{고객이름} 님의 계정이 탈퇴 처리될 예정입니다.

고객님의 계정 (#{고객 ID}) 이 휴면 전환 후 1년 동안 접속 이력이 없어 #{계정 삭제 예정일}일에 고객님의 계정이 탈퇴 처리될 예정입니다.

탈퇴 처리 후에는 기존의 광고 이용 내역은 삭제되어 재가입 시에도 복구할 수 없습니다.
탈퇴를 원치 않으실 경우 탈퇴 예정일 이전에 오디에 접속하시어 로그인하시면 정상 이용이 가능합니다.

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_accout_sleep', '계정 휴면 알림', '오디AD 알림> #{고객명} 님의 계정이 휴면 상태로 전환될 예정입니다.

고객님이 1년 동안 서비스에 로그인하지 않아 #{휴면 예정일}부터 고객님의 계정 (#{고객 ID}) 이 휴면 계정으로 전환됩니다.

휴면 전환 후 1년 경과 시에는 자동으로 탈퇴 처리되고 기존의 광고 이용 내역은 삭제되어 재가입 시에도 복구할 수 없습니다.
휴면을 원치 않으실 경우 전환 예정일 이전에 오디에 접속하시어 로그인하시면 정상 이용이 가능합니다.

#{홈페이지url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_ad_appr', '광고 승인 안내', '오디AD 알림> #{고객이름} 님이 신청하신 광고가 승인되었습니다.

광고 이름: #{광고 이름}
신청일: #{신청 일시}
광고 기간: #{광고시작일} ~ #{광고종료일}

광고 취소 및 환불은 #{광고시작일-광고취소가능일}까지 가능합니다.

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_ad_deny', '광고 보류 안내', '오디AD 알림> #{고객이름} 님이 신청하신 광고가 보류되었습니다.

광고 이름: #{광고 이름}
신청일: #{신청 일시}
광고 기간: #{광고시작일} ~ #{광고종료일}
보류 사유: #{보류사유}

오디AD에 접속하여 상세 내용 확인 후 다시 등록해주시기 바랍니다.

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_ad_exp', '광고 만료 안내', '오디AD 알림> #{고객이름} 님이 진행 중인 광고가 20일 후 종료됩니다.

광고 이름: #{광고 이름}
광고 기간: #{광고시작일} ~ #{광고종료일}

광고를 계속 진행하시려면 오디AD에 접속하여 다시 신청해주시기 바랍니다.

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_ad_refund', '광고 취소 및 환불', '오디AD 알림> #{고객이름} 님이 신청하신 광고를 취소하고, 결제 금액 환불을 요청하였습니다.

광고 이름: #{광고 이름}
신청일: #{신청 일시}
광고 기간: #{광고시작일} ~ #{광고종료일}
환불 예정 금액: #{환불 금액}

결제 대행사의 사정에 따라 실제 환불에 시간이 걸릴 수 있습니다. (통상 2~4일 소요)

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_cs_ans', '문의 답변 알림', '오디AD 알림> #{고객이름} 님 문의에 답변 드립니다.

문의: #{1:1문의 제목}
답변 확인: #{문의글 Url}

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_pw_find', '비밀번호 찾기', '오디AD 알림> #{고객명} 님 아래의 비밀번호로 로그인 후 비밀번호를 변경하시기 바랍니다.

초기화 비밀번호: #{초기화비밀번호}

#{홈페이지 url}');
INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_pw_reset', '관리자 비밀번호 초기화', '오디존 관리자>
초기화 비밀번호: #{초기화비밀번호}');

INSERT INTO oddi.kakao_template (template_id, template_name, template_contents) VALUES ('oddi_sys_alert', '시스템 얼럿', '오디AD 시스템 알림>
#{경고 메시지}

기기 > 기기관리 메뉴에서 상태 확인 후 조치하시기 바랍니다.

#{어드민 url}');
