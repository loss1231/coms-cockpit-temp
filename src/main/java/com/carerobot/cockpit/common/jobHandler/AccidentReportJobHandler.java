package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.AccidentRectify;
import com.carerobot.cockpit.model.AccidentReport;
import com.carerobot.cockpit.service.AccidentRectifyService;
import com.carerobot.cockpit.service.AccidentReportService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AccidentReportJobHandler {

    private Logger logger = LoggerFactory.getLogger(AccidentReportJobHandler.class);

    @Autowired
    private AccidentReportService accidentReportService;

    @Autowired
    private AccidentRectifyService accidentRectifyService;

    @XxlJob("accidentEntireJobHandler")
    public ReturnT<String> Executor(String param) {
        logger.info("事故报告数据同步定时任务日志");
        List<AccidentReport> dataList = this.accidentReportService.getEntireTableAccident();
        if(!dataList.isEmpty()){
            logger.info("获取事故报告数据成功,条数：{}",dataList.size());
            this.accidentReportService.updateThenAdd(dataList);
        }

        logger.info("事故整改数据同步定时任务日志");
        List<AccidentRectify> dataList1 = this.accidentRectifyService.getEntireTableAccident();
        if(!dataList.isEmpty()){
            logger.info("获取事故整改数据成功,条数：{}",dataList1.size());
            this.accidentRectifyService.updateThenAdd(dataList1);
        }
        return ReturnT.SUCCESS;
    }

    @XxlJob("accidentEntireJobHandlerTest")
    public ReturnT<String> Executor1(String param) {
        System.out.println(param);
        return ReturnT.SUCCESS;
    }

    /**
     * 定时拉取公众号信息保存到数据库中
     * @param param
     * @return
     */
    @XxlJob("saveOfficialAccountUsers")
    public ReturnT<String> Executor2(String param) {
        this.accidentReportService.saveOfficialAccountUsers();
        return ReturnT.SUCCESS;
    }
}
