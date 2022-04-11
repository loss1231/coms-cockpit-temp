package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.ComboboxItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-12-28
 */
public interface ComboboxItemService extends IService<ComboboxItem> {

    List<ComboboxItem> getEntireDataFromFile();

    void updateThenAdd(List<ComboboxItem> dataList);
}
