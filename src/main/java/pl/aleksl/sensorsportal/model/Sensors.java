package pl.aleksl.sensorsportal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ToString
@Entity
@Getter
@Setter
@Table(name = "sensors")
public class Sensors {

    @Id
    private @NotNull int id;
    @Column
    private String name;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column
    public boolean enable;
}
