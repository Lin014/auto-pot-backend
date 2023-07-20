package com.example.AutoPotBackend.controller;

import com.example.AutoPotBackend.dao.PlantDataDao;
import com.example.AutoPotBackend.entity.*;
import com.example.AutoPotBackend.service.DataService;
import com.example.AutoPotBackend.service.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class DataController {

    @Autowired
    DataService dataService;
    @Autowired
    PotService potService;

    @Autowired
    PlantDataDao plantDataDao;

    // (Arduino) 添加 data
    @PostMapping("/data/addData")
    public StatePayload addData(@RequestBody ArduinoData arduinoData) {
        try {
            System.out.println("arduinoWaterLevelState: " + arduinoData.getData_water_level());
            Pot pot = potService.getPot(arduinoData.getPot_mf_name());
            StatePayload statePayload = new StatePayload();
            Data data = new Data(arduinoData.getData_soil_moisture(), arduinoData.getData_water_level(), arduinoData.getData_light_lux(), pot);

            String wateringMode = pot.getWatering_mode();
            String lightMode = pot.getLight_mode();

            if (wateringMode.equals("custom")) {
                boolean isWateringOpenMode = dataService.checkTime(pot.getWatering_start_time(), pot.getWatering_end_time());
                if (isWateringOpenMode) {
                    System.out.println("..........");
                    String waterState = dataService.checkWatering(data.getData_soil_moisture(), data.getData_water_level());
                    if (waterState.equals("open")) {
                        data.setData_water_motor_state("open");
                        statePayload.setWateringState("open");
                    } else if (waterState.equals("close")) {
                        data.setData_water_motor_state("close");
                        statePayload.setWateringState("close");
                    } else {
                        data.setData_water_motor_state("close");
                        data.setData_water_level_state("notEnough");
                        statePayload.setWateringState("I need water");
                    }
                } else {
                    data.setData_water_motor_state("close");
                    statePayload.setWateringState("close");
                }
            } else {
                String waterState1 = dataService.checkWatering(data.getData_soil_moisture(), data.getData_water_level());
                if (waterState1.equals("open")) {
                    data.setData_water_motor_state("open");
                    statePayload.setWateringState("open");
                } else if (waterState1.equals("close")) {
                    data.setData_water_motor_state("close");
                    statePayload.setWateringState("close");
                } else {
                    data.setData_water_level_state("notEnough");
                    statePayload.setWateringState("I need water");
                }
            }

            if (lightMode.equals("auto")) {
                if (dataService.checkLightLux(arduinoData.getData_light_lux())) {
                    data.setData_light_state("open");
                    statePayload.setLightState("open");
                } else {
                    data.setData_light_state("close");
                    statePayload.setLightState("close");
                }
            } else if (lightMode.equals("custom") || pot.getLight_mode().equals("smart")) {
                System.out.println("custom or smart.....");
                boolean isLightOpenMode = dataService.checkTime(pot.getLight_start_time(), pot.getLight_end_time());
                if (isLightOpenMode) {
                    data.setData_light_state("open");
                    statePayload.setLightState("open");
                } else {
                    data.setData_light_state("close");
                    statePayload.setLightState("close");
                }
            } else {
                data.setData_light_state("close");
                statePayload.setLightState("close");
            }

            if (data.getData_water_level() == 0) {
                data.setData_water_level_state("notEnough");
            } else {
                data.setData_water_level_state("enough");
            }

            dataService.addData(data);
            System.out.println("waterMode: " + statePayload.getWateringState() + " LightMode: " + statePayload.getLightState());
            return statePayload;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/data/getLastDataByPotId/{id}")
    public ResponseEntity<Data> getLastDataByPotId(@PathVariable("id") int pot_id) {
        try {
            System.out.println("getLastData potId: " + pot_id);
            Data data = dataService.getLastData(pot_id);
            if (data == null) {
                System.out.println("......");
                Data data1 = new Data();
                return new ResponseEntity<>(data1, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/data/isDataNull/{id}")
    public ResponseEntity<String> checkIsDataNull(@PathVariable("id") int pot_id) {
        try {
            boolean result = dataService.isDataNull(pot_id);
            if (result) {
                return new ResponseEntity<>("true", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("false", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/data/getLastData")
    public ResponseEntity<Data> getLastDataByPotId() {
        try {
            Data data = dataService.getLastData(2);
            if (data == null) {
                Data data1 = new Data();
                return new ResponseEntity<>(data1, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/plantData/del")
    public void delPlantData(@RequestBody PlantData plantData) {
        plantDataDao.delPlantData(plantData.getPlantData_id());
    }

    @PostMapping("/plantData/add")
    public void addPlantData(@RequestBody PlantData plantData) {
        plantDataDao.addPlantData(plantData);
    }

    @PostMapping("/plantData/get")
    public ResponseEntity<PlantData> getPlantData(@RequestBody PlantData plantData) {
        try {
            PlantData plantData1 = plantDataDao.getPlantData(plantData.getPlant_type());
            return new ResponseEntity<>(plantData1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addDataTable")
    public void addDataTable() {
        dataService.addTable();
    }

}
