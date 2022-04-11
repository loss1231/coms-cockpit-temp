package com.carerobot.cockpit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.model.ElderDetail;
import com.carerobot.cockpit.model.newOccupancyRate;

import java.util.Date;
import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public interface CockpitArchivesService extends IService<CockpitArchives> {
    String getCode(Integer id);

    Boolean updateCareboxCode(Integer id, String token,Integer orgId);

    String updateNurseLevel(String code, Integer nurseLevel, String nurseLevelStr);

    void createOccupancyTable(String tableName);

    List<newOccupancyRate> searchOccupancy(Integer i, Date date, Date now);

    void insertOccupancy(String tableName, List<newOccupancyRate> result);

    void createElderDetailTable(String tableName);

    List<ElderDetail> searchElderDetail();

    void insertElderDetail(String tableName, List<ElderDetail> result);

}
