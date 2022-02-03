package com.exflyer.oddi.admin.api.contents.livestream.service;

import com.exflyer.oddi.admin.api.contents.livestream.dao.LiveStreamMapper;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveScheduleModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamDetailResult;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingVodList;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartner;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.LiveSchedule;
import com.exflyer.oddi.admin.models.LiveStreaming;
import com.exflyer.oddi.admin.models.Youtube;
import com.exflyer.oddi.admin.models.YoutubePartner;
import com.exflyer.oddi.admin.repository.LiveScheduleRepository;
import com.exflyer.oddi.admin.repository.LiveStreamingRepository;
import com.exflyer.oddi.admin.repository.YouTubePartnerRepository;
import com.exflyer.oddi.admin.repository.YouTubeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LiveStreamService {

    @Autowired
    private LiveStreamingRepository liveStreamingRepository;
    @Autowired
    private LiveScheduleRepository liveScheduleRepository;
    @Autowired
    private YouTubePartnerRepository youTubePartnerRepository;
    @Autowired
    private YouTubeRepository youTubeRepository;
    @Autowired
    private LiveStreamMapper liveStreamMapper;

    public PagingResult<SearchPartnerResult> findLiveStreamSearchPartner(SearchPartner searchReq){
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<SearchPartnerResult> result = liveStreamMapper.findLiveStreamSearchPartner(searchReq);
        return PagingResult.createResultDto(result);
    }

    public LiveStreamDetailResult findDetail() throws ApiException {
        LiveStreaming liveStreamResult = liveStreamingRepository.findLiveStreamResultInfo();
        List<LiveSchedule> liveSchedultResult = liveScheduleRepository.findAll(Sort.by(Order.asc("operationDay"),Order.asc("startTime")));
        if (liveStreamResult == null || liveSchedultResult == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }

        List<LiveStreamingVodList> liveStreamVodList = liveStreamMapper.findByYoutubeList();

        return new LiveStreamDetailResult(liveStreamResult, liveSchedultResult, liveStreamVodList);
    }

    public void channelIdModify(String channelId) {
        liveStreamingRepository.saveChannelId(channelId);
    }

    public void saveLiveStreaming(LiveStreamingReq liveStreamingReq) throws ApiException {
        LiveSchedule liveSchedule = new LiveSchedule(liveStreamingReq);
        liveScheduleRepository.save(liveSchedule);
    }

    public void modifyLiveChannel(Long seq, LiveStreamingReq liveStreamingReq) throws ApiException {

        Optional<LiveSchedule> liveScheduleOptional = liveScheduleRepository.findById(seq);
        LiveSchedule liveSchedule = liveScheduleOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        liveSchedule.setModifyLiveSchedule(liveStreamingReq);

        liveScheduleRepository.save(liveSchedule);
    }

    public void deleteLiveChannel(Long seq) {
        liveScheduleRepository.deleteById(seq);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modifyVod(LiveScheduleModReq liveScheduleModReq) throws ApiException {

//        Optional<YoutubePartner> youtubeOptional = youTubePartnerRepository.findByYoutubeIdByParentSeq(liveScheduleModReq.getYoutubeId());
//        YoutubePartner youtubePartner = youtubeOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        Youtube youtube = new Youtube();
        youtube.setYoutubeId(liveScheduleModReq.getYoutubeId());
        youtube.setExpo(liveScheduleModReq.getExpo());

        youTubeRepository.save(youtube);

        YoutubePartner youtubePartner = new YoutubePartner();
        youtubePartner.setYoutubeId(liveScheduleModReq.getYoutubeId());
        youtubePartner.setPartnerSeq(liveScheduleModReq.getPartnerSeq());

        youTubePartnerRepository.save(youtubePartner);
    }

    public void updateByPartnerByVod(String youtubeId) {
        youTubePartnerRepository.updateByPartnerByVod(youtubeId);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(LiveStreamModReq modReq) throws ApiException {

        if(StringUtil.isNotBlank(modReq.getLiveStreamChannelId()))liveStreamingRepository.saveChannelId(modReq.getLiveStreamChannelId());

        liveScheduleRepository.deleteAll();

        if(modReq.getLiveStreamList() != null) {
            modReq.getLiveStreamList().forEach(datas -> {

                LiveSchedule liveSchedule = new LiveSchedule(datas);
                liveSchedule.setRegId(modReq.getRegId());
                liveScheduleRepository.save(liveSchedule);
            });
        }

        youTubePartnerRepository.deleteAll();
        modReq.getVodList().forEach(datas ->{

            youTubeRepository.updateByYoutubeExpo(datas.getExpo(), datas.getYoutubeId());

            YoutubePartner youtubePartner = new YoutubePartner();
            youtubePartner.setYoutubeId(datas.getYoutubeId());
            youtubePartner.setPartnerSeq(datas.getPartnerSeq());
            youTubePartnerRepository.save(youtubePartner);
        });

    }
}
