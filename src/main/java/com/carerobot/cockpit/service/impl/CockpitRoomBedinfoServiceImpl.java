package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.mapper.CockpitRoomBedinfoMapper;
import com.carerobot.cockpit.mapper.RoomBedinfoMapper;
import com.carerobot.cockpit.model.CockpitBedInfo;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.service.CockpitRoomBedinfoService;
import com.carerobot.cockpit.service.RoomBedinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
@Service
public class CockpitRoomBedinfoServiceImpl extends ServiceImpl<CockpitRoomBedinfoMapper, CockpitBedInfo> implements CockpitRoomBedinfoService {

}
