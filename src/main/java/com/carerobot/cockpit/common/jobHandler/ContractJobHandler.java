package com.carerobot.cockpit.common.jobHandler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.model.ContractLive;
import com.carerobot.cockpit.service.ContractInfoService;
import com.carerobot.cockpit.service.ContractLiveService;
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
 * @Date: 2021/6/22 18:49
 * @Description:
 */
@Component
public class ContractJobHandler {
    private Logger logger = LoggerFactory.getLogger(ContractJobHandler.class);
    @Autowired
    private ContractInfoService contractInfoService;

    @Autowired
    private ContractLiveService contractLiveService;

    @XxlJob("ContractEntireDataJobHandler")
    public ReturnT<String> entireDataExecutor(String param) {
        logger.info("Contract数据同步定时任务日志");
        List<ContractInfo> dataList = this.contractInfoService.getEntireDataFromFile();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,条数：{}",dataList.size() );
            this.contractInfoService.updateThenAdd(dataList);
        }
        //更新ContractLive表
        contractLiveService.updateAndAddContractLive();
        return ReturnT.SUCCESS;
    }

    @XxlJob("ContractLivingJobHandler")
    public ReturnT<String> ContractLivingExecutor(String param) {
        logger.info("Contract数据同步定时任务日志");
        List<ContractLive> dataList = this.contractInfoService.getContractLiving();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,条数：{}",dataList.size() );
            this.contractInfoService.updateThenAddContractLive(dataList);
        }

        return ReturnT.SUCCESS;
    }
}
