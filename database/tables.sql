drop table if exists adv_file cascade;

drop table if exists adv_history cascade;

drop table if exists adv_partner cascade;

drop table if exists adv_product cascade;

drop table if exists adv cascade;

drop table if exists banner cascade;

drop table if exists banner_broadcasting cascade;

drop table if exists broadcasting cascade;

drop table if exists broadcasting_file cascade;

drop table if exists broadcasting_history cascade;

drop table if exists code cascade;

drop table if exists code_group cascade;

drop table if exists device_broadcasting_call_log cascade;

drop table if exists device_content_state cascade;

drop table if exists device_content_state_history cascade;

drop table if exists device_control cascade;

drop table if exists device_exception_log cascade;

drop table if exists device_fcm_history cascade;

drop table if exists device_info cascade;

drop table if exists device_state cascade;

drop table if exists device_state_history cascade;

drop table if exists device_weather cascade;

drop table if exists dust cascade;

drop table if exists faq cascade;

drop table if exists files cascade;

drop table if exists kakao_template cascade;

drop table if exists live_schedule cascade;

drop table if exists live_streaming cascade;

drop table if exists login_config cascade;

drop table if exists manager cascade;

drop table if exists manager_notification cascade;

drop table if exists member cascade;

drop table if exists member_company cascade;

drop table if exists member_promotion cascade;

drop table if exists member_terms cascade;

drop table if exists notice_files cascade;

drop table if exists notice cascade;

drop table if exists notification cascade;

drop table if exists notification_group cascade;

drop table if exists notification_history cascade;

drop table if exists notification_target cascade;

drop table if exists notification_target_group cascade;

drop table if exists partner_config cascade;

drop table if exists partner_default_adv cascade;

drop table if exists partner_default_adv_files cascade;

drop table if exists partner_device cascade;

drop table if exists partner_device_push_history cascade;

drop table if exists partner_image cascade;

drop table if exists partner_request cascade;

drop table if exists partner_subway cascade;

drop table if exists partner_tags cascade;

drop table if exists payment cascade;

drop table if exists pg_accredit_log cascade;

drop table if exists pg_payment_log cascade;

drop table if exists phone_auth cascade;

drop table if exists product_file cascade;

drop table if exists product_partner cascade;

drop table if exists partner cascade;

drop table if exists product cascade;

drop table if exists promotion_coupon cascade;

drop table if exists promotion cascade;

drop table if exists role_menu cascade;

drop table if exists menu cascade;

drop table if exists menu_group cascade;

drop table if exists role cascade;

drop table if exists tags cascade;

drop table if exists temp cascade;

drop table if exists terms cascade;

drop table if exists voc_file cascade;

drop table if exists voc cascade;

drop table if exists weather cascade;

drop table if exists withdrawal_member cascade;

drop table if exists youtube cascade;

drop table if exists youtube_partner cascade;

drop table if exists adv_reject_log cascade;


create table if not exists adv
(
    seq                  bigint auto_increment comment '순번'
        primary key,
    member_id            varchar(200)                 null comment '회원 id',
    title                varchar(500)                 null comment '제목',
    business_type_code   varchar(45)                  null comment '업종 종류 코드',
    start_date           varchar(8)                   null comment '시작 날짜',
    end_date             varchar(8)                   null comment '종료 날짜',
    price                decimal(18)                  null comment '금액',
    discount_price       decimal(18)                  null comment '할인 금액',
    reg_date             datetime                     null comment '수정 날짜',
    mod_date             datetime                     null comment '변경 날짜',
    audit_code           varchar(45)                  null comment '심사 코드',
    progress_code        varchar(45) default 'PGT001' null comment '진행 코드(심사중, 결제전, 결제후 등)',
    coupon_number        varchar(255)                 null comment '쿠폰 번호',
    payment_seq          bigint                       null comment '결제 순번',
    rejection_reason     varchar(255)                 null comment '거절 이유',
    rejection_code       varchar(45)                  null comment '거절 코드',
    rejection_date       datetime                     null comment '거절 날짜',
    audit_id             varchar(200)                 null comment '심사 id',
    design_request       tinyint(1)  default 0        null comment '디자인 요청 여부',
    channel_type         varchar(45)                  null comment '채널 종류(오디존, 지하철)',
    approval_date        datetime                     null comment '승인날짜',
    memo                 varchar(3000)                null comment '메모',
    etc_business_name    varchar(100)                 null comment '기타 업종명',
    expo                 tinyint(1)  default 1        null comment '노출여부',
    send_code            varchar(45) default 'ADM001' null comment '발송 코드(승인, 보류, 종료 예정 발송 코드)',
    company_seq          bigint                       null comment '회원 회사순번',
    oddi_adv_cancel_date int                          null comment '광고 신청취소 가능일',
	  user_check           tinyint(1)                   null comment '사용자 알림 확인여부(0:미확인, 1:확인)'
)
    comment '광고';

create table if not exists adv_file
(
    file_seq       bigint       not null comment '파일 순번',
    adv_seq        bigint       not null comment '광고 순번',
    type           varchar(45)  not null comment '종류(doc, image, video)',
    reg_date       datetime     not null comment '생성 날짜',
    view_order     int          null comment '노출순서',
    converted_path varchar(255) null comment '미디어 컨버팅된 경로',
    primary key (file_seq, adv_seq),
    constraint FK_adv_file_adv_seq_adv_seq
        foreign key (adv_seq) references adv (seq)
)
    comment '광고 파일';

create table if not exists adv_history
(
    seq            bigint auto_increment comment '순번'
        primary key,
    adv_seq        bigint       not null comment '광고 순번',
    partner_seq    bigint       not null comment '파트너 순번',
    member_id      varchar(200) not null comment '회원 id',
    device_id      varchar(200) not null comment '장비 id',
    device_name    varchar(100) not null comment '장비 이름',
    adv_start_date varchar(8)   not null comment '광고 시작 날짜',
    adv_end_date   varchar(8)   not null comment '광고 종료 날짜',
    reg_date       datetime     not null comment '생성 날짜'
)
    comment '광고 내역';

create table if not exists banner
(
    seq             bigint auto_increment comment '순번'
        primary key,
    name            varchar(100)  not null comment '이름',
    type            varchar(45)   not null comment '종류(web, device)',
    location_code   varchar(45)   not null comment '위치 코드(우측, 상단, 하단, 팝업)',
    description     varchar(3000) null comment '설명',
    image_path      varchar(255)  null comment '이미지 경로',
    usable          tinyint(1)    not null comment '사용 여부',
    expo_start_date varchar(8)    not null comment '노출 시작 날짜',
    expo_end_date   varchar(8)    not null comment '노출 종료 날짜',
    memo            varchar(3000) null comment '메모',
    reg_date        datetime      not null comment '생성 날짜',
    reg_id          varchar(200)  not null comment '생성 id',
    mod_date        datetime      null comment '변경 날짜',
    mod_id          varchar(200)  null comment '변경 id',
    router_link     varchar(255)  null comment '라우터 링크',
    button_name     varchar(100)  null comment '버튼 이름',
    file_seq        bigint        null comment '파일순번'
)
    comment '배너';

create table if not exists banner_broadcasting
(
    banner_seq    bigint       not null comment '배너 순번'
        primary key,
    video_time    int          null comment '영상 시간',
    path          varchar(255) null comment '경로',
    location_code varchar(45)  null comment '위치(side, bottom)'
)
    comment '배너 방송';

