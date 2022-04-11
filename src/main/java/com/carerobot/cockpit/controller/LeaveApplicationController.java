package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.dto.ElderLeaveTranDto;
import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.model.LeaveApplication;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.carerobot.cockpit.service.LeaveApplicationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api()
public class LeaveApplicationController {

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @GetMapping("/GetElderLeave")
    @ApiOperation("获取长者统计")
    public ResultVo GetElderStatistics(
            @RequestParam
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss") Date dateStart,
            @RequestParam
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")Date dateEnd,
            Integer areaId ,
            Integer liveType) {
        ElderLeaveTranDto leaveApplicationTable = this.archivesinfoService.getLeaveApplicationTable(dateStart, dateEnd, areaId, liveType);
        return ResultVo.ok(leaveApplicationTable);
    }

    /**
     * @Author
     * @Description  测试接口是否通
     * @Date  2021/6/20
     * @Param
     * @return com.carerobot.cockpit.common.vo.ResultVo
     **/
    @GetMapping("/GetAllInfo")
    @ApiOperation("获取长者统计")
    public ResultVo GetAll(){
        List<LeaveApplication> dataFromRemote = this.leaveApplicationService.getDataFromRemote();
        return ResultVo.ok(dataFromRemote);
    }
}

