package com.example.demo.dto;

import lombok.Data;

@Data
public class ScheduleRequestDTO {

    private Long teacherId;
    private Long subjectId;
    private Long roomId;
    private Long groupId;

    private String dayOfWeek;
    private String timeSlot;
}