package com.carerobot.cockpit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.dto.*;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.enums.AccidentType;
import com.carerobot.cockpit.model.Account;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.mapper.ArchivesinfoMapper;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.model.LeaveApplication;
import com.carerobot.cockpit.service.AccidentReportService;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.carerobot.cockpit.service.LeaveApplicationService;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-05-13
 */
@Service
public class ArchivesinfoServiceImpl extends ServiceImpl<ArchivesinfoMapper, Archivesinfo> implements ArchivesinfoService {
    private Logger logger = LoggerFactory.getLogger(ArchivesinfoServiceImpl.class);

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AccidentReportService accidentReportService;

    @Autowired
    public ArchivesinfoService archivesinfoService;

    @Autowired
    public LeaveApplicationService leaveApplicationService;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Resource(name = "db3")
    public javax.sql.DataSource dataSource;

    public ElderLeaveTranDto getLeaveApplicationTable(Date dateStart, Date dateEnd, Integer areaId, Integer liveType) {

        ElderLeaveTranDto elderLeaveTranDto = new ElderLeaveTranDto();

        //请假类型不确定    暂定 1是事假  2是病假
        List<Archivesinfo> leaveApplications = this.baseMapper.selectLeaveForTable(areaId, 1, dateStart, dateEnd, liveType);

        int alCount = 0;
        int ilCount = 0;
        List<ElderLeaveDto> dtos = new ArrayList<>();

        for (Archivesinfo archivesinfo : leaveApplications) {

            Long dateCount = 0L;
            ElderLeaveDto dto = new ElderLeaveDto();
            dto.setName(archivesinfo.getName());
            dto.setRoomNo(archivesinfo.getRoomId().toString());
            //缺少入住类型枚举

            for (LeaveApplication leaveApplication : archivesinfo.getLeaveApplications()) {

                if (leaveApplication.getStartDate() != null && leaveApplication.getEndDate() != null) {
                    dateCount += (leaveApplication.getEndDate().getTime() - leaveApplication.getStartDate().getTime()) / (1000 * 3600 * 24);
                }

                dto.setTotal(dateCount.doubleValue());
                dto.setResidentType(leaveApplication.getLeaveTypeId());
                dto.setReason(leaveApplication.getExplanation());
            }

            //缺少区域
            if (archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 1) {
                dto.setALDays(dateCount.intValue());
                alCount += dateCount;
            } else if (archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 2) {
                dto.setILDays(dateCount.intValue());
                ilCount += dateCount;
            }
            dtos.add(dto);
        }

        //事假统计
        ElderLeaveDto c = new ElderLeaveDto();
        c.setReason("事假 Sub Total");
        c.setILDays(ilCount);
        c.setALDays(alCount);
        c.setTotal((double) (ilCount + alCount));
        dtos.add(c);

        //请假类型不确定    暂定 1是事假  2是病假
        leaveApplications = this.baseMapper.selectLeaveForTable(areaId, 2, dateStart, dateEnd, liveType);

        int alCount2 = 0;
        int ilCount2 = 0;

        for (Archivesinfo archivesinfo : leaveApplications) {

            Long dateCount = 0L;
            ElderLeaveDto dto = new ElderLeaveDto();
            dto.setName(archivesinfo.getName());
            dto.setRoomNo(archivesinfo.getRoomId().toString());
            //缺少入住类型枚举

            for (LeaveApplication leaveApplication : archivesinfo.getLeaveApplications()) {

                if (leaveApplication.getStartDate() != null && leaveApplication.getEndDate() != null) {
                    dateCount += (leaveApplication.getEndDate().getTime() - leaveApplication.getStartDate().getTime()) / (1000 * 3600 * 24);
                }

                dto.setTotal(dateCount.doubleValue());
                dto.setResidentType(leaveApplication.getLeaveTypeId());
                dto.setReason(leaveApplication.getExplanation());
            }

            //缺少区域
            if (archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 1) {
                dto.setALDays(dateCount.intValue());
                alCount2 += dateCount;
            } else if (archivesinfo.getAreaId() != null && archivesinfo.getAreaId() == 2) {
                dto.setILDays(dateCount.intValue());
                ilCount2 += dateCount;
            }
            dtos.add(dto);
        }


        //病假统计
        ElderLeaveDto i = new ElderLeaveDto();
        i.setReason("病假 Sub Total");
        i.setILDays(ilCount2);
        i.setALDays(alCount2);
        i.setTotal((double) (ilCount + alCount));
        dtos.add(i);

        ElderLeaveDto total = new ElderLeaveDto();
        total.setReason("Total");
        total.setALDays(c.getALDays()+i.getALDays());
        total.setILDays(c.getILDays()+i.getILDays());
        dtos.add(total);

        elderLeaveTranDto.setCompassionate(dtos);
        return elderLeaveTranDto;
    }

