package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.BillRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface BillRecordService extends IService<BillRecord> {

    List<BillRecord> getEntireAccountFromBill();

    void updateThenAdd(List<BillRecord> billRecords,List<BillRecord> newInfo);

    List<BillRecord> getNewAccountFromBill();

}