create table if not exists broadcasting
(
    seq           bigint auto_increment comment '순번'
        primary key,
    device_id     varchar(200)         not null comment '플레이어 시리얼',
    adv_seq       bigint               not null comment '광고 순번',
    partner_seq   bigint               not null comment '파트너 순번',
    reg_date      datetime             not null comment '수정 날짜',
    mod_id        varchar(200)         null comment '변경 id',
    mod_date      datetime             null comment '변경 날짜',
    expo          tinyint(1) default 1 null comment '노출 여부',
    ordering      int                  null comment '정렬 번호',
    video_time    int                  not null comment '영상 시간',
    contents_type varchar(45)          null comment '내용 종류 코드(video, image 등)'
)
    comment '방송';

create table if not exists broadcasting_file
(
    seq              bigint auto_increment comment '순번'
        primary key,
    broadcasting_seq bigint       not null comment '방송 순번',
    path             varchar(255) not null comment '경로',
    reg_date         datetime     null comment '생성 날짜'
)
    comment '방송 파일';

create index FK_broadcasting_file_broadcasting_seq_broadcasting_seq
    on broadcasting_file (broadcasting_seq);

create table if not exists broadcasting_history
(
    seq            bigint auto_increment comment '순번'
        primary key,
    channel_type   varchar(45)  not null comment '채널 종류',
    partner_name   varchar(100) not null comment '파트너 이름',
    device_name    varchar(100) not null comment '장비 이름',
    member_id      varchar(200) not null comment '회원 id',
    advertisers    varchar(100) not null comment '광고주',
    adv_start_date varchar(8)   not null comment '광고 시작 날짜',
    adv_end_date   varchar(8)   not null comment '광고 종료 날짜',
    reg_date       datetime     not null comment '생성 날짜'
)
    comment '방송 이력';

create table if not exists code
(
    code       varchar(45)  not null comment '코드'
        primary key,
    group_code varchar(45)  not null comment '그룹 코드',
    name       varchar(100) null comment '이름',
    usable     tinyint(1)   null comment '사용',
    ordering   int          null comment '정렬',
    val        varchar(255) null comment '값',
    using_type varchar(20)  null comment '사용 타입'
)
    comment '코드';

create table if not exists code_group
(
    code     varchar(45)  not null comment '코드'
        primary key,
    name     varchar(100) null comment '이름',
    usable   tinyint(1)   null comment '사용',
    ordering int          null comment '정렬 번호'
)
    comment '코드 그룹';

create table if not exists device_broadcasting_call_log
(
    seq              bigint auto_increment comment '순번',
    device_id        varchar(30)  not null comment '디바이스 ID',
    position         varchar(20)  not null comment 'main: 메인, side:사이드, bottom: 하단',
    broadcasting_cnt bigint       null comment '불러온 광고 개수',
    device_latitude  varchar(100) null comment '디바이스에서 수집된 위치 정보 (위도) 값이 없으면 "" 로 전달처리',
    device_longitude varchar(100) null comment '디바이스에서 수집된 위치 정보 (경도) 값이 없으면 "" 로 전달처리',
    reg_date         datetime     not null comment '생성 날짜',
    primary key (seq, device_id)
)
    comment '디바이스 광고 호출 로그';

create table if not exists device_content_state
(
    device_id               varchar(30) not null comment '디바이스 ID'
        primary key,
    content_id              varchar(45) null comment '컨텐츠 id',
    content_state           varchar(20) null comment '컨텐츠 상태 값
Ex) "start" -> 컨텐츠의 재생 시작을 알림
     "end" -> 컨텐츠 재생의 끝을 알림',
    content_type            varchar(10) null comment '컨텐츠 타입 값
Ex) "image" -> 이미지 광고 컨텐츠
     "video" -> 동영상 광고 컨텐츠
',
    content_screen_position varchar(20) null comment '재생중인 광고 영역 값
Ex) "screen_pos_main" -> 메인화면 광고중
     "screen_pos_side" ->  우측화면 광고중
     "screen_pos_bottom" ->  하단영역 광고중',
    content_screen_type     varchar(20) null comment '재생중인 광고 스크린 타입
Ex) "divisions_1" -> 메인광고 full 화면
     "divisions_2" -> 메인광고 + 오른쪽 배너 광고 화면
     "divisions_3" -> 메인광고 + 오른쪽 배너 + 하단 배너 광고 화면 ',
    mod_date                datetime    null comment '변경 날짜',
    content_ts              varchar(20) null comment 'UNIX 타임스템프'
)
    comment '컨텐츠(재생) 상태';

create table if not exists device_content_state_history
(
    seq                     bigint auto_increment comment '순번'
        primary key,
    device_id               varchar(30) not null comment '디바이스 ID',
    content_id              varchar(45) null comment '컨텐츠 id',
    content_state           varchar(20) null comment '컨텐츠 상태 값
Ex) "start" -> 컨텐츠의 재생 시작을 알림
     "end" -> 컨텐츠 재생의 끝을 알림',
    content_type            varchar(10) null comment '컨텐츠 타입 값
Ex) "image" -> 이미지 광고 컨텐츠
     "video" -> 동영상 광고 컨텐츠
',
    content_screen_position varchar(20) null comment '재생중인 광고 영역 값
Ex) "screen_pos_main" -> 메인화면 광고중
     "screen_pos_side" ->  우측화면 광고중
     "screen_pos_bottom" ->  하단영역 광고중',
    content_screen_type     varchar(20) null comment '재생중인 광고 스크린 타입
Ex) "divisions_1" -> 메인광고 full 화면
     "divisions_2" -> 메인광고 + 오른쪽 배너 광고 화면
     "divisions_3" -> 메인광고 + 오른쪽 배너 + 하단 배너 광고 화면 ',
    reg_date                datetime    null comment '생성 날짜',
    content_ts              varchar(20) null comment 'UNIX 타임스템프'
)
    comment '컨텐츠(재생) 상태 히스토리';

create table if not exists device_control
(
    seq          bigint auto_increment comment '순번'
        primary key,
    device_id    varchar(200) not null comment '장비 id',
    control_code varchar(45)  not null comment '제어 코드',
    reg_date     datetime     not null comment '생성 날짜',
    success      tinyint(1)   not null comment '성공 여부',
    error_code   varchar(45)  not null comment '오류 코드'
)
    comment '장비 제어';

create table if not exists device_exception_log
(
    seq       bigint auto_increment comment '순번'
        primary key,
    device_id varchar(30) not null comment '디바이스 ID',
    acc_count varchar(20) null comment 'Exception 카운트 / Exception 이 발생하여 APP 재 시작한 카운트 전달
 카운트는 APP 시작후 5분후 초기화 하며, 5분안에 반복해서 발생시 카운트를 증가 하며, 그 횟수가 3회가 되면 APP 은 그냥 종료 처리함 ',
    reg_date  datetime    null comment '생성 날짜'
)
    comment '디바에스 APP 에러 로그';

