package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Account;
import com.carerobot.cockpit.model.AccountRecord;
import com.carerobot.cockpit.mapper.AccountRecordMapper;
import com.carerobot.cockpit.model.Item;
import com.carerobot.cockpit.service.AccountRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 缴费记录表 服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Service
public class AccountRecordServiceImpl extends ServiceImpl<AccountRecordMapper, AccountRecord> implements AccountRecordService {

    @Autowired
    private AccountRecordService accountRecordService;

    @Autowired
    private ApplicationContext context;

    @DataSource(DataSourceEnum.DB6)
    @Override
    public List<AccountRecord> getEntireAccountFromBill() {
        return this.list(new QueryWrapper<AccountRecord>());
    }

    @Override
    public void updateThenAdd() {

        //获取aop代理对象
        AccountRecordService recordService = context.getBean(AccountRecordService.class);

        //更新
        List<AccountRecord> bill = recordService.getEntireAccountFromBill();
        this.updateBatchById(bill,bill.size());

        //差量增加
        List<AccountRecord> list = this.list(new QueryWrapper<>());
        if (!list.isEmpty()){
            List<AccountRecord> different = recordService.different(list.stream().map(AccountRecord::getId).collect(Collectors.toList()));
            this.saveBatch(different,different.size());
        }
//        if (accountRecords.isEmpty())return;
//        if (this.updateBatchById(accountRecords) && !newInfo.isEmpty()){
//            this.saveBatch(newInfo);
//        }
//        for (int i = 0;i<25;i++){
//            List<AccountRecord> list = this.list(new QueryWrapper<AccountRecord>().lt("create_time", DateUtils.addDateHours(new Date(), -i)).gt("create_time", DateUtils.addDateHours(new Date(), -i-1)));
//        }
    }

    @DataSource(DataSourceEnum.DB6)
    @Override
    public List<AccountRecord> getNewAccountFromBill() {
        return this.list(new QueryWrapper<AccountRecord>().lt("create_time",new Date()).gt("create_time",DateUtils.addDateHours(new Date(),-1)));
    }

    @Override
    @DataSource(DataSourceEnum.DB6)
    public List<AccountRecord> different(List<Integer> ids){
        return this.baseMapper.different(ids);
    }
}
