package com.carerobot.cockpit.service.impl;

import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.model.Item;
import com.carerobot.cockpit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Service
public class BillServiceImpl {

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

    @DataSource(DataSourceEnum.DB6)
    public Map<String,List> getOldBillInfo(){
        List<Item> itemServiceEntireAccountFromBill = itemService.getEntireAccountFromBill();
        return null;
    }

}
