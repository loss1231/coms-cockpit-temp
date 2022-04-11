package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.dto.AccidentReportForEDto;
import com.carerobot.cockpit.service.AccidentReportService;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api
public class AccidentReportController {

    @Autowired
    public AccidentReportService accidentReportService;

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @GetMapping("/getAccidentReport")
    @ApiOperation("获取事故统计")
    public ResultVo GetElderStatistics(
            @RequestParam
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss") Date dateStart,
            @RequestParam
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")Date dateEnd) {
        return ResultVo.ok(this.archivesinfoService.getAccidentReport(dateStart, dateEnd));
    }


    @ApiOperation("事件报告新增或修改,不发送消息")
    @GetMapping("/GetAddAccident")
    public ResultVo GetAddAccident(AccidentReportForEDto accidentDto, HttpServletRequest request){
        try{
            if(accidentDto==null) return ResultVo.fail("新增或修改失败");
            String orgId = request.getHeader("OrgId");
//        if (orgId == null && accidentDto.getOrgId() == null)return ResultVo.fail("请选择机构！");
//        accidentDto.setOrgId(Integer.parseInt(orgId));
            if (accidentDto.getOrgId()==null){
                if (orgId == null)return ResultVo.fail("请选择机构！");
                accidentDto.setOrgId(Integer.parseInt(orgId));
            }
            return ResultVo.ok(this.accidentReportService.GetAddAccident(accidentDto,request));
        }catch (Exception e){
            return ResultVo.fail(e.getMessage());
        }
    }
    @GetMapping("/GetAccidentSend")
    @ApiOperation("测试发送公众号信息")
    public ResultVo GetAccidentSend(Long userId, String reportName,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date reportTime, String description, Integer reportId){
        String message=accidentReportService.sendAccidentMessage(userId, reportName,reportTime, description, reportId);
        return  ResultVo.ok(message);
    }
}

