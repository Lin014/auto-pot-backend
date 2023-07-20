package com.example.AutoPotBackend.service;

import com.example.AutoPotBackend.dao.PotDao;
import com.example.AutoPotBackend.entity.*;
import com.example.AutoPotBackend.user_entity.uPot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PotService {

    @Autowired
    PotDao potDao;

    public void addPot(Pot pot) {
        Pot addPot = new Pot(pot.getPot_mf_name(), pot.getPot_name(), pot.getUser());
        potDao.addPot(addPot);
        System.out.println("PotService: addPot done");
    }

    public String delPot(int pot_id) {
        if (potDao.getPot(pot_id) != null) {
            potDao.delPot(pot_id);
            System.out.println("PotService: delPot done");
            return "Successful";
        } else {
            return "Failed";
        }
    }

    public Long getCountPot() {
        return potDao.countPot();
    }

    public Pot getPot(int pot_id) {
        return potDao.getPot(pot_id);
    }

    public Pot getPot(String mfPotName) {
        return potDao.getPot(mfPotName);
    }

    public Pot getPot(int user_id, String potName) {
        return potDao.getPot(user_id, potName);
    }

    public List<Pot> getPots(int user_id) {
        return potDao.getPots(user_id);
    }

    public void changePotName(int pot_id, String potName) {
        potDao.updatePotName(pot_id, potName);
        System.out.println("PotService: changePotName done");
    }

    public void changePlantName(int pot_id, String plantName) {
        potDao.updatePlantName(pot_id, plantName);
        System.out.println("PotService: changePlantName done");
    }

    public void changePlantImg(int pot_id, String plantImg) {
        potDao.updatePlantImg(pot_id, plantImg);
        System.out.println("PotService: changePlantImg done");
    }

    public void changePlantType(int pot_id, String plantType) {
        potDao.updatePlantType(pot_id, plantType);
        System.out.println("PotService: changePlantType done");
    }

    public void changeWatering(int pot_id, String wateringMode, String wateringStartTime, String wateringEndTime) {
        potDao.updateWatering(pot_id, wateringMode, wateringStartTime, wateringEndTime);
        System.out.println("PotService: changeWateringTime done");
    }

    public void changeLight(int pot_id, String lightMode, String lightStartTime, String lightEndTime) {
        potDao.updateLight(pot_id, lightMode, lightStartTime, lightEndTime);
        System.out.println("PotService: changeLightTime done");
    }

    public void changePlantNameState(int pot_id, int plantNameState) {
        potDao.updatePlantNameState(pot_id, plantNameState);
        System.out.println("PotService: changePlantNameState done");
    }

    public void changePlantImgState(int pot_id, int plantImgState) {
        potDao.updatePlantImgState(pot_id, plantImgState);
        System.out.println("PotService: changePlantImgState done");
    }
}
