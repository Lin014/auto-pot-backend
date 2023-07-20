package com.example.AutoPotBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "pot")
public class Pot implements Serializable {

    @Id @GeneratedValue
    private int pot_id;
    private int plant_name_change_state;
    private int plant_img_change_state;
    private String pot_mf_name;
    private String pot_name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

//    @OneToMany(mappedBy = "pot", cascade = CascadeType.ALL)
//    private List<Data> datas;

    private String pot_create_time;

    // plant
    private String plant_name;
    private String plant_img;
    private String plant_type;
    // watering
    private String watering_mode;
    private String watering_start_time;
    private String watering_end_time;
    // light
    private String light_mode;
    private String light_start_time;
    private String light_end_time;

    public Pot() {};
    public Pot(int pot_id, String pot_mf_name,User user){
        this.pot_id=pot_id;
        this.pot_mf_name=pot_mf_name;
        this.user = user;
    }
    public Pot(int pot_id, String pot_mf_name, String pot_name, User user, String pot_create_time, String plant_name, String plant_img, String plant_type, String watering_mode, String watering_start_time, String watering_end_time, String light_mode, String light_start_time, String light_end_time) {
        this.pot_id = pot_id;
        this.pot_mf_name = pot_mf_name;
        this.pot_name = pot_name;
        this.user = user;
        this.pot_create_time = pot_create_time;
        this.plant_name = plant_name;
        this.plant_img = plant_img;
        this.plant_type = plant_type;
        this.watering_mode = watering_mode;
        this.watering_start_time = watering_start_time;
        this.watering_end_time = watering_end_time;
        this.light_mode = light_mode;
        this.light_start_time = light_start_time;
        this.light_end_time = light_end_time;
    }

    public Pot(String mf_name, String name, User user) {
        // pot
        this.pot_mf_name = mf_name;
        this.plant_name_change_state = 0;
        this.plant_img_change_state = 0;
        this.pot_name = name;
        this.user = user;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.pot_create_time = simpleDateFormat.format(date);
        // plant
        this.plant_name = null;
        byte[] imgByte = ImageToByte("src/img/add_plant.png");
        this.plant_img = Arrays.toString(imgByte);
        this.plant_type = null;
        // watering
        this.watering_mode = "auto";
        this.watering_start_time = null;
        this.watering_end_time = null;
        // light
        this.light_mode = "auto";
        this.light_start_time = null;
        this.light_end_time = null;
    }

    private static byte[] ImageToByte(String srcImage) {
        try {
            File img = new File(srcImage);
            BufferedImage bufferedImage = ImageIO.read(img);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
