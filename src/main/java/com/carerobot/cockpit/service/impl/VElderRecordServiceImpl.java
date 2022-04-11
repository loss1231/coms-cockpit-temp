package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.dto.*;
import com.carerobot.cockpit.model.VElderRecord;
import com.carerobot.cockpit.mapper.VElderRecordMapper;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.carerobot.cockpit.service.ContractCohabitantService;
import com.carerobot.cockpit.service.VElderRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-05-17
 */
@Service
public class VElderRecordServiceImpl extends ServiceImpl<VElderRecordMapper, VElderRecord> implements VElderRecordService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VElderRecordMapper vElderRecordMapper;

    @Autowired
    private VElderRecordService vElderRecordService;

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @Autowired
    private ContractCohabitantService contractCohabitantService;

    public VElderRecordServiceImpl() {
    }

    @Override
    public ElderDetailDto getElderDetails(DateTime dateStart, DateTime dateEnd, Integer liveType, String ItemCode, String Value, String Area) {
        try {
            ElderDetailDto dto = new ElderDetailDto();
            QueryWrapper<VElderRecord> wrapper = new QueryWrapper<VElderRecord>();
            if (dateStart != null && dateEnd != null) {
                wrapper.between("RecordTime", dateStart.toString("yyyy-MM-dd HH:mm:ss"), dateEnd.toString("yyyy-MM-dd HH:mm:ss"));
            }
            if (liveType != null) wrapper.eq("LiveType", liveType);
            if (Area != null && Area != "") wrapper.eq("Area", Area);
            if (ItemCode != null && ItemCode != "") {
                if (ItemCode.equals("Age")){
                    switch (Value){
                        case "60岁以下":
                            wrapper.lt(ItemCode,60);
                            break;
                        case "60-69岁":
                            wrapper.between(ItemCode, 60,69);
                            break;
                        case "70-79岁":
                            wrapper.between(ItemCode, 70,79);
                            break;
                        case "80-89岁":
                            wrapper.between(ItemCode, 80,89);
                            break;
                        case "90岁以上":
                            wrapper.ge(ItemCode, 90);
                            break;
                        default:
                            break;
                    }
                }
                else if (Value != null && !Value.equals("Total") ){
                    wrapper.eq(ItemCode, Value);
                }
            }
            List<VElderRecord> recordAll = vElderRecordService.list(wrapper);
            dto.elderDetails = recordAll;
            dto.TotalCount = recordAll.size();
            dto.TotalAge = recordAll.stream().collect(Collectors.summingInt(x -> x.Age));
            dto.TotalCurrAge = recordAll.stream().collect(Collectors.summingInt(x -> x.CurrAge));
            dto.AvgAge = recordAll.stream().collect(Collectors.averagingDouble(x -> x.Age));
            dto.AvgCurrAge = recordAll.stream().collect(Collectors.averagingDouble(x -> x.CurrAge));

            dto.AvgAge = new BigDecimal(dto.AvgAge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            dto.AvgCurrAge = new BigDecimal(dto.AvgCurrAge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return dto;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ElderStatisticsDto> getNewElderDetails(Date dateStart, Date dateEnd, Integer liveType, String itemCode, String value, String area, String token) {
        ArrayList<ElderStatisticsDto> list = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorizatior",token);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<ElDerJsonDto> archives = restTemplate.exchange("http://coms.tulucloud.com:8001/crm/api/webapi/GetIsMemType", HttpMethod.GET, requestEntity, ElDerJsonDto.class);
        String tableName = "Report_list_archives"+ UUID.randomUUID().toString().replace("-", "").toLowerCase();
        this.archivesinfoService.createNewTempArchiveTable(tableName);
        this.archivesinfoService.batchInsertTempArchive(archives.getBody().getData(),tableName);
        ResponseEntity<ElDerCohabitanJsonDto> cohabitants = restTemplate.exchange("http://coms.tulucloud.com:8001/file/api/webapi/contract/GetCohabitar", HttpMethod.GET, requestEntity, ElDerCohabitanJsonDto.class);
        String tableName1 = "Report_list_cohabitant" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
        this.contractCohabitantService.createNewCohabitantTable(tableName1);
        this.contractCohabitantService.batchInsertTempArchive(cohabitants.getBody().getData(),tableName1);
        List<ElderStatisticsDto> genderForElder = this.archivesinfoService.getGenderForElder(liveType,tableName,tableName1,dateStart,dateEnd);

        Double al = 0.0;
        Double il = 0.0;

        for (ElderStatisticsDto elderStatisticsDto : genderForElder) {
            al = al + elderStatisticsDto.AL;
            il = il + elderStatisticsDto.IL;
        }
        ElderStatisticsDto elderStatisticsDto = new ElderStatisticsDto();
        elderStatisticsDto.ValueDes = "Gender Total";
        elderStatisticsDto.IL = il;
        elderStatisticsDto.AL = al;
        elderStatisticsDto.Total = elderStatisticsDto.IL + elderStatisticsDto.AL;
        this.tranPercentage(genderForElder,elderStatisticsDto.Total);
        list.addAll(genderForElder);
        list.add(elderStatisticsDto);
        al = 0.0;
        il = 0.0;

        System.out.println(genderForElder);
//        List<ElderStatisticsDto> occupationForElderMap = this.archivesinfoService.occupationForElderMap(null,tableName,tableName1,dateStart,dateEnd);
//
//        for (ElderStatisticsDto occelderStatisticsDto : occupationForElderMap) {
//            al = al + occelderStatisticsDto.AL;
//            il = il + occelderStatisticsDto.IL;
//        }
//        elderStatisticsDto = new ElderStatisticsDto();
//        elderStatisticsDto.ValueDes = "Occupation Total";
//        elderStatisticsDto.IL = il;
//        elderStatisticsDto.AL = al;
//        elderStatisticsDto.Total = elderStatisticsDto.IL + elderStatisticsDto.AL;
//        this.tranPercentage(occupationForElderMap,elderStatisticsDto.Total);
//        list.addAll(occupationForElderMap);
//        list.add(elderStatisticsDto);
//        al = 0.0;
//        il = 0.0;
//        System.out.println(occupationForElderMap);
//        List<ElderStatisticsDto>  diseaseForElderMap= this.archivesinfoService.diseaseForElderMap(null,tableName,tableName1,dateStart,dateEnd);
//        for (ElderStatisticsDto DiseaseelderStatisticsDto : diseaseForElderMap) {
//            al = al + DiseaseelderStatisticsDto.AL;
//            il = il + DiseaseelderStatisticsDto.IL;
//        }
//        elderStatisticsDto = new ElderStatisticsDto();
//        elderStatisticsDto.ValueDes = "Disease Total";
//        elderStatisticsDto.IL = il;
//        elderStatisticsDto.AL = al;
//        elderStatisticsDto.Total = elderStatisticsDto.IL + elderStatisticsDto.AL;
//        this.tranPercentage(diseaseForElderMap,elderStatisticsDto.Total);
//        list.addAll(diseaseForElderMap);
//        list.add(elderStatisticsDto);
//        al = 0.0;
//        il = 0.0;
//        System.out.println(diseaseForElderMap);
//        List<ElderStatisticsDto>  ageForElderMap= this.archivesinfoService.ageForElderMap(null,tableName,tableName1,dateStart,dateEnd);
//        for (ElderStatisticsDto agelderStatisticsDto : ageForElderMap) {
//            al = al + agelderStatisticsDto.AL;
//            il = il + agelderStatisticsDto.IL;
//        }
//        elderStatisticsDto = new ElderStatisticsDto();
//        elderStatisticsDto.ValueDes = "Age Total";
//        elderStatisticsDto.IL = il;
//        elderStatisticsDto.AL = al;
//        elderStatisticsDto.Total = elderStatisticsDto.IL + elderStatisticsDto.AL;
//        this.tranPercentage(ageForElderMap,elderStatisticsDto.Total);
//        list.addAll(ageForElderMap);
//        list.add(elderStatisticsDto);
//        al = 0.0;
//        il = 0.0;
//        System.out.println(ageForElderMap);
//        List<ElderStatisticsDto>  curageForElderMap= this.archivesinfoService.curAgeForElderMap(null,tableName,tableName1,dateStart,dateEnd);
//        System.out.println(curageForElderMap);
//        this.archivesinfoService.dropTable(tableName);
//        this.archivesinfoService.dropTable(tableName1);
        return list;
    }

    @Override
    public List<ElderStatisticsDto> getElderStatistics(String strWhere, DateTime dateStart, DateTime dateEnd, Integer liveType) {
        List<ElderStatisticsDto> list = new ArrayList<>();
        try {
            QueryWrapper<VElderRecord> wrapper = new QueryWrapper<VElderRecord>();
            if (dateStart != null && dateEnd != null) {
                wrapper.between("RecordTime", dateStart.toString("yyyy-MM-dd HH:mm:ss"), dateEnd.toString("yyyy-MM-dd HH:mm:ss"));
            }
            if(liveType!=null) wrapper.eq("LiveType", liveType);
            List<VElderRecord> recordAll = vElderRecordService.list(wrapper);
            int allCount = recordAll.size();
            Map<Integer, List<VElderRecord>> mapGender = recordAll.stream().collect(Collectors.groupingBy(d -> d.Gender));
            for (Integer gender : mapGender.keySet()) {
                ElderStatisticsDto dto = new ElderStatisticsDto();
                dto.ItemName = "性别 Gender";
                dto.ItemCode = "Gender";
                dto.ValueDes = gender == 1 ? "男" : "女";
                dto.Value = gender.toString();
                SetGroupList(list, allCount, dto, mapGender.get(gender));
            }
            ElderStatisticsDto dtoTotalGender = new ElderStatisticsDto();
            dtoTotalGender.ItemName = "性别 Gender";
            dtoTotalGender.ItemCode = "Gender";
            SetTotalList(list, recordAll, allCount, dtoTotalGender);

            Map<String, List<VElderRecord>> mapOccupation = recordAll.stream().collect(Collectors.groupingBy(d -> d.Occupation));
            for (String Occupation : mapOccupation.keySet()) {
                ElderStatisticsDto dto = new ElderStatisticsDto();
                dto.ItemName = "职业 Occupation";
                dto.ItemCode = "Occupation";
                dto.ValueDes = Occupation;
                dto.Value = Occupation;
                SetGroupList(list, allCount, dto, mapOccupation.get(Occupation));
            }
            ElderStatisticsDto dtoTotalOccupation = new ElderStatisticsDto();
            dtoTotalOccupation.ItemName = "职业 Occupation";
            dtoTotalOccupation.ItemCode = "Occupation";
            SetTotalList(list, recordAll, allCount, dtoTotalOccupation);

            Map<String, List<VElderRecord>> mapDisease = recordAll.stream().filter(x -> !x.Disease.isEmpty() && x.Disease != "").collect(Collectors.groupingBy(d -> d.Disease));
            for (String Disease : mapDisease.keySet()) {
                ElderStatisticsDto dto = new ElderStatisticsDto();
                dto.ItemName = "疾病 Disease";
                dto.ItemCode = "Disease";
                dto.ValueDes = Disease;
                dto.Value = Disease;
                SetGroupList(list, allCount, dto, mapDisease.get(Disease));
            }
            ElderStatisticsDto dtoTotalDisease = new ElderStatisticsDto();
            dtoTotalOccupation.ItemName = "疾病 Disease";
            dtoTotalOccupation.ItemCode = "Disease";
            SetTotalList(list, recordAll, allCount, dtoTotalDisease);

            List<VElderRecord> mapAge1 = recordAll.stream().filter(x -> x.CurrAge < 60).collect(Collectors.toList());
            List<VElderRecord> mapAge2 = recordAll.stream().filter(x -> x.CurrAge >= 60 && x.CurrAge < 70).collect(Collectors.toList());
            List<VElderRecord> mapAge3 = recordAll.stream().filter(x -> x.CurrAge >= 70 && x.CurrAge < 80).collect(Collectors.toList());
            List<VElderRecord> mapAge4 = recordAll.stream().filter(x -> x.CurrAge >= 80 && x.CurrAge < 90).collect(Collectors.toList());
            List<VElderRecord> mapAge5 = recordAll.stream().filter(x -> x.CurrAge >= 90).collect(Collectors.toList());

            ElderStatisticsDto dto1 = new ElderStatisticsDto();
            dto1.ItemName = "年龄 Age";
            dto1.ItemCode = "CurrAge";
            dto1.ValueDes = "60岁以下";
            dto1.Value = "60岁以下";
            SetGroupList(list, allCount, dto1, mapAge1);
            ElderStatisticsDto dto2 = new ElderStatisticsDto();
            dto2.ItemName = "年龄 Age";
            dto2.ItemCode = "CurrAge";
            dto2.ValueDes = "60-69岁";
            dto2.Value = "60-69岁";
            SetGroupList(list, allCount, dto2, mapAge2);
            ElderStatisticsDto dto3 = new ElderStatisticsDto();
            dto3.ItemName = "年龄 Age";
            dto3.ItemCode = "CurrAge";
            dto3.ValueDes = "70-79岁";
            dto3.Value = "70-79岁";
            SetGroupList(list, allCount, dto3, mapAge3);
            ElderStatisticsDto dto4 = new ElderStatisticsDto();
            dto4.ItemName = "年龄 Age";
            dto4.ItemCode = "CurrAge";
            dto4.ValueDes = "80-89岁";
            dto4.Value = "80-89岁";
            SetGroupList(list, allCount, dto4, mapAge4);
            ElderStatisticsDto dto5 = new ElderStatisticsDto();
            dto5.ItemName = "年龄 Age";
            dto5.ItemCode = "CurrAge";
            dto5.ValueDes = "90岁以上";
            dto5.ValueDes = "90岁以上";
            SetGroupList(list, allCount, dto5, mapAge5);
            ElderStatisticsDto dtoTotalAge = new ElderStatisticsDto();
            dtoTotalAge.ItemName = "年龄 Age";
            dtoTotalAge.ItemCode = "CurrAge";
            SetTotalList(list, recordAll, allCount, dtoTotalAge);

            ElderStatisticsDto dtoAge = new ElderStatisticsDto();
            dtoAge.ItemName = "入住平均年龄 Age";
            dtoAge.Value = "";
            dtoAge.IL = recordAll.stream().filter(x -> x.Area.equals("IL")).collect(Collectors.averagingDouble(x -> x.Age));
            dtoAge.AL = recordAll.stream().filter(x -> x.Area.equals("AL")).collect(Collectors.averagingDouble(x -> x.Age));
            dtoAge.Total = recordAll.stream().collect(Collectors.averagingDouble(x -> x.Age));
            convertAge(list, dtoAge);

            ElderStatisticsDto dtoCurrAge = new ElderStatisticsDto();
            dtoCurrAge.ItemName = "当前平均年龄 CurrAge";
            dtoCurrAge.Value = "";
            dtoCurrAge.IL = recordAll.stream().filter(x -> x.Area.equals("IL")).collect(Collectors.averagingDouble(x -> x.CurrAge));
            dtoCurrAge.AL = recordAll.stream().filter(x -> x.Area.equals("AL")).collect(Collectors.averagingDouble(x -> x.CurrAge));
            dtoCurrAge.Total = recordAll.stream().collect(Collectors.averagingDouble(x -> x.CurrAge));
            convertAge(list, dtoCurrAge);

            return list;
//            return vElderRecordMapper.selectElderStatistics(strWhere);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void convertAge(List<ElderStatisticsDto> list, ElderStatisticsDto dtoAge) {
        dtoAge.IL =new BigDecimal(dtoAge.IL).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        dtoAge.AL =new BigDecimal(dtoAge.AL).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        dtoAge.Total =new BigDecimal(dtoAge.Total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        dtoAge.Percentage = 1;
        list.add(dtoAge);
    }

    private void SetGroupList(List<ElderStatisticsDto> list, int allCount, ElderStatisticsDto dto, List<VElderRecord> vElderRecords) {
        List<VElderRecord> records = vElderRecords;
//        dto.AL = records.stream().filter(x -> x.Area.equals("AL")).count();
//        dto.IL = records.stream().filter(x -> x.Area.equals("IL")).count();
//        dto.Total = records.size();
//        dto.Percentage = new BigDecimal((float)records.size() / allCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        list.add(dto);
    }

    private void SetTotalList(List<ElderStatisticsDto> list, List<VElderRecord> recordAll, int allCount, ElderStatisticsDto dtoTotalOccupation) {
        dtoTotalOccupation.Value = "Total";
//        dtoTotalOccupation.AL = recordAll.stream().filter(x -> x.Area.equals("AL")).count();
//        dtoTotalOccupation.IL = recordAll.stream().filter(x -> x.Area.equals("IL")).count();
//        dtoTotalOccupation.Total = allCount;
//        dtoTotalOccupation.Percentage = 1;
        list.add(dtoTotalOccupation);
    }

    private void tranPercentage(List<ElderStatisticsDto> list,Double total){
        for (ElderStatisticsDto elderStatisticsDto : list) {
            elderStatisticsDto.setPercentage(total);
        }
    }


    /**
     * 2022.3.21
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @param types     入住类型
     * @param itemName  项目名称
     * @param OrgId
     * @return
     */
    @Override
    public List<ElderDetailReportDto> GetElderDetails(Date dateStart, Date dateEnd, String types, String itemName, List<Integer> OrgId,String type,String nameOrRoomNo,Integer currcount,Integer pageSize,String startSnapsShot,String endSnapsShot) {
        return this.archivesinfoService.GetElderDetails(dateStart, dateEnd, types, itemName, OrgId, type, nameOrRoomNo,currcount,pageSize,startSnapsShot,endSnapsShot);
    }

    @Override
    public int GetElderDetailsCounts(Date startDate, Date endDate, String types, String itemName, List<Integer> orgId, String type, String nameOrRoomNo,String startSnapsShot,String endSnapsShot) {
        return this.archivesinfoService.GetElderDetailsCounts(startDate, endDate, types, itemName, orgId, type, nameOrRoomNo,startSnapsShot,endSnapsShot);
    }
}