create table if not exists device_fcm_history
(
    seq         bigint auto_increment comment '순번'
        primary key,
    device_id   varchar(30)  not null comment '디바이스 ID',
    type        varchar(10)  null comment 'SEND : FCM 발송, RECEP : 디바이스 FCM 수신',
    push_id     varchar(255) null comment 'FCM 수신시 전달받은 ID 값',
    action      varchar(20)  null comment '액션 코드',
    screen_type varchar(255) null comment '스크린 타입
"action이 "set_ad_screen_type" 일때는 필수
      Ex) "divisions_1" -> 메인광고 full 화면
           "divisions_2" -> 메인광고 + 오른쪽 배너 광고 화면
           "divisions_3" -> 메인광고 + 오른쪽 배너 + 하단 배너 광고 화면 ',
    reg_date    datetime     null comment '생성 날짜'
)
    comment '안드로이드 FCM 수신 체크 이력';

create table if not exists device_info
(
    device_id    varchar(30)  not null comment '디바이스 ID'
        primary key,
    fcm_token    varchar(255) not null comment '토큰(PUSH 발송용)',
    device_model varchar(45)  not null comment '디바이스 모델명 (ex. TCL tv, Chromecast)',
    android_id   varchar(100) not null comment '디바이스에 설치된 Android id 값(os 가 재설치 되기 전까지 유니크)',
    reg_date     datetime     not null comment '생성 날짜',
    reg_id       varchar(200) not null comment '생성 id',
    mod_date     datetime     null comment '변경 날짜',
    mod_id       varchar(200) null comment '변경 id'
)
    comment '디바이스 정보';

create table if not exists device_state
(
    device_id    varchar(30) not null comment '디바이스 ID'
        primary key,
    device_state varchar(20) null comment '상태 코드
Ex) "start" -> 광고 화면의 첫 시작을 알림
     "resume" -> 광고 화면이 활성화 되어 광고를 시작 하는 상태
    "pause" -> 광고 화면이 무언가에 의해 일부분이 가려진 상태
     (앱 실행중 팝업과 같은 화면이 노출된 상태)
    "stop"  -> 광고 화면의 전체가 사라진 상태
     (앱 실해중 홈으로 이동등)
    "destroy" -> 광고 APP 이 종료된 상태',
    content_id   varchar(45) null comment '컨텐츠 id',
    mod_date     datetime    null comment '변경 날짜'
)
    comment '디바이스 상태';

create table if not exists device_state_history
(
    seq          bigint auto_increment comment '순번'
        primary key,
    device_id    varchar(30) not null comment '디바이스 ID',
    device_state varchar(20) null comment '상태 코드
Ex) "start" -> 광고 화면의 첫 시작을 알림
     "resume" -> 광고 화면이 활성화 되어 광고를 시작 하는 상태
    "pause" -> 광고 화면이 무언가에 의해 일부분이 가려진 상태
     (앱 실행중 팝업과 같은 화면이 노출된 상태)
    "stop"  -> 광고 화면의 전체가 사라진 상태
     (앱 실해중 홈으로 이동등)
    "destroy" -> 광고 APP 이 종료된 상태',
    content_id   varchar(45) null comment '컨텐츠 id',
    reg_date     datetime    null comment '생성 날짜'
)
    comment '디바이스 상태 히스토리';

create table if not exists device_weather
(
    device_id  varchar(200) not null comment '장비 id'
        primary key,
    sky        varchar(255) null comment '하늘 상태',
    tmp        varchar(45)  null comment '기온',
    tmn        varchar(45)  null comment '일 최저 기온',
    tmx        varchar(45)  null comment '일 최고 기온',
    pop        varchar(255) null comment '강수 확률',
    reh        varchar(255) null comment '습도',
    wsd        varchar(255) null comment '풍속',
    pm10       varchar(255) null comment '미세먼지 농도',
    pm25       varchar(255) null comment '초미세먼지 농도',
    pm10_grade varchar(255) null comment '미세먼지 등급',
    pm25_grade varchar(255) null comment '초미세먼지 등급',
    addr       varchar(255) null comment '주소',
    icon       varchar(255) null comment '아이콘'
)
    comment '기기 날씨';

create table if not exists dust
(
    si_name    varchar(100) not null comment '시도 이름'
        primary key,
    pm10       varchar(45)  not null comment 'pm10',
    pm10_grade varchar(100) not null comment 'pm10_grade',
    pm25       varchar(45)  not null comment 'pm25',
    pm25_grade varchar(100) not null comment 'pm25_grade'
)
    comment '미세먼지';

create table if not exists faq
(
    seq           bigint auto_increment comment '순번'
        primary key,
    category_code varchar(45)          not null comment '카테고리 코드',
    title         varchar(500)         not null comment '제목',
    contents      varchar(3000)        not null comment '내용',
    expo          tinyint(1) default 1 not null comment '노출 여부',
    reg_date      datetime             not null comment '생성 날짜',
    reg_id        varchar(200)         not null comment '생성 id',
    mod_date      datetime             null comment '변경 날짜',
    mod_id        varchar(200)         null comment '변경 id',
    click_count   int        default 0 null comment '클릭 카운트'
)
    comment 'FAQ';

create table if not exists files
(
    seq            bigint auto_increment comment '순번'
        primary key,
    s3_file_key    varchar(255)         not null comment 's3_file_key',
    s3_bucket      varchar(255)         not null comment 's3_bucket',
    path           varchar(255)         not null comment '경로',
    reg_date       datetime             not null comment '수정 날짜',
    name           varchar(100)         null comment '이름',
    extension      varchar(10)          not null comment '확장자',
    type           varchar(45)          null comment '종류(S3, Local 등)',
    reg_id         varchar(200)         null comment '등록 id',
    mapping_done   tinyint(1) default 0 null comment '매핑 완료 여부',
    content_type   varchar(100)         null comment '파일 컨텐츠 타입',
    converted      tinyint(1) default 0 not null comment '미디어 컨버터 변환 여부',
    converted_path varchar(255)         null comment '미디어 컨버팅된 경로',
    constraint UQ_files_1
        unique (s3_file_key)
)
    comment '파일';

create table if not exists kakao_template
(
    template_id       varchar(45)   not null comment '슈어엠에 등록되어있는 템플릿 코드'
        primary key,
    template_name     varchar(45)   not null comment '템플릿명',
    template_contents varchar(3000) not null comment '템플릿 내용'
)
    comment '카카오 알림톡 템플릿';

create table if not exists live_schedule
(
    seq           bigint auto_increment comment '순번'
        primary key,
    title         varchar(500) not null comment '제목',
    operation_day varchar(8)   not null comment '운영일',
    start_time    varchar(6)   not null comment '시작시간',
    end_time      varchar(6)   not null comment '종료시간',
    reg_date      datetime     not null comment '생성 날짜',
    reg_id        varchar(200) not null comment '생성 id',
    mod_date      datetime     null comment '변경 날짜',
    mod_id        varchar(200) null comment '변경 id'
)
    comment '라이브 일정';

create table if not exists live_streaming
(
    live_channel_title     varchar(45)  not null comment '라이브채널 대표명',
    live_stream_channel_id varchar(45)  not null comment '라이브스트림 채널ID',
    reg_date               datetime     not null comment '생성 날짜',
    reg_id                 varchar(200) not null comment '생성 id',
    mod_date               datetime     null comment '변경 날짜',
    mod_id                 varchar(200) null comment '변경 id'
)
    comment '라이브 스트리밍';

create table if not exists login_config
(
    manager_password_mod_day_count int default 90 not null comment '관리자 비밀번호 변경 일수',
    member_password_mod_day_count  int default 90 not null comment '사용자 비밀번호 변경 일수',
    manager_token_expired_min      int default 60 not null comment '관리자 토큰 만료 일수',
    member_token_expired_min       int default 60 not null comment '사용자 토큰 만료 일수'
)
    comment '로그인 설정';

