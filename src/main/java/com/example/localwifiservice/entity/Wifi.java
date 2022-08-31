package com.example.localwifiservice.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = {"id", "name", "management_no"})
@AllArgsConstructor
public class Wifi {

    private Long id;
    private String management_no;
    private String borough;
    private String name;
    private String road_name_address;
    private String detail_address;
    private String floor;
    private String install_type;
    private String install_institution;
    private String service_classification;
    private String network_type;
    private String installed_year;
    private String inout;
    private String connection_env;
    private Double lat;
    private Double lnt;
    private LocalDateTime work_datetime;


}
