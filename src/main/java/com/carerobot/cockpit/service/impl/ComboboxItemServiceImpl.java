package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.model.ComboboxItem;
import com.carerobot.cockpit.mapper.ComboboxItemMapper;
import com.carerobot.cockpit.service.ComboboxItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-12-28
 */
@Service
public class ComboboxItemServiceImpl extends ServiceImpl<ComboboxItemMapper, ComboboxItem> implements ComboboxItemService {

    private Logger logger = LoggerFactory.getLogger(ComboboxItemServiceImpl.class);

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<ComboboxItem> getEntireDataFromFile() {
        return this.list(new QueryWrapper<>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<ComboboxItem> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新下拉框旧数据结束");

            //再批量插入操作
            for (ComboboxItem combobox : dataList) {
                ComboboxItem byId = this.getById(combobox.getId());
                if (byId==null){
                    this.save(combobox);
                }
            }
            logger.info("下拉框数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("下拉框数据更新失败");
        }
    }
}