create table if not exists manager
(
    id                   varchar(200)         not null comment 'id'
        primary key,
    role_seq             bigint               not null comment '역활순번',
    name                 varchar(100)         not null comment '이름',
    password             varchar(255)         not null comment '비밀번호',
    phone_number         varchar(45)          null comment '전화번호',
    password_mod_date    datetime             null comment '비밀번호변경날짜',
    password_error_count int                  null comment '비밀번호오류카운트',
    memo                 varchar(3000)        null comment '메모',
    state                varchar(45)          null comment '상태',
    auth_number          varchar(255)         null comment '인증 번호',
    auth_expired_time    datetime             null comment '인증 만료 시간',
    reg_date             datetime             null comment '생성 날짜',
    reg_id               varchar(200)         null comment '생성 id',
    mod_date             datetime             null comment '수정 날짜',
    mod_id               varchar(200)         null comment '수정 id',
    login_date           datetime             null comment '로그인 날짜',
    init_password        tinyint(1) default 0 null comment '초기 비밀번호 여부'
)
    comment '관리자';

create table if not exists member
(
    id                    varchar(200)         not null comment 'id'
        primary key,
    name                  varchar(100)         not null comment '이름',
    password              varchar(255)         not null comment '비밀번호',
    phone_number          varchar(45)          null comment '전화번호',
    receive_consent       tinyint(1) default 0 not null comment '수신 동의',
    auth_code             varchar(255)         null comment '인증 코드',
    signup_date           datetime             not null comment '가입 날짜',
    mod_id                varchar(200)         null comment '변경 관리자 ID',
    mod_date              datetime             null comment '변경 날짜',
    state_code            varchar(45)          null comment '상태 코드',
    password_error_count  int        default 0 null comment '비밀번호 오류 카운트',
    password_mod_date     datetime             null comment '비밀번호 변경 날짜',
    login_date            datetime             null comment '로그인 날짜',
    memo                  varchar(3000)        null comment '메모',
    member_gbn            tinyint(1)           null comment '개인구분(1:개인, 0:사업자)',
    email                 varchar(100)         not null comment '이메일',
    email_receive_consent tinyint(1)           null comment '이메일 수신동의',
    password_reset        tinyint(1)           null comment '비밀번호 초기화 여부'
)
    comment '회원(광고주) 정보';

create table if not exists member_company
(
    seq                     bigint auto_increment comment '순번'
        primary key,
    member_id               varchar(200)         not null comment '회원 id',
    corporation             tinyint(1) default 0 not null comment '법인 여부',
    name                    varchar(100)         not null comment '이름',
    ceo                     varchar(100)         not null comment '대표',
    business_license_number varchar(255)         not null comment '사업자 등록증 번호',
    business_license_file   bigint               not null comment '사업자 등록증 파일',
    reg_date                datetime             not null comment '생성 날짜',
    mod_date                datetime             null comment '변경 날짜'
)
    comment '회원 회사';

create table if not exists member_terms
(
    member_id varchar(200) charset utf8 not null comment '회원 id',
    terms_seq bigint                    not null comment '약관 순번',
    reg_date  datetime                  not null comment '생성 날짜',
    terms_agree             tinyint(1) default 1 not null comment '동의 여부',
    primary key (member_id, terms_seq)
)
    comment '고객 약관 동의';

create table if not exists menu_group
(
    id       varchar(200)         not null comment 'id'
        primary key,
    name     varchar(100)         null comment '이름',
    icon     varchar(255)         null comment '아이콘',
    usable   tinyint(1) default 1 null comment '사용 여부',
    ordering int                  null comment '정렬 번호'
)
    comment '메뉴 그룹';

create table if not exists menu
(
    id          varchar(200)         not null comment 'id'
        primary key,
    group_id    varchar(200)         null comment '그룹 id',
    menu_name   varchar(100)         null comment '메뉴 이름',
    icon        varchar(255)         null comment '아이콘',
    router_link varchar(255)         null comment '라우터 링크',
    usable      tinyint(1) default 1 null comment '사용 여부',
    ordering    int                  null comment '정렬 번호',
    constraint FK_menu_group_TO_menu
        foreign key (group_id) references menu_group (id)
)
    comment '메뉴';

create table if not exists notice
(
    seq          bigint auto_increment comment '순번'
        primary key,
    title        varchar(500)         not null comment '제목',
    contents     varchar(3000)        not null comment '내용',
    expo         tinyint(1) default 1 not null comment '노출 여부',
    top_location tinyint(1) default 0 not null comment '상단 위치 여부',
    reg_date     datetime             not null comment '등록 날짜',
    reg_id       varchar(200)         not null comment '등록 id',
    mod_date     datetime             null comment '변경 날짜',
    mod_id       varchar(200)         null comment '변경 id'
)
    comment '공지';

create table if not exists notice_files
(
    notice_seq bigint   not null comment '순번',
    file_seq   bigint   not null comment '파일 순번',
    reg_date   datetime null comment '생성 날짜',
    primary key (notice_seq, file_seq),
    constraint FK_notice_files_notice_seq_notice_seq
        foreign key (notice_seq) references notice (seq)
)
    comment '공지 파일';

create table if not exists notification
(
    seq                  bigint auto_increment comment '순번'
        primary key,
    group_seq            bigint               not null comment '그룹 순번',
    receive_id           varchar(200)         null comment '수신 id',
    receive_name         varchar(100)         null comment '수신자 이름',
    receive_phone_number varchar(45)          not null comment '수신자 전화 번호',
    contents             varchar(3000)        not null comment '내용',
    send_time            varchar(14)          null comment '발송 시간(즉시 일경우 0)',
    sender_id            varchar(200)         not null comment '발신자 id',
    sender_name          varchar(100)         not null comment '발신자 이름',
    sender_phone_number  varchar(45)          null comment '발신자 전화 번호',
    kakao_template_id    varchar(200)         null comment '카카오 템플릿 id',
    alrim_talk           tinyint(1) default 0 null comment '알림톡여부',
    reg_date             datetime             null comment '생성 날짜',
    fail tinyint(1) default '0' null comment '실패여부',
    response varchar(1000) null comment '응답 메세지'
)
    comment '알림';

create index FK_notification_group_seq_notification_group_seq
    on notification (group_seq);

create table if not exists notification_group
(
    seq                 bigint auto_increment comment '순번'
        primary key,
    target_group_seq    bigint                null comment '알림 대상 그룹 순번',
    contents            varchar(3000)         null comment '내용',
    adv_message         tinyint(1)            null comment '광고 메세지 여부',
    send_time           varchar(14)           null comment '발송 시간(즉시 일경우 0)',
    sender_phone_number varchar(45)           null comment '발신자 전화 번호',
    auto                tinyint(1)  default 0 not null comment '자동 여부',
    total_count         decimal(18) default 0 null comment '전체 카운트',
    success_count       decimal(18) default 0 null comment '성공 카운트',
    fail_count          decimal(18) default 0 null comment '실패 카운트',
    reg_date            datetime              not null comment '생성 날짜',
    reg_id              varchar(200)          not null comment '생성 id',
    done                tinyint(1)  default 0 not null comment '완료 여부',
    template_id         varchar(200)          null comment 'VARCHAR(200)',
    alrim_talk          tinyint(1)  default 0 null comment '알림톡 여부',
    target_create_done  tinyint(1)  default 0 null comment '발송대상 생성완료'
)
    comment '알림_group';

