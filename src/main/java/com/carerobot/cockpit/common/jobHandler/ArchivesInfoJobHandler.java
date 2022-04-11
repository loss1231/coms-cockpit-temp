package com.carerobot.cockpit.common.jobHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Component
public class ArchivesInfoJobHandler {

    private Logger logger = LoggerFactory.getLogger(ArchivesInfoJobHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private ArchivesinfoService archivesinfoService;

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

//    @XxlJob("ArchivesInfoEntireTableJobHandler")
//    public ReturnT<String> executorAllData(String param) {
//        logger.info("客户数据同步定时任务日志");
//        List<Archivesinfo> dataList = this.archivesinfoService.getAllDataFromCrm();
//        if(!dataList.isEmpty()){
//            logger.info("客户数据拉取结束,拉取条数：{}",dataList.size());
//            archivesinfoService.updateThenAdd(dataList);
//        }
//        return ReturnT.SUCCESS;
//    }

    @XxlJob("ArchivesInfoEntireTableJobHandler")
    public ReturnT<String> executorAllData(String param) {
        System.out.println("--------------------------------------");
        this.archivesinfoService.updateThenAdd(this.archivesinfoService.getEntireArchivesinfoFromCrm(),null);
        return ReturnT.SUCCESS;
    }

    @XxlJob("UpDateArchivesInfoJobHandler")
    public ReturnT<String> updateAllData(String param) {
        System.out.println("--------------------------------------");
        this.archivesinfoService.updateAll(this.archivesinfoService.getArchivesinfoFromCrm());
        return ReturnT.SUCCESS;
    }

    @XxlJob("SendArchivesToCare")
    public ReturnT<String> sendArchivesToCare(String param) {
        System.out.println("--------------------------------------");
        List<CockpitArchives> archivesinfos = this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>().eq("IsDeleted", false).eq("OrgId",7));
        HashMap<String, Object> map = new HashMap<>();

        HttpEntity requestEntity = new HttpEntity(map, null);
        for (CockpitArchives archivesinfo : archivesinfos) {
            try {
                map.put("Id",archivesinfo.getId()+1000000);
                map.put("OrgId",archivesinfo.getOrgId());
                map.put("Name",archivesinfo.getName());
                map.put("Gender",archivesinfo.getGender());
                map.put("CardNumber",archivesinfo.getCredentialsNo());
                map.put("Birthday",DateUtils.format(archivesinfo.getBirthday(),"yyyy-MM-dd HH:mm:ss"));
                map.put("PhotoImgUrl",archivesinfo.getPhotoImgUrl());
                map.put("MaritalStatus",archivesinfo.getMaritalStatusStr());
                map.put("BloodType",archivesinfo.getBloodTypeId());
                map.put("CulturalDegree",archivesinfo.getCulturalDegreeId());
                map.put("CellPhoneNumber",archivesinfo.getCellPhoneNumber());
                map.put("NativePlaceProvince",archivesinfo.getNativePlaceProvinceId());
                map.put("NativePlaceCity",archivesinfo.getNativePlaceCityId());
                map.put("NativePlaceAddress",archivesinfo.getNativePlaceAddress());
                map.put("LiveProvince",archivesinfo.getLiveProvinceId());
                map.put("LiveCity",archivesinfo.getLiveCityId());
                map.put("LiveAddress",archivesinfo.getLiveAddress());
                map.put("Age",archivesinfo.getAge());
                map.put("Area",archivesinfo.getArea());
                map.put("RoomNo",archivesinfo.getBedInfoBedNo());
                map.put("BedNo",archivesinfo.getBedInfoBedNo());
                map.put("NurseLevel",archivesinfo.getNurseLevel());
                map.put("IsDeleted",false);
                map.put("StatusCode",archivesinfo.getLiveStatus());
                map.put("NurseLevelId",archivesinfo.getNurseLevel());
                //https://test-carerobot.sungin.com.cn/care
                ResponseEntity<String> exchange = this.restTemplate.exchange("http://47.117.36.181:8001/care/api/carecustomercontroller/addcustomer", HttpMethod.POST, requestEntity, String.class);
                System.out.println(exchange.getBody());
                map.clear();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return ReturnT.SUCCESS;
    }
}
