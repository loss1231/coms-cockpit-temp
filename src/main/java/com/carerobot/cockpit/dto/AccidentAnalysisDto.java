package com.carerobot.cockpit.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Data
public class AccidentAnalysisDto {

    private List<Integer> directCause;

    private String directCauseDsc;

    private List<Integer> indirectCause;

    private String indirectCauseDsc;

    private List<Integer> reasonsOur;

    private List<Integer> reasonsArchives;

    private List<Integer> reasonsOther;

    private String reasonsOurDsc;

    private String reasonsArchivesDsc;

    private String reasonsOtherDsc;

}