    @Override
    public HashMap<String, List<AccidentReportDto>> getAccidentReport(Date dateStart, Date dateEnd) {

        HashMap<String, List<AccidentReportDto>> map = new HashMap<>();

        //事故分类统计
        ArrayList<AccidentReportDto> list = new ArrayList<>();

        //健康类
        Integer IL = this.baseMapper.selectAccdientReport(AccidentType.TYPE1002, 0,dateStart,dateEnd);
        Integer AL = this.baseMapper.selectAccdientReport(AccidentType.TYPE1002, 1,dateStart,dateEnd);
        AccidentReportDto dto = new AccidentReportDto();
        dto.setValues("健康类");
        dto.setIL(IL);
        dto.setAL(AL);
        dto.setTotal((double)(IL+AL));
        list.add(dto);

        //安全类
        IL = this.baseMapper.selectAccdientReport(AccidentType.TYPE1001, 0,dateStart,dateEnd);
        AL = this.baseMapper.selectAccdientReport(AccidentType.TYPE1001, 1,dateStart,dateEnd);
        dto = new AccidentReportDto();
        dto.setValues("安全类");
        dto.setIL(IL);
        dto.setAL(AL);
        dto.setTotal((double)(IL+AL));
        list.add(dto);

        //统计
        dto = new AccidentReportDto();
        dto.setValues("TOTAL");
        dto.setIL(list.get(0).getIL()+list.get(1).getIL());
        dto.setAL(list.get(0).getAL()+list.get(1).getAL());
        dto.setTotal(list.get(0).getTotal()+list.get(1).getTotal());
        list.add(dto);

        map.put("Accident",list);

        //应对措施
        list = new ArrayList<>();

        //救护治疗
        dto = new AccidentReportDto();
        IL = this.baseMapper.selectResponse(0,true,null,null,dateStart,dateEnd);
        AL = this.baseMapper.selectResponse(1,true,null,null,dateStart,dateEnd);
        dto.setValues("救护治疗");
        dto.setIL(IL);
        dto.setAL(AL);
        dto.setTotal((double)(IL+AL));
        list.add(dto);

        //救护车
        dto = new AccidentReportDto();
        IL = this.baseMapper.selectResponse(0,null,true,null,dateStart,dateEnd);
        AL = this.baseMapper.selectResponse(1,null,true,null,dateStart,dateEnd);
        dto.setValues("救护治疗");
        dto.setIL(IL);
        dto.setAL(AL);
        dto.setTotal((double)(IL+AL));
        list.add(dto);

        //报警
        dto = new AccidentReportDto();
        IL = this.baseMapper.selectResponse(0,null,null,true,dateStart,dateEnd);
        AL = this.baseMapper.selectResponse(1,null,null,true,dateStart,dateEnd);
        dto.setValues("救护治疗");
        dto.setIL(IL);
        dto.setAL(AL);
        dto.setTotal((double)(IL+AL));
        list.add(dto);

        //统计
        dto = new AccidentReportDto();
        dto.setValues("TOTAL");
        dto.setIL(list.get(0).getIL()+list.get(1).getIL()+list.get(2).getIL());
        dto.setAL(list.get(0).getAL()+list.get(1).getAL()+list.get(2).getAL());
        dto.setTotal(list.get(0).getTotal()+list.get(1).getTotal()+list.get(2).getTotal());
        list.add(dto);

        map.put("Response",list);

        return map;
    }

    @Override
    @DataSource(DataSourceEnum.DB3)
    public List<Archivesinfo> getDataFromCrm() {
        QueryWrapper<Archivesinfo> archiveInfoWrapper = new QueryWrapper<>();
        //archiveInfoWrapper.between("CreationTime", DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")
        //        , DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"));
        archiveInfoWrapper.between("CreationTime",DateUtils.addDateHours(new Date(),-1)
                        , new Date());
        return this.list(archiveInfoWrapper);
    }

    @Override
    @DataSource(DataSourceEnum.DB3)
    public List<Archivesinfo> getAllDataFromCrm() {
        //每天一次全表扫描
        QueryWrapper<Archivesinfo> wrapper = new QueryWrapper<>();
        return this.archivesinfoService.list(wrapper);
    }

