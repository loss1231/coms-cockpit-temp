package com.carerobot.cockpit.service;

import com.carerobot.cockpit.dto.AccidentReportForEDto;
import com.carerobot.cockpit.model.AccidentReport;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-16
 */
public interface AccidentReportService extends IService<AccidentReport> {

    List<AccidentReport> getDataAccidentFromRemote();

    boolean saveBatchIntoLocal(List<AccidentReport> dataList);

    List<AccidentReport> getDataAccidentByDay();

    void updateThenAdd(List<AccidentReport> dataList);

    List<AccidentReport> getEntireTableAccident();

    Map<String,Object> GetAddAccident(AccidentReportForEDto accidentDto, HttpServletRequest request);

    String sendAccidentMessage(Long userId, String reportName, Date reportTime, String description, Integer reportId);

    void saveOfficialAccountUsers();
}
