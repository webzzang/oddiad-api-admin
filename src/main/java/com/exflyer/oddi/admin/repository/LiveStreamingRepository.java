package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.LiveStreaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LiveStreamingRepository extends JpaRepository<LiveStreaming, Long>,JpaSpecificationExecutor<LiveStreaming> {

    @Query(value = "select * from live_streaming limit 1", nativeQuery = true)
    LiveStreaming findLiveStreamResultInfo();

    @Transactional
    @Modifying
    @Query(value = "update live_streaming set live_stream_channel_id = :channelId", nativeQuery = true)
    void saveChannelId(@Param("channelId")String channelId);

}
