package com.carerobot.cockpit.service;

import com.carerobot.cockpit.dto.ArchivesJsonDto;
import com.carerobot.cockpit.dto.OccupancyRateDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.model.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface RoomInfoService extends IService<RoomInfo> {

    void updateThenAdd(List<RoomInfo> dataList);

    List<RoomInfo> getEntireRoomInfoFromSpace();

    void createNewRoomTable(String name);

    void dropRoomTable(String name);

    void batchInsertRoom(List<RoomJsonDto> dtos, String name);

    OccupancyRateDto beginningUnitMap(Date startDate,Date endDate,String itemName,String param1,String param2,String tableName1,String tableName2);

    OccupancyRateDto doubleArchivesMap(Date startDate, Date endDate, String itemName, String param1, String param2,String tableName1,String tableName2,String tableName3);

    OccupancyRateDto singArchivesRate(String tableName1,String tableName2);

    OccupancyRateDto doubleArchivesRate(String tableName1,String tableName2,String tableName3);

    void updateThenAdd(List<RoomInfo> roomInfo,List<RoomInfo> room);

    List<RoomInfo> getNewRoomInfoFromSpace();
}