    @Override
    public void createNewTempArchiveTable(String name) {
        this.baseMapper.createNewTempArchiveTable(name);
    }

    @Override
    public void dropTable(String name) {
        this.baseMapper.dropTable(name);
    }

    @Override
    public void batchInsertTempArchive(List<ArchivesJsonDto> dtos,String name){
        this.baseMapper.batchInsertTempArchive(dtos,name);
    }

    @Override
    public List<ElderStatisticsDto> getGenderForElder(Integer liveType, String name1, String name2,Date startDate,Date endDate) {
        return this.baseMapper.getGenderForElder(liveType,name1,name2,startDate,endDate);
    }

    @Override
    public List<ElderStatisticsDto> occupationForElderMap(Integer liveType,String name1, String name2,Date startDate,Date endDate) {
        return this.baseMapper.occupationForElderMap(liveType,name1,name2,startDate,endDate);
    }

    @Override
    public List<ElderStatisticsDto> diseaseForElderMap(Integer liveType,String name1, String name2,Date startDate,Date endDate) {
        return this.baseMapper.diseaseForElderMap(liveType,name1,name2,startDate,endDate);
    }

    @Override
    public List<ElderStatisticsDto> ageForElderMap(Integer liveType,String name1, String name2,Date startDate,Date endDate) {
        return this.baseMapper.ageForElderMap(liveType,name1,name2,startDate,endDate);
    }

    @Override
    public List<ElderStatisticsDto> curAgeForElderMap(Integer liveType,String name1, String name2,Date startDate,Date endDate) {
        return this.baseMapper.curAgeForElderMap(liveType,name1,name2,startDate,endDate);
    }

    @Override
    public List<ElderStatisticsDto> curageForElderMap(Integer liveType) {
        return null;
    }

