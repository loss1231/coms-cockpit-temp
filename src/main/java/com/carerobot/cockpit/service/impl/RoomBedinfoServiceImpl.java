package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.mapper.RoomBedinfoMapper;
import com.carerobot.cockpit.service.RoomBedinfoService;
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
public class RoomBedinfoServiceImpl extends ServiceImpl<RoomBedinfoMapper, RoomBedinfo> implements RoomBedinfoService {

    private Logger logger = LoggerFactory.getLogger(LeaveApplicationServiceImpl.class);

    @DataSource(DataSourceEnum.DB4)
    public List<RoomBedinfo> getBedInfoFromSpace() {
        QueryWrapper<RoomBedinfo> bedInfoWrapper = new QueryWrapper<>();
        bedInfoWrapper.between("CreationTime", DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")
                , DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"));
        return this.list(bedInfoWrapper);
    }

    @Override
    @Transactional
    public void updateThenAdd(List<RoomBedinfo> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
            List<RoomBedinfo> todayData = getBedInfoFromSpace();
            this.saveBatch(todayData);
            logger.info("插入当日新增数据结束，条数为：{}", todayData);
            logger.info("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
    }

    @DataSource(DataSourceEnum.DB4)
    @Override
    public List<RoomBedinfo> getEntireBedInfoFromSpace() {
        System.out.println(this.count(new QueryWrapper<RoomBedinfo>()));
        return this.list(new QueryWrapper<RoomBedinfo>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<RoomBedinfo> roomBedInfo,List<RoomBedinfo> roomBed) {
        if (roomBedInfo.isEmpty())return;
//        if (this.updateBatchById(roomBedInfo)){
//            this.saveBatch(roomBed);
//        }
        for (RoomBedinfo bedinfo : roomBedInfo) {
            RoomBedinfo roomBedinfo = this.getById(bedinfo.getId());
            if (roomBedinfo==null){
                this.save(bedinfo);
            }else{
                this.updateById(bedinfo);
            }
        }
    }

    @DataSource(DataSourceEnum.DB4)
    @Override
    public List<RoomBedinfo> getNewBedInfoFromSpace() {
        return this.list(new QueryWrapper<RoomBedinfo>().lt("CreationTime",new Date()).gt("CreationTime",DateUtils.addDateHours(new Date(),-1)));
    }
}
