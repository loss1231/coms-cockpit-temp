package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.ItemDetail;
import com.carerobot.cockpit.model.ItemDetailParam;
import com.carerobot.cockpit.mapper.ItemDetailParamMapper;
import com.carerobot.cockpit.service.ItemDetailParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ItemDetailParamServiceImpl extends ServiceImpl<ItemDetailParamMapper, ItemDetailParam> implements ItemDetailParamService {

    @Autowired
    private ItemDetailParamService itemDetailParamService;

    @DataSource(DataSourceEnum.DB6)
    public List<ItemDetailParam> getEntireAccountFromBill() {
        return this.list(new QueryWrapper<ItemDetailParam>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<ItemDetailParam> itemDetailParams,List<ItemDetailParam> newInfo) {
        if (itemDetailParams.isEmpty())return;
        if (this.updateBatchById(itemDetailParams)){
            this.saveBatch(newInfo);
        }
    }

    @DataSource(DataSourceEnum.DB6)
    public List<ItemDetailParam> getNewAccountFromBill() {
        return this.list(new QueryWrapper<ItemDetailParam>().lt("create_time",DateUtils.addDateHours(new Date(),-8)).gt("create_time",DateUtils.addDateHours(new Date(),-9)));
    }
}
