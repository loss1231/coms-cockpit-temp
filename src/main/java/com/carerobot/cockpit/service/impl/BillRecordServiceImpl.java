package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.AccountRecord;
import com.carerobot.cockpit.model.BillRecord;
import com.carerobot.cockpit.mapper.BillRecordMapper;
import com.carerobot.cockpit.service.BillRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Service
public class BillRecordServiceImpl extends ServiceImpl<BillRecordMapper, BillRecord> implements BillRecordService {

    @Autowired
    private BillRecordService billRecordService;

    @DataSource(DataSourceEnum.DB6)
    public List<BillRecord> getEntireAccountFromBill() {
        return this.list(new QueryWrapper<BillRecord>());
    }

    @Override
    public void updateThenAdd(List<BillRecord> billRecords,List<BillRecord> newInfo) {
        if (billRecords.isEmpty())return;
//        if (this.updateBatchById(billRecords)){
//            this.saveBatch(newInfo);
//        }
        this.updateBatchById(billRecords,billRecords.size());
        for (BillRecord billRecord : billRecords) {
            BillRecord record = this.billRecordService.getById(billRecord.getId());
            if (record == null){
                this.save(billRecord);
            }
        }
    }

    @DataSource(DataSourceEnum.DB6)
    public List<BillRecord> getNewAccountFromBill() {
        return this.list(new QueryWrapper<BillRecord>().lt("create_time",DateUtils.addDateHours(new Date(),-8)).gt("create_time",DateUtils.addDateHours(new Date(),-9)));
    }
}
