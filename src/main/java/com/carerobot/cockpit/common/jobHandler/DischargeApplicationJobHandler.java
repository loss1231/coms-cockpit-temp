package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.service.DischargeApplicationService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DischargeApplicationJobHandler {

    private Logger logger = LoggerFactory.getLogger(DischargeApplicationJobHandler.class);

    private DischargeApplicationService dischargeApplicationService;

    @XxlJob("DischargeApplicationJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        this.dischargeApplicationService.updateThenAdd(this.dischargeApplicationService.getEntireDischargeFromRoutine(),this.dischargeApplicationService.getNewDischargeFromRoutine());
        return ReturnT.SUCCESS;
    }
}
