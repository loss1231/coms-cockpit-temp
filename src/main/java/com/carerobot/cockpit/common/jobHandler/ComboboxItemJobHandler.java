package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.ComboboxItem;
import com.carerobot.cockpit.service.ComboboxItemService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 下拉框表数据同步job
 * @author swb
 * @date 2021/12/28
 **/
@Component
public class ComboboxItemJobHandler {

    private Logger logger = LoggerFactory.getLogger(ComboboxItemJobHandler.class);

    @Autowired
    private ComboboxItemService comboboxItemService;

    @XxlJob("comboboxItemEntireDataJobHandler")
    public ReturnT<String> entireDataExecutor(String param) {
        logger.info("下拉框数据同步定时任务日志");
        List<ComboboxItem> dataList = this.comboboxItemService.getEntireDataFromFile();
        if(!dataList.isEmpty()){
            logger.info("获取数据成功,条数：{}",dataList.size() );
            this.comboboxItemService.updateThenAdd(dataList);
        }
        return ReturnT.SUCCESS;
    }
}
