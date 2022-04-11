package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.common.ResultVo;
import com.carerobot.cockpit.dto.FeeReportQueryDto;
import com.carerobot.cockpit.service.ContractInfoService;
import com.carerobot.cockpit.service.ViewFeeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author wd
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/viewFee")
@Api
public class ViewFeeController {

    @Autowired
    private ViewFeeService viewFeeService;

    @GetMapping("/getFeeForReport")
    @ApiOperation("费用报表")
    public ResultVo getFeeForReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                     @RequestParam("name") Integer name, @RequestParam("bedNo") Integer bedNo,
                                     @RequestParam("itemName") String itemName, @RequestParam("orgName") String orgName){
        return ResultVo.ok(this.viewFeeService.getFeeForReport(startDate, endDate, name, bedNo, itemName, orgName));
    }

    @PostMapping("/getFeeForReportByPage")
    @ApiOperation("费用报表，分页")
    public ResultVo getFeeForReportByPage(@RequestBody FeeReportQueryDto search){
        return ResultVo.ok(this.viewFeeService.getFeeForReportByPage(search));
    }

    @PostMapping("/getIncomeRecognitionReport")
    @ApiOperation("财务确认收入报表")
    public ResultVo getIncomeRecognitionReport(@RequestBody FeeReportQueryDto search){
        if(search.getEndDate()==null || search.getOrgIds()==null ||search.getOrgIds().size()==0)
            return ResultVo.fail("请输入正确参数！");
        return ResultVo.ok(this.viewFeeService.getIncomeRecognitionReport(search));
    }
}

