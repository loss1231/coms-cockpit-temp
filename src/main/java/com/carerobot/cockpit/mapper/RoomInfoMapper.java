package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.dto.OccupancyRateDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.RoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    void createNewRoomTable(@Param("tableName") String tableName);

    void dropRoomTable(@Param("tableName") String tableName);

    void batchInsertRoom(@Param("list") List<RoomJsonDto> roomJsonDtos, @Param("tableName") String name);

    OccupancyRateDto beginningUnitMap(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("itemName") String itemName,@Param("param1") String param1,@Param("param2") String param2,@Param("tableName1") String tableName1,@Param("tableName2") String tableName2);

    OccupancyRateDto doubleArchivesMap(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("itemName") String itemName,@Param("param1") String param1,@Param("param2") String param2,@Param("tableName1") String tableName1,@Param("tableName2") String tableName2,@Param("tableName3") String tableName3);

    OccupancyRateDto singArchivesRate(@Param("tableName1") String tableName1,@Param("tableName2") String tableName2);

    OccupancyRateDto doubleArchivesRate(@Param("tableName1") String tableName1,@Param("tableName2") String tableName2,@Param("tableName3") String tableName3);
}
