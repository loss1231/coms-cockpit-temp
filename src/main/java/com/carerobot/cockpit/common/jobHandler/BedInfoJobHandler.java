package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.service.RoomBedinfoService;
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
public class BedInfoJobHandler {

    private Logger logger = LoggerFactory.getLogger(BedInfoJobHandler.class);

    @Autowired
    private RoomBedinfoService bedInfoService;

//    @XxlJob("BedInfoEntireDataJobHandler")
//    public ReturnT<String> executorEntireData(String param) {
//        //删除当月手工跑的部分数据
//        logger.info("开始床位数据定时任务日志----");
//        List<RoomBedinfo> dataList = this.bedInfoService.getEntireBedInfoFromSpace();
//        if(!dataList.isEmpty()){
//            logger.info("拉取床位数据成功,本次拉取的条数：{}",dataList.size());
//            this.bedInfoService.updateThenAdd(dataList);
//        }
//        return ReturnT.SUCCESS;
//    }

    @XxlJob("BedInfoEntireDataJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        this.bedInfoService.updateThenAdd(this.bedInfoService.getEntireBedInfoFromSpace(),null);
        return ReturnT.SUCCESS;
    }
}
