package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.ContractLive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-20
 */
public interface ContractLiveService extends IService<ContractLive> {

    int updateAndAddContractLive();

    List<ContractLive> getContractLive();

    List<ContractLive> different(List<Integer> ids);
}
