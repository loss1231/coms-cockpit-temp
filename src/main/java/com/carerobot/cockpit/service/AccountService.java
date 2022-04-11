package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 账户表 服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface AccountService extends IService<Account> {

    List<Account> getEntireAccountFromBill();

    void updateThenAdd(List<Account> account,List<Account> bill);

    List<Account> getNewAccountFromBill();
}
