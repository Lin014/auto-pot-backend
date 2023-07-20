package com.example.AutoPotBackend.user_entity;

import com.example.AutoPotBackend.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Blob;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class uPot {

    private int pot_id;
    private String pot_mf_name;
    private String pot_name;
    private Date pot_create_time;

    // plant
    private String plant_name;
    private String plant_img;
    private String plant_type;
    // watering
    private String watering_mode;
    private Calendar watering_start_time;
    private Calendar watering_end_time;
    // light
    private String light_mode;
    private Calendar light_start_time;
    private Calendar light_end_time;
}
