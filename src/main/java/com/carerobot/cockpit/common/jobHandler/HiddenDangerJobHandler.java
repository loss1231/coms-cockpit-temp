package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.HiddenDanger;
import com.carerobot.cockpit.model.HiddenDangerRectification;
import com.carerobot.cockpit.service.HiddenDangerRectificationService;
import com.carerobot.cockpit.service.HiddenDangerService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 隐患巡查表数据同步job
 * @author swb
 * @date 2021/12/28
 **/
@Component
public class HiddenDangerJobHandler {

    private Logger logger = LoggerFactory.getLogger(HiddenDangerJobHandler.class);

    @Autowired
    private HiddenDangerService hiddenDangerService;

    @Autowired
    private HiddenDangerRectificationService hiddenDangerRectificationService;

    @XxlJob("hiddenDangerEntireDataJobHandler")
    public ReturnT<String> entireDataExecutor(String param) {
        logger.info("隐患巡查数据同步定时任务日志");
        List<HiddenDanger> dataList = this.hiddenDangerService.getEntireDataFromFile();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,条数：{}",dataList.size() );
            this.hiddenDangerService.updateThenAdd(dataList);
        }

        logger.info("隐患整改数据同步定时任务日志");
        List<HiddenDangerRectification> dataList1 = this.hiddenDangerRectificationService.getEntireDataFromFile();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,条数：{}",dataList1.size() );
            this.hiddenDangerRectificationService.updateThenAdd(dataList1);
        }
        return ReturnT.SUCCESS;
    }
}
