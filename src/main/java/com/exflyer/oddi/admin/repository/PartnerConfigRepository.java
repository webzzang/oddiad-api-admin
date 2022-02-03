package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerConfigRepository extends JpaRepository<PartnerConfig, String>, JpaSpecificationExecutor<PartnerConfig> {


    @Query(value="select default_adv_file_seq from partner_config where type = :type", nativeQuery = true)
    Long findByDefaultAdvFileSeq(String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set slot_count = :slotCount where type = :type", nativeQuery = true)
    void updateSlotCountSave(@Param("slotCount") int slotCount, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set slot_video_time = :slotVideoTime where type = :type", nativeQuery = true)
    void updateSlotVideoTimeSave(@Param("slotVideoTime") int slotVideoTime, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set oddi_adv_from_start_date = :oddiAdvFromStartDate, oddi_adv_to_start_date = :advToStartDate where type = :type", nativeQuery = true)
    void updateOddiAdvFromStartDateSave(@Param("oddiAdvFromStartDate") int oddiAdvFromStartDate,@Param("advToStartDate") int advToStartDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set oddi_adv_max_date = :oddiAdvMaxDate where type = :type", nativeQuery = true)
    void updateOddiAdvMaxDateSave(@Param("oddiAdvMaxDate") int oddiAdvMaxDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set oddi_adv_cancel_date = :oddiAdvCancelDate where type = :type", nativeQuery = true)
    void updateOddiAdvCancelDateSave(@Param("oddiAdvCancelDate") int oddiAdvCancelDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set subway_adv_last_date = :subwayAdvLastDate where type = :type", nativeQuery = true)
    void updateSubwayAdvLastDateSave(@Param("subwayAdvLastDate") int subwayAdvLastDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set subway_adv_max_start_date = :subwayAdvMaxStartDate where type = :type", nativeQuery = true)
    void updateSubwayAdvMaxStartDateSave(@Param("subwayAdvMaxStartDate") int subwayAdvMaxStartDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set subway_adv_cancel_date = :subwayAdvCancelDate where type = :type", nativeQuery = true)
    void updateSubwayAdvCancelDateSave(@Param("subwayAdvCancelDate") int subwayAdvCancelDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set subway_adv_max_date = :subwayAdvMaxDate where type = :type", nativeQuery = true)
    void updateSubwayAdvMaxDateSave(@Param("subwayAdvMaxDate") int subwayAdvMaxDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set design_request = :designRequest where type = :type", nativeQuery = true)
    void updateDesignRequestSave(@Param("designRequest") Boolean designRequest, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set adv_name = :advName, default_adv_file_seq = :defaultAdvFileSeq, default_adv_type = :defaultAdvType  where type = :type", nativeQuery = true)
    void updateAdvNameSave(@Param("advName") String advName, @Param("defaultAdvFileSeq") Long defaultAdvFileSeq, @Param("defaultAdvType") String defaultAdvType, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set display_div = :displayDiv, side_display_service_code = :sideDisplayServiceCode, bottom_display_service_code = :bottomDisplayServiceCode where type = :type", nativeQuery = true)
    void updateDisplayDivSave(@Param("displayDiv") String displayDiv, @Param("sideDisplayServiceCode") String sideDisplayServiceCode, @Param("bottomDisplayServiceCode") String bottomDisplayServiceCode, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update partner_config set operation_start_time = :operationStartTime, operation_end_time = :operationEndTime where type = :type", nativeQuery = true)
    void updateOperationStartTimeSave(@Param("operationStartTime") String operationStartTime, @Param("operationEndTime") String operationEndTime, @Param("type") String type);

}
