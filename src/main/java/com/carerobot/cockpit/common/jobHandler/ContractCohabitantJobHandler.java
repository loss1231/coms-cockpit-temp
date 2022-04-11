package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.ContractCohabitant;
import com.carerobot.cockpit.service.ContractCohabitantService;
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
 * @Date: 2021/6/23 7:51
 * @Description:
 */
@Component
public class ContractCohabitantJobHandler {

    private Logger logger = LoggerFactory.getLogger(BedInfoJobHandler.class);
    @Autowired
    private ContractCohabitantService cohabitantService;

//    @XxlJob("ContractCohabitantEntireJobHandler")
//    public ReturnT<String> entireDataExecutor(String param) {
//        logger.info("ContractCohabitant数据同步定时任务日志");
//        List<ContractCohabitant> dataList = this.cohabitantService.getEntireDataFromFile();
//        if(!dataList.isEmpty()){
//            logger.info("获取数据成功,条数：{}",dataList.size() );
//            this.cohabitantService.updateThenAdd(dataList);
//        }
//        return ReturnT.SUCCESS;
//    }

    @XxlJob("ContractCohabitantEntireJobHandler")
    public ReturnT<String> executorAllData(String param) {
        this.cohabitantService.updateThenAdd(this.cohabitantService.getEntireCohbitantFromFile(),null);
        return ReturnT.SUCCESS;
    }
}
