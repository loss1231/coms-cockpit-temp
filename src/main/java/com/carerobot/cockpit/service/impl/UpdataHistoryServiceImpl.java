package com.carerobot.cockpit.service.impl;

import com.carerobot.cockpit.model.UpdataHistory;
import com.carerobot.cockpit.mapper.UpdataHistoryMapper;
import com.carerobot.cockpit.service.UpdataHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Service
public class UpdataHistoryServiceImpl extends ServiceImpl<UpdataHistoryMapper, UpdataHistory> implements UpdataHistoryService {

    public void deleteTableAllInfo(String tableName){
        this.baseMapper.deleteTableAllInfo(tableName);
    }

}
