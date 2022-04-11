package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.ElderDetail;
import com.carerobot.cockpit.model.OccupancyRate;
import com.carerobot.cockpit.model.newOccupancyRate;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author YY
 * @Description
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class CockpitSnapshotJobHandler {

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @XxlJob("OccupancySnapshotJobHandler")
    public ReturnT<String> OccupancySnapshot(String param) {
        this.OccupancySnapshot();
        this.ElderDetailSnapshot();
        return ReturnT.SUCCESS;
    }

    private void OccupancySnapshot(){
        Date now = new Date();
        String tableName = "occupancy_snapshot_"+DateUtils.format(new Date(),"yyyyMMdd");
        //创建表
        cockpitArchivesService.createOccupancyTable(tableName);
        List<newOccupancyRate> result1 = cockpitArchivesService.searchOccupancy(1,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result2 = cockpitArchivesService.searchOccupancy(2,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result3 = cockpitArchivesService.searchOccupancy(3,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result4 = cockpitArchivesService.searchOccupancy(4,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result5 = cockpitArchivesService.searchOccupancy(5,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result6 = cockpitArchivesService.searchOccupancy(6,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result7 = cockpitArchivesService.searchOccupancy(7,DateUtils.addDateDays(now,-1),now);
        result1.addAll(result2);
        result1.addAll(result3);
        result1.addAll(result4);
        result1.addAll(result5);
        result1.addAll(result6);
        result1.addAll(result7);
        cockpitArchivesService.insertOccupancy(tableName,result1);
    }

    private void ElderDetailSnapshot(){
        String tableName = "elder_detail_snapshot_"+DateUtils.format(new Date(),"yyyyMMdd");
        //创建表
        cockpitArchivesService.createElderDetailTable(tableName);
        List<ElderDetail> result = cockpitArchivesService.searchElderDetail();
        cockpitArchivesService.insertElderDetail(tableName,result);
    }

}