create table if not exists notification_history
(
    seq                  bigint               not null comment '순번'
        primary key,
    group_seq            bigint               not null comment '그룹 순번',
    receive_id           varchar(200)         null comment '수신 id',
    receive_name         varchar(100)         not null comment '수신자 이름',
    receive_phone_number varchar(45)          not null comment '수신자 전화 번호',
    contents             varchar(3000)        not null comment '내용',
    send_time            varchar(14)          not null comment '발송 시간',
    request_time         datetime             not null comment '요청 시간',
    response_code        varchar(255)         not null comment '응답 코드',
    success              tinyint(1) default 1 not null comment '성공 여부',
    sender_id            varchar(200)         not null comment '발신자 ID',
    sender_name          varchar(100)         not null comment '발신자 이름',
    sender_phone_number  varchar(45)          null comment '발신자 전화 번호',
    reg_date             datetime             null comment '생성 날짜',
    kakao_template_id    varchar(200)         null comment '카카오 템플릿 id',
    alrim_talk           tinyint(1)           null comment '알림톡여부',
    response_message     varchar(1000)        null comment '응답 메세지',
    message_id           varchar(255)         null comment 'message_id'
);

create table if not exists notification_target
(
    seq              bigint auto_increment comment '순번'
        primary key,
    target_group_seq bigint       not null comment '알림 대상 그룹 순번',
    member_id        varchar(200) null comment '회원 id',
    name             varchar(100) null comment '이름',
    phone_number     varchar(45)  null comment '전화번호',
    partner_seq      bigint       null comment '파트너 순번'
)
    comment '알림 대상';

create table if not exists notification_target_group
(
    seq         bigint auto_increment comment '순번'
        primary key,
    name        varchar(100)         not null comment '이름',
    usable      tinyint(1) default 1 not null comment '사용 여부',
    fixed       tinyint(1) default 1 not null comment '고정 여부',
    reg_date    datetime             not null comment '생성 날짜',
    reg_id      varchar(200)         not null comment '생성 id',
    mod_date    datetime             null comment '변경 날짜',
    mod_id      varchar(200)         null comment '변경 id',
    target_code varchar(45)          null comment '알림대상 코드',
    send_time   varchar(14)          null comment '발송시간'
)
    comment '알림 대상 그룹';

create table if not exists partner
(
    seq                         bigint auto_increment comment '순번'
        primary key,
    channel_type                varchar(45)                              null comment '채널 종류(오디존, 지하철)',
    mall_name                   varchar(100)                             null comment '매장 이름',
    addr                        varchar(255)                             null comment '주소',
    detail_addr                 varchar(255)                             null comment '상세 주소',
    owner_name                  varchar(100)                             null comment '소유자 이름',
    owner_phone_number          varchar(45)                              null comment '소유자 전화 번호',
    reg_date                    datetime                                 not null comment '생성 날짜',
    reg_id                      varchar(200)                             not null comment '생성 id',
    mod_date                    datetime                                 null comment '변경 날짜',
    mod_id                      varchar(200)                             null comment '이름',
    description                 varchar(3000) collate utf8mb4_unicode_ci null,
    operation                   tinyint(1)                               null comment '운영 여부',
    memo                        varchar(3000)                            null comment '메모',
    total_slot                  int                                      not null comment '총 슬롯',
    slot_video_time             int                                      not null comment '슬롯당 노출 시간',
    slot_price                  int                                      not null comment '슬롯당 금액',
    operation_week              varchar(255)                             null comment '운영 요일',
    operation_start_time        varchar(6)                               null comment '운영 시작 시간',
    operation_end_time          varchar(6)                               null comment '운영 종료 시간',
    day_expo_count              int                                      not null comment '일 노출 카운트',
    subway_line                 varchar(255)                             null comment '지하철 노선',
    location                    varchar(255)                             null comment '위치(지하철 광고 위치)',
    display                     varchar(255)                             null comment '화면(지하철 광고 화면)',
    summary                     varchar(255) collate utf8mb4_unicode_ci  null,
    badge_code                  varchar(10)                              null comment '배지 코드',
    adv_case_expo               tinyint(1)                               null comment '광고 사례 노출 여부',
    side_display_service_code   varchar(45)                              null comment '측면 화면 서비스 코드',
    bottom_display_service_code varchar(45)                              null comment '아래 화면 서비스 코드',
    zipcode                     varchar(10)                              null comment '우편번호',
    latitude                    varchar(255)                             null comment '위도',
    longitude                   varchar(255)                             null comment '경도',
    grid_x                      int                                      null comment '그리드 x',
    grid_y                      int                                      null comment '그리드 y',
    old_addr                    varchar(255)                             null comment '지번주소',
    addr_si                     varchar(10)                              null comment '주소_시,군',
    addr_gu                     varchar(10)                              null comment '주소_구',
    addr_dong                   varchar(10)                              null comment '주소_동,읍,리',
    operation_end_tomorrow_gbn  tinyint(1)                               null comment '운영 종료 익일 구분',
    map_file_seq                bigint                                   null comment '도면 파일순번'
)
    comment '파트너';

create table if not exists adv_partner
(
    adv_seq      bigint  not null comment '광고 순번',
    partner_seq  bigint  not null comment '파트너 순번',
    request_slot int     not null comment '광고 슬롯',
    view_order   int     null comment '노출순서',
    expo         tinyint null comment '노출',
    primary key (adv_seq, partner_seq),
    constraint FK_adv_partner_adv_seq_adv_seq
        foreign key (adv_seq) references adv (seq),
    constraint FK_adv_partner_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq)
)
    comment '광고_광고처';

create table if not exists partner_config
(
    slot_count                  int          not null comment '슬롯 카운트',
    slot_video_time             int          null comment '슬롯 영상 시간',
    design_request              tinyint(1)   null comment '디자인 요청 여부',
    display_div                 varchar(45)  null comment '화면 분할(1, 2, 3분할)',
    side_display_service_code   varchar(45)  null comment '측면 화면 서비스 코드',
    bottom_display_service_code varchar(45)  null comment '하단 화면 서비스 코드',
    type                        varchar(45)  null comment '종류(오디존, 지하철)',
    operation_start_time        varchar(6)   null comment '운영 시작 시간',
    operation_end_time          varchar(6)   null comment '운영 종료 시간',
    adv_name                    varchar(500) null comment '기본광고이름',
    oddi_adv_from_start_date    int          null comment '오디존 광고 시작 가능일(from)',
    oddi_adv_to_start_date      int          null comment '오디존 광고 시작 가능일(to)',
    oddi_adv_max_date           int          null comment '오디존 최장 광고기간',
    subway_adv_last_date        int          null comment '지하철 익월 광고 신청 마감일',
    oddi_adv_cancel_date        int          null comment '오디존 광고 신청취소 가능일',
    subway_adv_max_start_date   int          null comment '지하철 최대광고 시작일',
    subway_adv_cancel_date      int          null comment '지하철 광고 신청취소 가능일',
    subway_adv_max_date         int          null comment '지하철 최장 광고기간'
)
    comment '파트너 설정';

