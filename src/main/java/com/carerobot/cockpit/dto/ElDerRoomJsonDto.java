package com.carerobot.cockpit.dto;

import lombok.Data;

import java.util.List;

@Data
public class ElDerRoomJsonDto {
    private Integer code;

    private String message;

    private List<RoomJsonDto> data;
}
