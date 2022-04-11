package com.carerobot.cockpit.common.jobHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.dto.CareBoxArchiveDto;
import com.carerobot.cockpit.dto.CareBoxArchiveUpdateDto;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.model.CockpitBedInfo;
import com.carerobot.cockpit.model.CockpitFloorInfo;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.carerobot.cockpit.service.CockpitFloorInfoService;
import com.carerobot.cockpit.service.RoomInfoService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CareBoxArchiveJobHandler {

    private Logger logger = LoggerFactory.getLogger(CareBoxArchiveJobHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @Autowired
    private CockpitRoomBedinfoServiceImpl cockpitRoomBedinfoService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private CockpitFloorInfoService cockpitFloorInfoService;

    //增加用户
    @XxlJob("CareBoxArchiveJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","tianjin");
        map.put("password","TianJin@123");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.carebox+"tianjin/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");

        logger.info("获取token成功，token为："+token);

        List<CockpitArchives> list = this.cockpitArchivesService
                .list(new QueryWrapper<CockpitArchives>()
                        .eq("IsDeleted",false).eq("OrgId",7).eq("LiveStatus",2));
        CareBoxArchiveDto resident_add = null;
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization",token);
        headers.setBearerAuth(token);
        HashMap<String,CareBoxArchiveDto> jsonMap = new HashMap<>();
        System.out.println("------------------------------------------");
        for (CockpitArchives archivesinfo : list) {
            resident_add = new CareBoxArchiveDto();
            resident_add.setCountry("中国");
            resident_add.setAddress(archivesinfo.getNativePlaceAddress());
            if (archivesinfo.getBirthday() != null){
                resident_add.setBirthday(archivesinfo.getBirthday().getTime());
            }
            resident_add.setDescription(archivesinfo.getRemarks());
            resident_add.setGender(archivesinfo.getGender());
            resident_add.setId_card(archivesinfo.getCredentialsNo());
            resident_add.setId_card_type(archivesinfo.getCredentialsId());
            resident_add.setName(archivesinfo.getName());
            resident_add.setPhone(archivesinfo.getCellPhoneNumber());
            if (archivesinfo.getPhotoImgUrl()!=null)resident_add.setAvatar(archivesinfo.getPhotoImgUrl());
            resident_add.setResident_status(1);
//            if(archivesinfo.getBedInfoBedNo() != null && !archivesinfo.getBedInfoBedNo().equals("")){
//                resident_add.setResident_status(1);
//            }else if (archivesinfo.getRegisterStatus() == null){
//                resident_add.setResident_status(0);
//            }else if (archivesinfo.getRegisterStatus().equals("新登记")){
//                resident_add.setResident_status(0);
//            }else if(archivesinfo.getRegisterStatus().equals("出院")){
//                resident_add.setResident_status(2);
//            }else if(archivesinfo.getRegisterStatus().equals("死亡")){
//                resident_add.setResident_status(5);
//            }else{
//                resident_add.setResident_status(0);
//            }
            jsonMap.put("resident_add",resident_add);
            String jsonString = JSON.toJSONString(jsonMap);

            HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
            ResponseEntity<String> add = restTemplate.exchange(Url.carebox+"tianjin/resident/", HttpMethod.POST, Entity, String.class);
            JSONObject archivesJson = JSONObject.parseObject(add.getBody());
            try{
                System.out.println(add.getBody());
                String careboxCode = JSONObject.parseObject(archivesJson.getString("data")).getString("code");
                archivesinfo.setCareboxCode(careboxCode);
                this.cockpitArchivesService.updateById(archivesinfo);
                logger.info("推送一条数据，会写Id为："+careboxCode);
                System.out.println("---------------------------------------------------------------------------");
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(archivesinfo.getId());
                System.out.println("---------------------------------------------------------------------------");
                logger.info("推送失败，失败Id为："+archivesinfo.getId());
            }

        }

        return ReturnT.SUCCESS;
    }

    @XxlJob("UpdataCareBoxArchiveJobHandler")
    public ReturnT<String> updateEntireData(String param) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","admin");
        map.put("password","Gps@123");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange("https://test.api.carebox.sungin.com.cn/gps/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");

        logger.info("获取token成功，token为："+token);

        List<CockpitArchives> list = this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>().isNotNull("CareboxCode"));
        System.out.println(list.size()+"----------------------------");
        CareBoxArchiveUpdateDto updateDto = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        int i= 0;
        for (CockpitArchives cockpitArchive : list) {
            updateDto = new CareBoxArchiveUpdateDto();
            updateDto.setAddress(cockpitArchive.getNativePlaceAddress());
            updateDto.setBirthday(cockpitArchive.getBirthday());
            updateDto.setDescription(cockpitArchive.getRemarks());
            updateDto.setGender(cockpitArchive.getGender());
            updateDto.setId_card(cockpitArchive.getCredentialsNo());
            updateDto.setId_card_type(cockpitArchive.getCredentialsId());
            updateDto.setName(cockpitArchive.getName());
            updateDto.setPhone(cockpitArchive.getCellPhoneNumber());

            if(cockpitArchive.getBedInfoBedNo() != null && !cockpitArchive.getBedInfoBedNo().equals("")){
                updateDto.setResident_status(1);
            }else if (cockpitArchive.getRegisterStatus() == null){
                updateDto.setResident_status(0);
            }else if (cockpitArchive.getRegisterStatus().equals("新登记")){
                updateDto.setResident_status(0);
            }else if(cockpitArchive.getRegisterStatus().equals("出院")){
                updateDto.setResident_status(2);
            }else{
                updateDto.setResident_status(0);
            }


            updateDto.setAvatar(cockpitArchive.getPhotoImgUrl());

            HashMap<String,Object> jsonMap = new HashMap<>();
            jsonMap.put("resident_update",updateDto);
            jsonMap.put("gpstracker_resident_to_disease_code_list",new ArrayList<>());
            String jsonString = JSONObject.toJSONString(jsonMap);
            HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
            try{
                restTemplate.exchange("https://test.api.carebox.sungin.com.cn/gps/resident/"+cockpitArchive.getCareboxCode()+"/", HttpMethod.PUT, Entity, String.class);
            }catch (Exception e){
                System.out.println(cockpitArchive.getId());
            }
            i++;
            System.out.println("推送成功--"+i);
        }
        System.out.println("推送完成---------------------------------------------");
        return ReturnT.SUCCESS;
    }

    @XxlJob("CareBoxSpaceJobHandler")
    public ReturnT<String> careBoxSpaceJobHandler(String param) {
        String token = this.login("ningbo","NingBo@123","ningbo");

        logger.info("获取token成功，token为："+token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> Entity = new HttpEntity<String>("", headers);
        //获取楼层
        List<CockpitFloorInfo> floorInfoList = this.cockpitFloorInfoService.list(new QueryWrapper<CockpitFloorInfo>().ne("CareBoxCode", "").isNotNull("CareBoxCode").eq("OrgId",4));
        for (CockpitFloorInfo floorInfo : floorInfoList) {

            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(Url.carebox+"beijing/resident/building/floor/number/default/"+floorInfo.getCareBoxCode()+"/", HttpMethod.GET, Entity, String.class);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            List<String> list = JSON.parseArray(jsonObject.getString("data"), String.class);
            for (String s : list) {
                JSONObject parseObject = JSON.parseObject(s);
                String code = parseObject.getString("code");
                String name = parseObject.getString("name").replace(" ","-");
                CockpitBedInfo bedNo = this.cockpitRoomBedinfoService.getOne(new QueryWrapper<CockpitBedInfo>().eq("BedNo", name).eq("OrgId",4));
                if (bedNo == null) {System.out.println(name);continue;}
                bedNo.setCareBoxCode(code);
                this.cockpitRoomBedinfoService.updateById(bedNo);
                System.out.println("同步成功 房间号  ------------ " + name);
            }

        }
        return ReturnT.SUCCESS;
    }

    @XxlJob("CareBoxCodeJobHandler")
    public ReturnT<String> CareBoxCodeJobHandler(String param) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","beijing");
        map.put("password","BeiJing@123");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.carebox+"beijing/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");

        logger.info("获取token成功，token为："+token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> Entity = new HttpEntity<String>("", headers);

        List<CockpitArchives> list = this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>().isNull("CareboxCode").eq("IsDeleted",false).eq("OrgId",4));
        for (CockpitArchives cockpitArchives : list) {
            ResponseEntity<String> response = restTemplate.exchange(Url.carebox+"beijing/resident/?name="+cockpitArchives.getName(), HttpMethod.GET, Entity, String.class);
            try{
                String code = JSON.parseObject(JSON.parseObject(JSON.parseObject(response.getBody()).getString("data")).getJSONArray("resident_list").getString(0)).getString("code");
                cockpitArchives.setCareboxCode(code);
                this.cockpitArchivesService.updateById(cockpitArchives);
            }catch (Exception e){
                System.out.println(response.getBody());
            }

        }
        return ReturnT.SUCCESS;
    }

    private String login(String userName,String pwd,String address){
        HashMap<String, String> map = new HashMap<>();
        map.put("name",userName);
        map.put("password",pwd);
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.carebox + address + "/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");
        return token;
    }

}
