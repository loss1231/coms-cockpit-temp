package com.carerobot.cockpit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.dto.CareBoxArchiveDto;
import com.carerobot.cockpit.dto.CareBoxLivingDto;
import com.carerobot.cockpit.mapper.CockpitArchivesMapper;
import com.carerobot.cockpit.model.*;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.carerobot.cockpit.service.CockpitFloorInfoService;
import com.carerobot.cockpit.service.ContractCohabitantService;
import com.carerobot.cockpit.service.RoomInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Service
public class CockpitArchivesServiceImpl extends ServiceImpl<CockpitArchivesMapper,CockpitArchives> implements CockpitArchivesService {

    private Logger logger = LoggerFactory.getLogger(CockpitArchivesServiceImpl.class);

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

    @Override
    public String getCode(Integer id) {
        CockpitArchives archives = this.getById(id);
        if (archives==null || "".equals(archives.getCareboxCode()))return null;
        return archives.getCareboxCode();
    }

    @Override
    public Boolean updateCareboxCode(Integer id, String token,Integer orgId) {
        CockpitArchives cockpitArchives = this.cockpitArchivesService.getById(id);
        //报表没有此客户   增加客户
        String careboxToken = this.getCareboxToken(orgId);
        if (careboxToken == null)return false;
        if (cockpitArchives == null) {
            CockpitArchives archives = this.getArchiveForCrm(id, token, orgId);
            this.save(archives);
            cockpitArchives = archives;

            String careboxCode = this.addArchive(orgId, cockpitArchives, careboxToken);
            if (careboxCode == null)return false;

            cockpitArchives.setCareboxCode(careboxCode);
            this.updateById(cockpitArchives);
            return true;
        }

        //有careboxcode
//        if (!"".equals(cockpitArchives.getCareboxCode()) || cockpitArchives.getCareboxCode() != null){
//            Boolean flag = this.flagForCareboxCode(careboxToken, cockpitArchives.getCareboxCode(), orgId);
//            if (flag){
////                if (cockpitArchives.getBedInfoBedNo() != null && !"".equals(cockpitArchives.getBedInfoBedNo())){
////                    this.updateArchiveLive(orgId,cockpitArchives,careboxToken);
////                }
//                return true;
//            }else{
//                String careboxCode = this.addArchive(orgId, cockpitArchives, careboxToken);
//                if (careboxCode == null)return false;
//
//                cockpitArchives.setCareboxCode(careboxCode);
//                this.updateById(cockpitArchives);
//
//                //carebox更新入住
////                if (cockpitArchives.getBedInfoBedNo() != null && !"".equals(cockpitArchives.getBedInfoBedNo())){
////                    this.updateArchiveLive(orgId,cockpitArchives,careboxToken);
////                }
//            }
//        }else{
            if (cockpitArchives.getCareboxCode()==null || "".equals(cockpitArchives.getCareboxCode())){
                String careboxCode = this.addArchive(orgId, cockpitArchives, careboxToken);
                if (careboxCode == null)return false;

                cockpitArchives.setCareboxCode(careboxCode);
                this.updateById(cockpitArchives);
//
//            //carebox更新入住
//            if (cockpitArchives.getBedInfoBedNo() != null && !"".equals(cockpitArchives.getBedInfoBedNo())){
//                this.updateArchiveLive(orgId,cockpitArchives,careboxToken);
//            }
                return true;
            }
        return true;
    }

    @Override
    public String updateNurseLevel(String code, Integer nurseLevel, String nurseLevelStr) {
        CockpitArchives careboxCode = this.getOne(new QueryWrapper<CockpitArchives>().eq("CareboxCode", code).eq("IsDeleted",0));
//        if (careboxCode==null){
//            return "客户不存在！";
//        }
//        careboxCode.setNurseLevel(nurseLevel.toString());
//        careboxCode.setNurseUnit(nurseLevelStr);
//        this.updateById(careboxCode);
        //HttpEntity<String> Entity = new HttpEntity<String>(null, null);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(Url.url + "crm/api/webapi/GetNurseLevel?archiveId="+careboxCode.getId()+"&nurseLevel="+nurseLevel+"&nurseLevelStr="+nurseLevelStr, HttpMethod.GET, null, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @Override
    public void createOccupancyTable(String tableName) {
        this.baseMapper.createOccupancyTable(tableName);
    }

    @Override
    public List<newOccupancyRate> searchOccupancy(Integer i, Date date, Date now) {
        return this.baseMapper.searchOccupancy(i,date,now);
    }

    @Override
    public void insertOccupancy(String tableName, List<newOccupancyRate> list) {
        this.baseMapper.insertOccupancy(tableName,list);
    }

    @Override
    public void createElderDetailTable(String tableName) {
        this.baseMapper.createElderDetailTable(tableName);
    }

    @Override
    public List<ElderDetail> searchElderDetail() {
        return this.baseMapper.searchElderDetail();
    }

    @Override
    public void insertElderDetail(String tableName, List<ElderDetail> list) {
        this.baseMapper.insertElderDetail(tableName,list);
    }

    private CockpitArchives getArchiveForCrm(Integer id,String token,Integer orgId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorizatior", token);
        HttpEntity<String> Entity = new HttpEntity<String>(null, headers);
        String url = Url.url + "crm/api/webapi/GetCustomerinfo?id=" + id;
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, Entity, String.class);
        String body = result.getBody();
        String jsonString = JSON.parseObject(JSON.parseObject(body).getString("data")).getString("archivesInfoDto");
        CockpitArchives archives = JSON.parseObject(jsonString, CockpitArchives.class);
        return archives;
    }

