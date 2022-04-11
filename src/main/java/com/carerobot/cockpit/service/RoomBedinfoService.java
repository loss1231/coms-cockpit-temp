package com.carerobot.cockpit.service;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface RoomBedinfoService extends IService<RoomBedinfo> {

    void updateThenAdd(List<RoomBedinfo> dataList);

    List<RoomBedinfo> getEntireBedInfoFromSpace();

    void updateThenAdd(List<RoomBedinfo> roomBedInfo,List<RoomBedinfo> roomBed);

    List<RoomBedinfo> getNewBedInfoFromSpace();
}
