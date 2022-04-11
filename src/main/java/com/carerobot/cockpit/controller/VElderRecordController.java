package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.common.vo.PageResultVo;
import com.carerobot.cockpit.dto.ElderDetailReportDto;
import com.carerobot.cockpit.dto.ElderPostParamDto;
import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.service.VElderRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api("长者统计接口控制器")
public class VElderRecordController {

    @Autowired
    private VElderRecordService vElderRecordService;

//    @GetMapping("/GetElderStatistics")
//    @ApiOperation("获取长者统计")
//    public ResultVo GetElderStatistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime dateStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime dateEnd,Integer liveType) {
//        String strWhere = String.format("recordTime BETWEEN '{0}' AND '{1}'", dateStart, dateEnd);
//        strWhere="recordTime BETWEEN '"+dateStart.toString("yyyy-MM-dd HH:mm:ss")+"' AND '"+dateEnd.toString("yyyy-MM-dd HH:mm:ss")+"'";
//        List<ElderStatisticsDto> list = vElderRecordService.getElderStatistics(strWhere,dateStart,dateEnd,liveType);
//        return ResultVo.ok(list);
//    }
//
//    @GetMapping("/GetElderDetails")
//    @ApiOperation("获取长者统计明细")
//    public ResultVo GetElderDetails(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime dateStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime dateEnd,Integer liveType,String ItemCode,String Value,String Area) {
//        ElderDetailDto list = vElderRecordService.getElderDetails(dateStart,dateEnd,liveType,ItemCode,Value,Area);
//        return ResultVo.ok(list);
//    }

    @GetMapping("/GetNewElderDetails")
    @ApiOperation("获取长者统计明细")
    public ResultVo GetNewElderDetails(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateEnd, Integer liveType, String ItemCode, String Value, String Area, HttpServletRequest request) {
        String token = request.getHeader("Authorizatior");
        List<ElderStatisticsDto> list = vElderRecordService.getNewElderDetails(dateStart,dateEnd,liveType,ItemCode,Value,Area,token);
        return ResultVo.ok(list);
    }


    /**
     * 2022.3.21
     * @return
     */
    @PostMapping("/GetElderDetails")
    @ApiOperation("获取长者统计明细")
    public ResultVo GetElderDetails(@RequestBody ElderPostParamDto el) throws ParseException {

        try{
        PageResultVo resultVo = new PageResultVo();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = el.getStartDate();
        if (startDate == null || startDate.equals("")){
            startDate = dateformat.parse("2022-03-08 00:00:00");
        }
        Date endDate = el.getEndDate();
        if (endDate == null || endDate.equals("")){
            endDate = dateformat.parse("2022-03-24 00:00:00");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startSnapsShot = sdf.format(startDate).substring(0,8);
        String endSnapsShot = sdf.format(endDate).substring(0,8);

        String types = el.getTypes();
            String item_name = el.getItem_Name();
        List<Integer> OrgId = el.getOrgId();
        String type = el.getType();
        String nameOrRoomNo = el.getNameOrRoomNo();
        nameOrRoomNo = nameOrRoomNo.trim();



        //当前页数
        int currPage = el.getCurrPage();
        //每页记录数
        int pageSize = el.getPageSize();
        //获取总记录数
        int totalCount = vElderRecordService.GetElderDetailsCounts(startDate,endDate,types,item_name,OrgId,type,nameOrRoomNo,startSnapsShot,endSnapsShot);
        //当前记录数
        int currcount=(currPage-1)*pageSize;

        List<ElderDetailReportDto> list = vElderRecordService.GetElderDetails(startDate,endDate,types,item_name,OrgId,type,nameOrRoomNo,currcount,pageSize,startSnapsShot,endSnapsShot);
        resultVo.setPageIndex((long)currPage);
        resultVo.setPageCount((long) ((totalCount % pageSize == 0)?(totalCount/pageSize):(totalCount/pageSize)+1));
        resultVo.setPageSize((long)pageSize);
        resultVo.setRecordCount((long)totalCount);
        resultVo.setList(list);
        return ResultVo.ok(resultVo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVo.fail("暂无数据");
        }
    }

}

