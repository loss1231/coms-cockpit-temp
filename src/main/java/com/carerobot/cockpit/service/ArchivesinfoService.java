package com.carerobot.cockpit.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.carerobot.cockpit.dto.*;
import com.carerobot.cockpit.model.Account;
import com.carerobot.cockpit.model.Archivesinfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-05-13
 */
public interface ArchivesinfoService extends IService<Archivesinfo> {

    ElderLeaveTranDto getLeaveApplicationTable(Date dateStart, Date dateEnd, Integer areaId, Integer isLongOrShort);

    HashMap<String, List<AccidentReportDto>> getAccidentReport(Date dateStart, Date dateEnd);

    List<Archivesinfo> getDataFromCrm();

    List<Archivesinfo> getAllDataFromCrm();

    void createNewTempArchiveTable(String name);

    void dropTable(String name);

    void batchInsertTempArchive(List<ArchivesJsonDto> dtos,String name);

    List<ElderStatisticsDto> getGenderForElder(Integer liveType,String name1,String name2,Date startDate,Date endDate);

    List<ElderStatisticsDto> occupationForElderMap(Integer liveType,String name1,String name2,Date startDate,Date endDate);

    List<ElderStatisticsDto> diseaseForElderMap(Integer liveType,String name1,String name2,Date startDate,Date endDate);

    List<ElderStatisticsDto> ageForElderMap(Integer liveType,String name1,String name2,Date startDate,Date endDate);

    List<ElderStatisticsDto> curAgeForElderMap(Integer liveType,String name1,String name2,Date startDate,Date endDate);

    List<ElderStatisticsDto> curageForElderMap(Integer liveType);

    void updateThenAdd(List<Archivesinfo> dataList);

    String test();

    List<Archivesinfo> getEntireArchivesinfoFromCrm();

    void updateThenAdd(List<Archivesinfo> archivesInfo,List<Archivesinfo> archives);

    List<Archivesinfo> getNewArchivesinfoFromCrm();

    List<Archivesinfo> getArchivesinfoFromCrm();

    void updateAll(List<Archivesinfo> archivesinfoFromCrm);

    void archivesLeave();

    List<ElderDetailReportDto> GetElderDetails(Date dateStart, Date dateEnd, String types, String itemName, List<Integer> orgId, String type, String nameOrRoomNo, Integer currcount, Integer pageSize, String startSnapsShot, String endSnapsShot);

    int GetElderDetailsCounts(Date startDate, Date endDate, String types, String itemName, List<Integer> orgId, String type, String nameOrRoomNo, String startSnapsShot, String endSnapsShot);



}
