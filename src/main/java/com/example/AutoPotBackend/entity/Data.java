package com.example.AutoPotBackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "data")
public class Data implements Serializable {

    @Id @GeneratedValue
    private int data_id;

    private String data_time;

    private String data_water_motor_state = "close";
    private String data_water_level_state = "enough";
    private int data_soil_moisture;
    private int data_water_level;
    private String data_light_state = "close";
    private int data_light_lux;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pot_id", nullable = false, referencedColumnName = "pot_id")
    @JsonManagedReference
    private Pot pot;

    public Data() {};
    public Data(int data_soil_moisture, int data_water_level, int data_light_lux, Pot pot) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.data_time = simpleDateFormat.format(date);
        this.data_soil_moisture = data_soil_moisture;
        this.data_water_level = data_water_level;
        this.data_light_lux = data_light_lux;
        this.pot = pot;
    }

    public void setData_water_level_state(String data_water_level_state) {
        this.data_water_level_state = data_water_level_state;
    }

    public void setData_water_motor_state(String data_water_motor_state) {
        this.data_water_motor_state = data_water_motor_state;
    }

    public void setData_light_state(String data_light_state) {
        this.data_light_state = data_light_state;
    }

    public int getData_id() {
        return data_id;
    }

    public String getData_time() {
        return data_time;
    }

    public String getData_water_motor_state() {
        return data_water_motor_state;
    }

    public String getData_water_level_state() {
        return data_water_level_state;
    }

    public int getData_soil_moisture() {
        return data_soil_moisture;
    }

    public int getData_water_level() {
        return data_water_level;
    }

    public String getData_light_state() {
        return data_light_state;
    }

    public int getData_light_lux() {
        return data_light_lux;
    }

    public Pot getPot() {
        return pot;
    }
}
