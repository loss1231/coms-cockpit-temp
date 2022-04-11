package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.mapper.SysSettingMapper;
import com.carerobot.cockpit.model.SysSetting;
import com.carerobot.cockpit.service.SysSettingService;
import org.springframework.stereotype.Service;

@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements SysSettingService {
}
