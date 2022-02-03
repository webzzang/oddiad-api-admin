package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerDevice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerDeviceRepository extends JpaRepository<PartnerDevice, String>, JpaSpecificationExecutor<PartnerDevice> {

    @Query(value = "select count(1) from partner_device where device_id in (:deviceId)", nativeQuery = true)
    int countPartnerDeviceInfo(@Param("deviceId") List<String> deviceId);

    @Query(value = "select device_id from partner_device where device_id = :deviceId", nativeQuery = true)
    String findAllByPartnerDevice(@Param("deviceId") String deviceId);

    List<PartnerDevice> findByPartnerSeq(@Param("partnerSeq") Long partnerSeq);

    @Query(value = "select * from partner_device where partner_seq = :partnerSeq and device_id = :deviceId", nativeQuery = true)
    Optional<PartnerDevice> findByDeviceIdByPartnerSeq(@Param("partnerSeq") Long partnerSeq, @Param("deviceId") String deviceId);

    @Query(value = "select device_id, partner_seq, name, reg_date, reg_id from partner_device where partner_seq = :partnerSeq and device_id not in (:deviceId)", nativeQuery = true)
    List<PartnerDevice> isNotInPartnerDeviceList(@Param("partnerSeq") Long partnerSeq, @Param("deviceId") List<String> deviceId);

    @Query(value = "select count(1) from partner_device where partner_seq = :partnerSeq and device_id = :deviceId", nativeQuery = true)
    int isNotInPartnerSeq(@Param("partnerSeq") Long partnerSeq, @Param("deviceId") String deviceId);

    @Modifying
    @Transactional
    @Query(value = "update partner_device set display_div = :displayDiv, side_contents_type = :sideContentsType, bottom_contents_type = :bottomContentsType", nativeQuery = true)
    void saveConfig(@Param("displayDiv") String displayDiv, @Param("sideContentsType") String sideContentsType, @Param("bottomContentsType") String bottomContentsType);

}
