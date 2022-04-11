package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.dto.OccupancyPostParamDto;
import com.carerobot.cockpit.dto.OccupancyRateDto;
import com.carerobot.cockpit.service.ContractInfoService;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 *  前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api
public class OccupancyRateController {

    @Autowired
    private ContractInfoService contractInfoService;

    /**
     * 2022.3.23
     * @param oc 接收前端post的对象
     * @return
     */
    @PostMapping("/getOccupancyRate")
    @ApiOperation("入住率")
    public ResultVo getOccupancyRate(@RequestBody OccupancyPostParamDto oc) throws ParseException {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = oc.getStartDate();
            if (startDate == null) {
                startDate = dateformat.parse("2022-03-08 00:00:00");
            }
            Date endDate = oc.getEndDate();
            if (endDate == null) {
                endDate = dateformat.parse("2022-03-24 00:00:00");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String startSnapsShot = sdf.format(startDate).substring(0, 8);
            String endSnapsShot = sdf.format(endDate).substring(0, 8);

            List<Integer> OrgId = oc.getOrgId();
            return ResultVo.ok(this.contractInfoService.getOccupancyRate(startDate, endDate, OrgId, startSnapsShot, endSnapsShot));
        }catch(Exception e){
            return ResultVo.fail("暂无数据");
        }
        }

//    @GetMapping("/getOccupancy")
//    @ApiOperation("入住率")
//    public ResultVo getOccupancyRate(
//            @RequestParam
//            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss") Date dateStart,
//            @RequestParam
//            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//            @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")Date dateEnd,
//            Integer areaId ,
//            Integer liveType,
//            Integer type){
//        return ResultVo.ok(this.contractInfoService.getOccupancy(dateStart,dateEnd,areaId,liveType,type));
//    }

    @GetMapping("/GetNewRoomRate")
    @ApiOperation("新入住率")
    public ResultVo GetNewRoomRate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateEnd,String ItemCode, String Value, String Area, HttpServletRequest request) {
        String token = request.getHeader("Authorizatior");
        List<OccupancyRateDto> list = contractInfoService.getNewRoomRate(dateStart,dateEnd,ItemCode,Value,Area,token);
        return ResultVo.ok(list);
    }
}

