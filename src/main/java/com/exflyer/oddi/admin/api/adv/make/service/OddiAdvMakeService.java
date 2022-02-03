package com.exflyer.oddi.admin.api.adv.make.service;

import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeModReq;
import com.exflyer.oddi.admin.api.adv.make.dto.DevicePushReq;
import com.exflyer.oddi.admin.api.adv.make.dto.DevicePushRes;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.config.DevicePushConfig;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.AdvFile;
import com.exflyer.oddi.admin.models.Broadcasting;
import com.exflyer.oddi.admin.models.PartnerDefaultAdv;
import com.exflyer.oddi.admin.models.PartnerDevice;
import com.exflyer.oddi.admin.models.PartnerDevicePushHistory;
import com.exflyer.oddi.admin.repository.AdvFileRepository;
import com.exflyer.oddi.admin.repository.BroadcastingRepository;
import com.exflyer.oddi.admin.repository.PartnerDefailtAdvRepository;
import com.exflyer.oddi.admin.repository.PartnerDevicePushHistoryRepository;
import com.exflyer.oddi.admin.repository.PartnerDeviceRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class OddiAdvMakeService {

    @Autowired
    private PartnerDeviceRepository partnerDeviceRepository;
    @Autowired
    private PartnerDefailtAdvRepository partnerDefailtAdvRepository;
    @Autowired
    private BroadcastingRepository broadcastingRepository;
    @Autowired
    private AdvFileRepository advFileRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private Gson gson;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private DevicePushConfig devicePushConfig;
    @Autowired
    private PartnerDevicePushHistoryRepository partnerDevicePushHistoryRepository;

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void modify(AdvMakeModReq advMakeModReq) throws ApiException {

        if(!advMakeModReq.getDeviceId().equals("null")) {
            Optional<PartnerDevice> advMakeOptional = partnerDeviceRepository.findByDeviceIdByPartnerSeq(advMakeModReq.getSeq(),advMakeModReq.getDeviceId());
            PartnerDevice partnerDevice = advMakeOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

            partnerDevice.setDeviceId(advMakeModReq.getDeviceId());
            partnerDevice.setDisplayDiv(advMakeModReq.getDisplayDiv());
            partnerDevice.setSideContentsType(advMakeModReq.getSideContentsType());
            partnerDevice.setBottomContentsType(advMakeModReq.getBottomContentsType());
            partnerDevice.setRegId(advMakeModReq.getRegId());
            partnerDevice.setRegDate(advMakeModReq.getRegDate());

            partnerDeviceRepository.save(partnerDevice);
        }

        Optional<PartnerDefaultAdv> partnerDefaultAdvOptional = partnerDefailtAdvRepository.findByChannelTypeByPartnerSeq(advMakeModReq.getSeq());
        PartnerDefaultAdv partnerDefaultAdv = partnerDefaultAdvOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
        partnerDefaultAdv.setExpo(advMakeModReq.getDefaultAdvExpo());
        partnerDefailtAdvRepository.save(partnerDefaultAdv);

        if (advMakeModReq.getAdvExpoList() != null) {
            advMakeModReq.getAdvExpoList().forEach(datas -> {
                Optional<Broadcasting> advOptional = broadcastingRepository.findByAdvSeqByPartnerSeq(datas.getAdvSeq(), advMakeModReq.getSeq(), advMakeModReq.getDeviceId());
                try {
                    Broadcasting broadcasting = advOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                    broadcasting.setOrdering(datas.getViewOrder());
                    broadcasting.setExpo(datas.getExpo());
                    broadcastingRepository.save(broadcasting);

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            });
        }

        List<AdvFile> newFileSeqItems = advMakeModReq.getAdvFileList();

        List<Long> l_newFileSeqItems = new ArrayList<>();
        newFileSeqItems.forEach(datas -> {
            l_newFileSeqItems.add(datas.getFileSeq());
        });

        // 변경전 fileSeq추출
        List<Long> isNotInFileSeq = advFileRepository.isNotInFileSeq(advMakeModReq.getSeq(), l_newFileSeqItems);

        advMakeModReq.getAdvFileList().forEach(datas -> {
            advFileRepository.deleteAdvFile(datas.getAdvSeq());
        });

        advFileRepository.saveAll(newFileSeqItems);

        fileService.updateMappingDone(l_newFileSeqItems);

        if (isNotInFileSeq != null) {
            fileService.delete(isNotInFileSeq);
        }
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public DevicePushRes devicePush(Long partnerSeq, String deviceId, String action, AdvMakeModReq advMakeModReq) throws IOException, ApiException {

        if(advMakeModReq != null)modify(advMakeModReq);

        DevicePushReq devicePushReq = new DevicePushReq();
        devicePushReq.setDevice_id(deviceId);
        devicePushReq.setAction(action);

        HttpPost httpPost = new HttpPost(devicePushConfig.getHostUrl() + "/devicePush");
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setEntity(new StringEntity(gson.toJson(devicePushReq), "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);

        String resBody = EntityUtils.toString(response.getEntity());
        DevicePushRes devicePushRes = gson.fromJson(resBody, DevicePushRes.class);


        PartnerDevicePushHistory partnerDevicePushHistory = new PartnerDevicePushHistory();
        partnerDevicePushHistory.setPartnerSeq(partnerSeq);
        partnerDevicePushHistory.setDeviceId(deviceId);
        partnerDevicePushHistory.setAction(action);
        partnerDevicePushHistory.setRegDate(LocalDateUtils.krNow());
        partnerDevicePushHistory.setSuccess(devicePushRes.getSuccess());
        partnerDevicePushHistory.setFailMessage(devicePushRes.getResults().get(0).getError());

        partnerDevicePushHistoryRepository.save(partnerDevicePushHistory);

        return devicePushRes;

    }
}
