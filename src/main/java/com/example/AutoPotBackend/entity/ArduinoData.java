package com.example.AutoPotBackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ArduinoData {

    String pot_mf_name;
    private int data_soil_moisture;
    private int data_water_level;
    private int data_light_lux;
}
