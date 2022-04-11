package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.AccidentRectify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-10-28
 */
public interface AccidentRectifyService extends IService<AccidentRectify> {

    List<AccidentRectify> getEntireTableAccident();

    void updateThenAdd(List<AccidentRectify> dataList);

}