create table if not exists partner_default_adv
(
    channel_type varchar(45)          not null comment '채널 종류',
    partner_seq  bigint               not null comment '파트너 순번',
    expo         tinyint(1) default 1 null comment '노출 여부',
    primary key (channel_type, partner_seq)
)
    comment '파트너 기본광고 노출여부';

create table if not exists partner_default_adv_files
(
    channel_type         varchar(45) not null comment '종류(오디존, 지하철)',
    default_adv_file_seq bigint      not null comment '기본 광고 파일 순번',
    default_adv_type     varchar(45) null comment '기본광고 파일타입',
    view_order           int         null comment '노출순서',
    primary key (channel_type, default_adv_file_seq)
)
    comment '파트너 기본광고 파일 매핑';

create table if not exists partner_device
(
    device_id            varchar(200)         not null comment 'id',
    partner_seq          bigint               not null comment '파트너 순번',
    name                 varchar(100)         not null comment '이름',
    type                 varchar(45)          null comment '종류(크롬, TCL)',
    reg_date             datetime             not null comment '생성 날짜',
    reg_id               varchar(200)         not null comment '생성 id',
    display_div          varchar(45)          null comment '화면 분할(1, 2, 3분할)',
    side_contents_type   varchar(45)          null comment '측면내용종류',
    bottom_contents_type varchar(45)          null comment '하단 내용 종류',
    default_adv_expo     tinyint(1) default 1 null comment '기본광고노출여부',
    primary key (device_id, partner_seq),
    constraint FK_partner_device_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq)
)
    comment '파트너 디바이스 매핑 테이블';

create table if not exists partner_device_push_history
(
    seq          bigint auto_increment comment '순번'
        primary key,
    partner_seq  bigint       not null comment '파트너 순번',
    device_id    varchar(10)  not null comment '디바이스 ID',
    action       varchar(20)  null comment '액션 코드',
    screen_type  varchar(255) null comment '스크린 타입',
    reg_date     datetime     not null comment '생성 날짜',
    success      tinyint(1)   not null comment '성공 여부',
    fail_message varchar(255) null comment '실패사유'
)
    comment '파트너 기기송출 로그';

create table if not exists partner_image
(
    partner_seq bigint   not null comment '파트너 순번',
    file_seq    bigint   not null comment '파일 순번',
    reg_date    datetime not null comment '생성 날짜',
    view_order  int      null comment '이미지 노출 순서',
    primary key (partner_seq, file_seq),
    constraint FK_partner_image_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq)
)
    comment '파트너(광고처) 이미지';

create table if not exists partner_request
(
    seq          bigint auto_increment comment '순번'
        primary key,
    name         varchar(100)                             not null comment '이름',
    phone_number varchar(45)                              not null comment '전화번호',
    location     varchar(255)                             null comment '지역',
    business     varchar(45)                              null comment '업종',
    contents     varchar(3000) collate utf8mb4_unicode_ci null,
    memo         varchar(3000)                            null comment '문의 사항',
    reg_date     datetime                                 null comment '생성 날짜',
    confirm      tinyint(1)                               null comment '확인 여부',
    confirm_id   varchar(200)                             null comment '확인 id'
)
    comment '파트너 요청';

create table if not exists partner_subway
(
    subway_seq  bigint auto_increment comment '지하철 노선 순번',
    partner_seq bigint       not null comment '파트너 순번',
    reg_id      varchar(200) null comment '생성 Id',
    reg_date    datetime     null comment '생성날짜',
    subway_code varchar(45)  null comment '지하철 노선코드',
    primary key (subway_seq, partner_seq),
    constraint FK_partner_subway_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq)
)
    comment '파트너 지하철노선 매핑 테이블';

create table if not exists payment
(
    seq              bigint auto_increment comment '순번'
        primary key,
    type             varchar(45)  not null comment '종류(결제, 취소)',
    member_id        varchar(200) not null comment '회원 id',
    adv_seq          bigint       not null comment '광고 순번',
    price            decimal(18)  not null comment '금액',
    channel_type     varchar(45)  not null comment '채널 종류(오디존, 지하철)',
    product_name     varchar(100) not null comment '상품 이름',
    adv_name         varchar(100) not null comment '광고 이름',
    adv_start_date   varchar(8)   not null comment '광고 시작 날짜',
    adv_end_date     varchar(8)   not null comment '광고 종료 날짜',
    reg_date         datetime     not null comment '생성 날짜',
    response_code    varchar(255) null comment '응답 코드(PG 연동)',
    response_message varchar(255) null comment '응답 메세지(PG 연동)',
    sync_date        datetime     null comment '연동 날짜(PG 연동)',
    success          tinyint(1)   null comment '성공 여부(PG 연동)'
)
    comment '결제';

create table if not exists pg_accredit_log
(
    seq            bigint auto_increment comment '순번'
        primary key,
    payment_seq    bigint       not null comment '결제순번',
    mid            varchar(10)  not null comment '상점아이디',
    order_number   varchar(40)  not null comment '주문번호',
    auth_url       varchar(500) null comment '승인요청url',
    net_cancel_url varchar(500) null comment '망취소요청url',
    signature      varchar(100) null comment 'SHA256 Hash값',
    timestamp      mediumtext   null comment '타임스템프',
    charset        varchar(6)   null comment '인증결과 인코딩',
    result_code    varchar(10)  not null comment '결과코드 [''0000'': 정상, 이외 실패]',
    result_msg     varchar(100) not null comment '결과메시지'
)
    comment 'pg인증결과로그';

create table if not exists pg_payment_log
(
    seq                bigint auto_increment comment '순번'
        primary key,
    tid                varchar(40)  not null comment '거래번호',
    moid               varchar(40)  null comment '주문번호 * 결제 요청시 oid 필드에 설정된 값',
    price              decimal(18)  not null comment '결제금액',
    pay_method         varchar(20)  not null comment '지불수단',
    buyer_name         varchar(30)  null comment '구매자명',
    buyer_tel          varchar(20)  null comment '구매자 휴대폰번호',
    buyer_email        varchar(100) null comment '구매자 이메일주소',
    cust_email         varchar(100) null comment '최종 이메일주소',
    appl_num           varchar(8)   null comment '승인번호( 지불수단에 따라 미전송)',
    appl_date          varchar(8)   null comment '승인일자 [YYYYMMDD]',
    appl_time          varchar(6)   null comment '승인시간 [hh24miss]',
    card_num           varchar(16)  null comment '신용카드번호',
    card_Interest      char         null comment '상점부담 무이자 할부여부 [''1'':상점부담 무이자]',
    card_quota         varchar(2)   null comment '카드 할부기간',
    card_code          varchar(2)   null comment '카드사코드',
    card_corp_flag     char         null comment '카드구분 [''0'':개인카드, ''1'':법인카드, ''9'':구분불가] 승인실패 시 빈값 전달',
    card_check_flag    char         null comment '카드종류 [''0'':신용카드, ''1'':체크카드, ''2'':기프트카드]',
    card_bank_code     varchar(2)   null comment '카드발급사(은행) 코드',
    card_src_code      char         null comment '간편(앱)결제구분',
    acc_bank_code      varchar(2)   null comment '실시간계좌 은행코드',
    cshr_result_code   varchar(6)   null comment '현금영수증 발행 정상여부 [''220000'': 정상]',
    cshr_type          char         null comment '현금영수증구분 [''0'':소득공제, ''1'':지출증빙]',
    acct_name          varchar(30)  null comment '계좌주명',
    vact_num           varchar(14)  null comment '가상계좌번호',
    vact_bank_code     varchar(2)   null comment '가상계좌 입금은행코드',
    vact_bank_name     varchar(20)  null comment '가상계좌 입금은행명',
    vact_name          varchar(20)  null comment '예금주명',
    vact_input_name    varchar(20)  null comment '송금자명',
    vact_date          varchar(8)   null comment '입금기한일자 [YYYYMMDD]',
    vact_time          varchar(6)   null comment '입금기한시각 [hhmmss]',
    hpp_num            varchar(14)  null comment '휴대폰번호(통신사결제시)',
    pay_device         varchar(6)   null comment '휴대폰 결제장치',
    hpp_billkey        varchar(40)  null comment '휴대폰 빌링키(빌키발급 요청시에만 반환)',
    result_code        varchar(6)   not null comment '결과코드 [''0000'':성공, 이외 실패 (실패코드 6byte)]',
    result_msg         varchar(100) not null comment '결과메세지',
    result_detail_code varchar(6)   null comment '취소 실패응답시 상세코드',
    payment_seq        mediumtext   null comment '결제순번',
    pg_payment_type    tinyint(1)   null comment '결제 구분(0:결제, 1:취소)',
    pay_type           bigint       null comment '웹모바일구분(0:웹,1:모바일)',
    mid                varchar(10)  null comment '상점아이디'
)
    comment 'PG 결제 로그';

