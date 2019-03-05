package pl.aleksl.sensorsportal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Entity
@Getter
@Setter
@Table(name = "sensors")
public class Sensors {

    @Column
    public boolean enable;
    @Transient
    public List<SensorDust> sensorsDustList;
    @Transient
    public List<SensorTempHumPress> sensorTempHumPress;
    @Id
    private @NotNull int id;
    @Column
    private String name;
    @Column
    private String latitude;
    @Column
    private String longitude;
}