    private String getCareboxToken(Integer orgId){
        String url = null;
        HashMap<String, String> map = new HashMap<>();
        if (orgId == 1) {
            url = "pujiang";
            map.put("name","pujiang");
            map.put("password","PuJiang@123");
        } else if (orgId == 4) {
            url = "beijing";
            map.put("name","beijing");
            map.put("password","BeiJing@123");
        }else if (orgId == 2){
            url = "baoshan";
            map.put("name","baoshan");
            map.put("password","BaoShan@123");
        }else if (orgId == 3){
            url = "baoshan2";
            map.put("name","admin");
            map.put("password","Gps@123");
        }else if (orgId == 5){
            url = "ningbo";
            map.put("name","ningbo");
            map.put("password","NingBo@123");
        }else if (orgId == 6){
            url = "suzhou";
            map.put("name","suzhou");
            map.put("password","SuZhou@123");
        }else if (orgId == 7){
            url = "tianjin";
            map.put("name","tianjin");
            map.put("password","TianJin@123");
        }

        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.carebox+url+"/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");
        return token;
    }

    private String addArchive(Integer orgId,CockpitArchives archivesinfo,String token) {
        String url = null;
        if (orgId == 1) {
            url = "pujiang";
        } else if (orgId == 4) {
            url = "beijing";
        }else if (orgId == 2){
            url = "baoshan";
        }else if (orgId == 3){
            url = "baoshan2";
        }else if (orgId == 5){
            url = "ningbo";
        }else if (orgId == 6){
            url = "suzhou";
        }else if (orgId == 7){
            url = "tianjin";
        }

        String careboxUrl = Url.carebox + url + "/resident/";
        CareBoxArchiveDto resident_add = null;
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization",token);
        headers.setBearerAuth(token);
        HashMap<String, CareBoxArchiveDto> jsonMap = new HashMap<>();
        resident_add = new CareBoxArchiveDto();
        resident_add.setCountry("中国");
        resident_add.setAddress(archivesinfo.getNativePlaceAddress());
        resident_add.setResident_status(1);
        if (archivesinfo.getBirthday() != null) {
            resident_add.setBirthday(archivesinfo.getBirthday().getTime());
        }
        resident_add.setDescription(archivesinfo.getRemarks());
        resident_add.setGender(archivesinfo.getGender());
        resident_add.setId_card(archivesinfo.getCredentialsNo());
        resident_add.setId_card_type(archivesinfo.getCredentialsId());
        resident_add.setName(archivesinfo.getName());
        resident_add.setPhone(archivesinfo.getCellPhoneNumber());
        if (archivesinfo.getPhotoImgUrl() != null) resident_add.setAvatar(archivesinfo.getPhotoImgUrl());

        jsonMap.put("resident_add", resident_add);
        String jsonString = JSON.toJSONString(jsonMap);

        HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
        ResponseEntity<String> add = restTemplate.exchange(careboxUrl, HttpMethod.POST, Entity, String.class);
        JSONObject archivesJson = JSONObject.parseObject(add.getBody());
        try {
            System.out.println(add.getBody());
            return JSONObject.parseObject(archivesJson.getString("data")).getString("code");
        } catch (Exception e) {
            logger.info("推送失败，失败Id为：" + archivesinfo.getId());
        }
        return null;
    }


    private Boolean updateArchiveLive(Integer orgId,CockpitArchives archivesinfo,String token) {
        String url = null;
        if (orgId == 1) {
            url = "pujiang";
        } else if (orgId == 4) {
            url = "beijing";
        }

        CareBoxLivingDto resident_add = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        resident_add = new CareBoxLivingDto();
        if (orgId == 1){
            resident_add.setBuilding("10770759317217894400");
        }else if (orgId == 4){
            resident_add.setBuilding("11002749488422866944");
        }

        resident_add.setProject("default");
        resident_add.setOccupancy_status(1);
        if (archivesinfo.getBedInfoBedNo() != null && !archivesinfo.getBedInfoBedNo().equals("")){
            CockpitBedInfo bed = this.cockpitRoomBedinfoService.getOne(new QueryWrapper<CockpitBedInfo>().eq("BedNo", archivesinfo.getBedInfoBedNo()).eq("OrgId",orgId));
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

        if (archivesinfo.getRemarks()!=null){
            resident_add.setDescription(archivesinfo.getRemarks());
        }


        String jsonString = JSON.toJSONString(resident_add);

        HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
        String careboxUrl = Url.carebox+url+"/resident/living_info/?resident_code="+archivesinfo.getCareboxCode();
        ResponseEntity<String> add = restTemplate.exchange(Url.carebox + url + "/resident/living_info/?resident_code="+archivesinfo.getCareboxCode(), HttpMethod.PUT, Entity, String.class);

        return true;

    }

    //判断carebox是否存在客户
    private Boolean flagForCareboxCode(String token,String careboxCode,Integer orgId){
        String url = null;
        if (orgId == 1){
            url = "pujiang";
        }else if (orgId == 4){
            url = "beijing";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String careboxUrl = Url.carebox+url+"/resident/"+careboxCode+"/";
        ResponseEntity<String> flag = restTemplate.exchange(careboxUrl, HttpMethod.GET, entity, String.class);
        JSONObject object = JSON.parseObject(flag.getBody());
        int code = Integer.parseInt(object.getString("code"));
        return code == 200;
    }
}
