package com.carerobot.cockpit.common.jobHandler;

import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.service.UpdataHistoryService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class ResetInfoJobHandler {

    @Autowired
    private UpdataHistoryService updataHistoryService;

//    @XxlJob("BedInfoEntireDataJobHandler")
//    public ReturnT<String> resetAllInfo(String param) {
//
//        try {
//
//            String[] split = param.split(",");
//            for (String s : split) {
//
//                updataHistoryService.deleteTableAllInfo(s);
//
//            }
//
//        }catch (Exception e){
//            return ReturnT.FAIL;
//        }
//
//        return ReturnT.SUCCESS;
//    }


}
