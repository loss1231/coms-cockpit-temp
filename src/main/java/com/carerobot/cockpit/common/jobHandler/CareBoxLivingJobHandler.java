package com.carerobot.cockpit.common.jobHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.dto.CareBoxArchiveDto;
import com.carerobot.cockpit.dto.CareBoxLivingDto;
import com.carerobot.cockpit.model.*;
import com.carerobot.cockpit.service.*;
import com.carerobot.cockpit.service.impl.CockpitRoomBedinfoServiceImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Component
public class CareBoxLivingJobHandler {

    private Logger logger = LoggerFactory.getLogger(CareBoxArchiveJobHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @Autowired
    private CockpitRoomBedinfoServiceImpl cockpitRoomBedinfoService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private CockpitFloorInfoService cockpitFloorInfoService;

    @Autowired
    private ContractCohabitantService contractCohabitantService;

    @XxlJob("CareBoxLivingJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","pujiang");
        map.put("password","PuJiang@123");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.carebox+"pujiang/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");

        logger.info("获取token成功，token为："+token);
        //this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>().eq("IsDeleted",false).isNotNull("CareboxCode").eq("Id",2811));
        List<CockpitArchives> list = this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>()
                .eq("IsDeleted",false).ne("CareboxCode","").eq("OrgId",1));
        CareBoxLivingDto resident_add = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        int i = 1;
        for (CockpitArchives archivesinfo : list) {
            resident_add = new CareBoxLivingDto();
            resident_add.setBuilding("10770759317217894400");
            resident_add.setProject("default");
            resident_add.setOccupancy_status(1);
//            if(archivesinfo.getBedInfoBedNo() != null && !archivesinfo.getBedInfoBedNo().equals("")){
//
//            }else if (archivesinfo.getRegisterStatus() == null){
//                continue;
//                //resident_add.setOccupancy_status(0);
//            }else if (archivesinfo.getRegisterStatus().equals("新登记")){
//                continue;
//                //resident_add.setOccupancy_status(0);
//            }else if(archivesinfo.getRegisterStatus().equals("出院")){
//                resident_add.setOccupancy_status(2);
//            }else if(archivesinfo.getRegisterStatus().equals("死亡")){
//                resident_add.setOccupancy_status(5);
//            }else{
//                continue;
//                //resident_add.setOccupancy_status(0);
//            }
//            if (archivesinfo.getBedId()!=null && archivesinfo.getBedId()!=0){
//                CockpitBedInfo bedInfo = this.cockpitRoomBedinfoService.getById(archivesinfo.getBedId());
//                resident_add.setNumber(bedInfo.getCareBoxCode());
//                RoomInfo roomInfo = this.roomInfoService.getById(archivesinfo.getRoomId());
//                CockpitFloorInfo floorInfo = this.cockpitFloorInfoService.getById(roomInfo.getFloorInfoId());
//                resident_add.setFloor(floorInfo.getCareBoxCode());
//            }else {
//                resident_add.setNumber("");
//                resident_add.setFloor("");
//            }

            if (archivesinfo.getBedInfoBedNo() != null && !archivesinfo.getBedInfoBedNo().equals("")){
                CockpitBedInfo bed = this.cockpitRoomBedinfoService.getOne(new QueryWrapper<CockpitBedInfo>().eq("BedNo", archivesinfo.getBedInfoBedNo()).eq("OrgId",1));
                RoomInfo room = this.roomInfoService.getById(bed.getRoomInfoId());
                CockpitFloorInfo floor = this.cockpitFloorInfoService.getById(room.getFloorInfoId());
                if (archivesinfo.getAreaId() == 1){
                    resident_add.setNursing_type(0);
                }else if(archivesinfo.getAreaId() == 2){
                    resident_add.setNursing_type(1);
                }
                resident_add.setFloor(floor.getCareBoxCode());
                resident_add.setNumber(bed.getCareBoxCode());
            }
            StringBuilder liveName = new StringBuilder("");
            List<ContractCohabitant> contractCohabitants = this.contractCohabitantService
                    .list(new QueryWrapper<ContractCohabitant>().eq("ArchiveInfoId", archivesinfo.getId()).eq("IsDeleted",false));
            List<ContractCohabitant> contractCohabitant = this.contractCohabitantService
                    .list(new QueryWrapper<ContractCohabitant>().eq("ArchivesId", archivesinfo.getId()).eq("IsDeleted",false));
            if (!contractCohabitants.isEmpty()){
                for (ContractCohabitant cohabitant : contractCohabitants) {
                    liveName.append(cohabitant.getLiveName()).append(" ");
                }
            }
            if (!contractCohabitant.isEmpty()){
                for (ContractCohabitant cohabitant : contractCohabitant) {
                    CockpitArchives name = this.cockpitArchivesService.getById(cohabitant.getArchiveInfoId());
                    liveName.append(name.getName());
                }
            }
            if (!liveName.toString().equals("")){
                resident_add.setCohabitant_name(liveName.toString());
                resident_add.setLiving_state(0);
            }else{
                resident_add.setLiving_state(1);
                resident_add.setCohabitant_name("");
            }
//
//            if (archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 1){
//                resident_add.setNursing_type(0);
//            }else if(archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 2){
//                resident_add.setNursing_type(1);
//            }

            if (archivesinfo.getRemarks()!=null){
                resident_add.setDescription(archivesinfo.getRemarks());
            }


            String jsonString = JSON.toJSONString(resident_add);

            HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
            ResponseEntity<String> add = restTemplate.exchange(Url.carebox+"pujiang/resident/living_info/?resident_code="+archivesinfo.getCareboxCode(), HttpMethod.PUT, Entity, String.class);

            Integer code = JSON.parseObject(add.getBody()).getInteger("code");
            if (code == 200) {
                System.out.println("修改成功----------"+i);

                i++;
            }else{
                System.out.println("--------修改失败----------id为"+archivesinfo.getId());
                System.out.println(JSON.parseObject(add.getBody()).getString("error_msg"));
            }
        }
        return ReturnT.SUCCESS;
    }
}
