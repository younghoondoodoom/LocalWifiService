package com.example.localwifiservice.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = {"lat", "lnt"})
@AllArgsConstructor
public class LocationHistory {
    private Long id;
    private Double lat;
    private Double lnt;
    private Timestamp inquiry_at;

}
