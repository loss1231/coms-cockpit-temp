package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Item;
import com.carerobot.cockpit.model.ItemDetail;
import com.carerobot.cockpit.mapper.ItemDetailMapper;
import com.carerobot.cockpit.service.ItemDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
public class ItemDetailServiceImpl extends ServiceImpl<ItemDetailMapper, ItemDetail> implements ItemDetailService {

    @DataSource(DataSourceEnum.DB6)
    public List<ItemDetail> getEntireAccountFromBill() {
        return this.list(new QueryWrapper<ItemDetail>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<ItemDetail> itemDetails,List<ItemDetail> newInfo) {
        if (itemDetails.isEmpty())return;
        if (this.updateBatchById(itemDetails)){
            this.saveBatch(newInfo);
        }
    }

    @DataSource(DataSourceEnum.DB6)
    public List<ItemDetail> getNewAccountFromBill() {
        return this.baseMapper.getNewItemDetails(DateUtils.addDateHours(new Date(),-8),DateUtils.addDateDays(new Date(),-8));
    }

    @DataSource(DataSourceEnum.DB6)
    public Collection<ItemDetail> getNewInfo(List<Integer> list) {
        if (list.isEmpty()){
            return null;
        }
        Collection<ItemDetail> itemDetails = this.list(new QueryWrapper<ItemDetail>().in("item_id",list));
        return itemDetails;
    }
}
