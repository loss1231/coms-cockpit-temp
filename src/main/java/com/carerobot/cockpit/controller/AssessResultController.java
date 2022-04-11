package com.carerobot.cockpit.controller;


import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.dto.AssessResultDto;
import com.carerobot.cockpit.service.AssessResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/assess")
@Api(tags = "回写评估结果")
public class AssessResultController {

    @Autowired
    private AssessResultService assessResultService;

    @PostMapping("/AddAssessResult")
    @ApiOperation("回写评估结果")
    public ResultVo AddAssessResult(@RequestBody AssessResultDto dto){
        return ResultVo.ok(this.assessResultService.addAssessResult(dto));
    }

}

