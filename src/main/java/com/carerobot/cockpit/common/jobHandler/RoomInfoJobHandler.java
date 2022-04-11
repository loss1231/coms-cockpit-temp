package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.RoomInfo;
import com.carerobot.cockpit.service.RoomInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:
 * @Date: 2021/6/22 16:34
 * @Description:
 */
@Component
public class RoomInfoJobHandler {
    private Logger logger = LoggerFactory.getLogger(RoomInfoJobHandler.class);

    @Autowired
    private RoomInfoService roomInfoService;

//    @XxlJob("RoomInfoEntireJobHandler")
//    public ReturnT<String> entireExecutor(String param) {
//        //删除当月手工跑的部分数据
//        logger.info("开始房间数据定时任务日志----");
//        List<RoomInfo> dataList = this.roomInfoService.getEntireRoomInfoFromSpace();
//        if(!dataList.isEmpty()){
//            logger.info("拉取数据成功,本次拉取的当日的条数：{}",dataList.size());
//            this.roomInfoService.updateThenAdd(dataList);
//        }
//        return ReturnT.SUCCESS;
//    }
    @XxlJob("RoomInfoEntireJobHandler")
    public ReturnT<String> executorAllData(String param) {
        this.roomInfoService.updateThenAdd(this.roomInfoService.getEntireRoomInfoFromSpace(),this.roomInfoService.getNewRoomInfoFromSpace());
        return ReturnT.SUCCESS;
    }
}
