package com.carerobot.cockpit.service;

import com.carerobot.cockpit.dto.AssessResultDto;
import com.carerobot.cockpit.model.AssessResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-08-27
 */
public interface AssessResultService extends IService<AssessResult> {

    String addAssessResult(AssessResultDto dto);
}
