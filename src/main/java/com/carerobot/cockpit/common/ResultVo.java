package com.carerobot.cockpit.common;

import lombok.Data;

/**
 * 返回数据
 */
@Data
public class ResultVo<T> {

	public Integer code;
	public String message;
	public T data;

	public static ResultVo fail() {
		ResultVo responseVo = new ResultVo();
		responseVo.code = 1;
		responseVo.message = "服务器错误！！！";
		return responseVo;
	}

	public static ResultVo fail(String msg) {
		ResultVo responseVo = new ResultVo();
		responseVo.code = 1;
		responseVo.message = msg;
		return responseVo;
	}

	public static <T> ResultVo<T> ok(T data) {
		ResultVo responseVo = new ResultVo();
		responseVo.code = 0;
		responseVo.data = data;
		return responseVo;
	}

	public static ResultVo ok() {
		ResultVo responseVo = new ResultVo();
		responseVo.code = 0;
		responseVo.message = "操作成功！";
		return responseVo;
	}

}
