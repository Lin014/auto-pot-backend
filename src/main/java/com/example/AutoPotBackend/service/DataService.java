package com.example.AutoPotBackend.service;

import com.example.AutoPotBackend.dao.DataDao;
import com.example.AutoPotBackend.dao.PotDao;
import com.example.AutoPotBackend.entity.ArduinoData;
import com.example.AutoPotBackend.entity.Data;
import com.example.AutoPotBackend.entity.Pot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DataService {

    @Autowired
    DataDao dataDao;
    @Autowired
    PotDao potDao;

    String eT;

    final private int moistureThresholdValue = 800;
    final private int lightThresholdValue = 100;

    public void addTable() {
        dataDao.addTable();
    }

    public void addData(Data data) {
//        Pot pot = potDao.getPot(arduinoData.getPot_mf_name());
//        Data data = new Data(arduinoData.getData_soil_moisture(), arduinoData.getData_water_level(), arduinoData.getData_light_lux(), pot);
//        return dataDao.addData(data);
        dataDao.addData(data);
        System.out.println("DataService: addData done");
    }

    public Data getLastData(int pot_id) {
        return dataDao.getLastData(pot_id);
    }

    public boolean isDataNull(int pot_id) {
        int dataNum = dataDao.getDataNum(pot_id);
        return dataNum == 0;
    }

    public String checkWatering(int soilMoisture, int waterLevel) {
        boolean checkSoilMoisture = checkSoilMoisture(soilMoisture);
        boolean checkWaterLevel = checkWaterLevel(waterLevel);

        if (checkSoilMoisture && checkWaterLevel) {
            return "open";
        } else if (!checkSoilMoisture && checkWaterLevel) {
            return "close";
        } else {
            return "water is not enough";
        }
    }

    private boolean checkSoilMoisture(int soilMoisture) {
        if (soilMoisture <= moistureThresholdValue) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkWaterLevel(int waterLevel) {
        return waterLevel == 1;
    }

    public boolean checkLightLux(int lightLux) {
        return lightLux < lightThresholdValue;
    }

    public boolean checkTime(String start_time, String end_time) throws ParseException {
        if (Integer.parseInt(end_time.substring(0, 2)) < Integer.parseInt(start_time.substring(0, 2))) {
            int t  = Integer.parseInt(end_time.substring(0, 2)) + 24;
            eT  = t + end_time.substring(2, end_time.length());
            System.out.println("eT: " + eT);
        } else {
            eT = end_time;
        }

        String format = "HH:mm:ss";
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(date1);
        System.out.println("time: " + time);
        Date nowTime = new SimpleDateFormat(format).parse(time);
        Date startTime = new SimpleDateFormat(format).parse(start_time + ":00");
        Date endTime = new SimpleDateFormat(format).parse(eT + ":00");

//        System.out.println("date: " + date1);
        System.out.println("nowTime: " + nowTime);
        System.out.println("startTime: " + startTime);
        System.out.println("endTime: " + endTime);

        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            System.out.println("true");
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        System.out.println("nowTime: " + simpleDateFormat.format(nowTime.getTime()));

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);
        System.out.println("startTime: " + simpleDateFormat.format(begin.getTime()));

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        System.out.println("endTime: " + simpleDateFormat.format(end.getTime()));

        System.out.println("checkTime:"+ (date.after(begin) && date.before(end)));
        return date.after(begin) && date.before(end);
    }

}
