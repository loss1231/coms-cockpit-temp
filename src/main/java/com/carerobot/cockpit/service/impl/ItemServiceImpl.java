package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.enums.TableNameEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.mapper.UpdataHistoryMapper;
import com.carerobot.cockpit.model.EnumsParam;
import com.carerobot.cockpit.model.Item;
import com.carerobot.cockpit.mapper.ItemMapper;
import com.carerobot.cockpit.model.ItemDetail;
import com.carerobot.cockpit.model.UpdataHistory;
import com.carerobot.cockpit.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.service.UpdataHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private UpdataHistoryService updataHistoryService;

    @Autowired
    private ItemDetailServiceImpl itemDetailService;

    @Autowired
    private ApplicationContext context;

    @Override
    @Transactional
    @DataSource(DataSourceEnum.DB6)
    public List<Item> getEntireAccountFromBill() {
//        UpdataHistory item = this.updataHistoryService.getOne(new QueryWrapper<UpdataHistory>().eq("table_name", TableNameEnum.Item));
//        List<Item> list = this.list(new QueryWrapper<Item>().lt("create_time", new Date()).gt("create_time", item.getLastUpdataDate()));
//        item.setLastUpdataDate(new Date());
//        updataHistoryService.updateById(item);
        return this.list(new QueryWrapper<Item>());
    }

    @Override
    public void updateThenAdd() {
        //获取代理对象
        ItemService itemService = context.getBean(ItemService.class);

        //全表更新
        List<Item> items = itemService.getEntireAccountFromBill();
        this.updateBatchById(items,items.size());

        //差量新增
        List<Item> list = this.list(new QueryWrapper<Item>());
        List<Item> differentItems = itemService.different(list.stream().map(Item::getId).collect(Collectors.toList()));
        if (!differentItems.isEmpty()){
            this.saveBatch(differentItems);
            List<Integer> collect = differentItems.stream().map(Item::getId).collect(Collectors.toList());
                Collection<ItemDetail> newInfoDetail = this.itemDetailService.getNewInfo(collect);
                if(!newInfoDetail.isEmpty()){
                    for (ItemDetail itemDetail : newInfoDetail) {
                        ItemDetail itemDetailServiceById = this.itemDetailService.getById(itemDetail.getId());
                        if (itemDetailServiceById==null){
                            try{
                                this.itemDetailService.save(itemDetail);
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                                System.out.println("新增失败 id："+itemDetail.getId());
                            }
                        }
                    }
                }
        }




//        if (items.isEmpty()){
//            return;
//        }
//          if (this.updateBatchById(items)){
//            List<ItemDetail> entireAccountFromBill = this.itemDetailService.getEntireAccountFromBill();
//            this.itemDetailService.updateBatchById(entireAccountFromBill);
//            if (!newInfo.isEmpty()){
//                this.saveBatch(newInfo);
//                List<Integer> collect = newInfo.stream().map(o -> o.getId()).collect(Collectors.toList());
//                Collection<ItemDetail> newInfoDetail = this.itemDetailService.getNewInfo(collect);
//                if(!newInfoDetail.isEmpty()){
//                    for (ItemDetail itemDetail : newInfoDetail) {
//                        ItemDetail itemDetailServiceById = this.itemDetailService.getById(itemDetail.getId());
//                        if (itemDetailServiceById==null){
//                            try{
//                                this.itemDetailService.save(itemDetail);
//                            }catch (Exception e){
//                                System.out.println(e.getMessage());
//                                System.out.println("新增失败 id："+itemDetail.getId());
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    @Override
    @DataSource(DataSourceEnum.DB6)
    public List<Item> getNewAccountFromBill() {
        return this.list(new QueryWrapper<Item>().lt("create_time",new Date()).gt("create_time",DateUtils.addDateHours(new Date(),-1)));
}

    @Override
    @DataSource(DataSourceEnum.DB6)
    public List<Item> different(List<Integer> ids){
        return this.baseMapper.different(ids);
    }

}
