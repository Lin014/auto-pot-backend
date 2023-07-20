package com.example.AutoPotBackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SwitchPayload {

    private String wateringMode;
    private String lightMode;
}
