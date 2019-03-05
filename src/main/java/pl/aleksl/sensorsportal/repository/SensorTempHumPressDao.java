package pl.aleksl.sensorsportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.aleksl.sensorsportal.model.SensorTempHumPress;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorTempHumPressDao extends CrudRepository<SensorTempHumPress, Integer> {

    @Query(value = "select * from sensors_temp_hum_press s where s.sensor_id = :sensorId and s.id = (select max(sd.id) from sensors_temp_hum_press sd where sd.sensor_id = :sensorId)", nativeQuery = true)
    Optional<SensorTempHumPress> findBySensorIdLastMeasurement(int sensorId);

    @Query(value = "select * from sensors_temp_hum_press s where s.sensor_id = :sensorId ORDER BY id DESC LIMIT :count", nativeQuery = true)
    Optional<List<SensorTempHumPress>> findBySensorIdLastMeasurement(int sensorId, int count);
}
