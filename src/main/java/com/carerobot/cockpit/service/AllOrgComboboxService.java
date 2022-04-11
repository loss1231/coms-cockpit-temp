package com.carerobot.cockpit.service;


import com.carerobot.cockpit.dto.AllOrgComboboxDto;
import com.carerobot.cockpit.dto.AllOrgComboboxReqDto;


import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xz
 * @since 2022/04/07
 */
public interface AllOrgComboboxService  {
    List<AllOrgComboboxDto> getOccupancyRate(List OrgId, Date startDate, Date endDate);

    AllOrgComboboxReqDto getOccupancyRates(List OrgId, Date startDate, Date endDate);
}