    @Override
    @Transactional
    public void updateThenAdd(List<Archivesinfo> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
            List<Archivesinfo> todayData = getDataFromCrm();
            this.saveBatch(todayData);
            logger.info("插入当日新增数据结束，条数为：{}", todayData);
            logger.info("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
    }

    @Override
    @DataSource(DataSourceEnum.DB3)
    public String test() {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        try {
            SqlSessionFactory factory = sqlSessionFactory.getObject();
            factory.openSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.baseMapper.selectTest().toString();
    }

    @DataSource(DataSourceEnum.DB3)
    @Override
    public List<Archivesinfo> getEntireArchivesinfoFromCrm() {
        return this.list(new QueryWrapper<Archivesinfo>());
    }

    @DataSource(DataSourceEnum.DB3)
    @Override
    public List<Archivesinfo> getArchivesinfoFromCrm() {
        return this.list(new QueryWrapper<Archivesinfo>());
    }

    @Override
    @Transactional
    public void updateAll(List<Archivesinfo> archivesinfoFromCrm) {
        this.updateBatchById(archivesinfoFromCrm);
    }

    @Override
    public void archivesLeave() {
//        List<Archivesinfo> list = this.list(new QueryWrapper<Archivesinfo>().le("LeftTime", new Date())
//                .ne("LiveStatus",3)
//                .ne("LiveStatus",4));
//        list.stream().forEach(o -> {
//            if (o.getBedInfoBedNo() != null && !o.getBedInfoBedNo().equals("")){
//                //同住人
//                List<Archivesinfo> bedInfoBedNo = this.list(new QueryWrapper<Archivesinfo>().eq("BedInfoBedNo", o.getBedInfoBedNo()));
//                for (Archivesinfo archivesinfo : bedInfoBedNo) {
//                    if (archivesinfo.getMembershipType() == 1){
//                        archivesinfo.setLiveStatus(4);
//                        archivesinfo.setRegisterStatus("持有");
//                    }else{
//                        archivesinfo.setLiveStatus(3);
//                        archivesinfo.setRegisterStatus("出院");
//                    }
//                    archivesinfo.setArea("");
//                    archivesinfo.setAreaId(0);
//                    archivesinfo.setBedInfoBedNo("");
//                    archivesinfo.setUpdateDate(new Date());
//                }
//                this.updateBatchById(bedInfoBedNo);
//            }
//        });
        //获取token
        HashMap<String, String> map = new HashMap<>();
        map.put("oauthid","");
        map.put("password","654321");
        map.put("type","0");
        map.put("user_name","admin");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.url + "ims/api/coms/login", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String token = result.getString("data");
        System.out.println(token);

        //获取离院的人的id
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorizatior",token);
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> listResponseEntity = this.restTemplate.exchange(Url.url + "report/dischargeApplication/GetLeaveInfo", HttpMethod.GET, entity, String.class);
        JSONArray jsonArray = JSONObject.parseObject(listResponseEntity.getBody()).getJSONArray("data");
            //没有离院的人直接跳出
            if (jsonArray==null || jsonArray.toJavaList(Integer.class).isEmpty()){
                return;
            }
        logger.info("离院的人的id:"+jsonArray.toJavaList(Integer.class).toString());

        //变更离院人的信息
        HttpHeaders header = new HttpHeaders();
        header.set("Authorizatior",token);
        HttpEntity<List<Integer>> leaveEntity = new HttpEntity<List<Integer>>(jsonArray.toJavaList(Integer.class), header);
        System.out.println(leaveEntity.getBody());
        ResponseEntity<String> crm = this.restTemplate.exchange(Url.url + "crm/api/webapi/PostArchivesLeave", HttpMethod.POST, leaveEntity, String.class);
        System.out.println(crm.getBody());
        logger.info("crm信息:"+crm.getBody());

        //清空房态信息
        ResponseEntity<String> space = this.restTemplate.exchange(Url.url + "space/api/webapi/PostLeaveInfo", HttpMethod.POST, leaveEntity, String.class);
        System.out.println(space.getBody());
        logger.info("space信息:"+space.getBody());

        //将合同终止
        ResponseEntity<String> contract = this.restTemplate.exchange(Url.url + "file/api/webapi/PostLevaeContract", HttpMethod.POST, leaveEntity, String.class);
        System.out.println(contract.getBody());
        logger.info("contract信息:"+contract.getBody());

        //同步carebox
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<CockpitArchives> list = this.cockpitArchivesService.list(new QueryWrapper<CockpitArchives>().in("Id", jsonArray.toJavaList(Integer.class)));
        if (list.isEmpty())return;
        list.stream().forEach(o -> hashMap.put(o.getCareboxCode(),o.getOrgId()));
        this.putCareboxLeave(hashMap);

    }

    @Override
    @Transactional
    public void updateThenAdd(List<Archivesinfo> archivesInfo,List<Archivesinfo> archives) {
//        String token = this.getToken();
//        HttpHeaders headers = new HttpHeaders();
//        //headers.add("Authorization",token);
//        headers.setBearerAuth(token);
        //更新
        if (!archivesInfo.isEmpty()){

            for (Archivesinfo archivesinfo : archivesInfo) {
                Archivesinfo archivesinfo1 = this.archivesinfoService.getById(archivesinfo.getId());
                if (archivesinfo1 == null){
                    this.archivesinfoService.save(archivesinfo);
                }else{
                    this.updateById(archivesinfo);
                }
            }
//            List<Integer> idList = archivesInfo.stream().map(o -> o.getId()).collect(Collectors.toList());
//            Collection<CockpitArchives> cockpitArchives = this.cockpitArchivesService.listByIds(idList);
//            CareBoxArchiveUpdateDto updateDto = null;
//            for (CockpitArchives cockpitArchive : cockpitArchives) {
//                if (cockpitArchive.getCareboxCode()==null)continue;
//                updateDto = new CareBoxArchiveUpdateDto();
//                updateDto.setAddress(cockpitArchive.getNativePlaceAddress());
//                updateDto.setBirthday(cockpitArchive.getBirthday());
//                updateDto.setDescription(cockpitArchive.getRemarks());
//                updateDto.setGender(cockpitArchive.getGender());
//                updateDto.setId_card(cockpitArchive.getCredentialsNo());
//                updateDto.setId_card_type(cockpitArchive.getCredentialsId());
//                updateDto.setName(cockpitArchive.getName());
//                updateDto.setPhone(cockpitArchive.getCellPhoneNumber());
//                updateDto.setResident_status(cockpitArchive.getRegisterStatusId());
//
//                HashMap<String,Object> jsonMap = new HashMap<>();
//                jsonMap.put("resident_update",updateDto);
//                jsonMap.put("gpstracker_resident_to_disease_code_list",new ArrayList<>());
//                String jsonString = JSONObject.toJSONString(jsonMap);
//                HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
//                restTemplate.exchange("https://test.api.carebox.sungin.com.cn/gps/resident/"+cockpitArchive.getCareboxCode()+"/", HttpMethod.POST, Entity, String.class);
//            }
        }

        //新增
//        if (!archives.isEmpty()){
//            this.saveBatch(archives);
////            HashMap<String,CareBoxArchiveDto> jsonMap = new HashMap<>();
////            CareBoxArchiveDto resident_add = null;
////            CockpitArchives cockpitArchives = null;
////            for (Archivesinfo archive : archives) {
////                resident_add = new CareBoxArchiveDto();
////                resident_add.setAddress(archive.getNativePlaceAddress());
////                resident_add.setBirthday(archive.getBirthday().getTime());
////                resident_add.setDescription(archive.getRemarks());
////                resident_add.setGender(archive.getGender());
////                resident_add.setId_card(archive.getCredentialsNo());
////                resident_add.setId_card_type(archive.getCredentialsId());
////                resident_add.setName(archive.getName());
////                resident_add.setPhone(archive.getCellPhoneNumber());
////                resident_add.setResident_status(archive.getRegisterStatusId());
////
////                jsonMap.put("resident_add",resident_add);
////                String jsonString = JSON.toJSONString(jsonMap);
////
////                HttpEntity<String> Entity = new HttpEntity<String>(jsonString, headers);
////                ResponseEntity<String> add = restTemplate.exchange("https://test.api.carebox.sungin.com.cn/gps/resident/", HttpMethod.POST, Entity, String.class);
////                JSONObject archivesJson = JSONObject.parseObject(add.getBody());
////                try{
////                    cockpitArchives = new CockpitArchives();
////                    BeanUtils.copyProperties(archive,cockpitArchives);
////                    String careboxCode = JSONObject.parseObject(archivesJson.getString("data")).getString("code");
////                    cockpitArchives.setCareboxCode(careboxCode);
////                    this.cockpitArchivesService.updateById(cockpitArchives);
////                    logger.info("推送一条数据，会写Id为："+careboxCode);
////                    System.out.println("---------------------------------------------------------------------------");
////                }catch (Exception e){
////                    logger.info("推送失败，失败Id为："+cockpitArchives.getId());
////                }
////            }
//        }
    }

    @DataSource(DataSourceEnum.DB3)
    @Override
    public List<Archivesinfo> getNewArchivesinfoFromCrm() {
        return this.list(new QueryWrapper<Archivesinfo>().lt("CreationTime",new Date()).gt("CreationTime",DateUtils.addDateHours(new Date(),-1)));
    }

    //获取carebox Token
    private String getToken(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","admin");
        map.put("password","Gps@123");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange("https://test.api.carebox.sungin.com.cn/gps/login/", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        String data = result.getString("data");

        JSONObject tokenjson = JSONObject.parseObject(data);
        String token = tokenjson.getString("access_token");
        return token;
    }

    //将carebox的人离院          careboxCode    orgid
    public void putCareboxLeave(Map<String,Integer> list){
        try {
            String address = "";
            String token_pj = login("pujiang", "PuJiang@123", "pujiang");
            String token_bj = login("beijing", "BeiJing@123", "beijing");
            String code = "";
            HttpHeaders headers = new HttpHeaders();
            HashMap<String, Integer> map = new HashMap<>();
            map.put("occupancy_status",2);
            HttpEntity Entity = new HttpEntity(map, headers);
            for (String s : list.keySet()) {
                switch (list.get(s)){
                    case 1: address="pujiang";
                        headers.setBearerAuth(token_pj);
                        break;
                    case 4: address="beijing";
                        headers.setBearerAuth(token_bj);
                        break;
                    default: continue;
                }
                code = s;
//                CockpitArchives careboxCode = this.cockpitArchivesService.getOne(new QueryWrapper<CockpitArchives>().eq("CareboxCode", s));
//                if (careboxCode==null)continue;
                ResponseEntity<String> archives = restTemplate.exchange(Url.carebox + address + "/resident/living_info/status/?resident_code="+code, HttpMethod.PUT, Entity, String.class);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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


    @Override
    public List<ElderDetailReportDto> GetElderDetails(Date dateStart, Date dateEnd, String types, String itemName, @Param("list") List<Integer> OrgId, String type, String nameOrRoomNo, Integer currcount, Integer pageSize, String startSnapsShot, String endSnapsShot) {
        return this.baseMapper.GetElderDetails(dateStart,dateEnd,types,itemName,OrgId,type,nameOrRoomNo,currcount,pageSize,startSnapsShot,endSnapsShot);
    }

    @Override
    public int GetElderDetailsCounts(Date startDate, Date endDate, String types, String itemName, @Param("list") List<Integer> orgId, String type, String nameOrRoomNo,String startSnapsShot,String endSnapsShot) {
        return this.baseMapper.GetElderDetailsCounts(startDate,endDate,types,itemName,orgId,type,nameOrRoomNo,startSnapsShot,endSnapsShot);
    }
}
