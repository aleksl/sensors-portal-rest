package pl.aleksl.sensorsportal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ToString
@Entity
@Getter
@Setter
@Table(name = "sensors_temp_hum_press")
public class SensorTempHumPress implements Serializable {

    @Column(name = "created_at")
    public Date createdAt;
    @Column
    private @NotNull
    int sensor_id;
    @Id
    @GeneratedValue
    private int id;
    @Column
    private int temp1;
    @Column
    private int temp2;
    @Column
    private int humidity;
    @Column
    private int pressure;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

