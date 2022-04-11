package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.dto.AssessResultDto;
import com.carerobot.cockpit.model.AssessResult;
import com.carerobot.cockpit.mapper.AssessResultMapper;
import com.carerobot.cockpit.model.CockpitArchives;
import com.carerobot.cockpit.service.AssessResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.service.CockpitArchivesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-08-27
 */
@Service
public class AssessResultServiceImpl extends ServiceImpl<AssessResultMapper, AssessResult> implements AssessResultService {

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @Override
    public String addAssessResult(AssessResultDto dto) {
        if (dto.getCode()==null)return null;
        CockpitArchives archives = this.cockpitArchivesService.getOne(new QueryWrapper<CockpitArchives>().eq("CareboxCode", dto.getCode()));
        AssessResult result = new AssessResult();
        BeanUtils.copyProperties(dto,result);
        result.setArchiveId(archives.getId());
        result.setCreatedDate(new Date());
        result.setIsDeleted(0);
        result.setAssessDate(new Date(dto.getAssessDate()));
        this.save(result);
        return "操作成功";
    }
}
