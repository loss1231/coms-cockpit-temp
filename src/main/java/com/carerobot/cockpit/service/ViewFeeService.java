package com.carerobot.cockpit.service;

import com.carerobot.cockpit.common.vo.PageResultVo;
import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.dto.FeeIncomeRecognitionShowDto;
import com.carerobot.cockpit.dto.FeeReportQueryDto;
import com.carerobot.cockpit.dto.FeeReportShowDto;
import com.carerobot.cockpit.model.ViewFee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author wd
 * @since 2022-03-28
 */
public interface ViewFeeService extends IService<ViewFee> {
    List<FeeReportShowDto> getFeeForReport(Date startDate, Date endDate, Integer name, Integer bedNo, String itemName, String orgName);

    PageResultVo getFeeForReportByPage(FeeReportQueryDto search);

    List<FeeIncomeRecognitionShowDto> getIncomeRecognitionReport(FeeReportQueryDto search);
}
