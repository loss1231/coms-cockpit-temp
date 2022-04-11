package com.carerobot.cockpit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.model.ElderDetail;
import com.carerobot.cockpit.model.OccupancyRate;
import com.carerobot.cockpit.model.newOccupancyRate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
public interface CockpitArchivesMapper extends BaseMapper<CockpitArchives> {

    void createOccupancyTable(@Param("tableName") String tableName);


    List<newOccupancyRate> searchOccupancy(@Param("orgId") Integer orgId, @Param("startDATE") Date startDATE, @Param("endDATE") Date endDATE);

    void insertOccupancy(@Param("tableName") String tableName, @Param("list") List<newOccupancyRate> list);

    void createElderDetailTable(@Param("tableName") String tableName);

    List<ElderDetail> searchElderDetail();

    void insertElderDetail(@Param("tableName") String tableName, @Param("list") List<ElderDetail> list);
}
