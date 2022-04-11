package com.carerobot.cockpit.common.vo;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分页查询参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParamDto {

	private static final long serialVersionUID = 1L;
	//当前页码
    private int pageIndex;
    //每页条数
    private int pageSize;

    // 查询条件
    private String key;

    private String type;

    public <T> IPage<T> getPage(){

        return new Page<>(pageIndex, pageSize);
    }
}
