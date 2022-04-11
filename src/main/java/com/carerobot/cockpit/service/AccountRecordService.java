package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.AccountRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 缴费记录表 服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface AccountRecordService extends IService<AccountRecord> {

    List<AccountRecord> getEntireAccountFromBill();

    void updateThenAdd();

    List<AccountRecord> getNewAccountFromBill();

    List<AccountRecord> different(List<Integer> ids);

}
