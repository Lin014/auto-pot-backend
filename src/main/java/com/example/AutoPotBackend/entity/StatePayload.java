package com.example.AutoPotBackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StatePayload {

    private String wateringState;
    private String lightState;
}
