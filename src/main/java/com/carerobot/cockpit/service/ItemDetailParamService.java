package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.ItemDetailParam;
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
public interface ItemDetailParamService extends IService<ItemDetailParam> {

    List<ItemDetailParam> getEntireAccountFromBill();

    void updateThenAdd(List<ItemDetailParam> itemDetailParams,List<ItemDetailParam> newInfo);

    List<ItemDetailParam> getNewAccountFromBill();

}
