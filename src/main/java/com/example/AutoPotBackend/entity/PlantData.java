package com.example.AutoPotBackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "plantData")
public class PlantData implements Serializable {

    @Id @GeneratedValue
    private int plantData_id;
    private String plant_type;
    private String light_start_time;
    private String light_end_time;

    public PlantData() {};
    public PlantData(String plant_type, String light_start_time, String light_end_time) {
        this.plant_type = plant_type;
        this.light_start_time = light_start_time;
        this.light_end_time = light_end_time;
    }
}
