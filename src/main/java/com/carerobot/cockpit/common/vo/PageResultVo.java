package com.carerobot.cockpit.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页工具类
 */
@Data
@NoArgsConstructor
public class PageResultVo{

	//每页记录数
	private Long PageSize;
	//当前页数
	private Long PageIndex;
	//总记录数
	private Long RecordCount;
	//总页数
	private Long PageCount;
	//列表数据
	private List<?> list;


	/**
	 * 分页
	 * @param list        列表数据
	 * @param RecordCount  总记录数
	 * @param PageSize    每页记录数
	 * @param PageIndex    当前页数
	 */
	public PageResultVo(List<?> list, Long RecordCount, Long PageSize, Long PageIndex) {
		this.list = list;
		this.RecordCount = RecordCount;
		this.PageSize = PageSize;
		this.PageIndex = PageIndex;
		this.PageCount = (long) Math.ceil((double)RecordCount/PageSize);
	}

	public PageResultVo(IPage page){
		this.list = page.getRecords();
		this.RecordCount = page.getTotal();
		this.PageSize = page.getSize();
		this.PageIndex = page.getCurrent();
		this.PageCount = page.getPages();
	}

}

