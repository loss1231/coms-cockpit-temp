package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.multi.DataSourceContextHolder;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Account;
import com.carerobot.cockpit.mapper.AccountMapper;
import com.carerobot.cockpit.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountService accountService;

    @DataSource(DataSourceEnum.DB6)
    @Override
    public List<Account> getEntireAccountFromBill() {
        System.out.println(this.count(new QueryWrapper<Account>()));
        return this.list(new QueryWrapper<Account>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<Account> account,List<Account> bill) {
        if (account.isEmpty())return;
//        if (this.updateBatchById(account)){
//
//            this.saveBatch(bill);
//        }
        for (Account id : account) {
            Account one = this.getById(id.getId());
            if (one != null){
                this.updateById(id);
            }else{
                this.save(id);
            }
        }
    }

    @DataSource(DataSourceEnum.DB6)
    @Override
    public List<Account> getNewAccountFromBill() {
        return this.list(new QueryWrapper<Account>().lt("create_time",DateUtils.addDateHours(new Date(),-8)).gt("create_time",DateUtils.addDateHours(new Date(),-9)));
    }
}
