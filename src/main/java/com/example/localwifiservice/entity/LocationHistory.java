package com.example.localwifiservice.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString()
@AllArgsConstructor
public class LocationHistory {
    private Long id;
    private Double lat;
    private Double lnt;
    private LocalDateTime inquiry_at;


}