create table if not exists phone_auth
(
    phone_number      varchar(45) charset utf8  not null comment '전화번호'
        primary key,
    auth_number       varchar(255) charset utf8 null comment '인증 번호',
    auth_expired_time datetime                  null comment '인증 만료 시간',
    confirm           tinyint default 0         null comment '확인 여부'
)
    comment '전화인증';

create table if not exists product
(
    seq           bigint auto_increment comment '순번'
        primary key,
    name          varchar(100)                             not null comment '이름',
    price         decimal(18)                              not null comment '금액',
    description   varchar(3000) collate utf8mb4_unicode_ci null,
    operation     tinyint(1) default 1                     not null comment '운영 여부',
    memo          varchar(3000)                            null comment '메모',
    reg_date      datetime                                 not null comment '수정 날짜',
    reg_id        varchar(200)                             not null comment '생성 id',
    mod_date      datetime                                 null comment '변경 날짜',
    mod_id        varchar(200)                             null comment '변경 id',
    adv_case_expo tinyint(1)                               null comment '광고 사례 노출',
    total_slot    int                                      null comment '총슬롯',
    badge_code    varchar(45)                              null comment '배지코드'
)
    comment '묶음 상품';

create table if not exists adv_product
(
    adv_seq      bigint        not null comment '광고 순번',
    product_seq  bigint        not null comment '묶음상품순번',
    request_slot int default 1 not null comment '슬롯수',
    reg_date     datetime      not null comment '생성 날짜',
    primary key (adv_seq, product_seq),
    constraint FK_adv_product_adv_seq_adv_seq
        foreign key (adv_seq) references adv (seq),
    constraint FK_adv_product_product_seq_product_seq
        foreign key (product_seq) references product (seq)
)
    comment '광고_묶음상품';

create table if not exists product_file
(
    file_seq    bigint       not null comment '파일 순번',
    product_seq bigint       not null comment '상품 순번',
    reg_date    datetime     not null comment '생성 날짜',
    reg_id      varchar(200) not null comment '생성 id',
    type        varchar(45)  null comment '종류',
    primary key (file_seq, product_seq),
    constraint FK_product_file_product_seq_product_seq
        foreign key (product_seq) references product (seq)
)
    comment '상품 파일';

create table if not exists product_partner
(
    product_seq bigint       not null comment '상품 순번',
    partner_seq bigint       not null comment '이름',
    reg_date    datetime     not null comment '생성 날짜',
    reg_id      varchar(200) not null comment '생성 id',
    primary key (product_seq, partner_seq),
    constraint FK_product_partner_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq),
    constraint FK_product_partner_product_seq_product_seq
        foreign key (product_seq) references product (seq)
)
    comment '상품 파트너';

create table if not exists promotion
(
    seq                       bigint auto_increment comment '순번'
        primary key,
    name                      varchar(100)         not null comment '이름',
    type                      varchar(45)          not null comment '종류(가입, 일반 등)',
    discount_type             varchar(45)          not null comment '할인 종류(정액, 정률)',
    discount_price            decimal(18)          not null comment '할인 금액',
    contents                  varchar(3000)        null comment '내용',
    usable                    tinyint(1) default 1 not null comment '사용 여부',
    reg_date                  datetime             not null comment '생성 날짜',
    reg_id                    varchar(200)         not null comment '생성 id',
    mod_date                  datetime             null comment '변경 날짜',
    mod_id                    varchar(200)         null comment '변경 id',
    member_coupon_expired_day int                  null comment '가입자 쿠폰 가입후 만료일자',
	  member_coupon_code varchar(45) null comment '가입자 쿠폰 사용기한(첫결제시, 가입후)'
)
    comment '프로모션';

create table if not exists member_promotion
(
    member_id     varchar(200)         not null comment '회원 id',
    promotion_seq bigint               not null comment '홍보 순번',
    reg_date      datetime             not null comment '생성 날짜',
    usable        tinyint(1) default 0 not null comment '사용 여부',
    primary key (member_id, promotion_seq),
    constraint FK_member_promotion_promotion_seq_promotion_seq
        foreign key (promotion_seq) references promotion (seq)
)
    comment '회원 프로모션';

create table if not exists promotion_coupon
(
    seq           bigint auto_increment comment '순번'
        primary key,
    promotion_seq bigint               not null comment '홍보 순번',
    coupon_code   varchar(255)         not null comment '쿠폰 번호',
    usable        tinyint(1) default 0 not null comment '사용 여부',
    member_id     varchar(200)         null comment '회원 id',
    using_date    datetime             null comment '사용 날짜',
    reg_date      datetime             not null comment '생성 날짜',
    expired_date  varchar(8)           not null comment '만료 날짜',
    del        tinyint(1) default 0 not null comment '삭제 여부',
    constraint promotion_coupon_coupon_code_uindex
        unique (coupon_code),
    constraint FK_promotion_coupon_promotion_seq_promotion_seq
        foreign key (promotion_seq) references promotion (seq)
)
    comment '프로모션 쿠폰';

create table if not exists role
(
    seq      bigint auto_increment comment '순번'
        primary key,
    name     varchar(100)         not null comment '이름',
    usable   tinyint(1) default 1 not null comment '사용 여부',
    reg_date datetime             null comment '생성 날짜',
    reg_id   varchar(200)         null comment '생성 id',
    mod_date datetime             null comment '변경 날짜',
    mod_id   varchar(200)         null comment '변경 id',
    memo     varchar(3000)        null comment '메모'
)
    comment '역활';

create table if not exists manager_notification
(
    role_seq          bigint            not null comment '관리자그룹 순번',
    device_state_code varchar(45)       not null comment '기기상태 알림코드',
    device_push       tinyint(1) default 1 null comment '기기상태 알림수신여부',
    manager_push      tinyint(1) default 0 null comment '관리자 푸시여부',
    reg_date          datetime             null comment '생성 날짜',
    primary key (role_seq, device_state_code),
    constraint FK_manager_notification_role_seq_role_seq
        foreign key (role_seq) references role (seq)
)
    comment '관리자 그룹 알림수신';

