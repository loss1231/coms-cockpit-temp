package com.carerobot.cockpit.service;

import com.carerobot.cockpit.dto.ElderDetailDto;
import com.carerobot.cockpit.dto.ElderDetailReportDto;
import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.model.VElderRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author wd
 * @since 2021-05-17
 */
public interface VElderRecordService  extends IService<VElderRecord>{

    List<ElderStatisticsDto> getElderStatistics(String strWhere, DateTime dateStart,DateTime dateEnd,Integer liveType);
    ElderDetailDto getElderDetails(DateTime dateStart, DateTime dateEnd, Integer liveType,String ItemCode,String Value,String Area);

    List<ElderStatisticsDto> getNewElderDetails(Date dateStart, Date dateEnd, Integer liveType, String itemCode, String value, String area, String token);

    int GetElderDetailsCounts(Date startDate, Date endDate, String types, String itemName, List<Integer> orgId, String type, String nameOrRoomNo,String startSnapsShot,String endSnapsShot);

    List<ElderDetailReportDto> GetElderDetails(Date dateStart, Date dateEnd,String types, String itemName,List<Integer> OrgId,String type,String nameOrRoomNo,Integer currcount,Integer pageSize,String startSnapsShot,String endSnapsShot);

}
