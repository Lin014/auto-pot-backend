package com.example.AutoPotBackend.controller;

import com.example.AutoPotBackend.entity.Pot;
import com.example.AutoPotBackend.entity.User;
import com.example.AutoPotBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            String result = userService.register(user);
            System.out.println(result);
            if (result.equals("Successful")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            User result = userService.login(user);
            if (result == null) {
                System.out.println("404");
                User u = new User();
                return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);
            }
            System.out.println("200");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/clearUser")
    public ResponseEntity<String> clearUser(@RequestBody User user) {
        try {
            String result = userService.clearUser(user);
            if (result.equals("Successful")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody User user) {
        try {
            userService.changePassword(user);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/user/getPots")
//    public ResponseEntity<List<Pot>> getPots(@RequestBody User user) {
//        try {
//            User user1 = userService.getUser(user.getUser_id());
//            List<Pot> pots = new ArrayList<>();
//
//            if (!user1.getPots().isEmpty()) {
//                pots = user1.getPots();
//                System.out.println(pots.get(0).getPot_mf_name());
//                return new ResponseEntity<>(pots, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(pots, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