create table if not exists role_menu
(
    menu_id          varchar(200) not null comment '메뉴 id',
    role_seq         bigint       not null comment '역활 순번',
    reg_authority    tinyint(1)   not null comment '생성 권한',
    search_authority tinyint(1)   not null comment '조회 권한',
    mod_authority    tinyint(1)   not null comment '수정 권한',
    del_authority    tinyint(1)   not null comment '삭제 권한',
    reg_date         datetime     null comment '생성 날짜',
    reg_id           varchar(200) null comment '생성 id',
    mod_date         datetime     null comment '변경 날짜',
    mod_id           varchar(200) null comment '변경 id',
    primary key (menu_id, role_seq),
    constraint FK_menu_TO_manager_menu
        foreign key (menu_id) references menu (id),
    constraint FK_role_TO_manager_menu
        foreign key (role_seq) references role (seq)
)
    comment '역활 메뉴';

create table if not exists tags
(
    seq      bigint auto_increment comment '순번'
        primary key,
    tag      varchar(50) not null comment '태그',
    reg_date datetime    not null comment '생성 날짜'
)
    comment '태그';

create table if not exists partner_tags
(
    partner_seq bigint   not null comment '파트너 순번',
    tag_seq     bigint   not null comment '태그 순번',
    reg_date    datetime not null comment '생성 날짜',
    view_order  int      null comment '태그 노출순서',
    primary key (partner_seq, tag_seq),
    constraint FK_partner_tags_partner_seq_partner_seq
        foreign key (partner_seq) references partner (seq),
    constraint FK_partner_tags_tag_seq_tags_seq
        foreign key (tag_seq) references tags (seq)
)
    comment '파트너 태그';

create table if not exists temp
(
    contents varchar(3000) null
);

create table if not exists terms
(
    seq         bigint auto_increment comment '순번'
        primary key,
    title       varchar(500)  not null comment '제목',
    contents    longtext          not null comment '내용',
    reg_date    datetime      not null comment '생성 날짜',
    reg_id      varchar(200)  not null comment '생성 id',
    mod_date    datetime      null comment '변경 날짜',
    mod_id      varchar(200)  null comment '변경 id',
    version     varchar(255)  null comment '버젼',
    memo        varchar(3000) null comment '메모',
    type        varchar(45)   not null comment '종류',
    status_code varchar(45)   null comment '상태 코드',
    adv_terms   tinyint(1)    null comment '광고신청시약관여부',
    required    tinyint(1)    null comment '필수여부'
)
    comment '약관';

create table if not exists voc
(
    seq               bigint auto_increment comment '순번'
        primary key,
    member_id         varchar(200)                             null comment '회원 id',
    name              varchar(100)                             not null comment '이름',
    phone_number      varchar(45)                              not null comment '전화번호',
    email             varchar(100)                             null comment '이메일',
    title             varchar(500)                             null comment '제목',
    contents          varchar(3000) collate utf8mb4_unicode_ci null,
    reg_date          datetime                                 not null comment '등록날짜',
    answer            varchar(3000) collate utf8mb4_unicode_ci null,
    answer_reg_date   datetime                                 null comment '답변 생성 날짜',
    answer_reg_id     varchar(200)                             null comment '답변 생성 id',
    inquiry_type_code varchar(45)                              null comment '문의유형코드',
    send_done         tinyint(1) default 0                     not null comment '발송 여부',
    uniq_code         varchar(255)                             null comment '유니크 코드(VOC 링크용)',
	  user_check        tinyint(1)                               null comment '사용자 알림 확인여부(0:미확인, 1:확인)'
)
    comment 'voc';

create table if not exists voc_file
(
    voc_seq  bigint   not null comment '순번',
    file_seq bigint   not null comment '파일 순번',
    reg_date datetime not null comment '생성날짜',
    primary key (voc_seq, file_seq),
    constraint FK_voc_file_voc_seq_voc_seq
        foreign key (voc_seq) references voc (seq)
)
    comment 'voc 파일';

create table if not exists weather
(
    grid_x       varchar(255) not null comment '측정 X좌표',
    grid_y       varchar(255) not null comment '측정 Y좌표',
    fcst_date    varchar(255) not null comment '예보 날짜',
    fcst_time    varchar(255) not null comment '예보 시간',
    sky          varchar(255) null comment '하늘 상태',
    tmp          varchar(45)  null comment '기온',
    tmn          varchar(45)  null comment '일 최저 기온',
    tmx          varchar(45)  null comment '일 최고 기온',
    pop          varchar(255) null comment '강수 확률',
    reh          varchar(255) null comment '습도',
    wsd          varchar(255) null comment '풍속',
    weather_icon varchar(255) null comment '날씨 아이콘',
    weather_code varchar(45)  null comment '날씨 코드',
    primary key (grid_x, grid_y, fcst_date, fcst_time)
)
    comment '기기 날씨';

create table if not exists withdrawal_member
(
    id                   varchar(200)                not null comment 'id'
        primary key,
    name                 varchar(100)                not null comment '이름',
    password             varchar(255)                not null comment '비밀번호',
    phone_number         varchar(45)                 null comment '전화번호',
    receive_consent      tinyint(1)  default 0       not null comment '수신 동의',
    auth_code            varchar(255)                null comment '인증 코드',
    signup_date          datetime                    not null comment '가입 날짜',
    mod_id               varchar(200)                null comment '변경 관리자 id',
    mod_date             datetime                    null comment '변경 날짜',
    state_code           varchar(45) default 'false' not null comment '상태 코드',
    password_error_count int                         null comment '비밀번호 오류 카운트',
    password_mod_date    datetime                    null comment '비밀번호 변경 날짜',
    login_date           datetime                    null comment '로그인 날짜',
    memo                 varchar(3000)               null comment '메모',
    member_gbn           tinyint                     null comment '개인구분(1:개인, 0:사업자)',
    email                varchar(100)                not null comment '이메일'
)
    comment '탈퇴 회원 정보';

create table if not exists youtube
(
    youtube_id          varchar(200)         not null comment '유튜브 ID'
        primary key,
    youtube_play_id     varchar(500)         not null comment '유튜브 플레이 ID',
    youtube_title       varchar(500)         not null comment '유튜브 제목',
    youtube_description varchar(3000)        null comment '유뷰브 설명',
    youtube_thumbnails  varchar(500)         not null comment '유튜브 썸네일',
    youtube_url         varchar(500)         not null comment '유튜브 경로',
    youtube_reg_date    datetime             not null comment '유튜브 등록일',
    reg_date            datetime             not null comment '생성 날짜',
    expo                tinyint(1) default 1 null comment '노출'
)
    comment '유튜브 vod목록';

create table if not exists youtube_partner
(
    youtube_id  varchar(200) not null comment '유튜브 ID'
        primary key,
    partner_seq bigint       null comment '파트너 순번'
)
    comment '유튜브 파트너 매핑';

create table if not exists adv_reject_log
(
	seq bigint auto_increment comment '순번'
		primary key,
	adv_seq bigint not null comment '광고 순번',
	adv_reg_date datetime not null comment '광고 신청일시',
	rejection_code varchar(45) not null comment '거절 코드',
	rejection_reason varchar(255) not null comment '거절 이유',
	reg_date datetime not null comment '생성 날짜',
	reg_id varchar(200) not null comment '생성 id'
)
comment '광고 보류 로그'
;


