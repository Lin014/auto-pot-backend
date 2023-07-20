package com.example.AutoPotBackend.controller;

import com.example.AutoPotBackend.entity.Pot;
import com.example.AutoPotBackend.entity.User;
import com.example.AutoPotBackend.service.PotService;
import com.example.AutoPotBackend.user_entity.uPot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PotController {

    @Autowired
    PotService potService;

    // pot
    @PostMapping("/pot/addPot")
    public ResponseEntity<Pot> addPot(@RequestBody Pot pot) {
        try {
            potService.addPot(pot);
            Pot nPot = potService.getPot(pot.getUser().getUser_id(), pot.getPot_name());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/delPot")
    public ResponseEntity<String> delPot(@RequestBody Pot pot) {
        try {
            String result = potService.delPot(pot.getPot_id());
            if (result.equals("Successful")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pot/getCountPot")
    public ResponseEntity<String> getCountPot() {
        try {
            Long result = potService.getCountPot();
            return new ResponseEntity<>(result.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pot/getPot/{id}")
    public ResponseEntity<Pot> getPotById(@PathVariable("id") int pot_id) {
        try {
            Pot pot1 = potService.getPot(pot_id);
            return new ResponseEntity<>(pot1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pot/getPots/{id}")
    public ResponseEntity<List<Pot>> getPotsByUserId(@PathVariable("id") int user_id) {
        try {
            System.out.println("getPots....");
            List<Pot> pots = potService.getPots(user_id);
            System.out.println(pots);
            if (pots == null) {
                List<Pot> nullPots = new ArrayList<>();
                return new ResponseEntity<>(nullPots, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(pots, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePotName")
    public  ResponseEntity<Pot> changePotName(@RequestBody Pot pot) {
        try {
            potService.changePotName(pot.getPot_id(), pot.getPot_name());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePlantName")
    public  ResponseEntity<Pot> changePlantName(@RequestBody Pot pot) {
        try {
            potService.changePlantName(pot.getPot_id(), pot.getPlant_name());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePlantImg")
    public  ResponseEntity<Pot> changePlantImg(@RequestBody Pot pot) {
        try {
            potService.changePlantImg(pot.getPot_id(), pot.getPlant_img());
            Pot nPot = potService.getPot(pot.getPot_id());
            System.out.println(".............");
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePlantType")
    public  ResponseEntity<Pot> changePlantType(@RequestBody Pot pot) {
        try {
            potService.changePlantType(pot.getPot_id(), pot.getPlant_type());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changeWatering")
    public  ResponseEntity<Pot> changeWatering(@RequestBody Pot pot) {
        try {
            potService.changeWatering(pot.getPot_id(), pot.getWatering_mode(), pot.getWatering_start_time(), pot.getWatering_end_time());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changeLight")
    public  ResponseEntity<Pot> changeLight(@RequestBody Pot pot) {
        try {
            potService.changeLight(pot.getPot_id(), pot.getLight_mode(), pot.getLight_start_time(), pot.getLight_end_time());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePlantNameState")
    public  ResponseEntity<Pot> changePlantNameState(@RequestBody Pot pot) {
        try {
            potService.changePlantNameState(pot.getPot_id(), pot.getPlant_name_change_state());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pot/changePlantImgState")
    public  ResponseEntity<Pot> changePlantImgState(@RequestBody Pot pot) {
        try {
            potService.changePlantImgState(pot.getPot_id(), pot.getPlant_img_change_state());
            Pot nPot = potService.getPot(pot.getPot_id());
            return new ResponseEntity<>(nPot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
