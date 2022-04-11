package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.model.LeaveApplication;
import com.carerobot.cockpit.service.LeaveApplicationService;
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
 * @Date: 2021/6/17 10:19
 * @Description:
 */
@Component
public class LeaveApplicationJobHandler {
    private Logger logger = LoggerFactory.getLogger(LeaveApplicationJobHandler.class);
    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @XxlJob("leaveApplicationEntireJobHandler")
    public ReturnT<String> entireExecutor2(String param) {
        logger.info("请假申请Routine服务数据同步定时任务日志");
        List<LeaveApplication> dataList = this.leaveApplicationService.getEntireTableFromRoutine();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,最近更新条数：{}",dataList.size() );
            this.leaveApplicationService.updateThenAdd(dataList);
        }
        return ReturnT.SUCCESS;
    }
}
