package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carerobot.cockpit.dto.*;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DataHandleUtil;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.mapper.ContractInfoMapper;
import com.carerobot.cockpit.model.ContractLive;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
@Service
public class ContractInfoServiceImpl extends ServiceImpl<ContractInfoMapper, ContractInfo> implements ContractInfoService {

    private Logger logger = LoggerFactory.getLogger(ContractInfoServiceImpl.class);

    @Autowired
    private ContractLiveService contractLiveService;

    @Autowired
    public ContractCohabitantService contractCohabitantService;

    @Autowired
    public RoomBedinfoService roomBedinfoService;

    @Autowired
    public ArchivesinfoService archivesinfoService;

    @Autowired
    public RoomInfoService roomInfoService;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public DischargeApplicationService dischargeApplicationService;

    @Autowired
    private ApplicationContext context;

    @Override
    public List<newOccupancyRateDto> getOccupancyRate(Date startDate,Date endDate,List OrgId, String startSnapsShot, String endSnapsShot){
        ArrayList<newOccupancyRateDto> list = new ArrayList<>();
        newOccupancyRateDto dto = new newOccupancyRateDto();

        //初期单元数
        dto = this.baseMapper.selectBegin(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("Beginning Unit 期初单元数");
        list.add(dto);

        //GMI 搬入数
        dto = new newOccupancyRateDto();
        /*
        Integer star = Integer.valueOf(startSnapsShot);
        Integer end = Integer.valueOf(endSnapsShot);
        for (int i=end-star ; i>=0 ;i--){
            startSnapsShot = String.valueOf(star+1);
            dto = this.baseMapper.selectGMI(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);

        }
        */
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectGMI(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("        GMI 搬入数");
        list.add(dto);

        //MO  搬出数
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectMO(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("        MO  搬出数");
        list.add(dto);

        //NMI 净搬入数
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectNMI(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("        NMI 净搬入数");
        list.add(dto);

        //Ending Units 单元数
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectEnd(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("Ending Units 单元数");
        list.add(dto);

        //Residents 住户人数
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectResidents(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("Residents 住户人数");
        list.add(dto);

        //Residents Day 住户天数
        dto = new newOccupancyRateDto();
        newOccupancyRateDto dtoDay =new newOccupancyRateDto();
        Integer star = Integer.parseInt(startSnapsShot);
        Integer end = Integer.parseInt(endSnapsShot);
        int days = end-star;
        for ( int i = 0 ; i <=days;i++){
            star+=i;
            String s = star.toString();
            dtoDay = this.baseMapper.selectResidentsDays(startDate, endDate, OrgId, s, endSnapsShot);
            dto.setLt(String.valueOf(Integer.parseInt(dto.getLt())+Integer.parseInt(dtoDay.getLt())));
            dto.setSt(String.valueOf(Integer.parseInt(dto.getSt())+Integer.parseInt(dtoDay.getSt())));
            dto.setTtial(String.valueOf(Integer.parseInt(dto.getTtial())+Integer.parseInt(dtoDay.getTtial())));
            dto.setGuest(String.valueOf(Integer.parseInt(dto.getGuest())+Integer.parseInt(dtoDay.getGuest())));
            dto.setIl_total(String.valueOf(Integer.parseInt(dto.getIl_total())+Integer.parseInt(dtoDay.getIl_total())));
            dto.setLevel_l(String.valueOf(Integer.parseInt(dto.getLevel_l())+Integer.parseInt(dtoDay.getLevel_l())));
            dto.setLevel_ll(String.valueOf(Integer.parseInt(dto.getLevel_ll())+Integer.parseInt(dtoDay.getLevel_ll())));
            dto.setLevel_lll(String.valueOf(Integer.parseInt(dto.getLevel_lll())+Integer.parseInt(dtoDay.getLevel_lll())));
            dto.setLevel_Ⅳ(String.valueOf(Integer.parseInt(dto.getLevel_Ⅳ())+Integer.parseInt(dtoDay.getLevel_Ⅳ())));
            dto.setAl_total(String.valueOf(Integer.parseInt(dto.getAl_total())+Integer.parseInt(dtoDay.getAl_total())));
            dto.setTotal(String.valueOf(Integer.parseInt(dto.getTotal())+Integer.parseInt(dtoDay.getTotal())));
            dtoDay = new newOccupancyRateDto();
        }
        dto.setItem_Name("Residents Day 住户天数");
        list.add(dto);

        //Saleable Room 可入住单元数
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.selectSaleableRoom(startDate, endDate, OrgId, startSnapsShot, endSnapsShot);
        dto.setItem_Name("Saleable Room 可入住单元数");
        list.add(dto);

        //Physical Occupancy 入住率
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.PhysicalOccupancy(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("Physical Occupancy 入住率");
        list.add(dto);

        //% of Double Occupancy  双人入住率
        dto = new newOccupancyRateDto();
        dto = this.baseMapper.DoubleOccupancy(startDate, endDate, OrgId,startSnapsShot,endSnapsShot);
        dto.setItem_Name("% of Double Occupancy  双人入住率");
        list.add(dto);

        return list;
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractInfo> getDataFromFile() {
        QueryWrapper<ContractInfo> contractInfoWrapper = new QueryWrapper<>();
        contractInfoWrapper.between("CreationTime", new Date()
                , DateUtils.addDateHours(new Date(),-1));
        return this.list(contractInfoWrapper);
    }

    @Override
    public boolean saveBatchIntoLocal(List<ContractInfo> dataList) {
        logger.info("数据更新开始");
        if(this.saveBatch(dataList)){
            logger.info("数据更新结束---此次更新数据条数：{}", dataList.size());
            return true;
        }
        logger.info("数据更新失败----");
        return false;
    }

    @Override
    public Map GetCockpit(Integer orgId) {
        HashMap<String,CockpitDto> map = new HashMap<>();
        CockpitDto dto = new CockpitDto();

        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();


        Calendar calendarEnd = Calendar.getInstance();// 获取当前日期
        calendarEnd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendarEnd.setTimeInMillis(System.currentTimeMillis());
        calendarEnd.add(Calendar.YEAR, 0);
        calendarEnd.add(Calendar.MONTH, 0);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        Date end = calendarEnd.getTime();

//        //首页入住率
//        Integer in = this.dischargeApplicationService.getCount(start,end);
//        Integer out = this.baseMapper.selectInCount(start,end);
//        dto.setParams2(in.toString());
//        dto.setParams4(this.dischargeApplicationService.getCount(start,end).toString());
//        dto.setParams3(out.toString());
//        dto.setParams1(DataHandleUtil.division(this.baseMapper.selectContract(null,null,null),this.roomBedinfoService.count(new QueryWrapper<RoomBedinfo>().eq("IsDeleted",false))));
//        dto.setParams5(DataHandleUtil.division(this.baseMapper.selectContract(null,null,null),this.roomBedinfoService.count(new QueryWrapper<RoomBedinfo>().eq("IsDeleted",false))));
//        map.put("occupancy",dto);
        //房间数
        Integer count = this.roomBedinfoService.count(new QueryWrapper<RoomBedinfo>().eq("OrgId", orgId).eq("IsDeleted", false));
        //入住数
        Integer count1 = this.roomBedinfoService.count(new QueryWrapper<RoomBedinfo>().eq("OrgId", orgId).eq("IsDeleted", false).eq("BedStatusId",2));
        Double oppy = ((double)count1/(double)count)*100;
        //入住率
        DecimalFormat df = new DecimalFormat(".00");
        dto.setParams1(df.format(oppy)+"%");
        //搬入
        Integer count2 = this.archivesinfoService.count(new QueryWrapper<Archivesinfo>().eq("IsDeleted", false).eq("OrgId", orgId).ge("EnterTime", start).le("EnterTime", end));
        dto.setParams2(count1.toString());
        dto.setParams3(count2.toString());
        Integer count3 = this.archivesinfoService.count(new QueryWrapper<Archivesinfo>().eq("IsDeleted", false).eq("OrgId", orgId).ge("LeftTime", start).le("LeftTime", end));
        dto.setParams4(count3.toString());
        dto.setParams5(df.format(oppy)+"%");
        map.put("occupancy",dto);

        //新增签约
        dto = new CockpitDto();
        dto.setParams1(this.dischargeApplicationService.getCount(start,end,orgId).toString());
        map.put("contract",dto);

        //本月客户
        dto = new CockpitDto();
        dto.setParams1(String.valueOf(archivesinfoService.count(new QueryWrapper<Archivesinfo>().eq("OrgId",orgId).eq("IsDeleted",false))));
        dto.setParams2(String.valueOf(archivesinfoService.count(new QueryWrapper<Archivesinfo>().eq("AreaId",2).eq("OrgId",orgId).eq("IsDeleted",false))));
        dto.setParams3(String.valueOf(archivesinfoService.count(new QueryWrapper<Archivesinfo>().eq("AreaId",1).eq("OrgId",orgId).eq("IsDeleted",false))));
        dto.setParams4(String.valueOf(archivesinfoService.count(new QueryWrapper<Archivesinfo>().ne("AreaId",0).eq("OrgId",orgId).eq("IsDeleted",false))));
        //dto.setParams5(out.toString());
        map.put("archives",dto);
        return map;
    }


    @Override
    public List<OccupancyRateDto> getNewRoomRate(Date dateStart, Date dateEnd, String itemCode, String value, String area, String token) {
        ArrayList<OccupancyRateDto> list = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorizatior",token);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<ElDerRoomJsonDto> room = restTemplate.exchange("http://coms.tulucloud.com:8001/space/api/webapi/GetRoomInfo", HttpMethod.GET, requestEntity, ElDerRoomJsonDto.class);
        String tableName = "Report_list_room" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
        this.roomInfoService.createNewRoomTable(tableName);
        this.roomInfoService.batchInsertRoom(room.getBody().getData(),tableName);

        ResponseEntity<ElDerJsonDto> archives = restTemplate.exchange("http://coms.tulucloud.com:8001/crm/api/webapi/GetIsMemType", HttpMethod.GET, requestEntity, ElDerJsonDto.class);
        String tableName1 = "Report_list_archives" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
        this.archivesinfoService.createNewTempArchiveTable(tableName1);
        this.archivesinfoService.batchInsertTempArchive(archives.getBody().getData(),tableName1);

        ResponseEntity<ElDerCohabitanJsonDto> cohabitants = restTemplate.exchange("http://coms.tulucloud.com:8001/file/api/webapi/contract/GetCohabitar", HttpMethod.GET, requestEntity, ElDerCohabitanJsonDto.class);
        String tableName2 = "Report_list_cohabitant" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
        this.contractCohabitantService.createNewCohabitantTable(tableName2);
        this.contractCohabitantService.batchInsertTempArchive(cohabitants.getBody().getData(),tableName2);


        list.add(this.roomInfoService.beginningUnitMap(dateStart,null,"'Beginning Unit'"," EnterTime <",null,tableName1,tableName));
        list.add(this.roomInfoService.beginningUnitMap(dateStart,dateEnd,"'GMI'"," EnterTime >"," EnterTime <",tableName1,tableName));
        list.add(this.roomInfoService.beginningUnitMap(dateStart,dateEnd,"'MO'"," LeftTime >"," LeftTime <",tableName1,tableName));

        OccupancyRateDto rateDto = new OccupancyRateDto();
        rateDto.setItemName("NMI");
        Integer total = Integer.parseInt(list.get(1).getLongLive())+Integer.parseInt(list.get(2).getLongLive());
        rateDto.setLongLive(total.toString());
        total = Integer.parseInt(list.get(1).getShortLive())+Integer.parseInt(list.get(2).getShortLive());
        rateDto.setShortLive(total.toString());

        total = Integer.parseInt(list.get(1).getGuest())+Integer.parseInt(list.get(2).getGuest());
        rateDto.setGuest(total.toString());
        total = Integer.parseInt(list.get(1).getTryLive())+Integer.parseInt(list.get(2).getTryLive());
        rateDto.setTryLive(total.toString());
        total = Integer.parseInt(list.get(1).getILTotal())+Integer.parseInt(list.get(2).getILTotal());
        rateDto.setILTotal(total.toString());
        total = Integer.parseInt(list.get(1).getCareLevel1())+Integer.parseInt(list.get(2).getCareLevel1());
        rateDto.setCareLevel1(total.toString());
        total = Integer.parseInt(list.get(1).getCareLevel2())+Integer.parseInt(list.get(2).getCareLevel2());
        rateDto.setCareLevel2(total.toString());
        total = Integer.parseInt(list.get(1).getCareLevel3())+Integer.parseInt(list.get(2).getCareLevel3());
        rateDto.setCareLevel3(total.toString());
        total = Integer.parseInt(list.get(1).getALTotal())+Integer.parseInt(list.get(2).getALTotal());
        rateDto.setALTotal(total.toString());
        total = Integer.parseInt(list.get(1).getTotal())+Integer.parseInt(list.get(2).getTotal());
        rateDto.setTotal(total.toString());



        list.add(this.roomInfoService.beginningUnitMap(dateStart,dateEnd,"'Ending Units 单元数'"," LeftTime <"," EnterTime >",tableName1,tableName));
        list.add(this.roomInfoService.doubleArchivesMap(dateStart,dateEnd,"'Residents 住户人数'"," LeftTime <"," EnterTime >",tableName1,tableName,tableName2));
        list.add(this.roomInfoService.singArchivesRate(tableName1,tableName));
        list.add(this.roomInfoService.doubleArchivesRate(tableName1,tableName,tableName2));
        //List<OccupancyRateDto> UnitForElder = this.contractCohabitantService.(tableName,tableName1,dateStart,dateEnd);

        //System.out.println(UnitForElder);
        this.archivesinfoService.dropTable(tableName);
        this.archivesinfoService.dropTable(tableName1);
        this.archivesinfoService.dropTable(tableName2);
        return list;
    }

    @Override
    @Transactional
    public void updateThenAdd(List<ContractInfo> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
//            List<ContractInfo> todayData = getDataFromFile();
//            this.saveBatch(todayData);
//            for (ContractInfo contractInfo : dataList) {
//                this.baseMapper.insertContract(contractInfo);
//            }
            for (ContractInfo contractInfo : dataList) {
                ContractInfo byId = this.getById(contractInfo.getId());
                if (byId==null){
                    this.save(contractInfo);
                }
            }
            logger.info("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
//        List<ContractLive> list = this.contractLiveService.list(new QueryWrapper<>());
//        if(this.contractLiveService.updateBatchById(list)){
//            List<ContractLive> add = this.contractLiveService.list(new QueryWrapper<ContractLive>().between("AddDate", new Date()
//                    , DateUtils.addDateHours(new Date(), -1)));
//            this.contractLiveService.saveBatch(add);
//        }else{
//            logger.info("数据更新失败");
//        }
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractInfo> getEntireDataFromFile() {
        QueryWrapper<ContractInfo> contractInfoWrapper = new QueryWrapper<>();
        return this.list(contractInfoWrapper);
    }

    @Override
    public Boolean updateSend(String ids) {
        return this.baseMapper.updateContractSend(ids);
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractLive> getContractLiving() {
        List<ContractLive> list = this.contractLiveService.list(new QueryWrapper<ContractLive>());
        return list;
    }

    @Override
    public void updateThenAddContractLive(List<ContractLive> dataList) {
        this.contractLiveService.updateBatchById(dataList,dataList.size());
        for (ContractLive contractLive : dataList) {
            ContractLive contractLive1 = this.contractLiveService.getById(contractLive.getId());
            if (contractLive1 == null){
                this.contractLiveService.save(contractLive);
            }
        }
    }

//    public BaseMapper<ContractInfo> getMapper(){
//        return this.baseMapper;
//    }

}
