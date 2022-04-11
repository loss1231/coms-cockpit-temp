package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.service.*;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class BillJobHandler {

    private Logger logger = LoggerFactory.getLogger(BillJobHandler.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDetailService itemDetailService;

    @Autowired
    private ItemDetailParamService itemDetailParamService;

    @Autowired
    private EnumsParamService enumsParamService;

    @Autowired
    private BillRecordService billRecordService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRecordService accountRecordService;

    @XxlJob("BillJobHandler")
    public ReturnT<String> executorEntireData(String param) {

        this.accountService.updateThenAdd(this.accountService.getEntireAccountFromBill(),null);
//
        this.accountRecordService.updateThenAdd();

        this.billRecordService.updateThenAdd(this.billRecordService.getEntireAccountFromBill(),null);
        //this.enumsParamService.updateThenAdd();

        this.itemService.updateThenAdd();

        //this.itemDetailService.updateThenAdd(this.itemDetailService.getEntireAccountFromBill(),this.itemDetailService.getNewAccountFromBill());

        //this.itemDetailParamService.updateThenAdd(this.itemDetailParamService.getEntireAccountFromBill(),this.itemDetailParamService.getNewAccountFromBill());

        return ReturnT.SUCCESS;
    }


}
