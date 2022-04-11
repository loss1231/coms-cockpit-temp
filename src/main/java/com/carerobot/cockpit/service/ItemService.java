package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.Item;
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
public interface ItemService extends IService<Item> {

    void updateThenAdd();

    List<Item> getEntireAccountFromBill();

    List<Item> getNewAccountFromBill();

    List<Item> different(List<Integer> ids);
}
