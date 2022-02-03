package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Partner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerRepository extends JpaRepository<Partner, Long>, JpaSpecificationExecutor<Partner> {

    @Query(value = "select * from partner where channel_type = :channelType and seq not in (:seq)", nativeQuery = true)
    List<Partner> findPartnerByChannelType(String channelType, Long[] seq);

    @Query(value = "select * from partner where channel_type = :channelType", nativeQuery = true)
    List<Partner> findPartnerByChannelType(String channelType);

    @Modifying
    @Transactional
    @Query(value = "update partner set total_slot = :totalSlot where channel_type = :channelType", nativeQuery = true)
    void saveSlotCount(@Param("totalSlot") Integer totalSlot, @Param("channelType") String channelType);

    @Modifying
    @Transactional
    @Query(value = "update partner set slot_video_time = :slotVideoTime where channel_type = :channelType", nativeQuery = true)
    void saveSlotVideoTime(@Param("slotVideoTime") Integer slotVideoTime, @Param("channelType") String channelType);

    @Modifying
    @Transactional
    @Query(value = "update partner set operation_start_time = :OperationStartTime, operation_end_time = :OperationEndTime where channel_type = :channelType", nativeQuery = true)
    void saveOperationTime(@Param("OperationStartTime") String OperationStartTime, @Param("OperationEndTime") String OperationEndTime, @Param("channelType") String channelType);

}
