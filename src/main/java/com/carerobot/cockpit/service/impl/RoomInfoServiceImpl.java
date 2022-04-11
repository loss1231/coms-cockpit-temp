package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.dto.ArchivesJsonDto;
import com.carerobot.cockpit.dto.OccupancyRateDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.model.RoomInfo;
import com.carerobot.cockpit.mapper.RoomInfoMapper;
import com.carerobot.cockpit.service.RoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService {

    private Logger logger = LoggerFactory.getLogger(RoomInfoServiceImpl.class);

    @DataSource(DataSourceEnum.DB4)
    public List<RoomInfo> getRoomInfoFromSpace() {
        QueryWrapper<RoomInfo> bedInfoWrapper = new QueryWrapper<>();
        bedInfoWrapper.between("CreationTime", DateUtils.addDateHours(new Date(),-1)
                , new Date());
        return this.list(bedInfoWrapper);
    }

    @Override
    public void updateThenAdd(List<RoomInfo> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
            List<RoomInfo> todayData = getRoomInfoFromSpace();
            this.saveBatch(todayData);
            logger.info("插入当日新增数据结束，条数为：{}", todayData);
            logger.info("房间数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
    }

//    @Override
//    @DataSource(DataSourceEnum.DB4)
//    public List<RoomInfo> getEntireRoomInfoFromSpace() {
//        QueryWrapper<RoomInfo> bedInfoWrapper = new QueryWrapper<>();
//        return this.list(bedInfoWrapper);
//    }

    @Override
    public void createNewRoomTable(String name) {
        this.baseMapper.createNewRoomTable(name);
    }

    @Override
    public void dropRoomTable(String name) {
        this.baseMapper.dropRoomTable(name);
    }

    public void batchInsertRoom(List<RoomJsonDto> dtos, String name){
        this.baseMapper.batchInsertRoom(dtos,name);
    }

    @Override
    public OccupancyRateDto beginningUnitMap(Date startDate, Date endDate, String itemName, String param1, String param2,String tableName1,String tableName2) {
        return this.baseMapper.beginningUnitMap(startDate,endDate,itemName,param1,param2,tableName1,tableName2);
    }

    @Override
    public OccupancyRateDto doubleArchivesMap(Date startDate, Date endDate, String itemName, String param1, String param2,String tableName1,String tableName2,String tableName3) {
        return this.baseMapper.doubleArchivesMap(startDate,endDate,itemName,param1,param2,tableName1,tableName2,tableName3);
    }

    @Override
    public OccupancyRateDto singArchivesRate(String tableName1,String tableName2) {
        return this.baseMapper.singArchivesRate(tableName1,tableName2);
    }

    @Override
    public OccupancyRateDto doubleArchivesRate(String tableName1,String tableName2,String tableName3) {
        return this.baseMapper.doubleArchivesRate(tableName1,tableName2,tableName3);
    }

    @DataSource(DataSourceEnum.DB4)
    @Override
    public List<RoomInfo> getEntireRoomInfoFromSpace() {
        System.out.println(this.count(new QueryWrapper<RoomInfo>()));
        return this.list(new QueryWrapper<RoomInfo>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<RoomInfo> roomInfo,List<RoomInfo> room) {
        if (roomInfo.isEmpty())return;
        if (this.updateBatchById(roomInfo)){
            this.saveBatch(room);
        }
    }

    @DataSource(DataSourceEnum.DB4)
    @Override
    public List<RoomInfo> getNewRoomInfoFromSpace() {
        return this.list(new QueryWrapper<RoomInfo>().lt("CreationTime",new Date()).gt("CreationTime",DateUtils.addDateHours(new Date(),-1)));
    }
}
