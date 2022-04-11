package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.service.*;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class ArchivesLeaveJobHandler {

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @XxlJob("ArchivesLeaveJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        this.archivesinfoService.archivesLeave();
        return ReturnT.SUCCESS;
    }

}
