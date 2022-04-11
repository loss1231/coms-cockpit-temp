package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.ItemDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface ItemDetailService extends IService<ItemDetail> {

    List<ItemDetail> getEntireAccountFromBill();

    void updateThenAdd(List<ItemDetail> itemDetails,List<ItemDetail> newInfo);

    List<ItemDetail> getNewAccountFromBill();

    Collection<ItemDetail> getNewInfo(List<Integer> list);
}
