package pl.aleksl.sensorsportal.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ToString
@Entity
@Getter
@Setter
@Table(name = "sensors_dust")
public class SensorsDust implements Serializable {

    @Column
    private @NotNull int sensor_id;
    @Id @GeneratedValue
    private int id;
    @Column
    private int pm1;
    @Column
    private int pm25;
    @Column
    private int pm10;
    @Column(name = "created_at")
    public Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
